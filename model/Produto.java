package model;

public class Produto {
    private static int contador = 1;
    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, String categoria, double preco, int quantidadeEstoque, String validade) {
        this.id = Produto.contador;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;

        Produto.contador++;
    }

    public int getId() 
    { 
      return id; 
    }

    public String getNome() 
    { 
      return nome; 
    }

    public String getCategoria() 
    { 
      return categoria; 
    }

    public double getPreco() 
    { 
      return preco; 
    }

    public int getQuantidadeEstoque() 
    { 
      return quantidadeEstoque; 
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) 
    {
      this.quantidadeEstoque = quantidadeEstoque;
    }
}
