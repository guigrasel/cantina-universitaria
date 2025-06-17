import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

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

    public List<Produto> getProdutos() 
    {
      return produtos;
    }
}
