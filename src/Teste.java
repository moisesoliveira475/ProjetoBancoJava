public class Teste {
    public static void main(String[] args) {
        /*
         * BancoUam c1 = new BancoUam();
         * c1.abrirConta("CC");
         * System.out.println(c1.getSaldo());
         * c1.sacar(10);
         * System.out.println(c1.getSaldo());
         * ContaEsp c2 = new ContaEsp();
         * c2.sacar(10);
         * System.out.println(c2.getSaldo());
         */
        Usuario u1 = new Usuario();
        u1.setNome("Moises");
        u1.abrirConta("CC");
        u1.depositar(200);
        u1.sacar(100);
        u1.estadoAtual();
    }

}
