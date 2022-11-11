package conexao;

import java.sql.*;
import java.util.Scanner;

public class Conexao  {
    private static Connection connection;

    Scanner input = new Scanner(System.in);

    public Connection Conexao() throws SQLException {
        String user = "", pass = "";
        
        if(connection == null) {
            System.out.println("--- Bem vindo ao banco ---");
            System.out.print("Usuario--> ");
            user = input.nextLine();
            System.out.print("Senha--> ");
            pass = input.nextLine();
        }
        
        String URL = "jdbc:mysql://"+user+"@localhost:3306/usuariosbancouam";
        
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, user, pass);
                System.out.println("\n--- Conectado ao banco ---");
            }
            return connection;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public String AddUser() throws SQLException {
        try {
            Statement stnt = (Statement) Conexao().createStatement();
            String name, rg, cpf, email;
            int age;
            
            System.out.println("Preencha os campos a seguir...");
            System.out.print("Nome--> ");
            name = input.next();
            System.out.print("Idade--> ");
            age = input.nextInt();
            System.out.print("RG sem traços/pontos--> ");
            rg = input.next();
            System.out.print("CPF sem traços/pontos--> ");
            cpf = input.next();
            System.out.print("Email--> ");
            email = input.next();
            
            System.out.println("\nEnviando informações...");
            String insertUser = "INSERT INTO users(name, age, rg, cpf, email) VALUES "
                    + "('"+name+"',"+age+",'"+rg+"','"+cpf+"','"+email+"');";
            stnt.execute(insertUser);

            System.out.println("Usuário adicionado com sucesso\n");
            return "Usuário adicionado com sucesso";
        } catch (SQLException e) {
            System.out.println("Algo deu errrado ao adicionar usuário!\n" + e);
            return "Algo deu errrado ao adicionar usuário!\n" + e;
        }
    }
}
