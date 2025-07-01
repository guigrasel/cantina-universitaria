package model;

public class Doce extends Produto {
  private String tipoAçucar;

  public Doce(String nome, String categoria, double preco, int quantidadeEstoque, String validade, String tipoAçucar) {
      super(nome, categoria, preco, quantidadeEstoque, validade);
      this.tipoAçucar = tipoAçucar;
  }

  public String getTipoAçucar() 
  {
    return tipoAçucar;
  }

  public void setTipoAçucar(String tipoAçucar) 
  {
    this.tipoAçucar = tipoAçucar;
  }
}
