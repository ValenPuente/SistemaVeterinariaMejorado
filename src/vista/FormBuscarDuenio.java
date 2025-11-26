package vista;

import controlador.ControladorDuenio;
import modelo.Duenio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormBuscarDuenio extends JFrame {

    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JButton buscarButton;
    private JTextField txtDni;
    private JLabel lblMensaje;


    // instancia del controlador
    ControladorDuenio controladorDuenio = new ControladorDuenio();

    public FormBuscarDuenio() {
        inicializar();

        buscarButton.addActionListener(new ActionListener() { // acción al tocar el botón de
            // buscar dueño!!!

            @Override
            public void actionPerformed(ActionEvent e) {
                // recuperamos dato del DNI -->
                String dni = txtDni.getText();

                // lo transformo en un entero porque me lo devuelve como String ->
                int dniInt = Integer.parseInt(dni);

                // una vez lo recuperamos tenemos que ver si existe en la base de datos! ->
                Duenio duenio = controladorDuenio.buscarDuenio(dniInt); // recuperamos el duenio!!

                // recuperamos el duenio como instancia tal que ahora
                // debemos abrir la siguiente ventana con los datos del dueño encontrado
                if (duenio != null) { // significa que se encontró el dueño!!
                    mostrarMensajeYSeguir(duenio);
                } else {
                    mostrarMensajeYSeguir2(dniInt);
                }
            }
        });
    }

    private void mostrarMensajeYSeguir(Duenio duenio){
        // mostramos el mensaje de retorno
        lblMensaje.setText("Dueño encontrado exitosamente");
        // y deshabilitamos los botones para evitar múltiples clicks en ese tiempo de espera!!
        buscarButton.setEnabled(false);
        // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir FormMain
        javax.swing.Timer t = new javax.swing.Timer(3000, ev -> {
            dispose();
            new FormDuenioMascota(duenio).setVisible(true); // creamos instancia de FormDuenioMascota
            // y pasamos como atributo de la clase el respectivo duenio que se creó!!!
        });
        t.setRepeats(false);
        t.start();
    }

    private void mostrarMensajeYSeguir2(int dniInt){
        // mostramos el mensaje de retorno
        lblMensaje.setText("No existe dueño con ese DNI. Redirigiendo a creación...");
        // y deshabilitamos los botones para evitar múltiples clicks en ese tiempo de espera!!
        buscarButton.setEnabled(false);
        // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir FormMain
        javax.swing.Timer t = new javax.swing.Timer(3000, ev -> {
            dispose();
            new FormCrearDuenio(dniInt).setVisible(true); // creamos instancia de FormDuenioMascota
            // y pasamos como atributo de la clase el respectivo duenio que se creó!!!
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
