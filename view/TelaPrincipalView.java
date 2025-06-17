package view;

import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

public class TelaPrincipalView extends JFrame {
    public TelaPrincipalView() 
    {
      setTitle("Cantina Universitária - Autoatendimento");
      setSize(400, 200);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

      JButton btnAutoatendimento = new JButton("Autoatendimento");
      JButton btnAdmin = new JButton("Área Administrativa");
      JPanel panel = new JPanel();
      panel.add(btnAutoatendimento);
      panel.add(btnAdmin);
      add(panel);

      // Ações dos botões
      // btnAutoatendimento.addActionListener(e -> {
      //     // Abrir a tela de autoatendimento
      //     new TelaAutoatendimentoView().setVisible(true);
      //     this.dispose();
      // });

      // btnAdmin.addActionListener(e -> {
      //     // Abrir tela de login administrativo
      //     new TelaLoginAdminView().setVisible(true);
      //     this.dispose();
      // });
    }
}

