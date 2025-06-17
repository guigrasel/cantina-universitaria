public class Pagamento {
    private String tipoPagamento;
    private double valorRecebido;
    private double troco;

    public Pagamento(String tipoPagamento, double valorRecebido, double troco)
    {
      this.tipoPagamento = tipoPagamento;
      this.valorRecebido = valorRecebido;
      this.troco = troco;
    }

    public String getTipoPagamento()
    {
      return tipoPagamento;
    }

    public double getValorRecebido()
    {
      return valorRecebido;
    }

    public double getTroco()
    {
      return troco;
    }
}
