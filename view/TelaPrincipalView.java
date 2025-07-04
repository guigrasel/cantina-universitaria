package view;

import javax.swing.*;

import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;

import java.awt.*;

/**
 * Tela principal da aplicação da cantina universitária.
 *
 * <p>
 * Apresenta ao usuário as opções de autoatendimento (compra de produtos)
 * e acesso à área administrativa (mediante login).
 * </p>
 *
 * <ul>
 *   <li>Permite acessar a interface de autoatendimento para compras.</li>
 *   <li>Permite acessar a área administrativa mediante autenticação.</li>
 *   <li>Recebe e repassa as instâncias de {@link Estoque}, {@link HistoricoTransacoes} e {@link Caixa}.</li>
 * </ul>
 */

public class TelaPrincipalView extends JFrame {
  /**
   * Construtor da tela principal.
   *
   * @param estoque             referência ao estoque do sistema
   * @param historicoTransacoes referência ao histórico de transações realizadas
   * @param caixa               referência ao caixa da cantina
   */
  public TelaPrincipalView(Estoque estoque, HistoricoTransacoes historicoTransacoes, Caixa caixa) 
  {
    setTitle("Cantina Universitária - Autoatendimento");
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(245, 245, 250)); 
    mainPanel.setLayout(new BorderLayout());

    JLabel titulo = new JLabel("Cantina Universitária", SwingConstants.CENTER);
    titulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));

    JLabel subtitulo = new JLabel("Seja bem-vindo! Escolha uma opção abaixo:", SwingConstants.CENTER);
    subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setOpaque(false);
    botoesPanel.setLayout(new BoxLayout(botoesPanel, BoxLayout.Y_AXIS));

    JButton btnAutoatendimento = new JButton("Autoatendimento");
    JButton btnAdmin = new JButton("Área Administrativa");

    btnAutoatendimento.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);

    btnAutoatendimento.setPreferredSize(new Dimension(200, 40));
    btnAdmin.setPreferredSize(new Dimension(200, 40));

    btnAutoatendimento.setMaximumSize(new Dimension(220, 50));
    btnAdmin.setMaximumSize(new Dimension(220, 50));

    botoesPanel.add(btnAutoatendimento);
    botoesPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    botoesPanel.add(btnAdmin);
    botoesPanel.add(Box.createRigidArea(new Dimension(0, 40)));

    mainPanel.add(titulo, BorderLayout.NORTH);
    mainPanel.add(subtitulo, BorderLayout.CENTER);
    mainPanel.add(botoesPanel, BorderLayout.SOUTH);

    add(mainPanel);

    btnAutoatendimento.addActionListener(e -> new TelaAutoatendimentoView(estoque, historicoTransacoes, caixa).setVisible(true));
    btnAdmin.addActionListener(e -> new TelaLoginAdminView(estoque, historicoTransacoes, caixa).setVisible(true));

    setResizable(false);
  }
}
