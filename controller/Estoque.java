package controller;

import java.util.ArrayList;

import model.Bebida;
import model.Doce;
import model.Lanche;
import model.Produto;

public class Estoque {
  private ArrayList<Produto> produtos;

  public Estoque() 
  {
    produtos = new ArrayList<>();
    // 4 Lanches
    adicionarProduto(new Lanche("Sanduíche Veggie", "Lanche", 9.99, 20, "20/12/2025", true));
    adicionarProduto(new Lanche("Cheeseburger", "Lanche", 11.50, 15, "20/12/2025", false));
    adicionarProduto(new Lanche("Wrap de Frango", "Lanche", 12.00, 10, "22/12/2025", false));
    adicionarProduto(new Lanche("Tostex Vegetariano", "Lanche", 8.99, 18, "23/12/2025", true));

    // 4 Doces
    adicionarProduto(new Doce("Brigadeiro", "Doce", 3.00, 40, "30/12/2025", "Açúcar refinado"));
    adicionarProduto(new Doce("Brownie Diet", "Doce", 6.00, 25, "31/12/2025", "Açúcar de coco"));
    adicionarProduto(new Doce("Cookie Integral", "Doce", 4.50, 30, "29/12/2025", "Mascavo"));
    adicionarProduto(new Doce("Pudim", "Doce", 7.00, 10, "28/12/2025", "Açúcar refinado"));

        // 4 Bebidas
    adicionarProduto(new Bebida("Refrigerante", "Bebida", 5.00, 35, "10/11/2025", false));
    adicionarProduto(new Bebida("Suco Natural", "Bebida", 7.50, 20, "12/11/2025", false));
    adicionarProduto(new Bebida("Cerveja Artesanal", "Bebida", 12.00, 12, "30/11/2025", true));
    adicionarProduto(new Bebida("Vinho", "Bebida", 35.00, 8, "15/11/2025", true));
  }

  public void adicionarProduto(Produto produto) 
  {
    produtos.add(produto);
  }

  public void removerProduto(Produto produto) 
  {
    produtos.remove(produto);
  }

  public Produto buscarProdutoPorId(int id) 
  {
    for (Produto p : produtos) {
    if (p.getId() == id) {
      return p;
    }
    }
    return null;
  }

  public ArrayList<Produto> getProdutos() 
  {
    return produtos;
  }
}
