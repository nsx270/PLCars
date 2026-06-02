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
import dao.VendaDAO;
import model.Funcionario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFuncionarioAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Pega o ID e a Senha que o usuário digitou
        int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
        String senha = request.getParameter("senha"); 
        
        FuncionarioDAO funcDao = new FuncionarioDAO();
        
        //Usamos o novo método fazerLogin passando o ID e a Senha
        Funcionario funcionarioLogado = funcDao.fazerLogin(idFuncionario, senha);

        if (funcionarioLogado != null) {
            VendaDAO vendaDao = new VendaDAO();
            int totalVendasJacarei = vendaDao.contarVendasPorLoja(1);
            int totalVendasMogi = vendaDao.contarVendasPorLoja(2);
            int vendasDesteFuncionario = vendaDao.contarVendasPorFuncionario(idFuncionario);
            
            request.setAttribute("funcionario", funcionarioLogado);
            request.setAttribute("vendasJacarei", totalVendasJacarei);
            request.setAttribute("vendasMogi", totalVendasMogi);
            request.setAttribute("minhasVendas", vendasDesteFuncionario);
            
            return "pagina3.jsp";
        } else {
            request.setAttribute("erro", "ID ou Senha incorretos!");
            return "pagina3.jsp";
        }
    }
}