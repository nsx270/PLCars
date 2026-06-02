package dao;

import model.Cliente;
import model.Venda;

public interface IVendaDAO {
    boolean registrarVendaCompleta(Venda venda, Cliente cliente);
    int contarVendasPorFuncionario(int idFuncionario);
    int contarVendasPorLoja(int idConcessionaria);
}
