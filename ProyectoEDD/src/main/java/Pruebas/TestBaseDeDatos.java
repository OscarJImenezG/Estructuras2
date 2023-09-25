package Pruebas;

import java.sql.*;

public class TestBaseDeDatos {

    public static void main(String[] args) {
        Connection conexion = obtenerConexion();
        if (conexion != null) {
            System.out.println("Conexión a la base de datos establecida correctamente.");
            listarTablasYCampos(conexion);
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

    private static void listarTablasYCampos(Connection conexion) {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet tablas = metaData.getTables(null, null, null, new String[]{"TABLE"});

            System.out.println("Tablas disponibles en la base de datos:");
            while (tablas.next()) {
                String nombreTabla = tablas.getString("TABLE_NAME");
                System.out.println("Tabla: " + nombreTabla);

                ResultSet campos = metaData.getColumns(null, null, nombreTabla, null);
                System.out.println("Campos:");
                while (campos.next()) {
                    String nombreCampo = campos.getString("COLUMN_NAME");
                    String tipoCampo = campos.getString("TYPE_NAME");
                    System.out.println("  " + nombreCampo + " (" + tipoCampo + ")");
                }
                System.out.println();
                mostrarContenidoTablas(conexion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void mostrarContenidoTablas(Connection conexion) {
        try {
            mostrarContenidoTabla(conexion, "cabecerapadre");
            mostrarContenidoTabla(conexion, "datoshijos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarContenidoTabla(Connection conexion, String nombreTabla) throws SQLException {
        String sql = "SELECT * FROM " + nombreTabla;
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Contenido de la tabla '" + nombreTabla + "':");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Imprimir encabezados de columnas
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(((ResultSetMetaData) metaData).getColumnName(i) + "\t");
            }
            System.out.println();

            // Imprimir datos de la tabla
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
            System.out.println();
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