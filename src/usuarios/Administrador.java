
package usuarios;

public class Administrador extends Pessoa {
    int idade;

    @Override
    public int getIdade() {
        return idade;
    }
    @Override
    public void setIdade(int value) {
        this.idade = value;
    }

    public Administrador() {

    }
}
