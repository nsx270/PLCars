/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Pedro
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    
    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/lp_veiculos?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = ""; // Coloque sua senha do MySQL aqui, se houver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Método estático para obter a conexão
    public static Connection getConexao() {
        try {
            // Carrega o driver do MySQL (o .jar que você vai colocar na pasta lib)
            Class.forName(DRIVER);
            
            // Retorna a conexão estabelecida
            return DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver do banco de dados não encontrado. Verifique se o mysql-connector-j está nas bibliotecas do projeto.");
            throw new RuntimeException("Erro de Driver: " + e.getMessage());
            
        } catch (SQLException e) {
            System.err.println("Erro: Não foi possível conectar ao banco de dados. Verifique se o MySQL está rodando e se os dados estão corretos.");
            throw new RuntimeException("Erro de Conexão: " + e.getMessage());
        }
    }
}
