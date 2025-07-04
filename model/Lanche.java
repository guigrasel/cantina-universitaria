package model;

/**
 * Representa um doce vendido na cantina.
 * 
 * <p>Herda de Produto e adiciona o atributo vegetariano.</p>
 */

public class Lanche extends Produto {
  private boolean vegetariano;

  /**
   * Construtor da classe Lanche.
   *
   * @param nome              o nome do lanche
   * @param categoria         a categoria do produto (ex: "Lanche")
   * @param preco             o preço unitário
   * @param quantidadeEstoque a quantidade disponível em estoque
   * @param validade          a data de validade do lanche (formato dd/MM/yyyy)
   * @param vegetariano       indica se o lanche é vegetariano ({@code true}) ou não
   */
  public Lanche(String nome, String categoria, double preco, int quantidadeEstoque, String validade, boolean vegetariano) {
    super(nome, categoria, preco, quantidadeEstoque, validade);
    this.vegetariano = vegetariano;
  }

  /**
   * Verifica se o lanche é vegetariano.
   * 
   * @return {@code true} se o lanche for vegetariano, {@code false} caso contrário
   */
  public boolean isVegetariano()
  {
    return vegetariano;
  }

  /**
   * Define se o lanche é vegetariano.
   * 
   * @param vegetariano {@code true} para lanche vegetariano, {@code false} caso contrário
   */
  public void setVegetariano(boolean vegetariano) 
  {
    this.vegetariano = vegetariano;
  }
}
