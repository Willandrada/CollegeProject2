package Controller;

import DAO.PedidoDAO;
import Model.ItemPedido;
import Model.Pedido;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.GerenciadorConexao;

/**
 * 
 * @author lojademoveis.com.br
 */
public class PedidoController {

    /**
     * 
     * @param p objeto do tipo Pedido
     * @param itens lista do tipo objeto ItemPedido
     * @return <code>boolean</code> true: sucesso, false: falho
     * @throws Exception 
     */
    public static boolean Salvar(Pedido p, List<ItemPedido> itens) throws Exception{
        return PedidoDAO.Salvar(p, itens);
    }

}
