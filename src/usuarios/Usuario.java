package usuarios;

import banco.BancoUam;

public class Usuario extends BancoUam {
    private int idu;

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public Usuario() {

    }

}
