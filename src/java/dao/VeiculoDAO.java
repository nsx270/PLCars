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

public class VeiculoDAO implements IVeiculoDAO {

    // Método para inserir um novo veículo
    public boolean inserir(Veiculo veiculo, int idConcessionaria) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("INSERT INTO veiculo (marca, modelo, ano_fabricacao, ano_modelo, quilometragem, cor, combustivel, placa, preco, descricao, imagem_placeholder, id_concessionaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAnoFabricacao());
            comando.setInt(4, veiculo.getAnoModelo());
            comando.setDouble(5, veiculo.getQuilometragem());
            comando.setString(6, veiculo.getCor());
            comando.setString(7, veiculo.getCombustivel());
            comando.setString(8, veiculo.getPlaca());
            comando.setDouble(9, veiculo.getPreco());
            comando.setString(10, veiculo.getDescricao());
            comando.setString(11, veiculo.getImagemPlaceholder());
            comando.setInt(12, idConcessionaria);
            
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
            return false;
        }
    }

    // Listar veículos de uma loja específica
    public List<Veiculo> listarPorConcessionaria(int idConcessionaria) {
        List<Veiculo> lista = new ArrayList<>();
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT * FROM veiculo WHERE id_concessionaria = ?")) {
            
            comando.setInt(1, idConcessionaria);
            ResultSet rs = comando.executeQuery();
            
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
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT * FROM veiculo");
             ResultSet rs = comando.executeQuery()) {
            
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
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT status FROM veiculo WHERE id = ?")) {
            
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();
            
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
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("SELECT * FROM veiculo WHERE id = ?")) {
            
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();
            
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
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("UPDATE veiculo SET marca=?, modelo=?, ano_fabricacao=?, ano_modelo=?, quilometragem=?, cor=?, combustivel=?, placa=?, preco=?, descricao=?, imagem_placeholder=?, id_concessionaria=? WHERE id=?")) {
            
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAnoFabricacao());
            comando.setInt(4, veiculo.getAnoModelo());
            comando.setDouble(5, veiculo.getQuilometragem());
            comando.setString(6, veiculo.getCor());
            comando.setString(7, veiculo.getCombustivel());
            comando.setString(8, veiculo.getPlaca());
            comando.setDouble(9, veiculo.getPreco());
            comando.setString(10, veiculo.getDescricao());
            comando.setString(11, veiculo.getImagemPlaceholder());
            comando.setInt(12, idConcessionaria);
            comando.setInt(13, veiculo.getId());
            
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    // Excluir anúncio
    public boolean excluir(int id) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("DELETE FROM veiculo WHERE id=?")) {
            
            comando.setInt(1, id);
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir veículo: " + e.getMessage());
            return false;
        }
    }
    
    //Marcar veículo como vendido
    public void marcarComoVendido(int id) {
        try (Connection con = FabricaConexao.getConexao(); 
             PreparedStatement comando = con.prepareStatement("UPDATE veiculo SET status = 'VENDIDO' WHERE id = ?")) {
            comando.setInt(1, id);
            comando.executeUpdate();
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