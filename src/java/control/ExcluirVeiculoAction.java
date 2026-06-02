/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Pedro
 */

import dao.VeiculoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcluirVeiculoAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idVeiculo = Integer.parseInt(request.getParameter("id"));
        
        VeiculoDAO dao = new VeiculoDAO();
        dao.excluir(idVeiculo);
        
        // Atualiza a lista e volta para a página de gestão
        request.setAttribute("listaVeiculos", dao.listarTodos());
        return "pagina4.jsp";
    }
}