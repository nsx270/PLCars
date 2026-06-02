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
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import util.FabricaConexao;

public class VeiculoDAO {

    // Método para inserir um novo veículo
    public boolean inserir(Veiculo veiculo, int idConcessionaria) {
        String sql = "INSERT INTO veiculo (marca, modelo, ano_fabricacao, ano_modelo, quilometragem, cor, combustivel, placa, preco, descricao, imagem_placeholder, id_concessionaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setInt(4, veiculo.getAnoModelo());
            stmt.setDouble(5, veiculo.getQuilometragem());
            stmt.setString(6, veiculo.getCor());
            stmt.setString(7, veiculo.getCombustivel());
            stmt.setString(8, veiculo.getPlaca());
            stmt.setDouble(9, veiculo.getPreco());
            stmt.setString(10, veiculo.getDescricao());
            stmt.setString(11, veiculo.getImagemPlaceholder());
            stmt.setInt(12, idConcessionaria);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
            return false;
        }
    }

    // Listar veículos de uma loja específica
    public List<Veiculo> listarPorConcessionaria(int idConcessionaria) {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE id_concessionaria = ?";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idConcessionaria);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                lista.add(extrairVeiculoDoResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos da loja: " + e.getMessage());
        }
        return lista;
    }

    // Listar TODOS os veículos
    public List<Veiculo> listarTodos() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(extrairVeiculoDoResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os veículos: " + e.getMessage());
        }
        return lista;
    }
    
    // Método para checar se o carro pode ser vendido
    public boolean isDisponivel(int id) {
        String sql = "SELECT status FROM veiculo WHERE id = ?";
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return "DISPONÍVEL".equalsIgnoreCase(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar disponibilidade: " + e.getMessage());
        }
        return false;
    }

    // Buscar apenas um veículo
    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extrairVeiculoDoResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo por ID: " + e.getMessage());
        }
        return null;
    }

    // Atualizar dados de um veículo existente
    public boolean atualizar(Veiculo veiculo, int idConcessionaria) {
        String sql = "UPDATE veiculo SET marca=?, modelo=?, ano_fabricacao=?, ano_modelo=?, quilometragem=?, cor=?, combustivel=?, placa=?, preco=?, descricao=?, imagem_placeholder=?, id_concessionaria=? WHERE id=?";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setInt(4, veiculo.getAnoModelo());
            stmt.setDouble(5, veiculo.getQuilometragem());
            stmt.setString(6, veiculo.getCor());
            stmt.setString(7, veiculo.getCombustivel());
            stmt.setString(8, veiculo.getPlaca());
            stmt.setDouble(9, veiculo.getPreco());
            stmt.setString(10, veiculo.getDescricao());
            stmt.setString(11, veiculo.getImagemPlaceholder());
            stmt.setInt(12, idConcessionaria);
            stmt.setInt(13, veiculo.getId());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    // Excluir anúncio
    public boolean excluir(int id) {
        String sql = "DELETE FROM veiculo WHERE id=?";
        
        try (Connection conn = FabricaConexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir veículo: " + e.getMessage());
            return false;
        }
    }
    
    //Marcar veículo como vendido
    public void marcarComoVendido(int id) {
    String sql = "UPDATE veiculo SET status = 'VENDIDO' WHERE id = ?";
    try (Connection conn = FabricaConexao.getConexao(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar status: " + e.getMessage());
    }
}
    private Veiculo extrairVeiculoDoResultSet(ResultSet rs) throws SQLException {
        return Veiculo.getBuilder()
                .comId(rs.getInt("id"))
                .comMarca(rs.getString("marca"))
                .comModelo(rs.getString("modelo"))
                .doAno(rs.getInt("ano_fabricacao"), rs.getInt("ano_modelo"))
                .comQuilometragem(rs.getDouble("quilometragem"))
                .daCor(rs.getString("cor"))
                .movidoA(rs.getString("combustivel"))
                .comPlaca(rs.getString("placa"))
                .custando(rs.getDouble("preco"))
                .comDescricao(rs.getString("descricao"))
                .comImagem(rs.getString("imagem_placeholder"))
                .daLoja(rs.getInt("id_concessionaria"))
                .constroi();
    }
}