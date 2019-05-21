package DAO;

import Model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GerenciadorConexao;

/**
 *
 * @author lojademoveis.com.br
 * @see Controller.ClienteController
 */
public class ClienteDAO {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    private static final String SERVIDOR = "localhost";     //servidor de banco de dados
    private static final String BASEDADOS = "lojademoveis";      //nome da base de dados
    private static final String LOGIN = "root";             //nome de um usuário do banco de dados
    private static final String SENHA = "";                 //sua senha de acesso
    private static String url = "";
    private static Connection conexao;
    
    /**
     * @param p objeto do tipo Cliente
     * @return <code>boolean</code> indicando true: sucesso , false:falha
     */
    public static boolean salvar(Cliente p)
    {
        
        boolean retorno = false;
        
        //Crio um comando PreparedStatement utilizando minha classe de conexão
        PreparedStatement comando = GerenciadorConexao.getPreparedStatement("INSERT INTO cliente (nome,CPF) VALUES(?,?);");
        
        try {
            comando.setString(1,p.getNome());
            comando.setString(2,p.getCPF());
            
            retorno = GerenciadorConexao.executarUpdate();
            
        } catch (SQLException ex) {
            retorno = false;
        }
        finally{
            GerenciadorConexao.fecharConexao();
        }
        
        return retorno;
    }
    
     /**
     * @param p objeto do tipo Cliente
     * @return <code>boolean</code> indicando true: sucesso , false:falha
     */
    public static boolean atualizar(Cliente p)
    {
        
        boolean retorno = false;
        
        PreparedStatement comando = GerenciadorConexao.getPreparedStatement("UPDATE cliente SET nome=?, CPF=? WHERE idcliente=?;");
        
        try {
            comando.setString(1,p.getNome());
            comando.setString(2,p.getCPF());
            comando.setInt(3,p.getId());
            retorno = GerenciadorConexao.executarUpdate();
            
        } catch (SQLException ex) {
            retorno = false;
        }
        finally{
            GerenciadorConexao.fecharConexao();
        }
        
        return retorno;
        
    }
     /**
     * @param pID objeto do tipo Cliente
     * @return <code>boolean</code> indicando true: sucesso , false:falha
     */
    public static boolean excluir(int pID)
    {
        boolean retorno = false;
        
        PreparedStatement comando = GerenciadorConexao.getPreparedStatement("DELETE FROM cliente WHERE idcliente=?;");
        
        try {
            comando.setInt(1,pID);
            retorno = GerenciadorConexao.executarUpdate();
            
        } catch(SQLException ex) {
            retorno = false;
        }
        finally{
            GerenciadorConexao.fecharConexao();
        }
        
        return retorno;
                       
    }
    
    /**
     * @return <code>ArrayList(Cliente)</code> retorna todos os clientes cadastrados no banco de dados;
     */
    public static ArrayList<Cliente> getClientes()
    {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
            
            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url,LOGIN,SENHA);
            
            Statement comando = conexao.createStatement();
            
            ResultSet rs = comando.executeQuery("SELECT * FROM cliente;");
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setId(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setCPF(rs.getString("CPF"));
                
                listaClientes.add(c);
            }
            rs.close();
            
        } catch (ClassNotFoundException ex) {
            listaClientes = null;
        } catch (SQLException ex) {
            listaClientes = null;
        }finally{
        
            try {
                if(conexao!=null)
                {
                    if(!conexao.isClosed())
                        conexao.close();
                    else
                    {
                        System.out.println("Conexão já fechada");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return listaClientes;
       
    }
    
    /**
     * Método para buscar clientes específicos da base de dados.
     * 
     * @param pNome String referemte ao nome do cliente.
     * @return <code>ArrayList(Cliente)</code> retorna o cliente buscado.
     */
    public static ArrayList<Cliente> getClientesPorNome(String pNome)
    {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
            
            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url,LOGIN,SENHA);
            
            //Statement comando = conexao.createStatement();
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM cliente WHERE Nome = ? ");
            comando.setString(1, pNome + "%");
            ResultSet rs = comando.executeQuery();
            //ResultSet rs = comando.executeQuery("SELECT * FROM cliente WHERE nome LIKE'%" + pNome + "%';");
            
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setId(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setCPF(rs.getString("CPF"));
                
                listaClientes.add(c);
            }
            rs.close();
            
        } catch (ClassNotFoundException ex) {
            listaClientes = null;
        } catch (SQLException ex) {
            listaClientes = null;
        }finally{
        
            try {
                if(!conexao.isClosed())
                    conexao.close();
                else
                {
                    System.out.println("Conexão já fechada");
                }
            } catch (SQLException ex) {
                listaClientes = null;
            }
        
        }
        
        return listaClientes;
       
        
    }
    
    /**
     * Método para buscar um cliente por ID.
     * 
     * @param pId inteiro referente ao ID do cliente
     * @return <code>ArrayList(Cliente)</code> retorna o cliente pelo ID
     */
    public static ArrayList<Cliente> getClientesPorId(int pId)
    {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try {
            
            //Tento carregar o driver para conectar ao MySQL
            Class.forName(DRIVER);
            url = "jdbc:mysql://" + SERVIDOR + ":3306/" + BASEDADOS;
            conexao = DriverManager.getConnection(url,LOGIN,SENHA);
            
            //Statement comando = conexao.createStatement();
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM cliente WHERE IdCliente = ? ");
            comando.setInt(1, pId);
            ResultSet rs = comando.executeQuery();
            //ResultSet rs = comando.executeQuery("SELECT * FROM cliente WHERE nome LIKE'%" + pNome + "%';");
            
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setId(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setCPF(rs.getString("CPF"));
                
                listaClientes.add(c);
            }
            rs.close();
            
        } catch (ClassNotFoundException ex) {
            listaClientes = null;
        } catch (SQLException ex) {
            listaClientes = null;
        }finally{
        
            try {
                if(!conexao.isClosed())
                    conexao.close();
                else
                {
                    System.out.println("Conexão já fechada");
                }
            } catch (SQLException ex) {
                listaClientes = null;
            }
        
        }
        
        return listaClientes;
       
        
    }
}
