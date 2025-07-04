package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;

/**
 * Tela de login administrativo da cantina universitária.
 * 
 * <p>
 * Permite o acesso à área administrativa do sistema, mediante autenticação simples de usuário e senha.
 * Após login bem-sucedido, abre a tela principal da administração.
 * </p>
 *
 * <ul>
 *   <li>Usuário e senha padrão: <b>admin</b>.</li>
 *   <li>Passa as instâncias de Estoque, Caixa e Histórico de Transações para a tela principal administrativa.</li>
 * </ul>
 */

public class TelaLoginAdminView extends JFrame {
  /**
   * Construtor da tela de login administrativo.
   * 
   * @param estoque             referência ao estoque para repasse à administração
   * @param historicoTransacoes referência ao histórico de transações
   * @param caixa               referência ao caixa da cantina
   */
  public TelaLoginAdminView(Estoque estoque, HistoricoTransacoes historicoTransacoes, Caixa caixa)
  {
    setTitle("Login Administrativo");
    setSize(350, 220);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(250, 250, 255));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel lblTitulo = new JLabel("Área administrativa");
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JTextField txtUsuario = new JTextField(15);
    JPasswordField txtSenha = new JPasswordField(15);

    JButton btnLogin = new JButton("Entrar");
    btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.add(lblTitulo);
    panel.add(Box.createRigidArea(new Dimension(0, 15)));
    panel.add(txtUsuario);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(txtSenha);
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(btnLogin);

    add(panel);

    btnLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuario.equals("admin") && senha.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            new TelaAdminPrincipalView(estoque, historicoTransacoes, caixa).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    setResizable(false);
  }
}
