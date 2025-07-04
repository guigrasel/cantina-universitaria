package model;

/**
 * Representa um doce vendido na cantina.
 * 
 * <p>Herda de Produto e adiciona o atributo tipoAçucar.</p>
 */
public class Doce extends Produto {
  private String tipoAçucar;

  /**
   * Construtor da classe Doce.
   *
   * @param nome              o nome do doce
   * @param categoria         a categoria do produto (ex: "Doce")
   * @param preco             o preço unitário
   * @param quantidadeEstoque a quantidade disponível em estoque
   * @param validade          a data de validade do doce (formato dd/MM/yyyy)
   * @param tipoAçucar        o tipo de açúcar utilizado
   */
  public Doce(String nome, String categoria, double preco, int quantidadeEstoque, String validade, String tipoAçucar) {
    super(nome, categoria, preco, quantidadeEstoque, validade);
    this.tipoAçucar = tipoAçucar;
  }

  /**
   * Retorna o tipo de açúcar utilizado no doce.
   * 
   * @return o tipo de açúcar
   */
  public String getTipoAçucar() 
  {
    return tipoAçucar;
  }

  /**
   * Define o tipo de açúcar utilizado no doce.
   * 
   * @param tipoAçucar o novo tipo de açúcar
   */
  public void setTipoAçucar(String tipoAçucar) 
  {
    this.tipoAçucar = tipoAçucar;
  }
}
