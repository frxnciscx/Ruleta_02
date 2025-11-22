package vista;

import controlador.ResultadoController;
import modelo.Resultado;

import javax.swing.*;
import java.util.List;

public class VentanaHistorial {

    private final JFrame frame;
    private final JTextArea txtHistorial;
    private final ResultadoController historial;

    public VentanaHistorial(ResultadoController historial) {
        this.historial = historial;
        this.frame = new JFrame("Historial de Jugadas");
        this.txtHistorial = new JTextArea();
        configurarVentana();
        cargarHistorial();
    }

    private void configurarVentana() {
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        txtHistorial.setEditable(false);
        frame.add(new JScrollPane(txtHistorial));
    }

    private void cargarHistorial() {
        List<Resultado> lista = historial.getHistorialUsuario();
        StringBuilder sb = new StringBuilder();

        sb.append("--- Historial de Jugadas ---\n\n");

        if (lista.isEmpty()) {
            sb.append("No hay jugadas registradas.");
        } else {
            for (Resultado res : lista) {
                String gano = res.isAcierto() ? "GANO" : "PERDIO";
                String linea = String.format(
                        "Numero: %d | Apuesta: %s | Monto: %d | %s | Saldo: %d\n",
                        res.getNumero(),
                        res.getApuestaEtiqueta(),
                        res.getMonto(),
                        gano,
                        res.getSaldoResultante()
                );
                sb.append(linea);
            }
        }
        txtHistorial.setText(sb.toString());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}