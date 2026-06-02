# 🚗 PLCars - Gestão de Vendas de Concessionária

Este é um projeto de aplicação web para gerenciamento de vendas de veículos de uma concessionária multiloja (Jacareí e Mogi das Cruzes), desenvolvido em **Java Web** (JSP, Servlets) utilizando arquiteturas robustas e diversos padrões de projeto (Design Patterns).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem Principal:** Java 17
* **Web (Frontend):** JSP, HTML5, CSS3, JavaScript (Vanilla)
* **Persistência / Banco de Dados:** JDBC, MySQL (MySQL Workbench)
* **Arquitetura Web:** Front Controller com Command Pattern (Reflection Dinâmico)
* **Testes Automatizados:** JUnit 4

---

## 🎨 Design Patterns Implementados

1. **Decorator (Estrutural):** Utilizado para adicionar dinamicamente opcionais aos veículos (como `Bancos de Couro Premium` e `Blindagem Nível III-A`) acumulando preços e descrições de forma transparente e flexível sem alterar a classe base.
2. **Builder (Criacional):** Implementado através do `VeiculoBuilder` para simplificar a criação passo a passo e leitura de objetos complexos da classe `Veiculo`.
3. **Front Controller com Command (Comportamental):** Centralizado no `ServletController`, que mapeia requisições dinamicamente via Reflection do Java, executando classes que implementam a interface `ICommand` (elimina o uso de múltiplos servlets ou cadeias gigantescas de `if/else`).
4. **Data Access Object (DAO):** Separação clara entre a lógica de persistência e regras de negócio da aplicação (Classes `VeiculoDAO`, `FuncionarioDAO`, `VendaDAO`).

---

## 🚀 Como Executar o Projeto Localmente

### 1. Pré-requisitos
* Ter o **NetBeans IDE** instalado.
* Ter o **MySQL Server** e **MySQL Workbench** instalados e rodando.
* Driver do JDBC do MySQL (`mysql-connector-j-x.x.x.jar`) adicionado nas bibliotecas do projeto do NetBeans.

### 2. Configurar o Banco de Dados
1. Abra o seu **MySQL Workbench**.
2. Crie e popule o banco utilizando o script SQL a seguir:
```sql
CREATE DATABASE IF NOT EXISTS lp_veiculos;
USE lp_veiculos;

-- Crie as tabelas conforme o esquema e rode o script SQL fornecido na pasta do projeto.
```
3. Verifique o arquivo `src/java/util/FabricaConexao.java` e altere a constante `SENHA` de acordo com as credenciais do seu MySQL local.

### 3. Executar na IDE
1. Abra o NetBeans IDE.
2. Clique em **Abrir Projeto** e selecione a pasta `PLCars`.
3. Clique com o botão direito no projeto e selecione **Executar (Run)**.
4. O servidor web local será iniciado e abrirá a página inicial (`index.jsp`) automaticamente.
5. Para acessar o painel administrativo, acesse o Portal do Funcionário com as credenciais de teste:
   * **ID:** `1`
   * **Senha:** `123`

---

## 🧪 Testes Automatizados
O projeto conta com validações automatizadas de lógica de negócios. Para executar os testes:
1. No NetBeans, clique com o botão direito no projeto e selecione **Testar (Test)**.
2. O JUnit executará os testes em `VeiculoDecoratorTest.java`, validando a soma de opcionais do padrão Decorator.

---

*Desenvolvido como projeto acadêmico focado em engenharia de software e padrões de projeto.*
