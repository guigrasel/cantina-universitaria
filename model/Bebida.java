package model;

public class Bebida extends Produto {
  private boolean alcoolica;

  public Bebida(String nome, String categoria, double preco, int quantidadeEstoque, String validade, boolean alcoolica) {
    super(nome, categoria, preco, quantidadeEstoque, validade);
    this.alcoolica = alcoolica;
  }

  public boolean isAlcoolica() 
  {
    return alcoolica;
  }

  public void setAlcoolica(boolean alcoolica)
  {
    this.alcoolica = alcoolica;
  }
}
