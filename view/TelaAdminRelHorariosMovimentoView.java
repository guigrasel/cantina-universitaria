package view;

import model.HistoricoTransacoes;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Tela de relatório de horários de maior movimento na cantina.
 * 
 * <p>
 * Exibe, em uma tabela, a quantidade de vendas agrupadas por hora do dia, ordenando do horário mais movimentado para o menos movimentado.
 * Permite recarregar os dados em tempo real.
 * </p>
 * 
 * <ul>
 *   <li>Analisa todas as transações do histórico e contabiliza o número de vendas por hora.</li>
 *   <li>Exibe a informação ordenada do maior para o menor movimento.</li>
 *   <li>Possui botão para recarregar os dados.</li>
 * </ul>
 */

public class TelaAdminRelHorariosMovimentoView extends JFrame {
  /** Referência ao histórico de transações. */
  private HistoricoTransacoes historico;

  /** Modelo da tabela de horários e vendas. */
  private DefaultTableModel tableModel;

  /** Tabela que exibe os dados de movimento por horário. */
  private JTable table;

  /**
   * Construtor da tela de relatório de horários de maior movimento.
   *
   * @param historico o histórico de transações a ser analisado
   */
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

  /**
   * Atualiza a tabela com os dados mais recentes do histórico,
   * mostrando as horas do dia com o maior número de vendas.
   */
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
