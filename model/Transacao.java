package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transacao {
    private static int proximoId = 1;

    private int id;
    private LocalDateTime dataHora;
    private ArrayList<Produto> produtos;
    private double valorTotal;

    public Transacao(ArrayList<Produto> produtos, double valorTotal) {
      this.id = proximoId++;
      this.dataHora = LocalDateTime.now();
      this.produtos = produtos;
      this.valorTotal = valorTotal;
    }

    public int getId() 
    { 
      return id; 
    }

    public LocalDateTime getDataHora() 
    { 
      return dataHora; 
    }

    public ArrayList<Produto> getProdutos() 
    { 
      return produtos; 
    }

    public double getValorTotal() 
    { 
      return valorTotal; 
    }

    @Override
    public String toString() 
    {
      return "Transacao #" + id + " - " + dataHora + " - Total: R$ " + String.format("%.2f", valorTotal);
    }
}