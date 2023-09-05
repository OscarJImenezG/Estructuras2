package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBaseDeDatos {

    public static void main(String[] args) {
        Connection conexion = obtenerConexion();
        if (conexion != null) {
            System.out.println("Conexión a la base de datos establecida correctamente.");
            verificarTablas(conexion);
            cerrarConexion(conexion);
        }
    }

    private static Connection obtenerConexion() {
        String directorioBase = System.getProperty("user.dir");
        String rutaDB = directorioBase + "\\estructuraDeDatos.db";
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + rutaDB;
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void verificarTablas(Connection conexion) {
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table';")) {

            System.out.println("Tablas disponibles en la base de datos:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}