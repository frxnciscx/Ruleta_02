package controlador;

import modelo.Resultado;

import java.util.List;

public class ResultadoController {
    private final IRepositorioResultados repositorio;

    public ResultadoController(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public List<Resultado> getHistorialUsuario() {
        return this.repositorio.getHistorial();
    }
}
