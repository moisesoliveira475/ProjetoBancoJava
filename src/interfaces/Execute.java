package interfaces;

import java.sql.*;
import java.util.Scanner;

import conexao.*;

public class Execute  {
    public static void main(String[] args) {
        Entrar entrar = new Entrar();
        Cadastrar cadastrar = new Cadastrar();
        
        try {
            Conexao connection = new Conexao();
            connection.Conexao();    

            entrar.show();
            //cadastrar.show();

        } catch (SQLException e) {
            System.out.println("Erro -> " + e);
        }
    }
}
