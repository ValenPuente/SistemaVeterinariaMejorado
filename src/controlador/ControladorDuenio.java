package controlador;

import datos.DuenioDAO;
import modelo.Duenio;

public class ControladorDuenio {
    // creamos instancia de DuenioDAO que accederá a la tabla de duenios de la base de datos!!
    private final DuenioDAO duenioDAO = new DuenioDAO(); // cuando se crea
    // una instancia de ControladorDuenio, se crea una instancia de DuenioDAO!!


    public ControladorDuenio() {
        // vacío!
    }

    public Duenio buscarDuenio(int dniDuenio) { // lo buscamos por su pk que es el dni!!
        // metodo para buscar un duenio por su id, que es su clave primaria (PK)!!!
        return duenioDAO.extraerDuenio(dniDuenio); // retornará null si no existe!
    }

    public boolean crearDuenio(Duenio duenio) {
        // metodo para crear un nuevo dueño en la base de datos!!

        return duenioDAO.insertarDuenio(duenio);
    }
}


