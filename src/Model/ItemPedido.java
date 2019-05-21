package Model;

/**
 * 
 * @author lojademoveis.com.br
 */
public class ItemPedido {
    private int quantidade, codigo, codigoVenda;
    private double vlUni, vlTotal;
    private String descricao, codigoProduto;
	
	public ItemPedido(){
	}
	
	public ItemPedido(boolean vazio){
		if (vazio) {
			quantidade = codigo = 0;
			vlUni = vlTotal = 0.0;
			descricao = codigoProduto = "";
		}
	}
	
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getVlUni() {
        return vlUni;
    }

    public void setVlUni(double vlUni) {
        this.vlUni = vlUni;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the codigoProduto
     */
    public String getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * @param codigoProduto the codigoProduto to set
     */
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

	/**
	 * @return the codigoVenda
	 */
	public int getCodigoVenda() {
		return codigoVenda;
	}

	/**
	 * @param codigoVenda the codigoVenda to set
	 */
	public void setCodigoVenda(int codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
}