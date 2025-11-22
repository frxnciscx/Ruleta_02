package controlador;

import modelo.ApuestaBase;
import modelo.Resultado;
import modelo.Ruleta;
import modelo.Usuario;

public class RuletaController {
    private final SessionController session;
    private final Usuario usuario;
    private final Ruleta ruleta;
    private final IRepositorioResultados repositorio;

    public RuletaController(SessionController session, IRepositorioResultados repositorio) {
        this.session = session;
        this.repositorio = repositorio;
        this.usuario = session.getUsuarioActual();
        this.ruleta = new Ruleta(usuario.getSaldo());
    }

    public void jugar(ApuestaBase apuesta) {
        ruleta.jugar(apuesta);

        int saldoDespues = ruleta.getSaldo();
        boolean acierto = ruleta.getUltimoAcierto();
        int numero = ruleta.getUltimoNumero();
        int ganancia = saldoDespues - usuario.getSaldo();

        usuario.actualizarSaldo(ganancia);

        Resultado res = new Resultado(numero, apuesta, acierto, saldoDespues);

        this.repositorio.agregarResultado(res);
    }

    public int getUltimoNumero() {
        return ruleta.getUltimoNumero();
    }
    public String getUltimoColor() {
        return ruleta.getUltimoColor();
    }
    public boolean getUltimoAcierto() {
        return ruleta.getUltimoAcierto();
    }
    public int getSaldoActual() {
        return usuario.getSaldo();
    }
}
