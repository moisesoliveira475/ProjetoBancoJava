package banco;

import java.util.Random;

public class BancoUam {

    // gerar um número de conta aleatorio
   public String gerarNumeroConta() {
        Random gerador = new Random();
        
        String resultado = "";
        for (int i = 1; i <= 5; i++) {
            resultado += gerador.nextInt(9);
         }
        System.out.println(resultado);
        return resultado;
   }
}
