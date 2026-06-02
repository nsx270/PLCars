package dao;

import model.Funcionario;

public interface IFuncionarioDAO {
    Funcionario fazerLogin(int id, String senha);
    boolean inserir(Funcionario funcionario);
    boolean adicionarComissao(int idFuncionario, double valorVenda);
}
