import java.util.ArrayList;

public class Estoque {
  private ArrayList<Produto> produtos;

  public Estoque() 
  {
    produtos = new ArrayList<>();
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
