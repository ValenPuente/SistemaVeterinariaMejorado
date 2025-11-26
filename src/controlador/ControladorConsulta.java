package controlador;

import datos.ConsultaDAO;
import modelo.*;

public class ControladorConsulta {
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    public boolean guardar(Veterinario veterinario, Mascota mascota, Tratamiento tratamiento , String condicion) {
        // validamos que los datos no estén vacíos!!
        if (veterinario == null || mascota == null || tratamiento == null || condicion == null || condicion.isEmpty()) {
            return false; // datos inválidos
        }
        // generamos fecha del día de hoy -->
        java.time.LocalDate fechaConsulta = java.time.LocalDate.now();

        // creamos objecto Consulta
        Consulta consulta = new Consulta(mascota, veterinario, condicion, tratamiento, fechaConsulta);
        return consultaDAO.guardarConsulta(consulta);
    }

}
