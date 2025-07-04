package model;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Representa uma transação (venda) realizada na cantina.
 * <p>
 * Cada transação possui um identificador único, data e hora da realização,
 * a lista de produtos vendidos e o valor total da compra.
 * </p>
 */

public class Transacao {
  private static int proximoId = 1;

  private int id;
  private LocalDateTime dataHora;
  private ArrayList<Produto> produtos;
  private double valorTotal;

  /**
   * Construtor da classe Transacao.
   * 
   * @param produtos   a lista de produtos vendidos na transação
   * @param valorTotal o valor total da transação
   */
  public Transacao(ArrayList<Produto> produtos, double valorTotal) {
    this.id = proximoId++;
    this.dataHora = LocalDateTime.now();
    this.produtos = produtos;
    this.valorTotal = valorTotal;
  }

  /**
   * Retorna o identificador único da transação.
   * 
   * @return o id da transação
   */
  public int getId() 
  { 
    return id; 
  }

  /**
   * Retorna a data e hora da realização da transação.
   * 
   * @return a data e hora da transação
   */
  public LocalDateTime getDataHora() 
  { 
    return dataHora; 
  }

  /**
   * Retorna a lista de produtos vendidos na transação.
   * 
   * @return lista de produtos da transação
   */
  public ArrayList<Produto> getProdutos() 
  { 
    return produtos; 
  }

  /**
   * Retorna o valor total da transação.
   * 
   * @return valor total da transação
   */
  public double getValorTotal() 
  { 
    return valorTotal; 
  }

  /**
   * Retorna um resumo textual da transação.
   * 
   * @return resumo da transação contendo id, data/hora e valor total
   */
  public String getResumo() {
    return "Transação ID: " + id + "\nData/Hora: " + dataHora + "\nValor Total: " + valorTotal;
  }
}