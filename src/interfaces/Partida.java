package interfaces;

import java.sql.*;
import conexao.*;

public class Partida {

    public static void main(String[] args) {
        DadosUsuario dadosUsuario = new DadosUsuario();
        Entrar entrar = new Entrar();

        try {
            Conexao connection = new Conexao();
            connection.Conexao();    

            entrar.show(dadosUsuario);
            
        } catch (SQLException e) {
            System.out.println("Erro -> " + e);
        }
    }
}
