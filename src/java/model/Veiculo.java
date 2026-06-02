/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.decorator.IVeiculo;

/**
 *
 * @author Pedro
 */

public class Veiculo implements IVeiculo {
    
    private int id;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double quilometragem;
    private String cor;
    private String combustivel;
    private String placa;
    private double preco;
    private String descricao;
    private String imagemPlaceholder;
    private int idConcessionaria;

    private Veiculo() {}

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(int anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public int getAnoModelo() { return anoModelo; }
    public void setAnoModelo(int anoModelo) { this.anoModelo = anoModelo; }
    public double getQuilometragem() { return quilometragem; }
    public void setQuilometragem(double quilometragem) { this.quilometragem = quilometragem; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getImagemPlaceholder() { return imagemPlaceholder; }
    public void setImagemPlaceholder(String imagemPlaceholder) { this.imagemPlaceholder = imagemPlaceholder; }
    public int getIdConcessionaria() { return idConcessionaria; }
    public void setIdConcessionaria(int idConcessionaria) { this.idConcessionaria = idConcessionaria; }

    public static VeiculoBuilder getBuilder() {
        return new VeiculoBuilder();
    }

    public static class VeiculoBuilder {
        private Veiculo veiculo;

        public VeiculoBuilder() { this.veiculo = new Veiculo(); }

        public VeiculoBuilder comId(int id) { this.veiculo.id = id; return this; }
        public VeiculoBuilder comMarca(String marca) { this.veiculo.marca = marca; return this; }
        public VeiculoBuilder comModelo(String modelo) { this.veiculo.modelo = modelo; return this; }
        public VeiculoBuilder doAno(int anoFabricacao, int anoModelo) {
            this.veiculo.anoFabricacao = anoFabricacao; this.veiculo.anoModelo = anoModelo; return this;
        }
        public VeiculoBuilder comQuilometragem(double km) { this.veiculo.quilometragem = km; return this; }
        public VeiculoBuilder daCor(String cor) { this.veiculo.cor = cor; return this; }
        public VeiculoBuilder movidoA(String combustivel) { this.veiculo.combustivel = combustivel; return this; }
        public VeiculoBuilder comPlaca(String placa) { this.veiculo.placa = placa; return this; }
        public VeiculoBuilder custando(double preco) { this.veiculo.preco = preco; return this; }
        public VeiculoBuilder comDescricao(String descricao) { this.veiculo.descricao = descricao; return this; }
        public VeiculoBuilder comImagem(String imagem) { this.veiculo.imagemPlaceholder = imagem; return this; }
        
        // Método novo no Builder para associar a loja
        public VeiculoBuilder daLoja(int idConcessionaria) { 
            this.veiculo.idConcessionaria = idConcessionaria; return this; 
        }

        public Veiculo constroi() { return this.veiculo; }
    }
}