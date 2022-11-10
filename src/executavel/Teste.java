package executavel;

import java.sql.*;
import java.util.Scanner;

import conexao.*;

public class Teste  {

    public static void main(String[] args) {
        int action;
        Scanner input = new Scanner(System.in);

        try {
            Conexao connection = new Conexao();
            System.out.println("Conectado ao banco...");

            System.out.print("####### BEM VINDO #######\n1(Adicionar usuário) 2(Remover alguém) 3(Sair)\n--> ");
            action = input.nextInt();

            switch (action) {
                case 1:
                    connection.AddUser();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Erro -> " + e);
        } catch (Exception e) {
            System.err.println("Digite apenas números...");
        }
    }
}
