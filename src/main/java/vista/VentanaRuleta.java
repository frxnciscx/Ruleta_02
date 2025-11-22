package vista;

import controlador.IRepositorioResultados;
import controlador.ResultadoController;
import controlador.RuletaController;
import controlador.SessionController;
import modelo.*;

import javax.swing.*;

public class VentanaRuleta {
    private final SessionController session;
    private final ResultadoController historial;
    private final IRepositorioResultados repositorio;
    private final RuletaController ruletaControlador;

    private final JFrame frame;
    private final JLabel lblTipoApuesta = new JLabel("Tipo de apuesta:");
    private final JComboBox<String> cmbTipoApuesta = new JComboBox<>(new String[]{"Color", "Paridad"});
    private final JLabel lblSeleccion = new JLabel("Seleccione color:");
    private final JComboBox<String> cmbSeleccion = new JComboBox<>(new String[]{"ROJO", "NEGRO"});
    private final JLabel lblMonto = new JLabel("Monto:");
    private final JTextField txtMonto = new JTextField("100");
    private final JButton btnGirar = new JButton("Girar");
    private final JLabel lblSaldo;
    private final JLabel lblResultado = new JLabel("Haga su apuesta");

    public VentanaRuleta(SessionController session, ResultadoController historial,  IRepositorioResultados repositorio) {
        this.session = session;
        this.historial = historial;
        this.repositorio = repositorio;

        this.ruletaControlador = new RuletaController(session, repositorio);
        this.frame = new JFrame("Juego Ruleta - Black Cat");
        this.lblSaldo = new JLabel("Saldo: " + ruletaControlador.getSaldoActual());

        configurarVentana();
        agregarEventos();
    }

    private void configurarVentana() {
        frame.setLayout(null);
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        lblTipoApuesta.setBounds(30, 30, 120, 25);
        cmbTipoApuesta.setBounds(160, 30, 150, 25);
        lblSeleccion.setBounds(30, 70, 120, 25);
        cmbSeleccion.setBounds(160, 70, 150, 25);
        lblMonto.setBounds(30, 110, 120, 25);
        txtMonto.setBounds(160, 110, 100, 25);
        btnGirar.setBounds(270, 110, 80, 25);
        lblSaldo.setBounds(370, 110, 150, 25);
        lblResultado.setBounds(30, 170, 480, 25);

        frame.add(lblTipoApuesta); frame.add(cmbTipoApuesta);
        frame.add(lblSeleccion); frame.add(cmbSeleccion);
        frame.add(lblMonto); frame.add(txtMonto);
        frame.add(btnGirar); frame.add(lblSaldo);
        frame.add(lblResultado);
    }

    private void agregarEventos() {
        cmbTipoApuesta.addActionListener(e -> actualizarCmbSeleccion());
        btnGirar.addActionListener(e -> accionGirar());
    }

    private void actualizarCmbSeleccion() {
        if (cmbTipoApuesta.getSelectedItem().equals("Color")) {
            lblSeleccion.setText("Seleccione color:");
            cmbSeleccion.setModel(new DefaultComboBoxModel<>(new String[]{"ROJO", "NEGRO"}));
        } else {
            lblSeleccion.setText("Seleccione par o impar:");
            cmbSeleccion.setModel(new DefaultComboBoxModel<>(new String[]{"PAR", "IMPAR"}));
        }
    }

    private void accionGirar() {
        int monto;
        try {
            monto = Integer.parseInt(txtMonto.getText());
            if (monto <= 0 || monto > ruletaControlador.getSaldoActual()) {
                JOptionPane.showMessageDialog(frame, "Monto invalido o insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ApuestaBase apuesta = crearApuesta(monto);
        if (apuesta == null) { return; }

        ruletaControlador.jugar(apuesta);

        actualizarVista(apuesta);
    }

    private ApuestaBase crearApuesta(int monto) {
        String seleccion = (String) cmbSeleccion.getSelectedItem();

        switch (seleccion) {
            case "ROJO":
                return new ApuestaRojo(monto);
            case "NEGRO":
                return new ApuestaNegro(monto);
            case "PAR":
                return new ApuestaPar(monto);
            case "IMPAR":
                return new ApuestaImpar(monto);
            default:
                return null;
        }
    }

    private void actualizarVista(ApuestaBase apuesta) {
        int numero = ruletaControlador.getUltimoNumero();
        String color = ruletaControlador.getUltimoColor();
        boolean acierto = ruletaControlador.getUltimoAcierto();
        int saldoFinal = ruletaControlador.getSaldoActual();

        String gano = acierto ? "GANASTE" : "PERDISTE";

        lblResultado.setText(String.format("Numero: %d (%s) | Apuesta: %s | %s",
                numero, color, apuesta.getEtiqueta(), gano));

        lblSaldo.setText("Saldo: " + saldoFinal);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}