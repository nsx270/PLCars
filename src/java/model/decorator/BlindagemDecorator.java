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

public class BlindagemDecorator extends VeiculoDecorator {

    public BlindagemDecorator(IVeiculo veiculoDecorado) {
        super(veiculoDecorado);
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 45000.00; // Acrescenta o valor da blindagem
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Blindagem Nível III-A";
    }
}
