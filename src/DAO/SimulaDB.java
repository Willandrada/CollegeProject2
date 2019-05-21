package DAO;

import Model.*;
import java.util.ArrayList;

public class SimulaDB {

    private static SimulaDB mockdb;

    private ArrayList<Cliente> listaClientes; //Simulo a tabela clientes
    private ArrayList<Produto> listaProdutos; //Simulo a tabela Produtos
    private ArrayList<Pedido> listaPedidos;

    private SimulaDB() {

        listaClientes = new ArrayList<Cliente>();
        listaProdutos = new ArrayList<Produto>();
        listaPedidos = new ArrayList<Pedido>();
    }

    public static synchronized SimulaDB getInstance() {
        if (mockdb == null) {
            mockdb = new SimulaDB();
        }

        return mockdb;
    }

    public boolean SalvarCliente(Cliente c) {
        listaClientes.add(c);

        return true;
    }

    public ArrayList<Cliente> getClientes() {
        return this.listaClientes;
    }

    public boolean AtualizarCliente(Cliente p) {
        for (Cliente item : listaClientes) {
            if (item.getId() == p.getId()) {
                item.setNome(p.getNome());
                item.setCPF(p.getCPF());
            }
        }

        return true;
    }

    public boolean ExcluirCliente(int i) {
        listaClientes.remove(i);

        return true;
    }

    public boolean SalvarProduto(Produto p) {
        listaProdutos.add(p);

        return true;
    }

    public ArrayList<Produto> getProdutos() {
        return this.listaProdutos;
    }

    public boolean AtualizarProduto(Produto p) {
        for (Produto item : listaProdutos) {
            if (item.getId() == p.getId()) {
                item.setModelo(p.getModelo());
                item.setTipo(p.getTipo());
                item.setPreco(p.getPreco());
            }
        }

        return true;
    }

    public boolean ExcluirProduto(int i) {
        listaProdutos.remove(i);

        return true;
    }

    public boolean SalvarPedido(Pedido ped) {
        listaPedidos.add(ped);

        return true;
    }

  

    public ArrayList<Pedido> getPedidos() {
        return this.listaPedidos;
    }

//    
//    public boolean AtualizarProduto(Produto p)
//    {
//        for(Produto item: listaProdutos)
//        {
//            if(item.getIdProduto() == p.getIdProduto())
//            {
//                item.setProduto(p.getProduto());
//                item.setTipo(p.getTipo());
//                item.setPreco(p.getPreco());
//            }
//        }
//            
//        return true;
//    }
//    
//    public boolean ExcluirProduto(int i)
//    {
//        listaProdutos.remove(i);
//        
//        return true;
//    }
}
