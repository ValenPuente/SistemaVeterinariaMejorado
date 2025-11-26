package vista;

import controlador.ControladorVeterinario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormSignIn extends JFrame {
    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JPanel pnlInferior;
    private JTextField txtNombre;
    private JTextField txtClave;
    private JButton btnInicioDeSesion;
    private JLabel lblMensaje;
    private JTextField txtApellido;
    private JTextField txtID;

    // creamos instancia de controladorVeterinarios para usar sus métodos ->
    ControladorVeterinario controladorVeterinarios = new ControladorVeterinario();

    public FormSignIn() {
        inicializar();
        btnInicioDeSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al toca el botón de inicio de sesión!!!
                // recuperamos los datos ingresados por el veterinario -->
                String id = txtID.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String clave = txtClave.getText();

                // llamamos al controlador para verificar si el inicio de sesión es correcto!!
                String retorno = controladorVeterinarios.iniciarSesionVeterinario(id, nombre, apellido, clave);

                // ahora si el retorno fue exitoso, podemos proceder a abrir la ventana principal
                // de la aplicación!!
                if (retorno.equals("Inicio de sesión exitoso")) {
                    mostrarMensajeYSeguir(retorno);
                } else { // significa que hubo algún error -->
                    // mostramos el mensaje de error en lblMensaje!!
                    lblMensaje.setText(retorno);
                }
            }
        });
    }

    private void mostrarMensajeYSeguir(String retorno){
        // mostramos el mensaje de retorno
        lblMensaje.setText(retorno);
        // y deshabilitamos los botones
        btnInicioDeSesion.setEnabled(false);
        // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir FormMain
        javax.swing.Timer t = new javax.swing.Timer(2000, ev -> {
            dispose();
            new FormBuscarDuenio().setVisible(true); // creamos instancia de FormMain y la hacemos visible!!
        });
        t.setRepeats(false);
        t.start();
    }

    public void inicializar() {
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
