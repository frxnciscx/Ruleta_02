package modelo;

import java.io.Serializable;

public class Resultado implements Serializable {

    private final int numero;
    private final String apuestaEtiqueta;
    private final int monto;
    private final boolean acierto;
    private final int saldoResultante;

    public Resultado(int numero, ApuestaBase apuesta, boolean acierto, int saldoResultante) {
        this.numero = numero;
        this.apuestaEtiqueta = apuesta.getEtiqueta();
        this.monto = apuesta.getMonto();
        this.acierto = acierto;
        this.saldoResultante = saldoResultante;
    }

    public int getNumero() { return numero; }
    public String getApuestaEtiqueta() { return apuestaEtiqueta; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }
    public int getSaldoResultante() { return saldoResultante; }
}
