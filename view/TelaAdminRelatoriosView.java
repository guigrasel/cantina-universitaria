package view;

import javax.swing.*;

import controller.Estoque;
import model.HistoricoTransacoes;

import java.awt.*;

/**
 * Tela da área administrativa para acesso aos relatórios da cantina.
 *
 * <p>
 * Permite ao administrador visualizar relatórios de transações realizadas,
 * itens mais pedidos e horários de maior movimento.
 * </p>
 * 
 * <ul>
 *   <li>Abre janelas específicas de relatórios conforme o botão selecionado.</li>
 *   <li>Recebe o {@link Estoque} e o {@link HistoricoTransacoes} para repasse às telas filhas.</li>
 * </ul>
 */
public class TelaAdminRelatoriosView extends JFrame {
  /**
   * Referência ao histórico de transações realizadas.
   */
  private HistoricoTransacoes historicoTransacoes;

  /**
   * Construtor da tela de relatórios administrativos.
   *
   * @param estoque             o estoque da cantina (caso seja necessário para relatórios futuros)
   * @param historicoTransacoes o histórico de transações realizadas
   */
  public TelaAdminRelatoriosView(Estoque estoque, HistoricoTransacoes historicoTransacoes) 
  {
    this.historicoTransacoes = historicoTransacoes;
    setTitle("Área Administrativa - Relatórios");
    setSize(500, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBackground(new Color(245, 245, 250));

    JLabel lblTitulo = new JLabel("Área Administrativo - Relatórios", JLabel.CENTER);
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(lblTitulo, BorderLayout.NORTH);

    JPanel botoesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
    botoesPanel.setBackground(new Color(245, 245, 250));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JButton btnTransacoesRealizadas = new JButton("Transações Realizadas");
    JButton btnItensMaisPedidos = new JButton("Itens mais pedidos");
    JButton btnHorariosMovimento = new JButton("Horários de maior movimento");

    botoesPanel.add(btnTransacoesRealizadas);
    botoesPanel.add(btnItensMaisPedidos);
    botoesPanel.add(btnHorariosMovimento);

    panel.add(botoesPanel, BorderLayout.CENTER);

    btnTransacoesRealizadas.addActionListener(e -> new TelaAdminRelTransacoesRealizadasView(this.historicoTransacoes).setVisible(true));
    btnItensMaisPedidos.addActionListener(e -> new TelaAdminRelMaisVendidosView(this.historicoTransacoes).setVisible(true));
    btnHorariosMovimento.addActionListener(e -> new TelaAdminRelHorariosMovimentoView(this.historicoTransacoes).setVisible(true));

    setContentPane(panel);
    setResizable(false);
  }
}
