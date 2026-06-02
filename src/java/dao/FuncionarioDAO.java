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
import model.Funcionario;
import util.FabricaConexao;

public class FuncionarioDAO implements IFuncionarioDAO {

    // 1. Novo método de login que verifica ID e Senha
    public Funcionario fazerLogin(int id, String senha) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT * FROM funcionario WHERE id = ? AND senha = ?")) {
            
            comando.setInt(1, id);
            comando.setString(2, senha);
            ResultSet rs = comando.executeQuery();
            
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setFuncao(rs.getString("funcao"));
                f.setSenha(rs.getString("senha"));
                f.setComissaoVendas(rs.getDouble("comissao_vendas"));
                return f;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }

    // 2. Inserir funcionário
    public boolean inserir(Funcionario funcionario) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("INSERT INTO funcionario (nome, funcao, senha, comissao_vendas) VALUES (?, ?, ?, ?)")) {
            
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getFuncao());
            comando.setString(3, funcionario.getSenha()); // Inserindo a senha
            comando.setDouble(4, funcionario.getComissaoVendas());
            
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
            return false;
        }
    }

    // 3. Atualiza a comissão do funcionário
    public boolean adicionarComissao(int idFuncionario, double valorVenda) {
        double comissao = valorVenda * 0.05; // 5% do valor
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("UPDATE funcionario SET comissao_vendas = comissao_vendas + ? WHERE id = ?")) {
            
            comando.setDouble(1, comissao);
            comando.setInt(2, idFuncionario);
            
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar comissão: " + e.getMessage());
            return false;
        }
    }
}