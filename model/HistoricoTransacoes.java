package model;

import java.util.ArrayList;

public class HistoricoTransacoes {
  private ArrayList<Transacao> transacoes;

  public HistoricoTransacoes() 
  {
    this.transacoes = new ArrayList<>();
  }

  public void adicionarTransacao(Transacao transacao) 
  {
    transacoes.add(transacao);
  }

  public ArrayList<Transacao> getTransacoes() 
  {
    return transacoes;
  }

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
