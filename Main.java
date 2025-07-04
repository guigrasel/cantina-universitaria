import controller.Caixa;
import controller.Estoque;
import model.HistoricoTransacoes;
import view.TelaPrincipalView;

/**
 * Classe principal do sistema de autoatendimento da cantina universitária.
 * 
 * <p>Responsável por inicializar os principais componentes do sistema e
 * exibir a tela principal da aplicação.</p>
 * 
 * <ul>
 *   <li>Inicializa o estoque de produtos.</li>
 *   <li>Inicializa o histórico de transações.</li>
 *   <li>Inicializa o caixa da cantina.</li>
 *   <li>Exibe a interface gráfica principal.</li>
 * </ul>
 */
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
