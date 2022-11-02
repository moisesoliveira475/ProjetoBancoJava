public class Teste {
    public static void main(String[] args) {
        BancoUam c1 = new BancoUam();
        c1.abrirConta("CC");
        System.out.println(c1.getSaldo());
        c1.sacar(10);
        System.out.println(c1.getSaldo());
        ContaEsp c2 = new ContaEsp();
        c2.sacar(10);
        System.out.println(c2.getSaldo());
    }

}
