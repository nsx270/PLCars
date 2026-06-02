<%-- 
    Document   : pagina2
    Created on : 27 de mar. de 2026, 09:31:22
    Author     : Pedro
--%>

<%@page import="java.util.List"%>
<%@page import="model.Veiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Veiculo> listaVeiculos = (List<Veiculo>) request.getAttribute("listaVeiculos");
    String nomeLoja = (String) request.getAttribute("nomeLoja");
    
    String mensagem = (String) request.getAttribute("mensagem");
    
    if(listaVeiculos == null) {
        listaVeiculos = new java.util.ArrayList<>();
        nomeLoja = "Nenhuma loja selecionada";
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo - LP Veículos</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
    
    <style>
        :root {
            --vermelho-tema: #9e1010; --branco-tema: #ffffff;
            --cinza-texto: #666666; --cinza-fundo: #f4f4f4; --preto-destaque: #222222;
        }

        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Roboto', sans-serif; }
        body { background-color: var(--cinza-fundo); }

        .banner-interno {
            background-image: url('img/fundo.png');
            background-size: cover; background-position: center; height: 250px;
            display: flex; align-items: center; justify-content: center; position: relative;
        }
        .overlay { position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.6); }
        .titulo-banner { position: relative; color: var(--branco-tema); z-index: 1; text-align: center; }
        .titulo-banner h1 { font-size: 3rem; font-weight: 900; }
        .titulo-banner p { font-size: 1.2rem; color: #dddddd; }

        .container { max-width: 1200px; margin: 40px auto; padding: 0 20px; }
        .grid-veiculos { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 25px; }

        .card-veiculo {
            background-color: var(--branco-tema); border-radius: 8px; overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1); cursor: pointer; transition: transform 0.2s, box-shadow 0.2s;
        }
        .card-veiculo:hover { transform: translateY(-5px); box-shadow: 0 8px 16px rgba(0,0,0,0.2); }
        .img-veiculo { width: 100%; height: 190px; object-fit: cover; }
        .info-veiculo { padding: 15px; }
        .info-modelo { font-size: 1.2rem; font-weight: 700; color: var(--preto-destaque); text-transform: uppercase;}
        .info-marca-ano { font-size: 0.9rem; color: var(--cinza-texto); margin: 5px 0 15px 0; }
        .info-preco { font-size: 1.4rem; font-weight: 900; color: var(--preto-destaque); }

        .modal-bg {
            display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%;
            background-color: rgba(0, 0, 0, 0.7); z-index: 1000; justify-content: center; align-items: center;
        }
        .modal-content {
            background-color: var(--branco-tema); width: 90%; max-width: 800px; border-radius: 10px;
            padding: 30px; position: relative; display: flex; gap: 20px; flex-wrap: wrap;
            max-height: 90vh; overflow-y: auto; /* Adicionado para permitir rolagem se a tela for pequena */
        }
        .fechar-modal {
            position: absolute; top: 15px; right: 20px; font-size: 1.5rem;
            cursor: pointer; color: var(--cinza-texto); font-weight: bold;
        }
        .modal-esq { flex: 1; min-width: 250px; }
        .modal-img { width: 100%; border-radius: 8px; margin-bottom: 15px; }
        
        .modal-dir { flex: 1.5; min-width: 300px; display: flex; flex-direction: column; }
        .modal-titulo { font-size: 1.5rem; font-weight: 900; color: var(--preto-destaque); margin-bottom: 5px;}
        .modal-preco { font-size: 1.8rem; font-weight: 900; color: var(--vermelho-tema); margin-bottom: 15px; }
        
        .grid-detalhes { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin-bottom: 15px; font-size: 0.9rem; color: var(--cinza-texto);}
        .grid-detalhes strong { color: var(--preto-destaque); }

        .form-compra { background-color: var(--cinza-fundo); padding: 15px; border-radius: 8px; margin-top: auto; }
        .form-compra h4 { margin-bottom: 10px; border-bottom: 1px solid #ccc; padding-bottom: 5px; color: var(--preto-destaque); font-size: 1rem; }
        .form-compra label { font-size: 0.85rem; font-weight: bold; color: var(--preto-destaque); display: block; margin-bottom: 5px;}
        .form-compra input, .form-compra select { width: 100%; padding: 8px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 4px; }
        
        .btn-comprar {
            background-color: var(--vermelho-tema); color: var(--branco-tema); border: none;
            padding: 12px; width: 100%; font-size: 1.1rem; font-weight: bold; border-radius: 5px;
            cursor: pointer; transition: 0.2s;
        }
        .btn-comprar:hover { background-color: #7a0c0c; }
        .voltar { display: inline-block; margin-bottom: 20px; color: var(--vermelho-tema); text-decoration: none; font-weight: bold;}
    </style>
</head>
<body>

    <header class="banner-interno">
        <div class="overlay"></div>
        <div class="titulo-banner">
            <h1>LP Veículos</h1>
            <p>Catálogo Oficial - <%= nomeLoja %></p>
        </div>
    </header>

    <main class="container">
        <a href="index.jsp" class="voltar">← Voltar para seleção de lojas</a>
        <% if (mensagem != null) { %>
            <script>
                alert("<%= mensagem %>");
            </script>
        <% } %>
        <div class="grid-veiculos">
            <% 
                for(Veiculo v : listaVeiculos) { 
                    String imagem = (v.getImagemPlaceholder() != null && !v.getImagemPlaceholder().isEmpty()) 
                                    ? v.getImagemPlaceholder() 
                                    : "https://images.unsplash.com/photo-1550355291-bbee04a92027?auto=format&fit=crop&w=400&q=80";
            %>
            
            <div class="card-veiculo" onclick="abrirModal('<%= v.getId() %>', '<%= v.getMarca() %>', '<%= v.getModelo() %>', '<%= v.getAnoFabricacao() %>', '<%= v.getAnoModelo() %>', '<%= v.getPreco() %>', '<%= v.getQuilometragem() %>', '<%= v.getCor() %>', '<%= v.getCombustivel() %>', '<%= v.getPlaca() %>', '<%= v.getDescricao() %>', '<%= imagem %>', '<%= v.getIdConcessionaria() %>')">
                <img src="<%= imagem %>" alt="Foto do veículo" class="img-veiculo">
                <div class="info-veiculo">
                    <div class="info-modelo"><%= v.getModelo() %></div>
                    <div class="info-marca-ano"><%= v.getMarca() %> • <%= v.getAnoModelo() %></div>
                    <div class="info-preco">R$ <%= String.format(new java.util.Locale("pt", "BR"), "%,.2f", v.getPreco()) %></div>
                </div>
            </div>
            
            <% } %>
        </div>
        
        <% if(listaVeiculos.isEmpty()) { %>
            <p style="text-align: center; color: var(--cinza-texto); margin-top: 50px;">Nenhum veículo cadastrado nesta loja no momento.</p>
        <% } %>
    </main>

    <div class="modal-bg" id="meuModal" onclick="fecharModalFora(event)">
        <div class="modal-content">
            <span class="fechar-modal" onclick="fecharModal()">&times;</span>
            
            <div class="modal-esq">
                <img id="modal-img" src="" alt="Veículo" class="modal-img">
                <p style="font-size: 0.85rem; color: var(--cinza-texto);"><strong>Descrição:</strong> <span id="modal-desc"></span></p>
            </div>
            
            <div class="modal-dir">
                <div class="modal-titulo" id="modal-titulo">Modelo</div>
                <div class="modal-preco" id="modal-preco">R$ 0,00</div>
                
                <div class="grid-detalhes">
                    <div><strong>Marca:</strong> <span id="modal-marca"></span></div>
                    <div><strong>Cor:</strong> <span id="modal-cor"></span></div>
                    <div><strong>Ano Fab/Mod:</strong> <span id="modal-ano"></span></div>
                    <div><strong>Combustível:</strong> <span id="modal-comb"></span></div>
                    <div><strong>Quilometragem:</strong> <span id="modal-km"></span> km</div>
                    <div><strong>Placa:</strong> <span id="modal-placa"></span></div>
                    <div><strong>ID Anúncio:</strong> <span id="modal-id-view"></span></div>
                </div>

                <form action="controller.do" method="POST" class="form-compra">
                    <input type="hidden" name="acao" value="ComprarVeiculo">
                    
                    <input type="hidden" name="idVeiculo" id="form-idVeiculo">
                    <input type="hidden" name="valorVenda" id="form-valorVenda">
                    <input type="hidden" name="idConcessionaria" id="form-idConcessionaria">
                    
                    <h4 style="margin-top: 10px;">Opcionais do Veículo</h4>
                    <div style="display: flex; flex-direction: column; gap: 6px; margin-bottom: 15px; background: #fff; padding: 10px; border-radius: 4px; border: 1px solid #ccc;">
                        <label style="display: flex; align-items: center; gap: 8px; font-weight: normal; font-size: 0.85rem; cursor: pointer;">
                            <input type="checkbox" name="opcionalCouro" id="opcionalCouro" value="sim">
                            Bancos de Couro Premium (+ R$ 3.500,00)
                        </label>
                        <label style="display: flex; align-items: center; gap: 8px; font-weight: normal; font-size: 0.85rem; cursor: pointer;">
                            <input type="checkbox" name="opcionalBlindagem" id="opcionalBlindagem" value="sim">
                            Blindagem Nível III-A (+ R$ 45.000,00)
                        </label>
                    </div>

                    <h4>Dados do Cliente</h4>
                    <label>Nome do Cliente:</label>
                    <input type="text" name="nomeCliente" required>
                    
                    <label>CPF do Cliente:</label>
                    <input type="text" name="cpfCliente" required>
                    
                    <label>Endereço de Entrega:</label>
                    <input type="text" name="enderecoCliente" required>
                    
                    <h4 style="margin-top: 10px;">Dados da Venda</h4>
                    <label for="idFuncionario">ID do Vendedor:</label>
                    <input type="number" id="idFuncionario" name="idFuncionario" placeholder="Ex: 1" required>
                    
                    <label for="formaPagamento">Forma de Pagamento:</label>
                    <select name="formaPagamento" id="formaPagamento" required>
                        <option value="PIX">PIX</option>
                        <option value="Financiamento">Financiamento</option>
                        <option value="Transferência">Transferência Bancária</option>
                    </select>

                    <button type="submit" class="btn-comprar">Finalizar Compra</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        const modal = document.getElementById("meuModal");
        let precoOriginal = 0;

        function abrirModal(id, marca, modelo, anoFab, anoMod, preco, km, cor, comb, placa, desc, imagem, idLoja) {
            document.getElementById("modal-titulo").innerText = modelo;
            precoOriginal = parseFloat(preco);
            
            // Reseta os checkboxes ao abrir o modal
            document.getElementById("opcionalCouro").checked = false;
            document.getElementById("opcionalBlindagem").checked = false;
            
            atualizarPrecoExibido();

            document.getElementById("modal-marca").innerText = marca;
            document.getElementById("modal-ano").innerText = anoFab + "/" + anoMod;
            document.getElementById("modal-km").innerText = km;
            document.getElementById("modal-cor").innerText = cor;
            document.getElementById("modal-comb").innerText = comb;
            document.getElementById("modal-placa").innerText = placa || "Não informada";
            document.getElementById("modal-desc").innerText = desc;
            document.getElementById("modal-id-view").innerText = id;
            document.getElementById("modal-img").src = imagem;

            document.getElementById("form-idVeiculo").value = id;
            document.getElementById("form-idConcessionaria").value = idLoja;

            modal.style.display = "flex";
        }

        function atualizarPrecoExibido() {
            let precoCalculado = precoOriginal;
            if (document.getElementById("opcionalCouro").checked) {
                precoCalculado += 3500.00;
            }
            if (document.getElementById("opcionalBlindagem").checked) {
                precoCalculado += 45000.00;
            }
            document.getElementById("modal-preco").innerText = "R$ " + precoCalculado.toLocaleString('pt-BR', {minimumFractionDigits: 2});
            document.getElementById("form-valorVenda").value = precoCalculado;
        }

        // Adiciona listeners para atualizar o preço quando mudar a seleção
        document.getElementById("opcionalCouro").addEventListener("change", atualizarPrecoExibido);
        document.getElementById("opcionalBlindagem").addEventListener("change", atualizarPrecoExibido);

        function fecharModal() { modal.style.display = "none"; }
        function fecharModalFora(event) { if (event.target === modal) fecharModal(); }
    </script>
</body>
</html>