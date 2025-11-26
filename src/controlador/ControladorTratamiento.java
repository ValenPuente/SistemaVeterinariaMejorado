package controlador;

import datos.TratamientoDAO;
import modelo.Tratamiento;

import java.util.List;

public class ControladorTratamiento {
    // creamos instancia de TratamientoDAO que accederá a la tabla de tratamientos de la base de datos!!
    private final TratamientoDAO tratamientoDAO = new TratamientoDAO(); //

    public List<Tratamiento> obtenerTratamientos(){
        return tratamientoDAO.extraerTratamientos();
    }
}
