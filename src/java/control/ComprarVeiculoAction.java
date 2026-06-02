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
import dao.VeiculoDAO;
import dao.VendaDAO;
import model.Cliente;
import model.Pagamento;
import model.Venda;
import model.Veiculo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComprarVeiculoAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Dados do Veículo e Venda
        int idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
        int idConcessionaria = Integer.parseInt(request.getParameter("idConcessionaria"));
        
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        //Verifica se o carro já foi vendido antes de fazer qualquer coisa
        if (!veiculoDAO.isDisponivel(idVeiculo)) {
            request.setAttribute("mensagem", "Este veículo já foi vendido por outro funcionário ou não está mais disponível.");
            request.setAttribute("listaVeiculos", veiculoDAO.listarPorConcessionaria(idConcessionaria));
            request.setAttribute("nomeLoja", idConcessionaria == 1 ? "Jacareí" : "Mogi das Cruzes");
            return "pagina2.jsp";
        }

        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
        String formaPagamento = request.getParameter("formaPagamento");

        String nomeCli = request.getParameter("nomeCliente");
        String cpfCli = request.getParameter("cpfCliente");
        String endCli = request.getParameter("enderecoCliente");

        // 1. Recupera os opcionais selecionados na tela (Design Pattern: Decorator)
        String opcionalCouro = request.getParameter("opcionalCouro");
        String opcionalBlindagem = request.getParameter("opcionalBlindagem");

        // 2. Busca o veículo base e inicia a decoração
        Veiculo veiculoBase = veiculoDAO.buscarPorId(idVeiculo);
        model.decorator.IVeiculo carroDecorado = veiculoBase;

        // 3. Aplica os decorators dinamicamente (nenhum, um ou ambos)
        if ("sim".equals(opcionalCouro)) {
            carroDecorado = new model.decorator.BancosDeCouroDecorator(carroDecorado);
        }
        if ("sim".equals(opcionalBlindagem)) {
            carroDecorado = new model.decorator.BlindagemDecorator(carroDecorado);
        }

        // 4. Obtém o preço final calculado dinamicamente pelo padrão Decorator
        double valorFinal = carroDecorado.getPreco();

        Cliente cliente = new Cliente(0, nomeCli, cpfCli, endCli);
        Pagamento pagamento = new Pagamento(0, formaPagamento, valorFinal, new java.util.Date(), "Aprovado");
        
        Venda venda = new Venda();
        venda.setIdFuncionario(idFuncionario);
        venda.setDataVenda(new java.util.Date());
        venda.setIdConcessionaria(idConcessionaria);
        venda.setIdVeiculo(idVeiculo);
        venda.setValorVenda(valorFinal);
        venda.setPagamento(pagamento);

        VendaDAO vendaDAO = new VendaDAO();
        boolean sucesso = vendaDAO.registrarVendaCompleta(venda, cliente);

        if (sucesso) {
            new FuncionarioDAO().adicionarComissao(idFuncionario, valorFinal);
            veiculoDAO.marcarComoVendido(idVeiculo);
            
            // Monta mensagem personalizada exibindo os opcionais inclusos
            String opcionaisAdicionados = "";
            if ("sim".equals(opcionalCouro)) opcionaisAdicionados += "[Bancos de Couro] ";
            if ("sim".equals(opcionalBlindagem)) opcionaisAdicionados += "[Blindagem]";
            if (opcionaisAdicionados.isEmpty()) opcionaisAdicionados = "[Nenhum opcional]";

            request.setAttribute("mensagem", "✅ Venda confirmada! Valor final recalculado com opcionais: R$ " + 
                    String.format("%,.2f", valorFinal) + " \nOpcionais: " + opcionaisAdicionados);
        } else {
            request.setAttribute("mensagem", "❌ Erro ao registrar a venda no banco de dados.");
        }

        request.setAttribute("listaVeiculos", veiculoDAO.listarPorConcessionaria(idConcessionaria));
        request.setAttribute("nomeLoja", idConcessionaria == 1 ? "Jacareí" : "Mogi das Cruzes");
        
        return "pagina2.jsp";
    }
}