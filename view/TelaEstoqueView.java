package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.Estoque;
import model.Produto;
import java.awt.*;

public class TelaEstoqueView extends JFrame {

  private Estoque estoque;
  private DefaultTableModel tableModel;
  private JTable table;

  public TelaEstoqueView(Estoque estoque) {
    this.estoque = estoque;
    setTitle("Gerenciar Estoque");
    setSize(650, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Modelo da tabela
    String[] colunas = {"ID", "Nome", "Quantidade", "Validade"};
    tableModel = new DefaultTableModel(colunas, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);

    JScrollPane scrollPane = new JScrollPane(table);

    // Botões
    JButton btnAdicionar = new JButton("Adicionar Produto");
    JButton btnAbastecer = new JButton("Abastecer Produto");
    JButton btnRemover = new JButton("Remover Produto");

    JPanel panelBotoes = new JPanel();
    panelBotoes.add(btnAdicionar);
    panelBotoes.add(btnAbastecer);
    panelBotoes.add(btnRemover);

    add(scrollPane, BorderLayout.CENTER);
    add(panelBotoes, BorderLayout.SOUTH);

    atualizarTabela();

    // Ações dos botões
    btnAdicionar.addActionListener(e -> adicionarProduto());
    btnAbastecer.addActionListener(e -> abastecerProduto());
    btnRemover.addActionListener(e -> removerProduto());
  }

  private void atualizarTabela() {
    tableModel.setRowCount(0);
    for (Produto p : estoque.getProdutos()) {
      tableModel.addRow(new Object[]{
        p.getId(),
        p.getNome(),
        p.getQuantidadeEstoque(),
        p.getValidade()
      });
    }
  }

  private void adicionarProduto() {
    JTextField txtNome = new JTextField();
    JTextField txtCategoria = new JTextField();
    JTextField txtPreco = new JTextField();
    JTextField txtQuantidade = new JTextField();
    JTextField txtValidade = new JTextField();

    JPanel panel = new JPanel(new GridLayout(0, 1));
    panel.add(new JLabel("Nome:"));
    panel.add(txtNome);
    panel.add(new JLabel("Categoria:"));
    panel.add(txtCategoria);
    panel.add(new JLabel("Preço:"));
    panel.add(txtPreco);
    panel.add(new JLabel("Quantidade em Estoque:"));
    panel.add(txtQuantidade);
    panel.add(new JLabel("Validade (dd/MM/yyyy):"));
    panel.add(txtValidade);

    int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Produto", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        String nome = txtNome.getText();
        String categoria = txtCategoria.getText();
        double preco = Double.parseDouble(txtPreco.getText());
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        String validade = txtValidade.getText();

        Produto novoProduto = new Produto(nome, categoria, preco, quantidade, validade);
        estoque.adicionarProduto(novoProduto);
        atualizarTabela();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Dados inválidos! Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void abastecerProduto() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Selecione um produto para abastecer.");
      return;
    }
    int produtoId = (int) tableModel.getValueAt(selectedRow, 0);
    Produto produto = estoque.buscarProdutoPorId(produtoId);
    if (produto == null) return;

    String input = JOptionPane.showInputDialog(this,
      "Quantidade a abastecer para '" + produto.getNome() + "':", "0");
    if (input != null) {
      try {
        int qtdAbastecer = Integer.parseInt(input);
        if (qtdAbastecer < 1) throw new NumberFormatException();
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + qtdAbastecer);
        atualizarTabela();
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void removerProduto() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
      return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o produto?",
      "Remover Produto", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
      int produtoId = (int) tableModel.getValueAt(selectedRow, 0);
      Produto produto = estoque.buscarProdutoPorId(produtoId);
      if (produto != null) {
        estoque.removerProduto(produto);
        atualizarTabela();
      }
    }
  }
}
