package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lojademoveis.com.br
 */
public class GerenciadorConexao {

    public static String status = "Não conectado";
    public static String driverName = "com.mysql.jdbc.Driver";  //A partir da versao 8.0, mudou para com.mysql.cj.jdbc.Driver (Connector/J)                   
    public static String serverName = "localhost";              //servidor de banco de dados
    public static String mydatabase = "lojademoveis";                //nome da base de dados
    public static String username = "root";                     //nome de um usuário de seu BD      
    public static String password = "";                         //sua senha de acesso

    public static String url = "jdbc:mysql://localhost:3306/lojademoveis";

    public static Connection conexao;
    public static PreparedStatement comando;
    //url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

    public GerenciadorConexao() {

    }

    public static Connection getConexao() {

        if (conexao == null) {
            try {

                //Carrega a classe responsável pelo driver
                Class.forName(driverName);
                conexao = DriverManager.getConnection(url, username, password);

                if (conexao != null) {

                    status = ("Conexão realizada com sucesso!");

                } else {

                    status = ("Não foi possivel realizar a conexão");

                }

            } catch (ClassNotFoundException e) {  //Driver não encontrado

                System.out.println("O driver expecificado nao foi encontrado.");

                return null;

            } catch (SQLException e) {  //Erro ao estabelecer a conexão (Ex: login ou senha errados)

                //Outra falha de conexão
                System.out.println(e.getMessage());
                return null;

            }

        } else {
            try {
                //Se a conexão estiver fechada, reabro a conexão
                if (conexao.isClosed()) {
                    conexao = DriverManager.getConnection(url, username, password);
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return conexao;
    }

    public static PreparedStatement getPreparedStatement(String sql) {

        if (comando == null) {
            try {
                comando = GerenciadorConexao.getConexao().prepareStatement(sql);
            } catch (SQLException ex) {
                return null;
            }
        }

        return comando;

    }

    public static boolean executarUpdate() {
        boolean retorno = false;

        if (comando != null) {
            int linhasAfetadas = 0;

            try {
                linhasAfetadas = comando.executeUpdate();

                if (linhasAfetadas > 0) {
                    retorno = true;
                } else {
                    retorno = false;
                }
                comando = null;

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                retorno = false;
            }
        }

        return retorno;
    }

    public static String getStatusConexao() {
        return status;
    }

    public static boolean fecharConexao() {

        try {
            GerenciadorConexao.getConexao().close();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

}
