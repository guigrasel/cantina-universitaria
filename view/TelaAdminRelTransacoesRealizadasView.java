package view;

import model.HistoricoTransacoes;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * Tela de relatório das transações realizadas na cantina.
 * 
 * <p>
 * Exibe, em uma tabela, todas as transações já realizadas,
 * mostrando o ID, data/hora e o valor total de cada uma.
 * Permite recarregar o relatório em tempo real.
 * </p>
 *
 * <ul>
 *   <li>Lê todas as transações do histórico e as exibe na interface administrativa.</li>
 *   <li>Oferece botão para atualizar os dados da tabela.</li>
 * </ul>
 */

public class TelaAdminRelTransacoesRealizadasView extends JFrame {
  /** Referência ao histórico de transações. */
  private HistoricoTransacoes historico;

  /** Modelo da tabela de transações. */
  private DefaultTableModel tableModel;

  /** Tabela de exibição das transações. */
  private JTable table;

  /**
   * Construtor da tela de relatório de transações realizadas.
   *
   * @param historico o histórico de transações a ser exibido
   */
  public TelaAdminRelTransacoesRealizadasView(HistoricoTransacoes historico) {
    this.historico = historico;
    setTitle("Relatório de Transações Realizadas");
    setSize(700, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    String[] colunas = {"ID", "Data/Hora", "Total (R$)"};
    tableModel = new DefaultTableModel(colunas, 0) 
    {
      @Override
      public boolean isCellEditable(int row, int column) {
          return false;
      }
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

  /**
   * Atualiza a tabela exibindo todas as transações realizadas.
   * Mostra o ID, data/hora formatada e valor total de cada transação.
   */
  private void atualizarTabela() 
  {
    tableModel.setRowCount(0);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    for (Transacao t : historico.getTransacoes()) {
      tableModel.addRow(new Object[]{
        t.getId(),
        t.getDataHora().format(formatter),
        String.format("%.2f", t.getValorTotal())
      });
    }
  }
}
