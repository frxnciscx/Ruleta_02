package vista;

import controlador.ResultadoController;
import modelo.Estadisticas;
import modelo.Resultado;
import modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaEstadisticas {
    private final JFrame frame;
    private final ResultadoController historial;

    public VentanaEstadisticas(ResultadoController historial) {
        this.historial = historial;
        this.frame = new JFrame("Estadisticas de Juego");

        configurarVentana();
        cargarEstadisticas();
    }

    private void configurarVentana() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas
    }

    private void cargarEstadisticas() {
        List<Resultado> listaHistorial = historial.getHistorialUsuario();

        Estadisticas stats = new Estadisticas(listaHistorial);

        frame.add(new JLabel(" Total de Jugadas:"));
        frame.add(new JLabel(String.valueOf(stats.getTotalJugadas())));

        frame.add(new JLabel(" Total de Victorias:"));
        frame.add(new JLabel(String.valueOf(stats.getVictorias())));

        frame.add(new JLabel(" Porcentaje de Victorias:"));
        frame.add(new JLabel(String.format("%.2f %%", stats.getPorcentajeVictorias())));

        frame.add(new JLabel(" Racha Maxima de Victorias:"));
        frame.add(new JLabel(String.valueOf(stats.getRachaMaxima())));

        frame.add(new JLabel(" Tipo de Apuesta Mas Jugado:"));
        TipoApuesta tipo = stats.getTipoMasJugado();
        frame.add(new JLabel(tipo != null ? tipo.toString() : "N/A"));

        frame.add(new JLabel(""));
        frame.add(new JLabel(""));
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
