package Model;

import java.math.BigInteger;

/**
 * 
 * @author lojademoveis.com.br
 */

public class Cliente {

    private int id;
    private String nome;
    private String cpf;

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public Cliente(String pNome, String pCPF) {
        this.nome = pNome;
        this.cpf = pCPF;
    }

    public Cliente(int pId, String pNome, String pCPF) {
        this.id = pId;
        this.nome = pNome;
        this.cpf = pCPF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String pNome) {
        this.nome = pNome;
    }

}
