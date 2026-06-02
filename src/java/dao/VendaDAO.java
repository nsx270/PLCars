/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Cliente;
import model.Venda;
import util.FabricaConexao;

public class VendaDAO implements IVendaDAO {

    public boolean registrarVendaCompleta(Venda venda, Cliente cliente) {
        try (Connection con = FabricaConexao.getConexao()) {
            con.setAutoCommit(false); // Inicia a Transação
            
            try {
                // 1. Inserir Cliente
                int idCliente;
                try (PreparedStatement comandoCli = con.prepareStatement("INSERT INTO cliente (nome, cpf, endereco) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                    comandoCli.setString(1, cliente.getNome());
                    comandoCli.setString(2, cliente.getCpf());
                    comandoCli.setString(3, cliente.getEndereco());
                    comandoCli.executeUpdate();
                    ResultSet rs = comandoCli.getGeneratedKeys();
                    rs.next();
                    idCliente = rs.getInt(1);
                }

                // 2. Inserir Pagamento
                int idPagamento;
                try (PreparedStatement comandoPag = con.prepareStatement("INSERT INTO pagamento (forma_pagamento, valor_pago, data_pagamento, status) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                    comandoPag.setString(1, venda.getPagamento().getFormaPagamento());
                    comandoPag.setDouble(2, venda.getPagamento().getValorPago());
                    comandoPag.setDate(3, new java.sql.Date(venda.getPagamento().getDataPagamento().getTime()));
                    comandoPag.setString(4, venda.getPagamento().getStatus());
                    comandoPag.executeUpdate();
                    ResultSet rs = comandoPag.getGeneratedKeys();
                    rs.next();
                    idPagamento = rs.getInt(1);
                }

                // 3. Inserir Venda
                try (PreparedStatement comandoVenda = con.prepareStatement("INSERT INTO venda (id_funcionario, data_venda, id_cliente, id_concessionaria, id_veiculo, valor_venda, id_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                    comandoVenda.setInt(1, venda.getIdFuncionario());
                    comandoVenda.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
                    comandoVenda.setInt(3, idCliente);
                    comandoVenda.setInt(4, venda.getIdConcessionaria());
                    comandoVenda.setInt(5, venda.getIdVeiculo());
                    comandoVenda.setDouble(6, venda.getValorVenda());
                    comandoVenda.setInt(7, idPagamento);
                    comandoVenda.executeUpdate();
                }

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                System.err.println("Erro na transação: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public int contarVendasPorFuncionario(int idFuncionario) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT COUNT(*) AS total FROM venda WHERE id_funcionario = ?")) {
            comando.setInt(1, idFuncionario);
            ResultSet rs = comando.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) { return 0; }
    }

    public int contarVendasPorLoja(int idConcessionaria) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT COUNT(*) AS total FROM venda WHERE id_concessionaria = ?")) {
            comando.setInt(1, idConcessionaria);
            ResultSet rs = comando.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) { return 0; }
    }
}