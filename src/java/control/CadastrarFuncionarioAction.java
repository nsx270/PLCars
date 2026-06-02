/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Pedro
 */

import dao.FuncionarioDAO;
import model.Funcionario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarFuncionarioAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String nome = request.getParameter("nome");
        String funcao = request.getParameter("funcao");
        String senha = request.getParameter("senha");

        Funcionario novo = new Funcionario(0, nome, funcao, senha, 0.0);
      
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.inserir(novo);

        request.setAttribute("mensagem", "Funcionário cadastrado com sucesso! Use seu ID gerado no banco e sua senha para entrar.");
        
        return "pagina3.jsp"; 
    }
}