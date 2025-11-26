package datos;

import modelo.Duenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DuenioDAO {

    // instancia de la clase ConexionBD
    private final ConexionBD conexionBD = new ConexionBD();

    // Aquí irían los métodos para acceder a la base de datos, a la tabla de duenios

    public Duenio extraerDuenio(int dniDuenio) { // lo buscamos por su PK que es el dni!!
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM duenios WHERE dni = ?"; // el * toma todos los
                // atributos!!
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setInt(1, dniDuenio);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) { // es necesario usar el next() porque el cursor en un
                    // principio está antes del primer registro! Como sabemos que esperamos una
                    // sola fila como resultado de la consulta, usamos el .next()
                    // una sola vez para posicionar el cursor en la fila obtenida, al igual que si lo
                    // consigue devuelve true y por ende entrará al if!!
                    // si quisiéramos recorrer todas las filas devueltas por una consulta, usaríamos un
                    // while(resultSet.next())!! FUNDAMENTAL ESTO!!
                    // es decir, el .next() avanza el cursor a la siguiente fila y devuelve true si hay
                    // una fila válida, o false si no hay más filas. Siempre que
                    // se trabaja con ResultSet se debe usar este metodo para moverse a través de los
                    // resultados.

                    // recuperamos los datos de la consulta -->
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String telefono = resultSet.getString("numTelefono");
                    // convierto el número a int para luego crear el objeto con el tipo de dato
                    // que corresponde!
                    int telefonoInt = Integer.parseInt(telefono);
                    String email = resultSet.getString("email");

                    // creamos duenio y lo devolvemps
                    return new Duenio(dniDuenio, nombre, apellido, telefonoInt, email);
                } else {
                    return null; // devolvemos null si no se encontró el dueño
                }
            } catch (Exception e) {
                System.err.println("Error al extraer el dueño: " + e);
                return null; // devolvemos null significando que no se pudo extraer el dueño
                // por un error
            } finally {
                conexionBD.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return null; // devolvemos null si no se pudo conectar a la base
        }
    }

    public boolean insertarDuenio(Duenio duenio) { // recibe un objeto Duenio para insertar!
        Connection conexion = conexionBD.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "INSERT INTO duenios (dni, nombre, apellido, numTelefono, email) " +
                        "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setInt(1, duenio.getDni());
                preparedStatement.setString(2, duenio.getNombre());
                preparedStatement.setString(3, duenio.getApellido());
                preparedStatement.setInt(4, duenio.getNumTelefono());
                preparedStatement.setString(5, duenio.getEmail());

                // si quisera insertar varios dueños a la vez, usaría un bucle para recorrer una lista de dueños
                // y en cada iteración setear los valores correspondientes y ejecutar el executeUpdate()

                preparedStatement.executeUpdate(); // ejecutamos la consulta con los datos que pasamos!
                // si llegamos acá es porque se pudo insertar el dueño, entonces
                return true;
            } catch (Exception e) {
                System.err.println("Error al insertar el dueño: " + e);
                return false; // devuelve false si hubo un error al insertar
            } finally {
                conexionBD.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return false; // devuelve false si no se pudo conectar a la base
        }
    }
}
