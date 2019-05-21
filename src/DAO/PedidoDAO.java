package DAO;

import utils.GerenciadorConexao;
import Model.ItemPedido;
import Model.Pedido;
import LojaDeMoveis.Raiz;
import Model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GerenciadorConexao;

/**
 *
 * @author lojademoveis.com.br
 * @see Controller.PedidoController
 */
public class PedidoDAO {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String SERVIDOR = "localhost";     //servidor de banco de dados
    private static final String BASEDADOS = "lojademoveis";      //nome da base de dados
    private static final String LOGIN = "root";             //nome de um usuário do banco de dados
    private static final String SENHA = "";                 //sua senha de acesso
    private static String url = "";
    private static Connection conexao;

    /**
     * Método para inserção de uma venda no banco de dados.
     *
     * @param venda objeto do tipo Pedido
     * @param itens lista de objetos do tipo ItemPedido
     * @return <code>boolean</code> indicando true: sucesso, false: falha;
     * @throws SQLException
     * @throws Exception
     */
    public static boolean Salvar(Pedido venda, List<ItemPedido> itens) throws SQLException, Exception {

        boolean retorno = false;

        //Crio um comando PreparedStatement utilizando minha classe de conexão
        PreparedStatement comando = GerenciadorConexao.getPreparedStatement("INSERT INTO VENDA (IDCLIENTE,FORMAPAGAMENTO,VLTOTAL,VALORDESCONTO,NOTASINTERNAS,DTVENDA) VALUES(?,?,?,?,?,?);");

        try {
            comando.setString(1, String.valueOf(venda.getCodigoCliente()));
            comando.setString(2, String.valueOf(venda.getFormaPagto()));
            comando.setString(3, String.valueOf(venda.getVlTotal()));
            comando.setString(4, String.valueOf(venda.getVlDesconto()));
            comando.setString(5, String.valueOf(venda.getNotasInternas()));
            comando.setDate(6, java.sql.Date.valueOf(new Raiz().formataDataRetornaBD(venda.getDtVenda())));

            retorno = GerenciadorConexao.executarUpdate();

            ResultSet rs = comando.executeQuery("SELECT MAX(IDPEDIDO) AS CODIGOVENDA FROM VENDA;");

            while (rs.next()) {
                venda.setCodigo(rs.getInt("CODIGOVENDA"));
            }

            for (ItemPedido item : itens) {
                item.setCodigoVenda(venda.getCodigo());
                PedidoDAO.SalvarItem(item, venda.getCodigo());
            }

        } catch (SQLException ex) {
            retorno = false;
        } finally {
            GerenciadorConexao.fecharConexao();
        }

        return retorno;
    }

    /**
     * Método para inserir o item do pedido no banco de dados.
     *
     * @param itens objeto do tipo ItemPedido
     * @param codigoVenda inteiro referente ao codigo do pedido
     * @return <code>boolean</code> indicando true: sucesso, false: falha;
     * @throws SQLException
     * @throws Exception
     */
    public static boolean SalvarItem(ItemPedido itens, int codigoVenda) throws SQLException, Exception {

        boolean retorno = false;

        //Crio um comando PreparedStatement utilizando minha classe de conexão
        PreparedStatement comando = GerenciadorConexao.getPreparedStatement("INSERT INTO ITENSVENDA(IDPRODUTO,IDPEDIDO,QTVENDIDA,VALORUNITARIO,VALORTOTAL) VALUES(?,?,?,?,?);");

        try {
            comando.setString(1, String.valueOf(itens.getCodigoProduto()));
            comando.setString(2, String.valueOf(codigoVenda));
            comando.setString(3, String.valueOf(itens.getQuantidade()));
            comando.setString(4, String.valueOf(itens.getVlUni()));
            comando.setString(5, String.valueOf(itens.getVlTotal()));

            retorno = GerenciadorConexao.executarUpdate();

        } catch (SQLException ex) {
            retorno = false;
        } finally {
            GerenciadorConexao.fecharConexao();
        }

        return retorno;
    }

    /**
     *
     * @return <code>ArrayList(Pedido)</code> retorna todos os pedidos do banco
     * de dados;
     */
    public static ArrayList<Pedido> getPedido() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        try {

            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url, LOGIN, SENHA);

            Statement comando = conexao.createStatement();

            ResultSet rs = comando.executeQuery("SELECT * FROM Pedido;");
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setCodigo(rs.getInt("IdPedido"));
                p.setCodigoCliente(rs.getInt("IdCliente"));
                p.setDtVenda(rs.getString("DtVenda"));
                p.setVlTotal(rs.getDouble("VlTotal"));
                listaPedidos.add(p);
            }
            rs.close();

        } catch (ClassNotFoundException ex) {
            listaPedidos = null;
        } catch (SQLException ex) {
            listaPedidos = null;
        } finally {

            try {
                if (conexao != null) {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    } else {
                        System.out.println("Conexão já fechada");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return listaPedidos;

    }

    /**
     * Método que retorna todos os pedidos do banco de um determinado período.
     * Insere-se uma data inicial e uma data final, para tanto é retornado os 
     * pedidos que se encontram entre essas datas.
     * 
     * @param DtInicio
     * @param DtFinal
     * @return <code>ArrayList(Pedido)</code> retorna todos os pedidos do banco;
     * @throws ParseException 
     */
    public static ArrayList<Pedido> getPedidosRel(String DtInicio, String DtFinal) throws ParseException {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        try {
            //return SimulaDB.getInstance().getClientes();

            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url, LOGIN, SENHA);

            PreparedStatement comando = conexao.prepareStatement("Select * from Venda Where DtVenda Between ? and ?");
            comando.setDate(1, java.sql.Date.valueOf(new Raiz().formataDataRetornaBD(DtInicio)));
            comando.setDate(2, java.sql.Date.valueOf(new Raiz().formataDataRetornaBD(DtFinal)));

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setCodigo(rs.getInt("IdPedido"));
                p.setCodigoCliente(rs.getInt("IdCliente"));
                p.setDtVenda(rs.getString("DtVenda"));
                p.setVlTotal(rs.getDouble("VlTotal"));
                p.setFormaPagto(rs.getString("FormaPagamento"));
                p.setVlDesconto(rs.getDouble("ValorDesconto"));
                p.setNotasInternas(rs.getString("NotasInternas"));
                
                listaPedidos.add(p);
            }
            rs.close();

        } catch (ClassNotFoundException ex) {
            listaPedidos = null;
        } catch (SQLException ex) {
            listaPedidos = null;
        } finally {

            try {
                if (conexao != null) {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    } else {
                        System.out.println("Conexão já fechada");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return listaPedidos;

    }
    // Executa o comando SQL informado como parâmetro no BD
       public static ArrayList<ItemPedido> getItemPedido(int pId)
    {
        ArrayList<ItemPedido> listaItem = new ArrayList<>();
        
        try {
            
            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url,LOGIN,SENHA);
            
            //Statement comando = conexao.createStatement();
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM ItensVenda WHERE IdPedido=? ");
            comando.setInt(1, pId);
            ResultSet rs = comando.executeQuery();
            //ResultSet rs = comando.executeQuery("SELECT * FROM cliente WHERE nome LIKE'%" + pNome + "%';");
            
            while(rs.next())
            {
                ItemPedido c = new ItemPedido();
                c.setCodigoProduto(rs.getString("IdProduto"));
                c.setQuantidade(rs.getInt("QtVendida"));
                c.setVlUni(rs.getDouble("ValorUnitario"));
                c.setVlTotal(rs.getDouble("ValorTotal"));
                
                listaItem.add(c);
            }
            rs.close();
            
        } catch (ClassNotFoundException ex) {
            listaItem = null;
        } catch (SQLException ex) {
            listaItem = null;
        }finally{
        
            try {
                if(!conexao.isClosed())
                    conexao.close();
                else
                {
                    System.out.println("Conexão já fechada");
                }
            } catch (SQLException ex) {
                listaItem = null;
            }
        
        }
        
        return listaItem;
       
        
    }
}
