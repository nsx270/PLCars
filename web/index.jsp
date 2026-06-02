<%-- 
    Document   : index
    Created on : 27 de mar. de 2026, 09:22:30
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LP Veículos</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
    
    <style>
        :root {
            --vermelho-tema: #9e1010;
            --branco-tema: #ffffff;
            --texto-escuro: #333333;
        }

        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Roboto', sans-serif; }
        body, html { height: 100%; background-color: var(--branco-tema); }

        .capa-fundo {
            height: 80vh;
            background-image: url('img/jeep.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed; 
            display: flex; justify-content: center; align-items: center; position: relative;
        }

        .overlay { position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.2); }

        .nome-loja { position: relative; color: var(--branco-tema); font-size: 5rem; font-weight: 900; text-shadow: 2px 4px 8px rgba(0,0,0,0.7); z-index: 1; text-align: center; }

        .conteudo-lojas { background-color: var(--branco-tema); padding: 80px 20px; text-align: center; position: relative; z-index: 2; box-shadow: 0px -5px 15px rgba(0,0,0,0.2); }
        .conteudo-lojas h2 { color: var(--texto-escuro); font-size: 2rem; margin-bottom: 40px; }

        .botoes-container { display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; margin-bottom: 80px; }

        .btn {
            background-color: var(--vermelho-tema); color: var(--branco-tema); text-decoration: none;
            padding: 15px 40px; font-size: 1.2rem; font-weight: 700; border-radius: 5px;
            transition: background-color 0.3s, transform 0.2s; border: none; cursor: pointer; box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .btn:hover { background-color: #7a0c0c; transform: translateY(-2px); }

        .secao-funcionarios { padding-top: 40px; border-top: 1px solid #eeeeee; }
        .btn-funcionario { background-color: transparent; color: var(--vermelho-tema); border: 2px solid var(--vermelho-tema); }
        .btn-funcionario:hover { background-color: var(--vermelho-tema); color: var(--branco-tema); }
    </style>
</head>
<body>
    <header class="capa-fundo">
        <div class="overlay"></div>
        <h1 class="nome-loja">LP Veículos</h1>
    </header>

    <main class="conteudo-lojas">
        <h2>Selecione a loja de sua preferência</h2>
        
        <% if(request.getAttribute("erro") != null) { %>
            <p style="color: red; margin-bottom: 20px;"><b>Erro:</b> <%= request.getAttribute("erro") %></p>
        <% } %>

        <div class="botoes-container">
            <a href="controller.do?acao=ListarVeiculoJacarei" class="btn">Catálogo Jacareí</a>
            <a href="controller.do?acao=ListarVeiculoMogi" class="btn">Catálogo Mogi das Cruzes</a>
        </div>

        <div class="secao-funcionarios">
            <a href="pagina3.jsp" class="btn btn-funcionario">Área de Funcionários</a>
        </div>
    </main>
</body>
</html>