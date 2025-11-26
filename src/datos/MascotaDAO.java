package datos;

import modelo.Duenio;
import modelo.Mascota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private final ConexionBD conexionBD = new ConexionBD();

    // metodo para obtener todas las mascotas de un dueño específico que se pasa por id ->
    public Object[][] obtenerMascotasPorDuenio(int dniDuenio) {
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "SELECT idMascota, tipo, nombre FROM mascota WHERE dniDuenio = ?";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setInt(1, dniDuenio);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<Object[]> listaMascotas = new ArrayList<>();
                while (resultSet.next()) {
                    Object[] mascota = new Object[3];
                    mascota[0] = resultSet.getInt("idMascota");
                    mascota[1] = resultSet.getString("tipo");
                    mascota[2] = resultSet.getString("nombre");
                    listaMascotas.add(mascota);
                }

                Object[][] mascotas = new Object[listaMascotas.size()][3];
                for (int i = 0; i < listaMascotas.size(); i++) {
                    mascotas[i] = listaMascotas.get(i);
                }
                return mascotas;
            } catch (Exception e) {
                System.err.println("Error al obtener las mascotas: " + e);
                return new Object[0][0];
            } finally {
                conexionBD.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return new Object[0][0];
        }
    }

    public boolean guardarMascota(Mascota mascota){ // recibimos el objeto mascota a guardar que tiene
        // todos los datos necesarios!
        // con el duenio obtenemos su dni que es la clave foranea en la tabla mascota!!
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String insercion = "INSERT INTO mascota (tipo, nombre, dniDuenio) VALUES (?, ?, ?)";
                // como vemos, el idMascota no se pasa porque es autoIncremental!! FUNDAMENTAL ESO!!!
                PreparedStatement ps = conexion.prepareStatement(insercion);
                ps.setString(1, mascota.getTipo());
                ps.setString(2, mascota.getNombre());
                ps.setInt(3, mascota.getDuenio().getDni()); // clave foranea!!!
                ps.executeUpdate();
                return true; // si llegamos acá es porque se pudo agregar la mascota!!
            } catch (Exception e) {
                System.err.println("Se ha producido un error al insertar en la base de datos.\n" + e);
                return false;
            } finally {
                conexionBD.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexion");
            return false;
        }

    }
}
