package interfaces;

import java.sql.*;

import conexao.*;

public class Partida  {
    public static void main(String[] args) {
        Entrar entrar = new Entrar();
        
        // Gera um usuario para persistir os dados;
        DadosUsuario dadosUsuario = new DadosUsuario();
       
        try {
            Conexao connection = new Conexao();
            connection.Conexao();    

            entrar.show(dadosUsuario);
            
        } catch (SQLException e) {
            System.out.println("Erro -> " + e);
        }
    }
}
