package utils;

import conexao.Conexao;
import java.sql.*;
import java.util.Random;

public class Utils {

    // gera um número de conta aleatorio que ainda não existe no banco;
   public String gerarNumeroConta() {
       Random gerador = new Random();
       String resultado = ""; 
       boolean existe = true;
       
       while (existe == true) {         
            resultado="";
            for (int i = 1; i <= 5; i++) {
                resultado += gerador.nextInt(9);
             }
            
            if(aContaExiste(resultado) == false) {
                existe = false;
            }
       }  
       return resultado;
   }
   
   // verifica no banco o numero de conta gerado ja existe;
   public boolean aContaExiste(String numeroConta) {
       Conexao connection = new Conexao();
       PreparedStatement psAcao;
       
       boolean existe = false;
       
       try {
           String aContaExiste = "select numero from contas where numero=?;";

            psAcao = connection.Conexao().prepareStatement(aContaExiste, Statement.RETURN_GENERATED_KEYS);
            psAcao.setString(1, numeroConta);
           
           ResultSet rsInsert = psAcao.executeQuery();
           
           if(rsInsert.next()) {
               existe = true;
           }
       } catch (SQLException e) {}
       return existe;
   }
   
   // busca dados do usuário no banco através do numero da conta;
   public float[] buscarDadosConta(String numeroContaDestino) {
        Conexao connection = new Conexao();
        PreparedStatement psAcao;

        float dados[] = new float[3];

        try {
            String buscarDados = "select u.id_usuario, c.id_conta, saldo from contas as c join usuarios as u where ?=c.numero and c.id_conta=u.id_conta;";
            psAcao = connection.Conexao().prepareStatement(buscarDados);
            psAcao.setString(1, numeroContaDestino);

            ResultSet rs;
            rs = psAcao.executeQuery();

            if (rs.next()) {
                dados[0] = rs.getInt(1);
                dados[1] = rs.getInt(2);
                dados[2] = rs.getFloat(3);
                return dados;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return dados;
    }
   
   // recebe um array de valores e faz a soma;
   public float CalculoGastos(java.util.List<Float> contas) {
       float result = 0;
       for(float conta:contas) {
           result+=conta;
       }
       return result;
   }
}
