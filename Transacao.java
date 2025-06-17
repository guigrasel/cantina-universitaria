import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private Pedido pedido;
    private Pagamento pagamento;
    private LocalDateTime dataHora;

    public Transacao(int id, Pedido pedido, Pagamento pagamento, LocalDateTime dataHora) 
    {
      this.id = id;
      this.pedido = pedido;
      this.pagamento = pagamento;
      this.dataHora = dataHora;
    }

    public int getId() 
    {
      return id;
    }

    public Pedido getPedido() 
    {
      return pedido;
    }

    public Pagamento getPagamento() 
    {
      return pagamento;
    }

    public LocalDateTime getDataHora() 
    {
      return dataHora;
    }
}
