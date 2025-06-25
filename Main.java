import controller.Estoque;
import view.TelaPrincipalView;

public class Main {
  public static void main(String[] args) {
    Estoque estoque = new Estoque();
    javax.swing.SwingUtilities.invokeLater(() -> {
      new TelaPrincipalView(estoque).setVisible(true);
    });
  }
}
