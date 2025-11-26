package datos;

import modelo.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConsultaDAO {

    private ConexionBD conexionBD = new ConexionBD();

    public boolean guardarConsulta(Consulta consulta) {
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "INSERT INTO consulta (idMascota, idVeterinario, condicion, idTratamiento, fecha)" +
                        " VALUES (?, ?, ?, ?, ?)";

                // pasamos valores
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, consulta.getMascota().getIdMascota());
                ps.setInt(2, consulta.getVeterinario().getIdVeterinario());
                ps.setString(3, consulta.getCondicion());
                ps.setInt(4, consulta.getTratamiento().getIdTratamiento());
                ps.setDate(5, java.sql.Date.valueOf(consulta.getFecha())); // convertimos LocalDate
                // a sql.Date porque fecha en la BBDD es de tipo DATE
                //
                ps.executeUpdate(); // ejecutamos la consulta
                return true; // si llegamos acá es porque se pudo guardar la consulta

            } catch (Exception e) {
                System.err.println("Error al guardar la consulta: " + e);
                return false;
            } finally {
                conexionBD.cerrarConexion(conexion);
            }
    } else {
            System.out.println("No se pudo establecer la conexión");
            return false;
        }
    }
}
