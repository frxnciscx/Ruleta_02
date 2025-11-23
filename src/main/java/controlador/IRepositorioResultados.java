package controlador;

import modelo.Resultado;
import java.util.List;

public interface IRepositorioResultados {
    void agregarResultado(Resultado r);
    List<Resultado> getHistorial();
}

