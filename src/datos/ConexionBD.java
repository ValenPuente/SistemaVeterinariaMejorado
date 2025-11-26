package datos;

import java.sql.Connection;

public class ConexionBD {
    // esta clase la usamos para conectarnos con la base de datos

    // atributos de la clase ->
    // driver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // base de datos
    private static final String BBDD = "jdbc:mysql://localhost:3306/";
    // nombre de la base de datos
    private static final String DB_NAME = "veterinaria";
    // usuario
    private static final String USUARIO = "root";
    // password
    private static final String PASSWORD = "mysql";

    // métodos de la clase, una para la conexion y otro para cerrar la conexión -->
    public Connection conexionBBDD() { // para conectar con la BBDD y acceder a la misma!!!
        // Devuelve un objeto del tipo Connection! FUNDAMENTAL ESO!
        java.sql.Connection conexion = null;
        try {
            Class.forName(DRIVER);
            conexion = java.sql.DriverManager.getConnection(BBDD + DB_NAME, USUARIO, PASSWORD);
            System.out.println("conexion ok a " + DB_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("Error en DRIVER\n" + e);
        } catch (java.sql.SQLException e) {
            System.err.println("Error al conectar con la BBDD\n" + e);
        }
        return conexion;
    }

    public void cerrarConexion(Connection conexion) { // para cerrar la conexión con la BBDD
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Se ha producido un error al cerrar la conexión con la base de datos." + e);
        }
    }



}
