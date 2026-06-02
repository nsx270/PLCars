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

// A URL "/controller.do" é o padrão ensinado no seu material para o Front Controller
@WebServlet(name = "ServletController", urlPatterns = {"/controller.do"})
public class ServletController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // 1. Recupera a ação do usuário (ex: "CadastrarVeiculo", "ListarVeiculo")
            String paramAction = request.getParameter("acao");
            
            // Se não vier nenhuma ação, manda listar por padrão
            if (paramAction == null || paramAction.isEmpty()) {
                paramAction = "ListarVeiculoTodos";
            }

            // 2. Monta o nome completo da classe baseado no parâmetro
            // Como estamos no pacote "control", ele vai procurar "control.CadastrarVeiculoAction", etc.
            String nomeDaClasse = "control." + paramAction + "Action";

            // 3. Cria uma classe de representação usando Reflection (Design Pattern: Factory Method dinâmico)
            Class classeAction = Class.forName(nomeDaClasse);

            // 4. Instancia a classe e faz o cast para a interface ICommand
            ICommand commandAction = (ICommand) classeAction.getDeclaredConstructor().newInstance();

            // 5. Executa a Action e recebe qual página JSP deve ser carregada a seguir
            String pageDispatcher = commandAction.executar(request, response);

            // 6. Redireciona o usuário para a página correta
            RequestDispatcher rd = request.getRequestDispatcher(pageDispatcher);
            rd.forward(request, response);

        } catch (Exception e) {
            System.err.println("Erro no Front Controller: " + e.getMessage());
            e.printStackTrace(); // Imprime o erro no console do NetBeans para ajudar a debugar
            
            // Em caso de erro, manda para a página inicial
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
