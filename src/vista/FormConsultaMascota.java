package vista;

import controlador.ControladorConsulta;
import controlador.ControladorTratamiento;
import controlador.ControladorVeterinario;
import modelo.Mascota;
import modelo.Tratamiento;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormConsultaMascota extends JFrame {
    private Mascota mascota; // mascota a la que se le realizará la respectiva consulta
    // que agendemos en la vista!!
    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCental;
    private JPanel pnlInferior;
    private JTextField txtCondicion;
    private JComboBox boxTratamientos;
    private JLabel lblCosto;
    private JComboBox boxVeterinarios;
    private JButton agendarConsultaButton;
    private JLabel lblMensaje;
    private JLabel lblValor;

    private ControladorTratamiento controladorTratamiento = new ControladorTratamiento();
    private ControladorVeterinario controladorVeterinario = new ControladorVeterinario();
    private ControladorConsulta controladorConsulta = new ControladorConsulta();

    public FormConsultaMascota(Mascota mascota) {
        this.mascota = mascota;
        inicializar();

        // queremos que nada más se abra la ventana se carguen todos los tratamientos
        // en la boxTratamientos-->
        cargarTratamientosEnComboBox();
        cargarVeterinariosEnComboBox();

        agendarConsultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción al presionar el botón de Agendar Consulta -->

                // recuperamos los datos de los box ->
                Tratamiento tratamientoSeleccionado = (Tratamiento) boxTratamientos.getSelectedItem();
                // obtenemos el objeto completo del tratamiento seleccionado en el box!!
                String veterinarioSeleccionado = (String) boxVeterinarios.getSelectedItem();

                if (veterinarioSeleccionado == null) { // verificamos que haya veterinarios cargados
                    JOptionPane.showMessageDialog(null, "ERROR: No hay veterinarios disponibles");
                    return; // salimos del metodo si no hay veterinarios
                }

                // si hay, el formato del elemento es "id - nombre apellido", por lo que debemos
                // separar el id del resto del String para buscar el veterinario por id, usamos split
                // para separar por partes el String segín el -!!
                String[] partes = veterinarioSeleccionado.split(" - ");
                String id = partes[0]; // la primera parte es el id!!
                // lo transformo a entero
                int idVeterinario = Integer.parseInt(id);
                Veterinario veterinario = controladorVeterinario.obtenerVeterinarioPorId(idVeterinario);

                // tomamos la condición ingresada -->
                String condicion = txtCondicion.getText();

                // ahora llamamos al metodo del controlador para registrar la consulta!!
                boolean agendado = controladorConsulta.guardar(veterinario, mascota, tratamientoSeleccionado, condicion);
                if (agendado) {
                    lblMensaje.setText("Consulta agendada exitosamente.");
                    // y deshabilitamos los botones
                    agendarConsultaButton.setEnabled(false);
                    boxTratamientos.setEnabled(false);
                    boxVeterinarios.setEnabled(false);
                    txtCondicion.setEnabled(false);
                    // esperar 3s sin bloquear la UI y entonces cerrar ventana actual y abrir la ventana
                    // FormFactura
                    javax.swing.Timer t = new javax.swing.Timer(2000, ev -> {
                        dispose();
                        new FormFactura(mascota.getDuenio(), tratamientoSeleccionado).setVisible(true); // creamos instancia de FormMain y la hacemos visible!!
                    });
                    t.setRepeats(false);
                    t.start();
                } else {
                    lblMensaje.setText("Error al agendar la consulta. Intente nuevamente.");
                }
            }
        });

        boxTratamientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // queremos que al seleccionar un tratamiento en el comboBox se muestre su costo
                // en la etiqueta lblCosto -->
                Tratamiento tratamientoSeleccionado = (Tratamiento) boxTratamientos.getSelectedItem();
                // dentro del box tenemos los objetos completos! Por eso podemos recuperarlo de
                // manera directa!
                if (tratamientoSeleccionado != null) {
                    lblValor.setText(String.valueOf("VALOR:$ " + tratamientoSeleccionado.getValor()));
                } else {
                    lblValor.setText("ERROR AL CARGAR EL VALOR DEL TRATAMIENTO");
                }
            }
        });
    }

    private void cargarTratamientosEnComboBox() {
        // obtenemos primero los tratamientos desde el controlador ->
        List<Tratamiento> listaTratamientos = controladorTratamiento.obtenerTratamientos();
        // ahora recorremos la lista y agregamos el objeto completo -->
        for (Tratamiento tratamiento : listaTratamientos) {
            boxTratamientos.addItem(tratamiento);
        }
    }

    private void cargarVeterinariosEnComboBox() {
        // tenemos que recuperar los nombres de los veterinarios desde la base de datos
        List<Veterinario> veterinarios = controladorVeterinario.obtenerVeterinarios();
        // ahora los agregamos al comboBox, para eso recorremos el List que tiene la implementación
        // lógica del ArrayList elemento por elemento y los vamos agregando al comboBox -->
        for (Veterinario veterinario : veterinarios) {
            String elementoAIngresar = veterinario.getIdVeterinario() + " - " + veterinario.getNombre() + " " + veterinario.getApellido();
            boxVeterinarios.addItem(elementoAIngresar);
        }
    }

    public void inicializar() {
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}


