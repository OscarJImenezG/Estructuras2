package Pruebas;

import java.io.File;
import java.sql.*;

public class ConexionBD {

    static Connection conexion;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            String directorioBase = System.getProperty("user.dir");
            String rutaDB = directorioBase + File.separator + "estructuraDeDatos.db";

            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:" + rutaDB;
                conexion = DriverManager.getConnection(url);

                // Comprobar si la base de datos ya existe
                File archivoDB = new File(rutaDB);
                if (!archivoDB.exists()) {
                    // Si no existe, crear la base de datos
                    crearBaseDeDatos(conexion);
                }

                // Llamamos a la función que crea las tablas
                crearTablas(conexion);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Manejo de errores de conexión
            }
        }

        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión a la base de datos.");
            }
        }
    }

    private static void crearBaseDeDatos(Connection conexion) {
        // Código para crear la base de datos aquí
    }

    private static void crearTablas(Connection conexion) {
        try ( Statement statement = conexion.createStatement()) {
            // Crear las tablas aquí
            String createCabeceraPadreTable = "CREATE TABLE IF NOT EXISTS cabecerapadre ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "listaCreada INTEGER,"
                    + "categoriaPadre TEXT"
                    + ")";
            statement.execute(createCabeceraPadreTable);

            String createDatosHijosTable = "CREATE TABLE IF NOT EXISTS datoshijos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "idPadre INTEGER,"
                    + "datosHijo TEXT,"
                    + "fecha DATE," // Campo de fecha
                    + "hora TIME" // Campo de hora
                    + ")";
            statement.execute(createDatosHijosTable);

            System.out.println("Tablas creadas con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existenArreglos() {
        try ( Statement statement = conexion.createStatement()) {
            String consulta = "SELECT COUNT(*) FROM cabecerapadre WHERE categoriaPadre = 'Arreglo'";
            ResultSet resultSet = statement.executeQuery(consulta);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
