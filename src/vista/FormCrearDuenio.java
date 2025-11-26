package vista;

import controlador.ControladorDuenio;
import modelo.Duenio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCrearDuenio extends JFrame {
    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JPanel pnlInferior;
    private JButton guardarButton;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JLabel lblMensaje;
    private int dniDuenio; // recibimos el dni del duenio a crear que se intentó buscar en la vista
    // anterior pero no se pudo!!!
    // creamos ahora una instancia de ControladorDuenio para usar sus métodos ->
    private ControladorDuenio controladorDuenio = new ControladorDuenio();

    public FormCrearDuenio(int dniDuenio) {
        this.dniDuenio = dniDuenio; // asignamos el valor del DNI al atributo dniDuenio!
        inicializar(); // cuando se crea la instancia se inicializa la ventana

        txtDni.setText(String.valueOf(dniDuenio)); // Muestra el DNI recibido en el campo de texto!!!!!
        // lo transformamos a String porque txtDni es un JTextField y lo recibimos como entero!

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al tocar el botón de guardar dueño!!!
                // recuperamos los datos ingresados por el usuario en la ventana con .getText()!! -->
                String dni = txtDni.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();

                // convertimos a entero el dni y telefono ya que nos lo devuelve como String! ->
                int dniInt = Integer.parseInt(dni);
                int telefonoInt = Integer.parseInt(telefono);

                // creamos instancia de Duenio ->
                Duenio duenio = new Duenio(dniInt, nombre, apellido, telefonoInt, email);

                // ahora debemos llamar al metodo del controladorDuenio para agregarlo a la
                // respectiva tabla!!
                boolean retorno = controladorDuenio.crearDuenio(duenio);

                if (retorno) {
                    mostrarMensajeYSeguir(duenio);
                } else {
                    // mostramos el mensaje de algún posible error en lblMensaje!!
                    lblMensaje.setText("Error en la creación del dueño");
                }
            }
        });
    }

    private void mostrarMensajeYSeguir(Duenio duenio){
        // mostramos el mensaje de retorno
        lblMensaje.setText("Dueño creado exitosamente");
        // y deshabilitamos los botones para evitar múltiples clicks en ese tiempo de espera!!
        guardarButton.setEnabled(false);
        // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir FormMain
        javax.swing.Timer t = new javax.swing.Timer(3000, ev -> {
            dispose();
            new FormDuenioMascota(duenio).setVisible(true); // creamos instancia de FormDuenioMascota
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
