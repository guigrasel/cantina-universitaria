package controller;

import java.util.ArrayList;

import model.Bebida;
import model.Doce;
import model.Lanche;
import model.Produto;

/**
 * Classe responsável pelo controle de estoque de produtos da cantina.
 * 
 * <p>Gerencia uma lista de produtos disponíveis, permitindo adicionar, remover e buscar produtos.</p>
 * 
 * <ul>
 *   <li>O estoque é inicializado com um conjunto padrão de lanches, doces e bebidas.</li>
 *   <li>Permite operações de cadastro, remoção e consulta por ID.</li>
 * </ul>
 */
public class Estoque {
  private ArrayList<Produto> produtos;

  /**
   * Construtor. Inicializa o estoque com alguns produtos padrão.
   */
  public Estoque() 
  {
    produtos = new ArrayList<>();
    // 4 Lanches
    adicionarProduto(new Lanche("Sanduíche", "Lanche", 10.00, 20, "20/12/2025", true));
    adicionarProduto(new Lanche("Hambúrguer", "Lanche", 20.00, 15, "20/12/2025", false));
    adicionarProduto(new Lanche("Pastel de Frango", "Lanche", 12.00, 10, "22/12/2025", false));
    adicionarProduto(new Lanche("Torrada", "Lanche", 8.00, 18, "23/12/2025", true));

    // 4 Doces
    adicionarProduto(new Doce("Brigadeiro", "Doce", 3.00, 40, "30/12/2025", "Açúcar refinado"));
    adicionarProduto(new Doce("Brownie", "Doce", 6.00, 25, "31/12/2025", "Açúcar refinado"));
    adicionarProduto(new Doce("Cookie", "Doce", 4.50, 30, "29/12/2025", "Mascavo"));
    adicionarProduto(new Doce("Pudim", "Doce", 7.00, 10, "28/12/2025", "Açúcar refinado"));

    // 4 Bebidas
    adicionarProduto(new Bebida("Refrigerante", "Bebida", 5.00, 35, "10/11/2025", false));
    adicionarProduto(new Bebida("Suco", "Bebida", 7.50, 20, "12/11/2025", false));
    adicionarProduto(new Bebida("Cerveja", "Bebida", 12.00, 12, "30/11/2025", true));
    adicionarProduto(new Bebida("Vinho", "Bebida", 35.00, 8, "15/11/2025", true));
  }

  /**
   * Adiciona um novo produto ao estoque.
   * 
   * @param produto o produto a ser adicionado
   */
  public void adicionarProduto(Produto produto) 
  {
    produtos.add(produto);
  }

  /**
   * Remove um produto do estoque.
   * 
   * @param produto o produto a ser removido
   */
  public void removerProduto(Produto produto) 
  {
    produtos.remove(produto);
  }

  /**
   * Busca um produto no estoque pelo seu identificador.
   * 
   * @param id o id do produto a ser buscado
   * @return o produto encontrado, ou {@code null} se não existir
   */
  public Produto buscarProdutoPorId(int id) 
  {
    for (Produto p : produtos) {
    if (p.getId() == id) {
      return p;
    }
    }
    return null;
  }

  /**
   * Retorna a lista de produtos disponíveis no estoque.
   * 
   * @return lista de produtos
   */
  public ArrayList<Produto> getProdutos() 
  {
    return produtos;
  }
}
