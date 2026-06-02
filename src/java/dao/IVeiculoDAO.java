package dao;

import java.util.List;
import model.Veiculo;

public interface IVeiculoDAO {
    boolean inserir(Veiculo veiculo, int idConcessionaria);
    List<Veiculo> listarPorConcessionaria(int idConcessionaria);
    List<Veiculo> listarTodos();
    boolean isDisponivel(int id);
    Veiculo buscarPorId(int id);
    boolean atualizar(Veiculo veiculo, int idConcessionaria);
    boolean excluir(int id);
    void marcarComoVendido(int id);
}
