package banco;

import java.util.Random;
import usuarios.*;

public class BancoUam extends Pessoa {

    // atributos
    private int numConta;
    private String tipo;
    private float saldo;
    private boolean status;
    private float taxaEspecial = 0.5f;

    // gerar um número de conta aleatorio
   public  int gerarNumeroConta() {
        Random gerador = new Random();
        
        String resultado = "";
        for (int i = 0; i < 4; i++) {
            resultado += gerador.nextInt(10);
         }
        System.out.println(resultado);
        return Integer.parseInt(resultado) ;
   }
    
    // métodos personalizados
    public void estadoAtual() {
        System.out.println("Conta: " + this.getNumConta());
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Dono: " + super.getNome());
        System.out.println("Saldo: " + this.getSaldo());
        System.out.println("Status: " + this.isStatus());
    }

    public void abrirConta(String tipo) {
        this.setTipo(tipo);
        this.setStatus(true);
        if (null == tipo) {
            System.out.println("Não foi possível abrir a conta "+ super.getNome()+ "! Contate um administrador.");
        } else switch (tipo) {
            case "CC":
                this.setSaldo(100);
                this.setNumConta(gerarNumeroConta());
                System.out.println("Conta aberta com sucesso " + super.getNome() +
                        "! e o número da sua conta é " + this.getNumConta());
                break;
            case "CP":
                this.setSaldo(50);
                this.setNumConta(gerarNumeroConta());
                System.out.println("Conta aberta com sucesso " + super.getNome() + "!");
                break;
            default:
                System.out.println("Não foi possível abrir a conta " + super.getNome() +"! Contate um administrador.");
                break;
        }
    }

    public void fecharConta() {
        if (this.getSaldo() > 0) {
            System.out.println("Conta não pode ser fechada\n pois ainda tem saldo");
        } else if (this.getSaldo() < 0) {
            System.out.println("Conta não pode ser fechada\n pois tem débitos");
        } else {
            this.setStatus(false);
            System.out.println("Conta fechada com sucesso!");
        }
    }

    public void depositar(float value) {
        if (this.isStatus()) {
            this.setSaldo(this.getSaldo() + value);
            System.out.println("depósito realizado com sucesso na conta de " + super.getNome());
        } else {
            System.out.println("impossível depositar em uma conta desativada");
        }
    }

    public void sacar(float value) {
        if (this.isStatus()) {
            if (this.getSaldo() >= value) {
                this.setSaldo(this.getSaldo() - value);
                System.out.println(super.getNome() + ", Saque efetuado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("Necessário ativação de conta");
        }
    }

    public void saqueEspecial(float value) {
        if (this.isStatus()) {
            if (this.getSaldo() >= value) {
                this.setSaldo((this.getSaldo() - value) - taxaEspecial);
                System.out.println(super.getNome() + " ,Saque efetuado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("Necessário ativação de conta");
        }
    }

    public void pagarMensalidade() {
        int v = 0;
        if ("CC".equals(this.getTipo())) {
            v = 12;
        } else if ("CP".equals(this.getTipo())) {
            v = 20;
        }
        if (this.isStatus()) {
            this.setSaldo(this.getSaldo() - v);
            System.out.println(super.getNome() + " ,mensalidade paga com sucesso");
        } else {
            System.out.println("Necessário conta aberta");
        }
    }

    public BancoUam() {
        saldo = 0;
        status = false;
    }

    public BancoUam(int numConta, String tipo, float saldo) {
        this.numConta = numConta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.status = true;
    }

    public void setNumConta(int n) {
        this.numConta = n;
    }

    public int getNumConta() {
        return this.numConta;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public boolean isStatus() {
        return status;
    }

}
