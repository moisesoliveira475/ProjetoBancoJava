package conexao;

import java.sql.*;
import java.util.Scanner;

public class Conexao {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://root@localhost:3306/usuariosbancouam";

    Scanner input = new Scanner(System.in);

    public Connection Conexao() throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL);
            }
            return connection;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public String AddUser() throws SQLException {
        try {
            Statement stnt = (Statement) Conexao().createStatement();

            // System.out.println("\nEnviando informações...\n");
            // String insertUser = "INSERT INTO funcionarios(name, rg, cpf, position) VALUES ('" + name + "','" + rg + "','" + cpf + "','" + position + "');";
            // stnt.execute(insertUser);

            return "Usuário adicionado com sucesso";
        } catch (SQLException e) {
            System.out.println("Algo deu errrado ao adicionar usuário!\n" + e);
            return "Algo deu errrado ao adicionar usuário!\n" + e;
        }
    }
}
