package view;

import javax.swing.*;

import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;

import java.awt.*;

/**
 * Tela principal da área administrativa da cantina universitária.
 *
 * <p>Permite ao administrador acessar a gestão de estoque, caixa e relatórios do sistema.</p>
 * 
 * <ul>
 *   <li>Abre janelas para gerenciamento do estoque, caixa e relatórios administrativos.</li>
 *   <li>Recebe as instâncias de Estoque, HistoricoTransacoes e Caixa para repasse às telas filhas.</li>
 * </ul>
 */

public class TelaAdminPrincipalView extends JFrame {
  /**
   * Referência ao estoque do sistema.
   */
  private Estoque estoque;

  /**
   * Referência ao histórico de transações realizadas.
   */
  private HistoricoTransacoes historicoTransacoes;

  /**
   * Referência ao caixa do sistema.
   */
  private Caixa caixa;

  /**
   * Construtor da tela administrativa principal.
   *
   * @param estoque             o estoque a ser gerenciado
   * @param historicoTransacoes o histórico de transações realizadas
   * @param caixa               o caixa da cantina
   */
  public TelaAdminPrincipalView(Estoque estoque, HistoricoTransacoes historicoTransacoes, Caixa caixa) 
  {
    this.estoque = estoque;
    this.historicoTransacoes = historicoTransacoes;
    this.caixa = caixa;
    setTitle("Área Administrativa - Cantina");
    setSize(500, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBackground(new Color(245, 245, 250));

    JLabel lblTitulo = new JLabel("Área administrativa", JLabel.CENTER);
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(lblTitulo, BorderLayout.NORTH);

    JPanel botoesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JButton btnEstoque = new JButton("Gerenciar estoque");
    JButton btnCaixa = new JButton("Gerenciar caixa");
    JButton btnRelatorios = new JButton("Relatórios");

    botoesPanel.add(btnEstoque);
    botoesPanel.add(btnCaixa);
    botoesPanel.add(btnRelatorios);

    panel.add(botoesPanel, BorderLayout.CENTER);

    btnEstoque.addActionListener(e -> new TelaEstoqueView(this.estoque).setVisible(true));
    btnCaixa.addActionListener(e -> new TelaAdminRelCaixaView(this.caixa).setVisible(true));
    btnRelatorios.addActionListener(e -> new TelaAdminRelatoriosView(this.estoque, this.historicoTransacoes).setVisible(true));

    setContentPane(panel);
    setResizable(false);
  }
}
