package controller;

public class Caixa {
    private double saldo;

    public Caixa() 
    {
      this.saldo = 0.0;
    }

    public void registrarEntrada(double valor) 
    {
      saldo += valor;
    }

    public void registrarSaida(double valor) 
    {
      if (valor > saldo) 
      {
        throw new IllegalArgumentException("Saldo insuficiente no caixa para retirada.");
      }
      saldo -= valor;
    }

    public double consultarSaldo() 
    {
      return saldo;
    }

    public boolean retirarValor(double valor) 
    {
      if (valor > saldo) 
      {
        return false;
      }
      saldo -= valor;
      return true;
    }

    public void abastecerCaixa(double valor) 
    {
      saldo += valor;
    }
}
