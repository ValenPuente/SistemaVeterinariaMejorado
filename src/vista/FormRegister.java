package vista;

import controlador.ControladorVeterinario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegister extends JFrame {
    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JPanel pnlInferior;
    private JTextField txtNombre;
    private JTextField txtClave;
    private JButton btnRegistrase;
    private JButton btnIniciarSesion;
    private JLabel lblMensaje;
    private JTextField txtApellido;
    private JTextField txtId;

    // creamos instancia de controladorVeterinarios para usar sus métodos ->
    ControladorVeterinario controladorVeterinarios = new ControladorVeterinario();

    public FormRegister() {
        inicializar(); // metodo que se crea para inicializar la ventana

        btnRegistrase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // accíon que ocurre al presionar el botón de registrarse
                // recuperamos los datos ingresados por el usuario en la ventana con .getText()!! -->
                String id = txtId.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String clave = txtClave.getText();
                // llamamos al metodo del controladorVeterinarios para agregarlo a la respectiva tabla!
                // los mandamos como String para verificar primero si están vacíos!!
                // luego el controlador se encargará de convertirlos a int el id y la clave
                // para que sean almacenados correctamente en la base de datos con su tipo
                // de dato correcto!!
                String retorno = controladorVeterinarios.registrarVeterinario(id, nombre, apellido, clave);

                if (retorno.equals("Veterinario registrado exitosamente")) {
                    mostrarMensajeYSeguir(retorno);
                } else{
                    // mostramos el mensaje de algún posible error en lblMensaje!!
                    lblMensaje.setText(retorno);
                }
            }
        });

        // botón de iniciar sesión ->
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // accíon que ocurre al presionar el botón de iniciar sesión
                // debemos cerrar la ventana y abrir una nueva que sea destinada al inicio de sesión -->
                dispose(); // cierra la ventana actual de register!!!
                // creamos instancia de la clase FormSignIn -->
                FormSignIn formSignIn = new FormSignIn();
                // ponemos visibilidad en true ->
                formSignIn.setVisible(true);

            }
        });
    }

    private void mostrarMensajeYSeguir(String retorno){
        // mostramos el mensaje de retorno
        lblMensaje.setText(retorno);
        // y deshabilitamos los botones
        btnRegistrase.setEnabled(false);
        btnIniciarSesion.setEnabled(false);
        // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir FormMain
        javax.swing.Timer t = new javax.swing.Timer(2000, ev -> {
            dispose();
            new FormBuscarDuenio().setVisible(true); // creamos instancia de FormMain y la hacemos visible!!
        });
        t.setRepeats(false);
        t.start();
    }

    public void inicializar() { // esto es para inicializar la ventana, se pone siempre!
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
