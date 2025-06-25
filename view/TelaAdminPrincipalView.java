package view;

import javax.swing.*;

import controller.Estoque;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaAdminPrincipalView extends JFrame {
  private Estoque estoque;

  public TelaAdminPrincipalView(Estoque estoque) 
  {
    this.estoque = estoque;
    setTitle("Área Administrativa - Cantina");
    setSize(500, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBackground(new Color(245, 245, 250));

    JLabel lblTitulo = new JLabel("Painel Administrativo", JLabel.CENTER);
    lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(lblTitulo, BorderLayout.NORTH);

    JPanel botoesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
    botoesPanel.setBackground(new Color(245, 245, 250));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JButton btnEstoque = new JButton("Gerenciar Estoque");
    JButton btnRelatorios = new JButton("Relatórios");
    JButton btnSair = new JButton("Sair");

    botoesPanel.add(btnEstoque);
    botoesPanel.add(btnRelatorios);
    botoesPanel.add(btnSair);

    panel.add(botoesPanel, BorderLayout.CENTER);

    btnSair.addActionListener((ActionEvent e) -> dispose());
    btnEstoque.addActionListener(e -> new TelaEstoqueView(this.estoque).setVisible(true));

    setContentPane(panel);
    setResizable(false);
  }
}
