package controlador;

import modelo.Usuario;

import java.io.*;

public class SessionController {

    private Usuario usuarioActual;
    private static final String ARCHIVO_USUARIO = "usuario.dat";

    public SessionController() {
        this.usuarioActual = null;
        cargarUsuario();
    }

    private void cargarUsuario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIO))) {
            this.usuarioActual = (Usuario) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuario no encontrado. Iniciar sin usuario.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el usuario: " + e.getMessage());
        }
    }

    private void guardarUsuario() {
        if (this.usuarioActual == null) return;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIO))) {
            oos.writeObject(this.usuarioActual);
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            System.err.println("Datos de registro incompletos");
            return;
        }
        this.usuarioActual = new Usuario(u, p, n);
        guardarUsuario();
    }

    public boolean iniciarSesion(String u, String p) {
        if (usuarioActual == null) {
            return false;
        }
        return usuarioActual.validarCredenciales(u, p);
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        guardarUsuario();
        this.usuarioActual = null;
    }
}
