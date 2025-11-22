package modelo;

import java.io.Serializable;

public abstract class ApuestaBase implements Serializable {

    protected int monto;
    protected String etiqueta;

    public ApuestaBase(int monto, String etiqueta) {
        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public abstract boolean acierta(int numero, String color);

    public int getMonto() {
        return monto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}