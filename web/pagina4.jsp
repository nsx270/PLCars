<%-- 
    Document   : pagina4
    Created on : 30 de mar. de 2026, 21:18:18
    Author     : Pedro
--%>

<%@page import="java.util.List"%>
<%@page import="model.Veiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Veiculo> listaVeiculos = (List<Veiculo>) request.getAttribute("listaVeiculos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Gestão de Catálogo - LP Veículos</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
    <style>
        :root { --vermelho: #9e1010; --branco: #ffffff; --cinza: #f4f4f4; --escuro: #222222; }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Roboto', sans-serif; }
        body { background-color: var(--cinza); }

        .banner-interno { background: var(--escuro); height: 150px; display: flex; align-items: center; justify-content: center; }
        .banner-interno h1 { color: var(--branco); font-size: 2.5rem; }

        .container { max-width: 1200px; margin: 40px auto; padding: 0 20px; }
        .acoes-topo { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .voltar { color: var(--vermelho); text-decoration: none; font-weight: bold; }
        .btn { background: var(--vermelho); color: var(--branco); border: none; padding: 12px 25px; border-radius: 5px; font-weight: bold; cursor: pointer; font-size: 1rem; }
        .btn:hover { background: #7a0c0c; }
        .btn-excluir { background: #333; margin-top: 10px; width: 100%; }
        
        /* Grid de Veículos idêntica à página 2 */
        .grid-veiculos { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 25px; }
        .card-veiculo { background: var(--branco); border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        .img-veiculo { width: 100%; height: 190px; object-fit: cover; }
        .info-veiculo { padding: 15px; }
        .info-modelo { font-size: 1.2rem; font-weight: 700; color: var(--escuro); text-transform: uppercase;}
        .info-loja { font-size: 0.85rem; color: var(--vermelho); font-weight: bold; margin-bottom: 5px; }
        
        /* Modal de Inserir/Editar */
        .modal-bg { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.7); z-index: 1000; justify-content: center; align-items: center; }
        .modal-content { background: var(--branco); width: 95%; max-width: 900px; border-radius: 10px; padding: 30px; position: relative; max-height: 90vh; overflow-y: auto;}
        .fechar-modal { position: absolute; top: 15px; right: 20px; font-size: 1.5rem; cursor: pointer; color: #666; font-weight: bold; }
        
        .form-grid { display: grid; grid-template-columns: 1fr 1fr 1fr; gap: 15px; margin-top: 20px; }
        .form-group { display: flex; flex-direction: column; }
        .form-group label { font-size: 0.85rem; font-weight: bold; margin-bottom: 5px; }
        .form-group input, .form-group select { padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        .form-group.full { grid-column: span 3; }
    </style>
</head>
<body>

    <header class="banner-interno">
        <h1>Painel de Gestão de Anúncios</h1>
    </header>

    <main class="container">
        <div class="acoes-topo">
            <a href="pagina3.jsp" class="voltar">← Voltar para Dashboard</a>
            <button class="btn" onclick="abrirModalInserir()">+ INSERIR NOVO VEÍCULO</button>
        </div>

        <div class="grid-veiculos">
            <% if(listaVeiculos != null) { 
                for(Veiculo v : listaVeiculos) { 
                    String loja = v.getIdConcessionaria() == 1 ? "Loja Jacareí" : "Loja Mogi";
                    String imagem = (v.getImagemPlaceholder() != null && !v.getImagemPlaceholder().isEmpty()) ? v.getImagemPlaceholder() : "https://images.unsplash.com/photo-1550355291-bbee04a92027?auto=format&fit=crop&w=400&q=80";
            %>
            <div class="card-veiculo">
                <img src="<%= imagem %>" class="img-veiculo">
                <div class="info-veiculo">
                    <div class="info-loja"><%= loja %> (ID: <%= v.getId() %>)</div>
                    <div class="info-modelo"><%= v.getModelo() %></div>
                    <div style="font-size: 0.9rem; color: #666; margin: 5px 0;">R$ <%= String.format(new java.util.Locale("pt", "BR"), "%,.2f", v.getPreco()) %></div>
                    
                    <button class="btn" style="width: 100%; margin-top: 10px;" 
                            onclick="abrirModalEditar('<%= v.getId() %>', '<%= v.getMarca() %>', '<%= v.getModelo() %>', '<%= v.getAnoFabricacao() %>', '<%= v.getAnoModelo() %>', '<%= v.getQuilometragem() %>', '<%= v.getCor() %>', '<%= v.getCombustivel() %>', '<%= v.getPlaca() %>', '<%= v.getPreco() %>', '<%= v.getDescricao() %>', '<%= v.getImagemPlaceholder() %>', '<%= v.getIdConcessionaria() %>')">
                        EDITAR
                    </button>
                    
                    <form action="controller.do" method="POST" onsubmit="return confirm('Tem certeza que deseja excluir o anúncio deste <%= v.getModelo() %>?');">
                        <input type="hidden" name="acao" value="ExcluirVeiculo">
                        <input type="hidden" name="id" value="<%= v.getId() %>">
                        <button type="submit" class="btn btn-excluir">EXCLUIR</button>
                    </form>
                </div>
            </div>
            <% } } %>
        </div>
    </main>

    <div class="modal-bg" id="modalForm">
        <div class="modal-content">
            <span class="fechar-modal" onclick="fecharModal()">&times;</span>
            <h2 id="modal-titulo">Inserir Novo Veículo</h2>
            
            <form action="controller.do" method="POST">
                <input type="hidden" name="acao" id="formAcao">
                <input type="hidden" name="id" id="formId">

                <div class="form-grid">
                    <div class="form-group"><label>Marca</label><input type="text" name="marca" id="f_marca" required></div>
                    <div class="form-group"><label>Modelo</label><input type="text" name="modelo" id="f_modelo" required></div>
                    <div class="form-group">
                        <label>Loja de Destino</label>
                        <select name="idConcessionaria" id="f_loja" required>
                            <option value="1">Jacareí</option>
                            <option value="2">Mogi das Cruzes</option>
                        </select>
                    </div>

                    <div class="form-group"><label>Ano Fabricação</label><input type="number" name="anoFabricacao" id="f_anoFab" required></div>
                    <div class="form-group"><label>Ano Modelo</label><input type="number" name="anoModelo" id="f_anoMod" required></div>
                    <div class="form-group"><label>Quilometragem</label><input type="number" step="0.1" name="quilometragem" id="f_km" required></div>
                    
                    <div class="form-group"><label>Cor</label><input type="text" name="cor" id="f_cor" required></div>
                    <div class="form-group"><label>Combustível</label><input type="text" name="combustivel" id="f_comb" required></div>
                    <div class="form-group"><label>Placa</label><input type="text" name="placa" id="f_placa" required></div>
                    
                    <div class="form-group"><label>Preço (R$)</label><input type="number" step="0.01" name="preco" id="f_preco" required></div>
                    <div class="form-group" style="grid-column: span 2;"><label>URL da Imagem (Local ou Link)</label><input type="text" name="imagemPlaceholder" id="f_img" placeholder="Ex: img/onix.png"></div>
                    
                    <div class="form-group full">
                        <label>Descrição do Veículo</label>
                        <input type="text" name="descricao" id="f_desc" required>
                    </div>
                </div>

                <div style="text-align: right; margin-top: 20px;">
                    <button type="submit" class="btn">SALVAR DADOS</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        const modal = document.getElementById("modalForm");

        function limparFormulario() {
            document.getElementById("formId").value = "";
            document.getElementById("f_marca").value = "";
            document.getElementById("f_modelo").value = "";
            document.getElementById("f_anoFab").value = "";
            document.getElementById("f_anoMod").value = "";
            document.getElementById("f_km").value = "";
            document.getElementById("f_cor").value = "";
            document.getElementById("f_comb").value = "";
            document.getElementById("f_placa").value = "";
            document.getElementById("f_preco").value = "";
            document.getElementById("f_img").value = "";
            document.getElementById("f_desc").value = "";
            document.getElementById("f_loja").value = "1";
        }

        // Prepara o Modal para um cadastro do zero
        function abrirModalInserir() {
            limparFormulario();
            document.getElementById("modal-titulo").innerText = "Inserir Novo Veículo";
            document.getElementById("formAcao").value = "CadastrarVeiculo"; // Chama o CadastrarVeiculoAction
            modal.style.display = "flex";
        }

        // Prepara o Modal carregando os dados do carro clicado para edição
        function abrirModalEditar(id, marca, modelo, anoFab, anoMod, km, cor, comb, placa, preco, desc, img, loja) {
            document.getElementById("modal-titulo").innerText = "Editar Anúncio (ID: " + id + ")";
            document.getElementById("formAcao").value = "EditarVeiculo"; // Chama o EditarVeiculoAction
            
            document.getElementById("formId").value = id;
            document.getElementById("f_marca").value = marca;
            document.getElementById("f_modelo").value = modelo;
            document.getElementById("f_anoFab").value = anoFab;
            document.getElementById("f_anoMod").value = anoMod;
            document.getElementById("f_km").value = km;
            document.getElementById("f_cor").value = cor;
            document.getElementById("f_comb").value = comb;
            document.getElementById("f_placa").value = placa;
            document.getElementById("f_preco").value = preco;
            document.getElementById("f_img").value = img;
            document.getElementById("f_desc").value = desc;
            document.getElementById("f_loja").value = loja;
            
            modal.style.display = "flex";
        }

        function fecharModal() { modal.style.display = "none"; }
    </script>
</body>
</html>
