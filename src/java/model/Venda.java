/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pedro
 */

import java.util.Date;

public class Venda {
    private int id;
    private Date dataVenda;
    private double valorVenda;
    
    // Relacionamento 1:1
    private Pagamento pagamento; 

    // Associações
    private int idFuncionario;
    private int idCliente;
    private int idVeiculo;
    private int idConcessionaria;

    public Venda() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDataVenda() { return dataVenda; }
    public void setDataVenda(Date dataVenda) { this.dataVenda = dataVenda; }
    public double getValorVenda() { return valorVenda; }
    public void setValorVenda(double valorVenda) { this.valorVenda = valorVenda; }
    
    // Getters e Setters do Relacionamento 1:1
    public Pagamento getPagamento() { return pagamento; }
    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }

    // Getters e Setters das chaves
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }
    public int getIdConcessionaria() { return idConcessionaria; }
    public void setIdConcessionaria(int idConcessionaria) { this.idConcessionaria = idConcessionaria; }
}