package datos;

import modelo.Tratamiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO {
    private ConexionBD conexionBD = new ConexionBD();

    public List<Tratamiento> extraerTratamientos() {
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM tratamiento";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(); // guardamos el resultado
                // de la consulta

                List<Tratamiento> listaTratamientos = new ArrayList<>();
                while (resultSet.next()) { // recorremos todas las filas del resultado
                    int idTratamiento = resultSet.getInt("idTratamiento");
                    String nombreTratamiento = resultSet.getString("tratamiento");
                    double valor = resultSet.getDouble("valor");

                    Tratamiento tratamiento = new Tratamiento(idTratamiento, nombreTratamiento, valor);
                    listaTratamientos.add(tratamiento);
                }

                return listaTratamientos;
            } catch (Exception e) {
                System.err.println("Error al obtener los tratamientos: " + e);
                return new ArrayList<>(); // retornamos una lista vacía en caso de error
            } finally { // cerramos la conexión!
                conexionBD.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return new ArrayList<>(); // retornamos una lista vacía si no se pudo conectar
        }
    }
}
