public class BancoUam extends Pessoa {

    // atributos
    private int numConta;
    private String tipo;
    private float saldo;
    private boolean status;
    private float taxaEsp = 0.5f;

    // métodos personalizados
    public void estadoAtual() {
        System.out.println("Conta: " + this.getNumConta());
        System.out.println("tipo: " + this.getTipo());
        System.out.println("Dono: " + super.getNome());
        System.out.println("saldo: " + this.getSaldo());
        System.out.println("Status: " + this.isStatus());
    }

    public void abrirConta(String tipo) {
        this.setTipo(tipo);
        this.setStatus(true);
        if ("CC".equals(tipo)) {
            this.setSaldo(50);
        } else if ("CP".equals(tipo)) {
            this.setSaldo(150);
        }
        System.out.println("Conta aberta com sucesso!");
    }

    public void fecharConta() {
        if (this.getSaldo() > 0) {
            System.out.println("Conta não pode ser fechada\n pois tem saldo");
        } else if (this.getSaldo() < 0) {
            System.out.println("Conta não pode ser fechada\n pois tem débitos");
        } else {
            this.setStatus(false);
            System.out.println("Conta fechada com sucesso!");
        }

    }

    public void depositar(float v) {
        if (this.isStatus()) {
            this.setSaldo(this.getSaldo() + v);
            System.out.println("depósito realizado com sucesso na conta de " + super.getNome());
        } else {
            System.out.println("impossível depositar em uma conta desativada");
        }
    }

    public void sacar(float v) {
        if (this.isStatus()) {
            if (this.getSaldo() >= v) {
                this.setSaldo(this.getSaldo() - v);
                System.out.println(super.getNome() + ", Saque efetuado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("Necessário ativação de conta");
        }
    }

    public void saqueEspecial(float v) {
        if (this.isStatus()) {
            if (this.getSaldo() >= v) {
                this.setSaldo((this.getSaldo() - v) - taxaEsp);
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
