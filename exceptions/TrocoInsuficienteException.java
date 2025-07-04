package exceptions;

/**
 * Exceção lançada quando o caixa não possui troco suficiente para concluir uma operação de pagamento.
 *
 * <p>
 * Esta exceção é utilizada para sinalizar que não há quantidade suficiente
 * no caixa para fornecer o troco ao cliente.
 * </p>
 */
public class TrocoInsuficienteException extends Exception {
  /**
   * Cria uma nova exceção com a mensagem especificada.
   *
   * @param mensagem a mensagem explicando o motivo da exceção
   */
  public TrocoInsuficienteException(String mensagem) {
    super(mensagem);
  }
}
