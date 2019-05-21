package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.util.ArrayList;

/**
 * 
 * @author lojademoveis.com.br
 */

public class ClienteController {

    /**
     * Método para salvar os clientes na memória ou na base de dados.
     * 
     * @param pNome String nome do cliente
     * @param pCPF String CPF do cliente
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Salvar(String pNome, String pCPF) {
        //Salvo na memória
        Cliente p = new Cliente(pNome, pCPF);
        return ClienteDAO.salvar(p);
    }

    /**
     * Método para excluir um determinado cliente.
     * 
     * @param indice inteiro com a localização da linha que contém o cliente a ser excluído
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Excluir(int indice) {
        return ClienteDAO.excluir(indice);
    }

    /**
     * Método para atualizar determinado cliente da base de dados.
     * 
     * @param pId inteiro ID do cliente
     * @param pNome String nome do cliente
     * @param pCPF String CPF do cliente
     * @return <code>boolean</code> true: sucesso, false: falho
     */
    public static boolean Atualizar(int pId, String pNome, String pCPF) {
        Cliente p = new Cliente(pId, pNome, pCPF);
        return ClienteDAO.atualizar(p);

    }

    /**
     * Transforma uma lista de objetos Cliente em uma lista de Strings
     *
     * @return lista de string
     */
    public static ArrayList<String[]> getClientes() {
        ArrayList<Cliente> clientes = ClienteDAO.getClientes();
        ArrayList<String[]> listaClientes = new ArrayList<>();

        for (int i = 0; i < clientes.size(); i++) {

            listaClientes.add(new String[]{String.valueOf(clientes.get(i).getId()), clientes.get(i).getNome(), clientes.get(i).getCPF()});

        }

        return listaClientes;

    }

}
