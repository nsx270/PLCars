/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pedro
 */

public class Funcionario {
    private int id;
    private String nome;
    private String funcao;
    private String senha;
    private double comissaoVendas;

    public Funcionario() {}

    // Construtor atualizado recebendo a senha (String)
    public Funcionario(int id, String nome, String funcao, String senha, double comissaoVendas) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.senha = senha;
        this.comissaoVendas = comissaoVendas;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }
    
    // Getter e Setter para a senha
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public double getComissaoVendas() { return comissaoVendas; }
    public void setComissaoVendas(double comissaoVendas) { this.comissaoVendas = comissaoVendas; }
}