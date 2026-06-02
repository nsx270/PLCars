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

public class EditarVeiculoAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
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

        // Usamos o Builder novamente para montar o objeto atualizado
        Veiculo veiculoEditado = Veiculo.getBuilder()
                .comId(id)
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

        VeiculoDAO dao = new VeiculoDAO();
        dao.atualizar(veiculoEditado, idConcessionaria);

        // Atualiza a lista e volta para a página de gestão
        request.setAttribute("listaVeiculos", dao.listarTodos());
        return "pagina4.jsp";
    }
}
