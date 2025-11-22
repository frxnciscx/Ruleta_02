package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private String tipoMasJugado;

    public Estadisticas(List<Resultado> historial) {
        this.totalJugadas = 0;
        this.victorias = 0;
        this.porcentajeVictorias = 0.0;
        this.rachaMaxima = 0;
        this.tipoMasJugado = "N/A";

        if (historial != null && !historial.isEmpty()) {
            calcularEstadisticas(historial);
        }
    }

    private void calcularEstadisticas(List<Resultado> historial) {
        this.totalJugadas = historial.size();

        int rachaActual = 0;

        Map<String, Integer> conteoApuestas = new HashMap<>();

        for (Resultado res : historial) {

            if (res.isAcierto()) {
                this.victorias++;
                rachaActual++;
            } else {
                if (rachaActual > this.rachaMaxima) {
                    this.rachaMaxima = rachaActual;
                }
                rachaActual = 0;
            }

            String tipo = res.getApuestaEtiqueta();
            conteoApuestas.put(tipo, conteoApuestas.getOrDefault(tipo, 0) + 1);
        }

        if (rachaActual > this.rachaMaxima) {
            this.rachaMaxima = rachaActual;
        }

        if (this.totalJugadas > 0) {
            this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100.0;
        }

        int maxConteo = 0;
        if (!conteoApuestas.isEmpty()){
            for (Map.Entry<String, Integer> entry : conteoApuestas.entrySet()) {
                if (entry.getValue() > maxConteo) {
                    maxConteo = entry.getValue();
                    this.tipoMasJugado = entry.getKey();
                }
            }
        }
    }

    public int getTotalJugadas() {
        return totalJugadas;
    }

    public int getVictorias() {
        return victorias;
    }

    public double getPorcentajeVictorias() {
        return porcentajeVictorias;
    }

    public int getRachaMaxima() {
        return rachaMaxima;
    }

    public String getTipoMasJugado() {
        return tipoMasJugado;
    }
}