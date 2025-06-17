public class Usuario {
    private int id;
    private String nome;
    private String tipo; // "ALUNO", "PROFESSOR", "VISITANTE", "ADMIN"

    public Usuario(int id, String nome, String tipo) 
    {
      this.id = id;
      this.nome = nome;
      this.tipo = tipo;
    }

    public int getId() 
    { 
      return id; 
    }

    public String getNome() 
    { 
      return nome; 
    }

    public String getTipo() 
    { 
      return tipo; 
    }
}
