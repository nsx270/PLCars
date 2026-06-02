<%-- 
    Document   : pagina3
    Created on : 30 de mar. de 2026, 21:18:02
    Author     : Pedro
--%>

<%@page import="model.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Tenta recuperar o funcionário logado
    Funcionario func = (Funcionario) request.getAttribute("funcionario");
    String erro = (String) request.getAttribute("erro");
    String mensagem = (String) request.getAttribute("mensagem");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Portal do Funcionário - LP Veículos</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700;900&display=swap" rel="stylesheet">
    <style>
        :root { --vermelho: #9e1010; --branco: #ffffff; --cinza: #f4f4f4; --escuro: #333333; }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Roboto', sans-serif; }
        body { background-color: var(--cinza); padding: 40px 20px; }
        
        .container { max-width: 800px; margin: 0 auto; background: var(--branco); padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h1, h2 { color: var(--escuro); text-align: center; margin-bottom: 20px; }
        .voltar { display: inline-block; margin-bottom: 20px; color: var(--vermelho); text-decoration: none; font-weight: bold;}
        
        /* Grid para alinhar Login e Cadastro lado a lado */
        .grid-forms { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; }
        .form-box { background: var(--cinza); padding: 20px; border-radius: 8px; }
        .form-box label { display: block; font-weight: bold; margin-bottom: 5px; font-size: 0.9rem;}
        .form-box input { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; }
        
        .btn { background: var(--vermelho); color: var(--branco); padding: 12px; width: 100%; border: none; border-radius: 4px; font-size: 1rem; font-weight: bold; cursor: pointer; transition: 0.2s;}
        .btn:hover { background: #7a0c0c; }
        
        /* Estilos da Dashboard */
        .dashboard-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }
        .card-dash { background: var(--vermelho); color: var(--branco); padding: 20px; border-radius: 8px; text-align: center; }
        .card-dash h3 { font-size: 2.5rem; margin-bottom: 10px; }
        .card-dash p { font-size: 1.1rem; font-weight: bold; }
        
        .alert { padding: 15px; margin-bottom: 20px; border-radius: 4px; text-align: center; font-weight: bold;}
        .alert-erro { background: #ffdce0; color: #9e1010; }
        .alert-sucesso { background: #d4edda; color: #155724; }
    </style>
</head>
<body>
    <div class="container">
        <a href="index.jsp" class="voltar">← Voltar para o Início</a>

        <% if(erro != null) { %> <div class="alert alert-erro"><%= erro %></div> <% } %>
        <% if(mensagem != null) { %> <div class="alert alert-sucesso"><%= mensagem %></div> <% } %>

        <% if(func == null) { %>
            <h1>Acesso Restrito</h1>
            <p style="text-align: center; margin-bottom: 30px; color: #666;">Faça login ou cadastre-se para acessar o painel de vendas.</p>
            
            <div class="grid-forms">
                <div class="form-box">
                    <h2>Login</h2>
                    <form action="controller.do" method="POST">
                        <input type="hidden" name="acao" value="LoginFuncionario">
                        
                        <label>Seu ID de Funcionário:</label>
                        <input type="number" name="idFuncionario" required placeholder="Digite seu ID numérico">
                        
                        <label>Senha:</label>
                        <input type="password" name="senha" required placeholder="Sua senha">
                        
                        <button type="submit" class="btn">Entrar</button>
                    </form>
                </div>

                <div class="form-box" style="border-left: 2px solid #ddd;">
                    <h2>Novo Cadastro</h2>
                    <form action="controller.do" method="POST">
                        <input type="hidden" name="acao" value="CadastrarFuncionario">
                        
                        <label>Nome Completo:</label>
                        <input type="text" name="nome" required>
                        
                        <label>Função / Cargo:</label>
                        <input type="text" name="funcao" placeholder="Ex: Vendedor Sênior" required>
                        
                        <label>Crie uma Senha:</label>
                        <input type="password" name="senha" required placeholder="Digite uma senha segura">
                        
                        <button type="submit" class="btn" style="background: #333;">Cadastrar-se</button>
                    </form>
                </div>
            </div>

        <% } else { %>
            <h1>Bem-vindo, <%= func.getNome() %>!</h1>
            <p style="text-align: center; margin-bottom: 30px; color: #666;">Cargo: <%= func.getFuncao() %> | ID: <%= func.getId() %></p>

            <div class="dashboard-grid">
                <div class="card-dash" style="background: #222;">
                    <h3>R$ <%= String.format(new java.util.Locale("pt", "BR"), "%,.2f", func.getComissaoVendas()) %></h3>
                    <p>Sua Comissão Acumulada</p>
                </div>
                <div class="card-dash">
                    <h3><%= request.getAttribute("minhasVendas") %></h3>
                    <p>Veículos Vendidos por Você</p>
                </div>
                <div class="card-dash" style="background: #666;">
                    <h3><%= request.getAttribute("vendasJacarei") %></h3>
                    <p>Total Vendas (Jacareí)</p>
                </div>
                <div class="card-dash" style="background: #666;">
                    <h3><%= request.getAttribute("vendasMogi") %></h3>
                    <p>Total Vendas (Mogi)</p>
                </div>
            </div>

            <div style="text-align: center; margin-top: 40px;">
                <form action="controller.do" method="POST">
                    <input type="hidden" name="acao" value="ListarVeiculoTodos">
                    <button type="submit" class="btn" style="max-width: 400px; padding: 20px; font-size: 1.2rem;">Gerenciar Catálogo de Veículos</button>
                </form>
            </div>
        <% } %>
    </div>
</body>
</html>