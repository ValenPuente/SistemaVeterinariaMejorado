package datos;

import modelo.Veterinario;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    // esta clase se encarga de gestionar el acceso a los datos de los veterinarios, trabajará con la tabla
    // veterinarios de la base de datos!!

    ConexionBD conexionDB = new ConexionBD(); // creamos instancia de la clase conexionBD
    // para conectarnos a la BBDD!!

    public boolean agregarVeterinario(Veterinario veterinario) { // recibimos una instancia de veterinario!!
        // metodo para agregar un veterinario a la tabla de veterinarios de la base!

        Connection conexion = conexionDB.conexionBBDD();
        if (conexion != null) { // verificamos si nos pudimos conectar a la base!

            // primero verificamos si ya existe porque puede que esté tranando de registrar un
            // veterinario que ya existe!!
            if (existeVeterinario(veterinario)) {
                System.out.println("El veterinario con ID " + veterinario.getIdVeterinario() + " ya existe en la base de datos.");
                return false; // no se pudo agregar porque ya existe!!
            }

            try { // lo hacemos en un bloque try para manejar los posibles errores
                String insercion = "INSERT INTO veterinarios (idVeterinario, nombre, apellido, clave) " +
                        "VALUES (?, ?, ?, ?)";
                // preparamos la consulta
                PreparedStatement ps = conexion.prepareStatement(insercion);
                // pasamos los valores a los ?
                ps.setInt(1, veterinario.getIdVeterinario());
                ps.setString(2, veterinario.getNombre());
                ps.setString(3, veterinario.getApellido());
                ps.setInt(4, veterinario.getClave());
                ps.executeUpdate(); // Ejecuta el comando SQL con los valores que
                // escribimos arriba!!
                return true; // si llegamos acá es porque se pudo agregar el veterinario!!
            } catch (SQLException e) {
                System.err.println("Se ha producido un error al insertar en la base de datos.\n" + e);
                return false;
            } finally { // bloque que se ejecuta sí o sí!! Lo hacemos para cerrar la base de datos -->
                conexionDB.cerrarConexion(conexion); // cerramos el acceso a la base de datos!!
            }
        } else {
            System.out.println("No se pudo establecer la conexion");
            return false;
        }
    }


    public boolean existeVeterinario(Veterinario veterinario) { // se recibe el veterinario a buscar!!
        // metodo que verifica si un veterinario ya se encuentra registrado en la base de datos!!

        // accedemos a la base de datos
        Connection conexion = conexionDB.conexionBBDD();
        if (conexion != null) { // verificamos si nos pudimos conectar a la base! Si es != null significa que se
            // pudo!
            try{ // lo hacemos en un bloque try para manejar los posibles errores, siempre que interactuamos con
                // una base de datos debemos hacerlo en un bloque try-catch o try-with-resources!! FUNDAMENTAL!

                // escribimos consulta
                String consulta = "SELECT COUNT(*) AS total FROM veterinarios WHERE idVeterinario = ?";
                // el ? indica que es un valor que se pasará por parámetro!!

                // preparamos la consulta
                PreparedStatement ps = conexion.prepareStatement(consulta); // creamos un objeto del tipo
                // preparedStatement para preparar la consulta y poder ejecuar la consulta que está en formato
                // string!!

                // pasamos los valores a los ? -->
                ps.setInt(1, veterinario.getIdVeterinario()); // el 1 indica la posición del ? en la
                // consulta, y el segundo parámetro es el valor que queremos pasar! ponemos setInt porque
                // estamos pasando un entero como valor, el idEmpleado que lo representa!!

                // como es una consulta que recibimos algo, entonces debemos crear un objeto del tipo
                // ResultSet para recibir el resultado de la consulta!!

                ResultSet rs = null; // lo inicializamos en null y luego le ponemos como valor el resultado de la
                // consulta!!

                rs = ps.executeQuery(); // ejecutamos la consulta y guardamos el resultado en el ResultSet!!

                if(rs.next()){ // nos movemos al primer registro del resultado de la consulta, y si se puede
                    // devuelve true!!
                    int totalRegistro = rs.getInt("total"); // obtenemos el resultado de la columna total de la
                    // consulta
                    //no existe
                    return totalRegistro > 0; // retornará true si se cumple lo que indica que existe o false
                    // indicando que no existe!!
                }else{
                    return false; // no existe
                }

            } catch (SQLException e) {
                System.err.println("Se ha producido un error al consultar en la base de datos.\n" + e); // el
                // e tiene el mensaje de error del error que manejamos, SQLException en este caso!!
                return false;
            } finally { // bloque que se ejecuta sí o sí!! Lo hacemos para cerrar la base de datos -->
                conexionDB.cerrarConexion(conexion); // cerramos el acceso a la base de datos!!
            }
        } else {
            System.out.println("No se pudo establecer la conexion");
            return false;
        }
    }

    public List<Veterinario> obtenerTodosLosVeterinarios() {
        Connection conexion = conexionDB.conexionBBDD();
        List<Veterinario> listaVeterinarios = new ArrayList<>();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM veterinarios";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) { // recorro todas las filas del resultado
                    // y tomo todas las columnas de cada fila!!
                    int idVeterinario = resultSet.getInt("idVeterinario");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    int clave = resultSet.getInt("clave");

                    // creo el objeto veterinario con los datos obtenidos
                    Veterinario veterinario = new Veterinario(idVeterinario, nombre, apellido, clave);

                    // lo agrego a la lista!
                    listaVeterinarios.add(veterinario);
                }

                return listaVeterinarios;
            } catch (SQLException e) {
                System.err.println("Error al obtener los veterinarios: " + e);
                return listaVeterinarios; // estará vacía si hubo un error
            } finally {
                conexionDB.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return listaVeterinarios; // estará vacía si no se pudo conectar
        }
    }

    public Veterinario obtenerVeterinario(int idVeterinario) {
        Connection conexion = conexionDB.conexionBBDD();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM veterinarios WHERE idVeterinario = ?";
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setInt(1, idVeterinario);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) { // si encontramos el veterinario
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    int clave = resultSet.getInt("clave");

                    return new Veterinario(idVeterinario, nombre, apellido, clave);
                } else {
                    return null; // no se encontró el veterinario
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el veterinario: " + e);
                return null; // en caso de error retornamos null
            } finally {
                conexionDB.cerrarConexion(conexion);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
            return null; // no se pudo conectar
        }
    }
}
