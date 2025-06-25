package view;

import javax.swing.*;

import controller.Estoque;

import java.awt.*;

public class TelaPrincipalView extends JFrame {
  public TelaPrincipalView(Estoque estoque) 
  {
    setTitle("Cantina Universitária - Autoatendimento");
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Painel principal com cor de fundo suave
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(245, 245, 250)); // Um cinza bem clarinho
    mainPanel.setLayout(new BorderLayout());

    // Título estilizado
    JLabel titulo = new JLabel("Cantina Universitária", SwingConstants.CENTER);
    titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
    titulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));

    // Subtítulo
    JLabel subtitulo = new JLabel("Seja bem-vindo! Escolha uma opção abaixo:", SwingConstants.CENTER);
    subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
    subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));

    // Painel para botões (coluna)
    JPanel botoesPanel = new JPanel();
    botoesPanel.setOpaque(false); // Herdar a cor do fundo
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
    botoesPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaço entre botões
    botoesPanel.add(btnAdmin);
    botoesPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Espaço extra abaixo dos botões

    // Adiciona os elementos ao painel principal
    mainPanel.add(titulo, BorderLayout.NORTH);
    mainPanel.add(subtitulo, BorderLayout.CENTER);
    mainPanel.add(botoesPanel, BorderLayout.SOUTH);

    add(mainPanel);

    // Ações dos botões (descomente quando tiver as telas)
    // btnAutoatendimento.addActionListener(e -> {
    //     new TelaAutoatendimentoView().setVisible(true);
    //     this.dispose();
    // });

    btnAdmin.addActionListener(e -> new TelaLoginAdminView(estoque).setVisible(true));
  }
}
