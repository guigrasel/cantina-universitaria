package model;

/**
 * Representa uma bebida vendida na cantina.
 * 
 * <p>Herda de Produto e adiciona o atributo alcoolica para indicar
 * se a bebida possui teor alcoólico.</p>
 */
public class Bebida extends Produto {
  /**
   * Indica se a bebida é alcoólica.
   */
  private boolean alcoolica;

  /**
   * Construtor da classe Bebida.
   *
   * @param nome              o nome da bebida
   * @param categoria         a categoria da bebida (ex: "Bebida")
   * @param preco             o preço unitário
   * @param quantidadeEstoque a quantidade disponível em estoque
   * @param validade          a data de validade no formato "dd/MM/yyyy"
   * @param alcoolica         indica se a bebida é alcoólica ({@code true}) ou não ({@code false})
   */
  public Bebida(String nome, String categoria, double preco, int quantidadeEstoque, String validade, boolean alcoolica) {
    super(nome, categoria, preco, quantidadeEstoque, validade);
    this.alcoolica = alcoolica;
  }

  /**
   * Verifica se a bebida é alcoólica.
   *
   * @return {@code true} se a bebida for alcoólica, {@code false} caso contrário
   */
  public boolean isAlcoolica() 
  {
    return alcoolica;
  }

  /**
   * Define se a bebida é alcoólica.
   *
   * @param alcoolica {@code true} para bebida alcoólica, {@code false} caso contrário
   */
  public void setAlcoolica(boolean alcoolica)
  {
    this.alcoolica = alcoolica;
  }
}
