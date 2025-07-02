package view;

import model.HistoricoTransacoes;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TelaAdminRelTransacoesRealizadasView extends JFrame {
  private HistoricoTransacoes historico;
  private DefaultTableModel tableModel;
  private JTable table;

  public TelaAdminRelTransacoesRealizadasView(HistoricoTransacoes historico) {
    this.historico = historico;
    setTitle("Relatório de Transações Realizadas");
    setSize(700, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    String[] colunas = {"ID", "Data/Hora", "Qtd. Produtos", "Total (R$)"};
    tableModel = new DefaultTableModel(colunas, 0) 
    {
      @Override
      public boolean isCellEditable(int row, int column) {
          return false;
      }
    };

    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    add(scrollPane, BorderLayout.CENTER);

    atualizarTabela();
  }

  private void atualizarTabela() 
  {
    tableModel.setRowCount(0);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    for (Transacao t : historico.getTransacoes()) {
      tableModel.addRow(new Object[]{
        t.getId(),
        t.getDataHora().format(formatter),
        t.getProdutos().size(),
        String.format("%.2f", t.getValorTotal())
      });
    }
  }
}
