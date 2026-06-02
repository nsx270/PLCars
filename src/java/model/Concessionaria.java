/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pedro
 */

import java.util.ArrayList;
import java.util.List;

public class Concessionaria {
    private int id;
    private String nome;
    private String endereco;
    private String cnpj;
    
    // Relacionamento 1:N (Uma concessionária possui MUITOS veículos)
    private List<Veiculo> estoqueVeiculos;

    public Concessionaria() {
        // Inicializa a lista vazia para evitar NullPointerException
        this.estoqueVeiculos = new ArrayList<>(); 
    }

    public Concessionaria(int id, String nome, String endereco, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.estoqueVeiculos = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    // Getters e Setters do Relacionamento 1:N
    public List<Veiculo> getEstoqueVeiculos() { return estoqueVeiculos; }
    public void setEstoqueVeiculos(List<Veiculo> estoqueVeiculos) { this.estoqueVeiculos = estoqueVeiculos; }
    
    // Método auxiliar bacana para adicionar 1 carro por vez na lista
    public void adicionarVeiculoNoEstoque(Veiculo veiculo) {
        this.estoqueVeiculos.add(veiculo);
    }
}