package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String username;
    private String password;
    private String nombre;
    private int saldo;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 1000;
    }

    public Usuario() {
        this("invitado", "invitado", "Invitado");
    }

    public boolean validarCredenciales(String u, String p) {
        if (u == null || p == null) {
            return false;
        }
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre;
        }
    }

    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    public void actualizarSaldo(int monto) {
        this.saldo += monto;
    }
}