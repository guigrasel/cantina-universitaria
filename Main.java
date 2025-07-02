import controller.Estoque;
import model.HistoricoTransacoes;
import view.TelaPrincipalView;

public class Main {
  public static void main(String[] args) {
    Estoque estoque = new Estoque();
    HistoricoTransacoes historicoTransacoes = new HistoricoTransacoes();
    javax.swing.SwingUtilities.invokeLater(() -> {
      new TelaPrincipalView(estoque, historicoTransacoes).setVisible(true);
    });
  }
}
