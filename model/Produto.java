package model;

/**
 * Representa um produto vendido na cantina.
 * <p>
 * Cada produto possui um identificador único, nome, categoria,
 * preço, quantidade disponível em estoque e data de validade.
 * </p>
 */

public class Produto {
  private static int contador = 1;
  private int id;
  private String nome;
  private String categoria;
  private double preco;
  private int quantidadeEstoque;
  private String validade;

  /**
   * Construtor da classe Produto.
   * 
   * @param nome              o nome do produto
   * @param categoria         a categoria do produto
   * @param preco             o preço unitário
   * @param quantidadeEstoque a quantidade inicial em estoque
   * @param validade          a data de validade do produto (dd/MM/yyyy)
   */
  public Produto(String nome, String categoria, double preco, int quantidadeEstoque, String validade) {
    this.id = Produto.contador;
    this.nome = nome;
    this.categoria = categoria;
    this.preco = preco;
    this.quantidadeEstoque = quantidadeEstoque;
    this.validade = validade;
    Produto.contador++;
  }

  /**
   * Retorna o identificador único do produto.
   * 
   * @return o id do produto
   */
  public int getId() 
  { 
    return id; 
  }

  /**
   * Retorna o nome do produto.
   * 
   * @return o nome do produto
   */
  public String getNome() 
  { 
    return nome; 
  }


  /**
   * Retorna a categoria do produto.
   * 
   * @return a categoria do produto
   */
  public String getCategoria() 
  { 
    return categoria; 
  }

  /**
   * Retorna o preço unitário do produto.
   * 
   * @return o preço do produto
   */
  public double getPreco() 
  { 
    return preco; 
  }

  /**
   * Retorna a data de validade do produto.
   * 
   * @return a validade do produto
   */
  public String getValidade() 
  {
    return validade;
  }

  /**
   * Retorna a quantidade disponível em estoque.
   * 
   * @return a quantidade em estoque
   */
  public int getQuantidadeEstoque() 
  { 
    return quantidadeEstoque; 
  }

  /**
   * Define a quantidade disponível em estoque.
   * 
   * @param quantidadeEstoque a nova quantidade em estoque
   */
  public void setQuantidadeEstoque(int quantidadeEstoque) 
  {
    this.quantidadeEstoque = quantidadeEstoque;
  }
}
