package banco;

public class ContaEsp extends BancoUam {
    @Override
    public void sacar(float valor) {
        float testar = (float) (valor * 1.005);
        if (testar <= super.getSaldo()) {
            super.setSaldo(super.getSaldo() - testar);
            System.out.println("Saque realizado!");
        } else {
            System.out.println("Saque não realizado, por falta de saldo");
        }
    }
}
