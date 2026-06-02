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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarVeiculoAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // Coleta os dados do formulário
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        int anoFab = Integer.parseInt(request.getParameter("anoFabricacao"));
        int anoMod = Integer.parseInt(request.getParameter("anoModelo"));
        double km = Double.parseDouble(request.getParameter("quilometragem"));
        String cor = request.getParameter("cor");
        String combustivel = request.getParameter("combustivel");
        String placa = request.getParameter("placa");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String descricao = request.getParameter("descricao");
        String imagem = request.getParameter("imagemPlaceholder");
        int idConcessionaria = Integer.parseInt(request.getParameter("idConcessionaria"));

        // Cria o objeto Veiculo usando o Builder
        Veiculo novoVeiculo = Veiculo.getBuilder()
                .comMarca(marca)
                .comModelo(modelo)
                .doAno(anoFab, anoMod)
                .comQuilometragem(km)
                .daCor(cor)
                .movidoA(combustivel)
                .comPlaca(placa)
                .custando(preco)
                .comDescricao(descricao)
                .comImagem(imagem)
                .daLoja(idConcessionaria)
                .constroi();

        // Salva no banco de dados
        VeiculoDAO dao = new VeiculoDAO();
        dao.inserir(novoVeiculo, idConcessionaria);

        // Busca a lista atualizada de todos os veículos
        request.setAttribute("listaVeiculos", dao.listarTodos());
        
        // Retorna para a página de gestão
        return "pagina4.jsp";
    }
}
