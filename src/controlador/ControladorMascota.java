package controlador;

import datos.MascotaDAO;
import modelo.Duenio;
import modelo.Mascota;

public class ControladorMascota {

    private MascotaDAO mascotaDAO = new MascotaDAO();

    // metodo para obtener todas las mascotas de un dueño
    public Object[][] obtenerMascotasPorDuenio(int dniDuenio) {
        return mascotaDAO.obtenerMascotasPorDuenio(dniDuenio);
    }

    public boolean guardar(Mascota mascota){
        // validamos datos antes de guardar porque puede que estén vacíos!!
        if (mascota.getTipo() == null || mascota.getTipo().isEmpty() ||
            mascota.getNombre() == null || mascota.getNombre().isEmpty() ||
            mascota.getDuenio() == null) {
            return false; // datos inválidos
        } // verificamos que el dueño no sea null o tenga datos vacíos!!!
        return mascotaDAO.guardarMascota(mascota);
    }




}
