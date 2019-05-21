package Controller;

import DAO.ProdutoDAO;
import Model.Produto;
import java.util.ArrayList;

/**
 * 
 * @author lojademoveis.com.br
 */
public class ProdutoController {

    /**
     * Método para salvar um produto na memória ou na base de dados.
     * 
     * @param prProduto String nome do produto
     * @param prTipo String tipo descritivo do produto
     * @param prPreco double valor de venda do produto
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Salvar(String prProduto, String prTipo, double prPreco, int prQuantidade) {
        //Salvo na memória
        Produto pr = new Produto(prProduto, prTipo, prPreco,prQuantidade);
        return ProdutoDAO.salvar(pr);
    }
    
    /**
     * Método para excluir determinado produto.
     * 
     * @param indice inteiro com a localização da linha que contém o produto a ser excluído
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Excluir(int indice) {
        return ProdutoDAO.excluir(indice);
    }

    /**
     * Método para atualizar determinado produto.
     * 
     * @param prIdProduto inteiro ID do produto
     * @param prProduto String nome do produto
     * @param prTipo String tipo descritivo do produto
     * @param prPreco double valor de venda do produto
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Atualizar(int prIdProduto, String prProduto, String prTipo, double prPreco, int prQuantidade) {
        Produto pr = new Produto(prIdProduto, prProduto, prTipo, prPreco, prQuantidade);
        return ProdutoDAO.atualizar(pr);

    }

    /**
     * Transforma uma lista de objetos Cliente em uma lista de Strings
     *
     * @return lista de string
     */
    public static ArrayList<String[]> getProdutos() {
        ArrayList<Produto> produtos = ProdutoDAO.getProdutos();
        ArrayList<String[]> listaProdutos = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {
            listaProdutos.add(new String[]{String.valueOf(produtos.get(i).getId()),
                 produtos.get(i).getModelo(),
                 produtos.get(i).getTipo(),
                 String.valueOf(produtos.get(i).getPreco()),
                 String.valueOf(produtos.get(i).getQuantidade())});

        }

        return listaProdutos;

    }

}
