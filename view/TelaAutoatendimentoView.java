package view;

import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;
import model.Produto;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaAutoatendimentoView extends JFrame {

  private Estoque estoque;
  private Caixa caixa;
  private HistoricoTransacoes historicoTransacoes;
  private DefaultTableModel tableModel;
  private JTable table;
  private DefaultTableModel carrinhoModel;
  private JTable carrinhoTable;
  private ArrayList<Produto> carrinho = new ArrayList<>();
  private JLabel lblTotal;

  public TelaAutoatendimentoView(Estoque estoque, HistoricoTransacoes historicoTransacoes, Caixa caixa) {
    this.estoque = estoque;
    this.historicoTransacoes = historicoTransacoes;
    this.caixa = caixa;
    setTitle("Autoatendimento - Cantina Universitária");
    setSize(1200, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    String[] colunas = {"ID", "Nome", "Categoria", "Preço", "Estoque"};
    tableModel = new DefaultTableModel(colunas, 0) 
    {
      @Override
      public boolean isCellEditable(int row, int column) 
      {
        return false;
      }
    };
    table = new JTable(tableModel);
    JScrollPane scrollProdutos = new JScrollPane(table);

    String[] carrinhoColunas = {"Nome", "Categoria", "Preço"};
    carrinhoModel = new DefaultTableModel(carrinhoColunas, 0) 
    {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    carrinhoTable = new JTable(carrinhoModel);
    JScrollPane scrollCarrinho = new JScrollPane(carrinhoTable);

    JPanel painelEstoque = new JPanel(new BorderLayout());
    painelEstoque.setBorder(BorderFactory.createTitledBorder("Estoque"));
    painelEstoque.add(scrollProdutos, BorderLayout.CENTER);

    JPanel painelCarrinho = new JPanel(new BorderLayout());
    painelCarrinho.setBorder(BorderFactory.createTitledBorder("Carrinho"));
    painelCarrinho.add(scrollCarrinho, BorderLayout.CENTER);

    JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
    JButton btnRemover = new JButton("Remover do Carrinho");
    JButton btnFinalizar = new JButton("Finalizar Compra");
    JButton btnCancelar = new JButton("Cancelar Tudo");

    lblTotal = new JLabel("Total: R$ 0,00");

    JPanel painel = new JPanel(new GridLayout(1, 2, 10, 10));
    painel.add(painelEstoque);
    painel.add(painelCarrinho);

    JPanel painelSul = new JPanel(new FlowLayout());
    painelSul.add(btnAdicionar);
    painelSul.add(btnRemover);
    painelSul.add(btnFinalizar);
    painelSul.add(btnCancelar);
    painelSul.add(lblTotal);

    add(painel, BorderLayout.CENTER);
    add(painelSul, BorderLayout.SOUTH);

    atualizarTabelaProdutos();

    btnAdicionar.addActionListener(e -> adicionarAoCarrinho());
    btnRemover.addActionListener(e -> removerDoCarrinho());
    btnFinalizar.addActionListener(e -> finalizarCompra());
    btnCancelar.addActionListener(e -> cancelarCompra());
  }

  private void atualizarTabelaProdutos() {
    tableModel.setRowCount(0);
    for (Produto p : estoque.getProdutos()) 
    {
      if (p.getQuantidadeEstoque() > 0) 
      {
        tableModel.addRow(new Object[]{
          p.getId(),
          p.getNome(),
          p.getCategoria(),
          String.format("R$ %.2f", p.getPreco()),
          p.getQuantidadeEstoque()
        });
      }
    }
  }

  private void atualizarTabelaCarrinho() 
  {
    carrinhoModel.setRowCount(0);
    for (Produto p : carrinho) 
    {
      carrinhoModel.addRow(new Object[]{
        p.getNome(),
        p.getCategoria(),
        String.format("R$ %.2f", p.getPreco())
      });
    }
    atualizarTotal();
  }

  private void atualizarTotal() 
  {
    double total = 0;
    for (Produto p : carrinho) 
    {
      total += p.getPreco();
    }
    lblTotal.setText(String.format("Total: R$ %.2f", total));
  }

  private void adicionarAoCarrinho() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) 
    {
      JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar ao carrinho.");
      return;
    }
    int produtoId = (int) tableModel.getValueAt(selectedRow, 0);
    Produto produto = estoque.buscarProdutoPorId(produtoId);

    if (produto != null && produto.getQuantidadeEstoque() > 0) 
    {
      carrinho.add(produto);
      produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - 1);
      atualizarTabelaProdutos();
      atualizarTabelaCarrinho();
    } else {
      JOptionPane.showMessageDialog(this, "Produto esgotado!");
    }
  }

  private void removerDoCarrinho() {
    int selectedRow = carrinhoTable.getSelectedRow();
    if (selectedRow == -1) 
    {
      JOptionPane.showMessageDialog(this, "Selecione um produto no carrinho para remover.");
      return;
    }
    Produto produto = carrinho.get(selectedRow);
    produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + 1);
    carrinho.remove(selectedRow);
    atualizarTabelaProdutos();
    atualizarTabelaCarrinho();
  }

  private void finalizarCompra() {
    if (carrinho.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Seu carrinho está vazio!");
      return;
    }
    double total = 0;
    for (Produto p : carrinho) total += p.getPreco();

    String valorPagoStr = JOptionPane.showInputDialog(this, String.format("Valor total: R$ %.2f\nInsira o valor pago:", total));

    if (valorPagoStr == null) return;

    try {
      double valorPago = Double.parseDouble(valorPagoStr.replace(",", "."));
      if (valorPago < total) throw new Exception("Valor insuficiente.");

      double troco = valorPago - total;
      Map<Double, Integer> trocoEntregar = caixa.calcularTroco(troco);

      Map<Double, Integer> dinheiroRecebido = converterValorParaNotas(valorPago);
      caixa.adicionarDinheiro(dinheiroRecebido);
      caixa.removerDinheiro(trocoEntregar);

      ArrayList<Produto> produtosDaCompra = new ArrayList<>(carrinho);
      Transacao transacao = new Transacao(produtosDaCompra, total);
      historicoTransacoes.adicionarTransacao(transacao);

      StringBuilder trocoMsg = new StringBuilder("Compra finalizada!\nTroco entregue: R$ " + String.format("%.2f", troco) + "\n");
      for (Map.Entry<Double, Integer> entry : trocoEntregar.entrySet()) 
      {
          trocoMsg.append(String.format("%dx R$ %.2f\n", entry.getValue(), entry.getKey()));
      }
      JOptionPane.showMessageDialog(this, trocoMsg);

      carrinho.clear();
      atualizarTabelaProdutos();
      atualizarTabelaCarrinho();
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void cancelarCompra() 
  {
    for (Produto p : carrinho) 
    {
      p.setQuantidadeEstoque(p.getQuantidadeEstoque() + 1);
    }
    carrinho.clear();
    atualizarTabelaProdutos();
    atualizarTabelaCarrinho();
  }

  private Map<Double, Integer> converterValorParaNotas(double valor) {
    Map<Double, Integer> mapa = new HashMap<>();
    double[] valores = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.25, 0.10, 0.05};
    double restante = Math.round(valor * 100.0) / 100.0;
    for (double v : valores) 
    {
      int qtd = (int)(restante / v);
      if (qtd > 0) 
      {
        mapa.put(v, qtd);
        restante -= v * qtd;
        restante = Math.round(restante * 100.0) / 100.0;
      }
    }
    return mapa;
  }
}
