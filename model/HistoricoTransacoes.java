package model;

import java.util.ArrayList;


/**
 * Classe responsável por armazenar e gerenciar o histórico de transações realizadas na cantina.
 * 
 * <p>Permite adicionar novas transações, consultar todas as transações realizadas
 * e calcular o valor total vendido.</p>
 */
public class HistoricoTransacoes {
  /**
    * Lista de transações realizadas.
    */
  private ArrayList<Transacao> transacoes;


  /**
    * Construtor padrão. Inicializa o histórico vazio.
    */
  public HistoricoTransacoes() 
  {
    this.transacoes = new ArrayList<Transacao>();
  }


  /**
   * Adiciona uma transação ao histórico.
   *
   * @param transacao a transação a ser adicionada
   */
  public void adicionarTransacao(Transacao transacao) 
  {
    transacoes.add(transacao);
  }

  /**
   * Retorna a lista de transações realizadas.
   *
   * @return lista de transações
   */
  public ArrayList<Transacao> getTransacoes() 
  {
    return transacoes;
  }

  /**
   * Calcula o valor total vendido somando o valor de todas as transações.
   *
   * @return valor total vendido
   */
  public double valorTotalVendido() 
  {
    double total = 0;
    for (Transacao t : transacoes) 
    {
      total += t.getValorTotal();
    }
    return total;
  }
}
