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

    String[] colunas = {"ID", "Categoria", "Nome", "Quantidade", "Validade"};
    tableModel = new DefaultTableModel(colunas, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);

    JScrollPane scrollPane = new JScrollPane(table);

    JButton btnAdicionar = new JButton("Adicionar Produto");
    JButton btnAbastecer = new JButton("Abastecer Produto");
    JButton btnRemover = new JButton("Remover Produto");
    JButton btnAtualizar = new JButton("Recarregar");

    JPanel panelBotoes = new JPanel();
    panelBotoes.add(btnAdicionar);
    panelBotoes.add(btnAbastecer);
    panelBotoes.add(btnRemover);
    panelBotoes.add(btnAtualizar);

    add(scrollPane, BorderLayout.CENTER);
    add(panelBotoes, BorderLayout.SOUTH);

    atualizarTabela();

    btnAdicionar.addActionListener(e -> adicionarProduto());
    btnAbastecer.addActionListener(e -> abastecerProduto());
    btnRemover.addActionListener(e -> removerProduto());
    btnAtualizar.addActionListener(e -> atualizarTabela());

    setResizable(false);
  }

  private void atualizarTabela() {
    tableModel.setRowCount(0);
    for (Produto p : estoque.getProdutos()) {
      tableModel.addRow(new Object[]{
        p.getId(),
        p.getCategoria(),
        p.getNome(),
        p.getQuantidadeEstoque(),
        p.getValidade()
      });
    }
  }

  private void adicionarProduto() 
  {
      JTextField txtNome = new JTextField();
      JComboBox<String> comboCategoria = new JComboBox<>(new String[]{"Lanche", "Doce", "Bebida"});
      JTextField txtPreco = new JTextField();
      JTextField txtQuantidade = new JTextField();
      JTextField txtValidade = new JTextField();

      JPanel panel = new JPanel(new GridLayout(0, 1));
      panel.add(new JLabel("Nome:"));
      panel.add(txtNome);
      panel.add(new JLabel("Categoria:"));
      panel.add(comboCategoria);
      panel.add(new JLabel("Preço:"));
      panel.add(txtPreco);
      panel.add(new JLabel("Quantidade em Estoque:"));
      panel.add(txtQuantidade);
      panel.add(new JLabel("Validade (dd/MM/yyyy):"));
      panel.add(txtValidade);

      // Campos extras
      JTextField txtAtributoExtra = new JTextField();
      JCheckBox chkExtra = new JCheckBox();

      // Atualiza o painel quando muda a categoria
      comboCategoria.addActionListener(e -> {
          panel.remove(txtAtributoExtra);
          panel.remove(chkExtra);

          String categoria = (String) comboCategoria.getSelectedItem();
          if (categoria.equals("Lanche")) {
              chkExtra.setText("É vegetariano?");
              panel.add(chkExtra);
          } else if (categoria.equals("Doce")) {
              panel.add(new JLabel("Tipo de Açúcar:"));
              panel.add(txtAtributoExtra);
          } else if (categoria.equals("Bebida")) {
              chkExtra.setText("É alcoólica?");
              panel.add(chkExtra);
          }
          panel.revalidate();
          panel.repaint();
      });
      // Chama para inicializar com o campo certo
      comboCategoria.setSelectedIndex(0);

      int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar Produto", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
          try {
              String nome = txtNome.getText();
              String categoria = (String) comboCategoria.getSelectedItem();
              double preco = Double.parseDouble(txtPreco.getText());
              int quantidade = Integer.parseInt(txtQuantidade.getText());
              String validade = txtValidade.getText();

              Produto novoProduto;
              switch (categoria) {
                  case "Lanche":
                      boolean vegetariano = chkExtra.isSelected();
                      novoProduto = new model.Lanche(nome, categoria, preco, quantidade, validade, vegetariano);
                      break;
                  case "Doce":
                      String tipoAcucar = txtAtributoExtra.getText();
                      novoProduto = new model.Doce(nome, categoria, preco, quantidade, validade, tipoAcucar);
                      break;
                  case "Bebida":
                      boolean alcoolica = chkExtra.isSelected();
                      novoProduto = new model.Bebida(nome, categoria, preco, quantidade, validade, alcoolica);
                      break;
                  default:
                      throw new IllegalArgumentException("Categoria inválida");
              }

              estoque.adicionarProduto(novoProduto);
              atualizarTabela();
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
          }
      }
  }


  private void abastecerProduto() 
  {
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
        JOptionPane.showMessageDialog(this, "Quantidade inválida, valor deve partir de 1!", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void removerProduto() 
  {
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
