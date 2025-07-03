package view;

import model.HistoricoTransacoes;
import model.Produto;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaAdminRelMaisVendidosView extends JFrame {
  private HistoricoTransacoes historico;
  private DefaultTableModel tableModel;
  private JTable table;

  public TelaAdminRelMaisVendidosView(HistoricoTransacoes historico) {
    this.historico = historico;
    setTitle("Relatório de itens mais pedidos");
    setSize(600, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    String[] colunas = {"Produto", "Categoria", "Quantidade Vendida"};
    tableModel = new DefaultTableModel(colunas, 0) {
      @Override
      public boolean isCellEditable(int row, int column) { return false; }
    };
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);
    JPanel panelBottom = new JPanel(new BorderLayout());
    JButton btnRecarregar = new JButton("Recarregar");
    panelBottom.add(btnRecarregar, BorderLayout.CENTER);
    btnRecarregar.addActionListener(e -> atualizarTabela());

    add(scrollPane, BorderLayout.CENTER);
    add(panelBottom, BorderLayout.SOUTH);

    atualizarTabela();
    setResizable(false);
  }

  private void atualizarTabela() 
  {
    tableModel.setRowCount(0);

    ArrayList<Produto> produtosUnicos = new ArrayList<>();
    ArrayList<Integer> quantidades = new ArrayList<>();

    for (Transacao t : historico.getTransacoes()) 
    {
      for (Produto p : t.getProdutos()) 
      {
        int idx = -1;
        for (int i = 0; i < produtosUnicos.size(); i++) 
        {
          Produto prod = produtosUnicos.get(i);
          if (prod.getNome().equals(p.getNome()) &&
            prod.getCategoria().equals(p.getCategoria())) {
            idx = i;
            break;
          }
        }
        if (idx != -1) {
          quantidades.set(idx, quantidades.get(idx) + 1);
        } else {
          produtosUnicos.add(p);
          quantidades.add(1);
        }
      }
    }

    for (int i = 0; i < quantidades.size() - 1; i++) {
      for (int j = 0; j < quantidades.size() - i - 1; j++) {
        if (quantidades.get(j) < quantidades.get(j + 1)) {
          int tempQ = quantidades.get(j);
          quantidades.set(j, quantidades.get(j + 1));
          quantidades.set(j + 1, tempQ);

          Produto tempP = produtosUnicos.get(j);
          produtosUnicos.set(j, produtosUnicos.get(j + 1));
          produtosUnicos.set(j + 1, tempP);
        }
      }
    }

    for (int i = 0; i < produtosUnicos.size(); i++) {
      Produto p = produtosUnicos.get(i);
      tableModel.addRow(new Object[]{
        p.getNome(),
        p.getCategoria(),
        quantidades.get(i)
      });
    }
  }
}
