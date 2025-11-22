package launcher;

import controlador.IRepositorioResultados;
import controlador.RepositorioArchivo;
import controlador.ResultadoController;
import controlador.SessionController;
import vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        IRepositorioResultados repositorio = new RepositorioArchivo();
        ResultadoController historial = new ResultadoController(repositorio);
        VentanaLogin vLogin = new VentanaLogin(session, historial, repositorio);
        vLogin.mostrarVentana();
    }
}