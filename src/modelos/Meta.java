package modelos;

import java.util.Date;

public class Meta {
    private int id;
    private String titulo;
    private Date dataInicial;
    private Date dataFinal;
    private float valorAportado;
    private float valorEstipulado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public float getValorAportado() {
        return valorAportado;
    }

    public void setValorAportado(float valorAportado) {
        this.valorAportado = valorAportado;
    }

    public float getValorEstipulado() {
        return valorEstipulado;
    }

    public void setValorEstipulado(float valorEstipulado) {
        this.valorEstipulado = valorEstipulado;
    }

    
}
