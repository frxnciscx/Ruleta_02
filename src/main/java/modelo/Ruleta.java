package modelo;

import java.util.Random;

public class Ruleta {
    private static final int[] NUMEROS_ROJOS = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 27, 30, 32, 34, 36};

    private final Random rng;
    private int ultimoNumero;
    private String ultimoColor;
    private boolean ultimoAcierto;
    private int saldo;

    public Ruleta(int saldoInicial) {
        //CASO DE PRUEBA 1
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial negativo");
        }
        this.rng = new Random();
        this.ultimoNumero = -1;
        this.ultimoColor = "N/A";
        this.saldo = saldoInicial;
    }

    public Ruleta() {
        this(0);
    }

    //CASO DE PRUEBA 2
    public void depositar(int monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El saldo es negativo");
        }
        this.saldo += monto;
    }

    public void jugar(ApuestaBase apuesta) {
        //CASO DE PRUEBA 3
        if (apuesta == null) {
            throw new IllegalArgumentException("Apuesta es obligatoria");
        }
        if (apuesta.getMonto() > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.ultimoNumero = this.girarRuleta();
        this.ultimoColor = this.colorDe(this.ultimoNumero);
        this.ultimoAcierto = apuesta.acierta(this.ultimoNumero, this.ultimoColor);

        if (this.ultimoAcierto) {
            this.saldo += apuesta.getMonto();
        } else {
            this.saldo -= apuesta.getMonto();
        }
    }

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private String colorDe(int numero) {
        if (numero == 0) return "VERDE";
        if (this.esRojo(numero)) {
            return "ROJO";
        }
        return "NEGRO";
    }

    private boolean esRojo(int n) {
        if (n == 0) return false;
        for (int r : NUMEROS_ROJOS) {
            if (r == n) return true;
        }
        return false;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }
    public String getUltimoColor() {
        return ultimoColor;
    }
    public boolean getUltimoAcierto() {
        return ultimoAcierto;
    }
    public int getSaldo() {
        return saldo;
    }
}