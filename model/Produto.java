package model;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidadeEstoque;
    private String validade;

    public Produto(int id, String nome, String categoria, double preco, int quantidadeEstoque, String validade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.validade = validade;
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

    public String getValidade() 
    { 
      return validade; 
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) 
    {
      this.quantidadeEstoque = quantidadeEstoque;
    }
}
