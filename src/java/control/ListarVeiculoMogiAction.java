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
import model.Veiculo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarVeiculoMogiAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        
        // Busca a lista de veículos da loja 2 (Mogi das Cruzes)
        List<Veiculo> lista = veiculoDAO.listarPorConcessionaria(2);
        
        // Envia os dados para o JSP
        request.setAttribute("listaVeiculos", lista);
        request.setAttribute("nomeLoja", "Mogi das Cruzes");
        
        // Retorna o nome da página que o Front Controller deve exibir
        return "pagina2.jsp";
    }
}
