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
import model.Veiculo;
import org.junit.Assert;
import org.junit.Test;

public class VeiculoDecoratorTest {
    
    @Test
    public void testVeiculoComBlindagemEBancos() {
        System.out.println("=== EXECUTANDO TESTE DO DECORATOR ===");
        try {
            IVeiculo meuCarro = Veiculo.getBuilder()
                    .comMarca("Chevrolet")
                    .comModelo("Equinox")
                    .custando(120000.00)
                    .comDescricao("Chevrolet Equinox 2.0")
                    .constroi();
            
            System.out.println("1. Carro Base Construido:");
            System.out.println("   Preco: R$ " + meuCarro.getPreco());
            System.out.println("   Descricao: " + meuCarro.getDescricao());
            
            // 2. Aplica o Decorator de Bancos de Couro (+ 3500)
            meuCarro = new BancosDeCouroDecorator(meuCarro);
            System.out.println("2. Apos Decorator de Bancos de Couro (+3500):");
            System.out.println("   Preco: R$ " + meuCarro.getPreco());
            System.out.println("   Descricao: " + meuCarro.getDescricao());
            
            // 3. Aplica o Decorator de Blindagem (+ 45000)
            meuCarro = new BlindagemDecorator(meuCarro);
            System.out.println("3. Apos Decorator de Blindagem (+45000):");
            System.out.println("   Preco: R$ " + meuCarro.getPreco());
            System.out.println("   Descricao: " + meuCarro.getDescricao());
            
            double precoEsperado = 120000.00 + 3500.00 + 45000.00; // 168500.00
            String descricaoEsperada = "Chevrolet Equinox 2.0 + Bancos de Couro Premium + Blindagem Nível III-A";
            
            System.out.println("4. Comparando valores esperados...");
            System.out.println("   Esperado Preco: " + precoEsperado + " | Obtido: " + meuCarro.getPreco());
            System.out.println("   Esperada Descricao: '" + descricaoEsperada + "'");
            System.out.println("   Obtida Descricao:   '" + meuCarro.getDescricao() + "'");
            
            Assert.assertEquals(precoEsperado, meuCarro.getPreco(), 0.01);
            Assert.assertEquals(descricaoEsperada, meuCarro.getDescricao());
            System.out.println("=== TESTE PASSOU COM SUCESSO! ===");
        } catch (Throwable t) {
            System.out.println("!!! ERRO DETECTADO NO TESTE !!!");
            System.out.println("Mensagem: " + t.getMessage());
            t.printStackTrace(System.out);
            throw t;
        }
    }
}