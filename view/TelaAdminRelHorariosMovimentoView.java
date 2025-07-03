package view;

import model.HistoricoTransacoes;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TelaAdminRelHorariosMovimentoView extends JFrame {
  private HistoricoTransacoes historico;
  private DefaultTableModel tableModel;
  private JTable table;

  public TelaAdminRelHorariosMovimentoView(HistoricoTransacoes historico) {
    this.historico = historico;
    setTitle("Relatório de horários de maior movimento");
    setSize(400, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    String[] colunas = {"Hora", "Quantidade de Vendas"};
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
  }

  private void atualizarTabela() 
  {
    tableModel.setRowCount(0);

    int[] vendasPorHora = new int[24];

    ArrayList<Transacao> transacoes = historico.getTransacoes();
    for (Transacao transacao : transacoes) 
    {
      LocalDateTime dataHora = transacao.getDataHora();
      int hora = dataHora.getHour();
      vendasPorHora[hora]++;
    }

    int[] horas = new int[24];
    for (int i = 0; i < 24; i++) horas[i] = i;

    for (int i = 0; i < 23; i++) 
    {
      for (int j = 0; j < 23 - i; j++) 
      {
        if (vendasPorHora[horas[j]] < vendasPorHora[horas[j + 1]]) 
        {
          int temp = horas[j];
          horas[j] = horas[j + 1];
          horas[j + 1] = temp;
        }
      }
    }

    for (int i = 0; i < 24; i++) 
    {
      int h = horas[i];
      if (vendasPorHora[h] > 0) 
      {
        String horaFormatada = String.format("%02d:00", h);
        tableModel.addRow(new Object[]{horaFormatada, vendasPorHora[h]});
      }
    }
  }
}
