package Model;

/**
 * 
 * @author lojademoveis.com.br
 */
public class Produto {

    private int idProduto;
    private String produto;
    private String tipo;
    private double preco;
    private int quantidade;
    
    public Produto() {
    }

    public Produto(String produto, String tipo, double preco, int qtd) {
        this.produto = produto;
        this.tipo = tipo;
        this.preco = preco;
        this.quantidade = qtd;
    }

    public Produto(int idProduto, String produto, String tipo, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.produto = produto;
        this.tipo = tipo;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return idProduto;
    }

    public void setId(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getModelo() {
        return produto;
    }

    public void setModelo(String pModelo) {
        this.produto = pModelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
