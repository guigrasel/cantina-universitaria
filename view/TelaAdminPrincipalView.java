package view;

import javax.swing.*;

import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaAdminPrincipalView extends JFrame {
  private Estoque estoque;
  private HistoricoTransacoes historicoTransacoes;
  private Caixa caixa;

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

    JLabel lblTitulo = new JLabel("Área Administrativa", JLabel.CENTER);
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    panel.add(lblTitulo, BorderLayout.NORTH);

    JPanel botoesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JButton btnEstoque = new JButton("Gerenciar Estoque");
    JButton btnCaixa = new JButton("Gerenciar Caixa");
    JButton btnRelatorios = new JButton("Relatórios");
    JButton btnSair = new JButton("Sair");

    botoesPanel.add(btnEstoque);
    botoesPanel.add(btnCaixa);
    botoesPanel.add(btnRelatorios);
    botoesPanel.add(btnSair);

    panel.add(botoesPanel, BorderLayout.CENTER);

    btnSair.addActionListener((ActionEvent e) -> dispose());
    btnEstoque.addActionListener(e -> new TelaEstoqueView(this.estoque).setVisible(true));
    btnCaixa.addActionListener(e -> new TelaAdminRelCaixaView(this.caixa).setVisible(true));
    btnRelatorios.addActionListener(e -> new TelaAdminRelatoriosView(this.estoque, this.historicoTransacoes).setVisible(true));

    setContentPane(panel);
    setResizable(false);
  }
}
