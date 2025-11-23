package modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    //CASO 5
    @Test
    void testCalculoEstadisticas() {
        List<Resultado> historial = new ArrayList<>();

        ApuestaBase apuesta1 = new ApuestaRojo(100);
        historial.add(new Resultado(1, apuesta1, true, 1100));

        ApuestaBase apuesta2 = new ApuestaRojo(100);
        historial.add(new Resultado(3, apuesta2, true, 1200));

        ApuestaBase apuesta3 = new ApuestaNegro(100);
        historial.add(new Resultado(1,apuesta3,false,1100));

        Estadisticas stats = new Estadisticas(historial);

        assertEquals(3,stats.getTotalJugadas(),"Total de jugadas incorrecto");
        assertEquals(2,stats.getVictorias(),"Total de victorias incorrecto");
        assertEquals(2,stats.getRachaMaxima(),"Racha Maxima incorrecto");

        assertEquals("Rojo",stats.getTipoMasJugado(),"Tipo mas jugado incorrecto");
    }


}