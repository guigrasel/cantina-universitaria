package model;

public class Lanche extends Produto {
  private boolean vegetariano;

  public Lanche(String nome, String categoria, double preco, int quantidadeEstoque, String validade, boolean vegetariano) {
    super(nome, categoria, preco, quantidadeEstoque, validade);
    this.vegetariano = vegetariano;
  }

  public boolean isVegetariano()
  {
    return vegetariano;
  }

  public void setVegetariano(boolean vegetariano) 
  {
    this.vegetariano = vegetariano;
  }
}
