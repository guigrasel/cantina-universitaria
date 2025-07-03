import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;
import view.TelaPrincipalView;

public class Main {
  public static void main(String[] args) {
    Estoque estoque = new Estoque();
    HistoricoTransacoes historicoTransacoes = new HistoricoTransacoes();
    Caixa caixa = new Caixa();
    javax.swing.SwingUtilities.invokeLater(() -> {
      new TelaPrincipalView(estoque, historicoTransacoes, caixa).setVisible(true);
    });
  }
}
