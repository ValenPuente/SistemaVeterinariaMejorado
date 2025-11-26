package controlador;

import datos.VeterinarioDAO;
import modelo.Veterinario;

import java.util.List;

public class ControladorVeterinario { // controlador que actuará de intermediario entre la capa vista
    // específicamente en las clases FormRegister y FormSignIn, y la capa datos con la clase VeterinarioDAO
    // que accederá a la base de datos!!!

    // creamos clase de veterinarioDAO para usar sus métodos que acceden a la base de datos
    // para validar o ingresar un nuevo veterinario ->
    private final VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

    public ControladorVeterinario() {
        // constructor vacío!!
    }

    public String registrarVeterinario(String id, String nombre, String apellido, String clave) {
        // metodo para registrar un nuevo veterinario!!

        // primero validamos que TODOS los datos no estén vacíos!!
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || clave.isEmpty()) {
            return "ERROR: Todos los campos son obligatorios";
        }

        // transformarmos a enteros el id y la clave!!
        int idVeterinario = Integer.parseInt(id);
        int claveEmpleado = Integer.parseInt(clave);

        // creamos instancia de veterinario ->

        Veterinario vet = new Veterinario(idVeterinario, nombre, apellido, claveEmpleado);

        // llamamos al metodo de la clase VeterinarioDAO para registrar el veterinario

        boolean agregado = veterinarioDAO.agregarVeterinario(vet);
        if (agregado) {
            return "Veterinario registrado exitosamente"; //retornamos este String a la capa vista!!
        } else {
            return "ERROR: Ya existe un veterinario con ese ID";
        }
    }

    public String iniciarSesionVeterinario(String id, String nombre, String apellido, String clave) {
        // metodo para el inicio de sesión de los veterinarios!!

        // verificamos que los datos no estén vacíos!!
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || clave.isEmpty()) {
            return "ERROR: Todos los campos son obligatorios";
        }

        // ahora transformarmos a enteros el id y la clave para crear el veterinario como corresponde!
        int idVeterinario = Integer.parseInt(id);
        int claveEmpleado = Integer.parseInt(clave);

        // creamos instancia de veterinario ->
        Veterinario vet = new Veterinario(idVeterinario, nombre, apellido, claveEmpleado);

        // ahora usamos el metodo del veterinarioDAO para verificar si el veterinario existe!!
        if (veterinarioDAO.existeVeterinario(vet)) {
            return "Inicio de sesión exitoso"; // retornamos este String a la capa vista!!
        } else {
            return "ERROR: Datos de inicio de sesión incorrectos";
        }
    }

    public List<Veterinario> obtenerVeterinarios() {
        return veterinarioDAO.obtenerTodosLosVeterinarios();
    }

    public Veterinario obtenerVeterinarioPorId(int idVeterinario) {
        return veterinarioDAO.obtenerVeterinario(idVeterinario);
    }
}

