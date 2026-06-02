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
        String sqlCliente = "INSERT INTO cliente (nome, cpf, endereco) VALUES (?, ?, ?)";
        String sqlPagamento = "INSERT INTO pagamento (forma_pagamento, valor_pago, data_pagamento, status) VALUES (?, ?, ?, ?)";
        String sqlVenda = "INSERT INTO venda (id_funcionario, data_venda, id_cliente, id_concessionaria, id_veiculo, valor_venda, id_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = FabricaConexao.getConexao()) {
            conn.setAutoCommit(false); // Inicia a Transação
            
            try {
                // 1. Inserir Cliente
                int idCliente;
                try (PreparedStatement stmtCli = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                    stmtCli.setString(1, cliente.getNome());
                    stmtCli.setString(2, cliente.getCpf());
                    stmtCli.setString(3, cliente.getEndereco());
                    stmtCli.executeUpdate();
                    ResultSet rs = stmtCli.getGeneratedKeys();
                    rs.next();
                    idCliente = rs.getInt(1);
                }

                // 2. Inserir Pagamento
                int idPagamento;
                try (PreparedStatement stmtPag = conn.prepareStatement(sqlPagamento, Statement.RETURN_GENERATED_KEYS)) {
                    stmtPag.setString(1, venda.getPagamento().getFormaPagamento());
                    stmtPag.setDouble(2, venda.getPagamento().getValorPago());
                    stmtPag.setDate(3, new java.sql.Date(venda.getPagamento().getDataPagamento().getTime()));
                    stmtPag.setString(4, venda.getPagamento().getStatus());
                    stmtPag.executeUpdate();
                    ResultSet rs = stmtPag.getGeneratedKeys();
                    rs.next();
                    idPagamento = rs.getInt(1);
                }

                // 3. Inserir Venda
                try (PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda)) {
                    stmtVenda.setInt(1, venda.getIdFuncionario());
                    stmtVenda.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
                    stmtVenda.setInt(3, idCliente);
                    stmtVenda.setInt(4, venda.getIdConcessionaria());
                    stmtVenda.setInt(5, venda.getIdVeiculo());
                    stmtVenda.setDouble(6, venda.getValorVenda());
                    stmtVenda.setInt(7, idPagamento);
                    stmtVenda.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Erro na transação: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public int contarVendasPorFuncionario(int idFuncionario) {
        String sql = "SELECT COUNT(*) AS total FROM venda WHERE id_funcionario = ?";
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) { return 0; }
    }

    public int contarVendasPorLoja(int idConcessionaria) {
        String sql = "SELECT COUNT(*) AS total FROM venda WHERE id_concessionaria = ?";
        try (Connection conn = FabricaConexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConcessionaria);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) { return 0; }
    }
}