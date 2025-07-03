package controller;

import java.util.HashMap;
import java.util.Map;

import exceptions.TrocoInsuficienteException;

public class Caixa {

  private Map<Double, Integer> dinheiro = new HashMap<>();

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

  public void adicionarDinheiro(Map<Double, Integer> recebido) 
  {
    for (Map.Entry<Double, Integer> entry : recebido.entrySet()) 
    {
      dinheiro.put(entry.getKey(), dinheiro.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }
  }

  public void removerDinheiro(Map<Double, Integer> entregue) 
  {
    for (Map.Entry<Double, Integer> entry : entregue.entrySet())
    {
      dinheiro.put(entry.getKey(), dinheiro.getOrDefault(entry.getKey(), 0) - entry.getValue());
    }
  }

  public double getSaldo() 
  {
    double total = 0;
    for (Map.Entry<Double, Integer> entry : dinheiro.entrySet()) 
    {
      total += entry.getKey() * entry.getValue();
    }
    return total;
  }

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

  public Map<Double, Integer> getDinheiro() 
  {
    return new HashMap<>(dinheiro);
  }
}
