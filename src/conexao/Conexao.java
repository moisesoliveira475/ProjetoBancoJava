package conexao;

import java.sql.*;

import utils.Utils;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection connection;
    
    Utils utils = new Utils();
    
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

    public Boolean Entrar(DadosUsuario dadosUsuario, String emailCpf, String pass) {
        try {
            String verifyPass = "select email, senha, cpf from usuarios where email=? and senha=? or cpf=? and senha=?;";
            PreparedStatement psVerifyPass = Conexao().prepareStatement(verifyPass);
            psVerifyPass.setString(1, emailCpf);
            psVerifyPass.setString(2, pass);
            psVerifyPass.setString(3, emailCpf);
            psVerifyPass.setString(4, pass);
            
            boolean isConnected = false;

            ResultSet rs;
            rs = psVerifyPass.executeQuery();

            if (rs.next()) {
                dadosUsuario.setEmail(rs.getString("email"));
                dadosUsuario.setSenha(rs.getString("senha"));
        
                String buscaUsuario = "select id_usuario,nome,cpf,data_nascimento,renda_mensal,id_conta from usuarios where email=? and senha=? or cpf=? and senha=?;";
                PreparedStatement psBuscaUsuario = Conexao().prepareStatement(buscaUsuario);
                psBuscaUsuario.setString(1, emailCpf);
                psBuscaUsuario.setString(2, pass);
                psBuscaUsuario.setString(3, emailCpf);
                psBuscaUsuario.setString(4, pass);

                rs = psBuscaUsuario.executeQuery();
                if (rs.next()) {
                   java.util.Date dataNascimentoConvertido = new java.sql.Date(rs.getDate(4).getTime());
                    
                    dadosUsuario.setId(rs.getInt(1));
                    dadosUsuario.setNome(rs.getString(2));
                    dadosUsuario.setCpf(rs.getString(3));
                    dadosUsuario.setData_nascimento(dataNascimentoConvertido);
                    dadosUsuario.setRenda_mensal(rs.getFloat(5));
                    dadosUsuario.setId_conta(rs.getInt(6));
                    
                    String buscaDadosBanco = "select numero, senha_conta, tipo, status, saldo from contas where id_conta=?;";
                    PreparedStatement psBuscaDadosBanco = Conexao().prepareCall(buscaDadosBanco);
                    psBuscaDadosBanco.setInt(1, rs.getInt(6));
                    
                    rs = psBuscaDadosBanco.executeQuery();
                    if(rs.next()) {
                        dadosUsuario.setNumeroConta(rs.getString(1));
                        dadosUsuario.setSenhaConta(rs.getString(2));
                        dadosUsuario.setTipoConta(rs.getString(3));
                        dadosUsuario.setStatusConta(rs.getString(4));
                        dadosUsuario.setSaldoConta(rs.getFloat(5));
                        
                        isConnected = true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Verifique suas credenciais!");
            }
            return isConnected;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public Boolean Cadastrar(
            DadosUsuario dadosUsuario, String nome, String cpf, 
            String email, String senha, java.util.Date dataNascimento, 
            float rendaMensal, String numeroConta, String senhaConta, String tipoConta, String statusConta
    ) throws SQLException {

        boolean isSuccess = false;
        PreparedStatement psAcao;

        String statusContaConvertido = (statusConta.equals("ATIVADA")) ? "1" : "0";
        char tipoContaConvertido = (tipoConta.equals("Poupança")) ? 'p' : 'c';
        java.sql.Date dataNascimentoConvertido = new java.sql.Date (dataNascimento.getTime());
        
        try {
            String criarConta = "INSERT INTO contas(numero, senha_conta, tipo, status) VALUES (?, ?, ?, ?);";
            psAcao = Conexao().prepareStatement(criarConta);
            psAcao.setString(1, numeroConta);
            psAcao.setString(2, senhaConta);
            psAcao.setString(3, String.valueOf(tipoContaConvertido));
            psAcao.setString(4, statusContaConvertido);

            int nextInsertConta = psAcao.executeUpdate();

            if (nextInsertConta == 1) {
                String getAccountId = "select id_conta from contas where numero=?";
                psAcao = Conexao().prepareStatement(getAccountId);
                psAcao.setString(1, numeroConta);
                
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
                    psAcao.setDate(5, dataNascimentoConvertido);
                    psAcao.setFloat(6, rendaMensal);
                    psAcao.setInt(7, idConta);

                    int nextInsertUsuario = psAcao.executeUpdate();
                    if (nextInsertUsuario == 1) {
                        if(dadosUsuario != null) {
                            dadosUsuario.setNome(nome);
                            dadosUsuario.setEmail(email);
                            dadosUsuario.setSenha(senha);
                            dadosUsuario.setCpf(cpf);
                            dadosUsuario.setData_nascimento(dataNascimento);
                            dadosUsuario.setRenda_mensal(rendaMensal);
                            dadosUsuario.setId_conta(idConta);

                            dadosUsuario.setNumeroConta(numeroConta);
                            dadosUsuario.setSenhaConta(senhaConta);
                            dadosUsuario.setTipoConta(tipoConta);
                            dadosUsuario.setSaldoConta(0);
                            dadosUsuario.setStatusConta(statusConta);
                        }
                        
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
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Algo deu errado -> " + e.getMessage());
            return isSuccess;
        }
    }
}
