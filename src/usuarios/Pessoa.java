package usuarios;

public abstract class Pessoa {
    // atributos
    private String nome;
    private int idade;
    private String rg;
    private String cpf;
    
    // métodos acessores
    public String getNome() {
        return nome;
    }
    public void setNome(String value) {
        this.nome = value;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int value) {
        this.idade = value;
    }

    public String getRG() {
        return rg;
    }
    public void setRG(String value) {
        rg = value;
    }

    public String getCPF() {
        return cpf;
    }
    public void setCPF(String value) {
        cpf = value;
    }
}
