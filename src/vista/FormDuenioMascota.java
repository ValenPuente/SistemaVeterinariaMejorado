package vista;

import controlador.ControladorMascota;
import modelo.Duenio;
import modelo.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDuenioMascota extends JFrame{
    // atributos ->
    private final Duenio duenio; // tenemos un atributo de duenio, que se refiere al duenio encontrado
    private JPanel pnlPrincipal;
    private JPanel pnlSuperior;
    private JPanel pnlCentral;
    private JPanel pnlInferior;
    private JLabel txtDni;
    private JLabel txtNombre;
    private JLabel txtApellido;
    private JLabel txtTelefono;
    private JLabel txtEmail;
    private JTable showTable;
    private JTextField txtTipoMascota;
    private JTextField txtNombreMascota;
    private JButton guardarButton;
    private JPanel pnlInferior2;
    private JLabel lblMensaje;
    private JButton realizarConsultaButton;
    private JLabel txtError;

    // creamos como atributo una instancia del controladorMascota para usar sus métodos ->
    private ControladorMascota controladorMascota = new ControladorMascota();

    // constructor ->
    public FormDuenioMascota(Duenio duenio) {
        this.duenio = duenio;
        inicializar();
        // mostramos los datos del dueño en las etiquetas correspondientes nada mas abrir la ventana!!
        // transformo dni del duenio en string para poder ponerlo en la etiqueta!! Ya que
        // recordemos que en las etiquetas van String porque es texto!!->
        String dniString = String.valueOf(duenio.getDni());
        txtDni.setText("DNI: " + dniString);
        txtNombre.setText("Nombre: " + duenio.getNombre());
        txtApellido.setText("Apellido: " + duenio.getApellido());
        // transformo el numTelefono en String porque va a ir en una etiqueta!! ->
        String telefono = String.valueOf(duenio.getNumTelefono());
        txtTelefono.setText("Número de teléfono: " + telefono);
        txtEmail.setText("Email: " + duenio.getEmail());

        // creamos la tabla nada mas se abre la ventana, y de esta manera si el duenio
        // ya tiene cargado mascotas podemos mostrarlas de manera directa ->
        crearTablaMascotas(duenio); // pasamos como parámetro el duenio para obtener las respectivas
        // mascotas si es que tiene en un principio


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción que ocurre al presionar el botón de guardar mascota
                // recuperamos los datos ingresados por el usuario en la ventana con .getText()
                String tipo = txtTipoMascota.getText();
                String nombreMascota = txtNombreMascota.getText();
                // creamos mascota con el duenio correspondiente y datos recuperados (usamos
                // el constructor que no tiene el id!!
                Mascota mascota = new Mascota(tipo, nombreMascota, duenio);
                // llamamos al controlador para guardar la mascota asociada al dueño actual en
                // la base de datos
                boolean retorno = controladorMascota.guardar(mascota);
                // pasamos como parámetros el tipo, nombre y el dueño
                // el idMascota es autoIncremental! No es necesario pasarlo, no se lo pedimos al
                // usuario!
                if (retorno) { // si se pudo guardar en la base -->
                    // si se guardó correctamente, mostramos mensaje de éxito
                    lblMensaje.setText("Mascota guardada exitosamente.");
                    // limpiamos los campos de texto para ingresar otra mascota si se desea
                    txtTipoMascota.setText("");
                    txtNombreMascota.setText("");
                    // actualizamos la tabla para mostrar la nueva mascota agregada, llamando
                    // al metodo para crearla nuevamente donde se buscan las mascotas del dueño
                    // otra vez!
                    crearTablaMascotas(duenio);
                } else {
                    // si hubo un error al guardar, mostramos mensaje de error
                    lblMensaje.setText("Error al guardar la mascota. Intente nuevamente.");
                }

            }
        });

        realizarConsultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // acción que ocurre al presionar el botón de realizar consulta
                // tenemos que recuperar la fila que hizo click el usuario en la tabla
                int filaSeleccionada = showTable.getSelectedRow(); // me devuelve
                // -1 si no hay ninguna fila seleccionada!! Si hay, devuelve el número de la fila
                // seleccionada

                if (filaSeleccionada == -1){
                    txtError.setText("Error: Debe seleccionar una mascota de la tabla.");
                }else{
                    // si hay una fila seleccionada, los datos para crear la Mascota y pasarla a
                    // la otra vista -->
                    int idMascota = (int) showTable.getValueAt(filaSeleccionada, 0); // está
                    // en la columna 0 porque es la primera columna de la tabla!
                    String tipo = (String) showTable.getValueAt(filaSeleccionada, 1);
                    String nombreMascota = (String) showTable.getValueAt(filaSeleccionada,2);
                    // creamos la instancia de Mascota con los datos obtenidos
                    Mascota Mascota = new Mascota(idMascota, tipo, nombreMascota, duenio);

                    // ahora cerramos la ventana y creamos la instancia de FormConsultaMascota
                    dispose(); // cerramos la ventana actual
                    FormConsultaMascota formConsultaMascota = new FormConsultaMascota(Mascota);
                    formConsultaMascota.setVisible(true); // ponemos visibilidad en true para mostrarla
                }
            }
        });
    }

    private void crearTablaMascotas(Duenio duenio) {
        // Obtener los datos de las mascotas del dueño (si ya tiene guardadas previamente aparecerán
        // al buscarlas porque es algo que se hace nada mas se crea la ventana!)

        Object[][] datosMascotas = controladorMascota.obtenerMascotasPorDuenio(duenio.getDni());
        // Object[][] -> Object significa que puede contener cualquier tipo de dato (String, int, etc)
        // y [][] significa que es una matriz (filas y columnas), un array de arrays!!!
        // por lo tanto creamos un array de array que puede contener cualquier tipo de dato!!

        // Definir los nombres de las columnas de la tabla ->
        String[] columnas = {"ID Mascota", "Tipo", "Nombre"};

        // Crear el modelo de la tabla con los datos obtenidos
        DefaultTableModel modelo = new DefaultTableModel(datosMascotas, columnas);
        // Asignar el modelo a la tabla
        showTable.setModel(modelo);
    }

    public void inicializar() {
        setContentPane(pnlPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
