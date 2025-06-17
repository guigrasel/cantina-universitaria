package model;

import java.util.ArrayList;

public class Pedido {
  private int id;
  private Usuario usuario;
  private ArrayList<ItemPedido> itens;
  private String status; // "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO"

  public Pedido(int id, Usuario usuario) 
  {
    this.id = id;
    this.usuario = usuario;
    this.itens = new ArrayList<>();
    this.status = "EM_ANDAMENTO";
  }

  public void adicionarItem(ItemPedido item) 
  {
    itens.add(item);
  }

  public double calcularTotal() 
  {
    double total = 0;
    for (ItemPedido item : itens) {
      total += item.getSubtotal();
    }
    return total;
  }

  public int getId() 
  { 
    return id; 
  }

  public Usuario getUsuario() 
  { 
    return usuario; 
  }

  public ArrayList<ItemPedido> getItens() 
  { 
    return itens; 
  }

  public String getStatus() 
  { 
    return status; 
  }

  public void setStatus(String status) 
  { 
    this.status = status; 
  }
}
