package conexao;

import java.sql.*;

import banco.BancoUam;
import java.time.LocalDate;

public class Conexao {

    private static Connection connection;

    DadosUsuario dadosUsuario = new DadosUsuario();
    BancoUam bancouam = new BancoUam();

    public Connection Conexao() throws SQLException {
        String user = "root", pass = "root";

        String URL = "jdbc:mysql://" + user + "@localhost:3306/bancouam";

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

            boolean isConnected = false;

            ResultSet rs;
            rs = psVerifyPass.executeQuery();

            if (rs.next()) {
                dadosUsuario.setEmail(rs.getString("email"));
                dadosUsuario.setSenha(rs.getString("senha"));

                String getUserData = "select id_usuario,nome,cpf,data_nascimento,renda_mensal,id_conta from usuarios where email=? and senha=?";
                PreparedStatement psGetUserData = Conexao().prepareStatement(getUserData);
                psGetUserData.setString(1, email);
                psGetUserData.setString(2, pass);

                rs = psGetUserData.executeQuery();
                if (rs.next()) {
                    dadosUsuario.setId(rs.getInt(1));
                    dadosUsuario.setNome(rs.getString(2));
                    dadosUsuario.setCpf(rs.getString(3));
                    dadosUsuario.setData_nascimento(rs.getString(4));
                    dadosUsuario.setRenda_mensal(rs.getFloat(5));
                    dadosUsuario.setId_conta(rs.getInt(6));

                    isConnected = true;
                }
            }
            return isConnected;
        } catch (SQLException e) {
            System.out.println("Algo deu errado -> " + e.getMessage());
            return false;
        }
    }

    public Boolean Cadastrar(String nome, String cpf, String email, String senha, java.util.Date dataNascimento, float rendaMensal, String senhaConta, String tipoConta, String statusConta) throws SQLException {

        String numeroConta = bancouam.gerarNumeroConta();

        boolean isSuccess = false;
        PreparedStatement psAcao;

        try {
            String criarConta = "INSERT INTO contas(id_conta, numero, senha_conta, tipo, status, saldo) VALUES (null, ?, ?, ?, ?, null);";
            psAcao = Conexao().prepareStatement(criarConta);
            psAcao.setString(1, numeroConta);
            psAcao.setString(2, senhaConta);
            psAcao.setString(3, tipoConta);
            psAcao.setString(4, statusConta);

            int nextInsertConta = psAcao.executeUpdate();

            if (nextInsertConta == 1) {
                String getAccountId = "select id_conta from contas where numero=?";
                psAcao = Conexao().prepareStatement(getAccountId);
                psAcao.setInt(1, Integer.parseInt(numeroConta));

                ResultSet rs;
                rs = psAcao.executeQuery();

                if (rs.next()) {
                    
                    int idConta = rs.getInt(1);

                    String criarUsuario = "INSERT INTO usuarios(nome, email, senha, cpf, data_nascimento, renda_mensal, id_conta) VALUES (?, ?, ?, ?, ?, ?, ?);";
                    psAcao = Conexao().prepareStatement(criarUsuario);
                    psAcao.setString(1, nome);
                    psAcao.setString(2, email);
                    psAcao.setString(3, senha);
                    psAcao.setString(4, cpf);
                    psAcao.setDate(5, (java.sql.Date) dataNascimento);
                    psAcao.setFloat(6, rendaMensal);
                    psAcao.setInt(7, idConta);

                    int nextInsertUsuario = psAcao.executeUpdate();
                    if (nextInsertUsuario == 1) {
                        
                        dadosUsuario.setNome(nome);
                        dadosUsuario.setEmail(email);
                        dadosUsuario.setCpf(cpf);
                        dadosUsuario.setData_nascimento("2003-05-13");
                        dadosUsuario.setRenda_mensal(rendaMensal);
                        dadosUsuario.setId_conta(idConta);
                        
                        System.out.println("Usuario criado e conta adicionada ^^");
                        isSuccess = true;
                    } else {
                        System.out.println("Não foi possivel criar o usuario :(");
                        isSuccess = false;
                    }
                } else {
                    System.out.println("Não foi possivel pegar o id da conta :(");
                }
            } else {
                System.out.println("Não foi possivel pegar criar a conta :(");
            }

            return isSuccess;
        } catch (SQLException e) {
            System.out.println("Algo deu errado -> " + e.getMessage());
            return isSuccess;
        }
    }
}
