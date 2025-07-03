package view;

import javax.swing.*;

import controller.Estoque;
import model.HistoricoTransacoes;

import java.awt.*;

public class TelaAdminRelatoriosView extends JFrame {
  private HistoricoTransacoes historicoTransacoes;

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

    botoesPanel.add(btnTransacoesRealizadas);
    botoesPanel.add(btnItensMaisPedidos);

    panel.add(botoesPanel, BorderLayout.CENTER);

    btnTransacoesRealizadas.addActionListener(e -> new TelaAdminRelTransacoesRealizadasView(this.historicoTransacoes).setVisible(true));
    btnItensMaisPedidos.addActionListener(e -> new TelaAdminRelMaisVendidosView(this.historicoTransacoes).setVisible(true));

    setContentPane(panel);
    setResizable(false);
  }
}
