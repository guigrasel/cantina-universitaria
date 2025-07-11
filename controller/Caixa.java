package controller;

import java.util.HashMap;
import java.util.Map;

import exceptions.TrocoInsuficienteException;

/**
 * Classe responsável pelo controle de dinheiro no caixa da cantina.
 * 
 * <p>
 * Gerencia as operações de entrada e saída de cédulas/moedas, cálculo de saldo e troco, além de fornecer a situação atual do caixa.
 * </p>
 * 
 * <ul>
 *   <li>Permite adicionar e remover dinheiro do caixa.</li>
 *   <li>Calcula o saldo total disponível.</li>
 *   <li>Calcula o troco a ser fornecido ao cliente, verificando se o caixa possui cédulas/moedas suficientes.</li>
 * </ul>
 */

public class Caixa {
  private Map<Double, Integer> dinheiro = new HashMap<>();

  /**
   * Construtor padrão.
   * Inicializa o caixa com quantidades pré-definidas de cada valor.
   */
  public Caixa() {
    dinheiro.put(100.0, 5);
    dinheiro.put(50.0, 5);
    dinheiro.put(20.0, 5);
    dinheiro.put(10.0, 5);
    dinheiro.put(5.0, 10);
    dinheiro.put(2.0, 20);
    dinheiro.put(1.0, 20);
    dinheiro.put(0.5, 30);
    dinheiro.put(0.25, 40);
    dinheiro.put(0.10, 50);
    dinheiro.put(0.05, 50);
  }

  /**
   * Adiciona as quantidades recebidas de cada cédula/moeda ao caixa.
   * 
   * @param recebido mapa contendo os valores e as quantidades a serem adicionadas
   */
  public void adicionarDinheiro(Map<Double, Integer> recebido) 
  {
    for (Map.Entry<Double, Integer> entry : recebido.entrySet()) 
    {
      dinheiro.put(entry.getKey(), dinheiro.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }
  }

  /**
   * Remove as quantidades entregues de cada cédula/moeda do caixa.
   * 
   * @param entregue mapa contendo os valores e as quantidades a serem removidas
   */
  public void removerDinheiro(Map<Double, Integer> entregue) 
  {
    for (Map.Entry<Double, Integer> entry : entregue.entrySet())
    {
      dinheiro.put(entry.getKey(), dinheiro.getOrDefault(entry.getKey(), 0) - entry.getValue());
    }
  }

  /**
   * Calcula o saldo total em dinheiro disponível no caixa.
   * 
   * @return o valor total do caixa
   */
  public double getSaldo() 
  {
    double total = 0;
    for (Map.Entry<Double, Integer> entry : dinheiro.entrySet()) 
    {
      total += entry.getKey() * entry.getValue();
    }
    return total;
  }

  /**
   * Calcula o troco a ser fornecido para um determinado valor.
   * <p>
   * O método verifica se há quantidade suficiente de cada cédula/moeda no caixa para fornecer o troco.
   * Se não houver troco suficiente, lança a exceção {@link TrocoInsuficienteException}.
   * </p>
   * 
   * @param valorTroco valor de troco a ser fornecido
   * @return mapa com os valores e as quantidades de cédulas/moedas a serem entregues como troco
   * @throws TrocoInsuficienteException se não houver troco suficiente no caixa
   */
  public Map<Double, Integer> calcularTroco(double valorTroco) throws TrocoInsuficienteException 
  {
    Map<Double, Integer> troco = new HashMap<>();
    double[] valores = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.25, 0.10, 0.05};
    double restante = Math.round(valorTroco * 100.0) / 100.0;

    for (double valor : valores) 
    {
      int disponiveis = dinheiro.getOrDefault(valor, 0);
      int qtd = 0;
      while (restante >= valor - 0.001 && disponiveis > 0) {
        restante -= valor;
        restante = Math.round(restante * 100.0) / 100.0;
        disponiveis--;
        qtd++;
      }
      if (qtd > 0) 
      {
        troco.put(valor, qtd);
      }
    }
    if (restante > 0.05) 
    {
      throw new TrocoInsuficienteException("Caixa sem troco suficiente para esta operação.");
    }
    return troco;
  }

  /**
   * Retorna a situação atual do caixa, informando a quantidade de cada valor de cédula/moeda.
   * 
   * @return um novo mapa contendo os valores e quantidades atuais do caixa
   */
  public Map<Double, Integer> getDinheiro() 
  {
    return new HashMap<>(dinheiro);
  }
}
