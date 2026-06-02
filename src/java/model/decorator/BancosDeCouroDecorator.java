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

public class BancosDeCouroDecorator extends VeiculoDecorator {

    public BancosDeCouroDecorator(IVeiculo veiculoDecorado) {
        super(veiculoDecorado);
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 3500.00; // Acrescenta o valor dos bancos
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Bancos de Couro Premium";
    }
}
