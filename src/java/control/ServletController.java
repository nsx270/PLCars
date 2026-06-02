/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Pedro
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Controlador Central (Front Controller)
@WebServlet(name = "ServletController", urlPatterns = {"/controller.do"})
public class ServletController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Recupera o parâmetro da ação do usuário
            String paramAction = request.getParameter("acao");
            
            // Define a ação padrão caso esteja vazia
            if (paramAction == null || paramAction.isEmpty()) {
                paramAction = "ListarVeiculoTodos";
            }

            // Define o nome completo da classe correspondente
            String nomeDaClasse = "control." + paramAction + "Action";

            // Carrega a classe dinamicamente via Reflection
            Class classeAction = Class.forName(nomeDaClasse);

            // Cria a instância do comando
            ICommand commandAction = (ICommand) classeAction.getDeclaredConstructor().newInstance();

            // Executa a ação e recupera a página de destino
            String pageDispatcher = commandAction.executar(request, response);

            // Redireciona o usuário para a página de destino
            RequestDispatcher rd = request.getRequestDispatcher(pageDispatcher);
            rd.forward(request, response);

        } catch (Exception e) {
            System.err.println("Erro no Front Controller: " + e.getMessage());
            e.printStackTrace();
            
            // Redireciona para a página inicial em caso de erro
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("erro", e.getMessage());
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
