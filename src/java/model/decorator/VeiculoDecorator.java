/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.decorator;

/**
 *
 * @author Pedro
 */

import model.decorator.IVeiculo;

public abstract class VeiculoDecorator implements IVeiculo {
    
    protected IVeiculo veiculoDecorado;

    public VeiculoDecorator(IVeiculo veiculoDecorado) {
        this.veiculoDecorado = veiculoDecorado;
    }

    @Override
    public double getPreco() {
        return veiculoDecorado.getPreco();
    }

    @Override
    public String getDescricao() {
        return veiculoDecorado.getDescricao();
    }
}
