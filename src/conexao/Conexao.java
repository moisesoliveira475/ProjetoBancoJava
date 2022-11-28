package conexao;

import java.sql.*;
import java.util.Scanner;

public class Conexao  {
    private static Connection connection;
    
    DadosUsuario dadosUsuario = new DadosUsuario();

    Scanner input = new Scanner(System.in);

    public Connection Conexao() throws SQLException {
        String user = "root", pass = "root";
   
        String URL = "jdbc:mysql://"+user+"@localhost:3306/bancouam";
        
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
    
    public Boolean Entrar(String email, String pass) {
        try {
            String verifyPass = "select email, senha from usuarios where email=? and senha=?;";
            PreparedStatement psVerifyPass = Conexao().prepareStatement(verifyPass);
            psVerifyPass.setString(1, email);
            psVerifyPass.setString(2, pass);
            
            ResultSet rs;
            rs = psVerifyPass.executeQuery();
            
            boolean isConnected = false;
            
            if (rs.next()) {
                dadosUsuario.setEmail(rs.getString("email"));
                dadosUsuario.setSenha(rs.getString("senha"));

                String getUserData = "select id_usuario,nome,cpf,data_nascimento,renda_mensal,id_conta from usuarios where email=? and senha=?";
                PreparedStatement psGetUserData = Conexao().prepareStatement(getUserData);
                psGetUserData.setString(1, email);
                psGetUserData.setString(2, pass);
                
                rs = psGetUserData.executeQuery();
                if(rs.next()) {
                    dadosUsuario.setId(rs.getInt(1));
                    dadosUsuario.setNome(rs.getString(2));
                    dadosUsuario.setCpf(rs.getString(3));
                    dadosUsuario.setData_nascimento(rs.getString(4));
                    dadosUsuario.setRenda_mensal(rs.getString(5));
                    dadosUsuario.setId_conta(rs.getString(6));
                    
                    isConnected = true;
                } 
            }
            return isConnected;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar -> " +e.getSQLState());
            return false;
        }
    }
    
    public Boolean Cadastrar() throws SQLException {
        try {
            
            
            
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao conectar -> "+e);
            return false;
        }
    }
}
