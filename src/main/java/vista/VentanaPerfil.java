package vista;

import controlador.SessionController;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPerfil {

    private final SessionController session;
    private final VentanaMenu menuPadre;
    private final JFrame frame;

    private final JLabel lblUsernameTitulo = new JLabel("Usuario (Login):");
    private final JLabel lblUsername;
    private final JLabel lblNombreTitulo = new JLabel("Nombre (Editable):");
    private final JTextField txtNombre;
    private final JButton btnGuardarNombre = new JButton("Guardar");
    private final JLabel lblSaldoTitulo = new JLabel("Saldo Actual:");
    private final JLabel lblSaldo;
    private final JLabel lblRecarga = new JLabel("Recargar Saldo:");
    private final JTextField txtRecarga = new JTextField("0");
    private final JButton btnRecargar = new JButton("Recargar");

    public VentanaPerfil(SessionController session, VentanaMenu menuPadre) {
        this.session = session;
        this.menuPadre = menuPadre;
        this.frame = new JFrame("Perfil de Usuario - Black Cat");

        Usuario usuario = session.getUsuarioActual();
        this.lblUsername = new JLabel(usuario.getUsername());
        this.txtNombre = new JTextField(usuario.getNombre());
        this.lblSaldo = new JLabel(String.valueOf(usuario.getSaldo()));

        configurarVentana();
        agregarEventos();
    }

    private void configurarVentana() {
        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        lblUsernameTitulo.setBounds(30, 30, 120, 25);
        lblUsername.setBounds(160, 30, 200, 25);
        lblNombreTitulo.setBounds(30, 70, 120, 25);
        txtNombre.setBounds(160, 70, 150, 25);
        btnGuardarNombre.setBounds(315, 70, 80, 25);
        lblSaldoTitulo.setBounds(30, 110, 120, 25);
        lblSaldo.setBounds(160, 110, 200, 25);
        lblRecarga.setBounds(30, 150, 120, 25);
        txtRecarga.setBounds(160, 150, 100, 25);
        btnRecargar.setBounds(270, 150, 100, 25);

        frame.add(lblUsernameTitulo);
        frame.add(lblUsername);
        frame.add(lblNombreTitulo);
        frame.add(txtNombre);
        frame.add(btnGuardarNombre);
        frame.add(lblSaldoTitulo);
        frame.add(lblSaldo);
        frame.add(lblRecarga);
        frame.add(txtRecarga);
        frame.add(btnRecargar);
    }

    private void agregarEventos() {
        btnGuardarNombre.addActionListener(e -> guardarNombre());
        btnRecargar.addActionListener(e -> recargarSaldo());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menuPadre.refrescarDatos();
            }
        });
    }

    private void guardarNombre() {
        String nuevoNombre = txtNombre.getText();
        session.getUsuarioActual().setNombre(nuevoNombre);
        JOptionPane.showMessageDialog(frame, "Nombre actualizado");
        refrescarDatosVista();
    }

    private void recargarSaldo() {
        try {
            int monto = Integer.parseInt(txtRecarga.getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(frame, "Monto debe ser positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            session.getUsuarioActual().depositar(monto);
            JOptionPane.showMessageDialog(frame, "Recarga exitosa");
            refrescarDatosVista();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto invalido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarDatosVista() {
        Usuario usuario = session.getUsuarioActual();
        txtNombre.setText(usuario.getNombre());
        lblSaldo.setText(String.valueOf(usuario.getSaldo()));
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
