package Pruebas;

import java.sql.*;

public class InsertarDatos {
    private Connection conexion;

    public InsertarDatos(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarDatosCampopadre(String categoriaPadre, int numeroConteo, String[] valoresHijos) {
        try {
            String sqlCabeceraPadre = "INSERT INTO cabecerapadre (listaCreada, categoriaPadre) VALUES (?, ?)";

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:estructuraDeDatos.db");
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlCabeceraPadre, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setInt(1, numeroConteo);
                preparedStatement.setString(2, categoriaPadre);

                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas == 1) {
                    System.out.println("Registro en cabecerapadre insertado correctamente.");

                    // Obtener el último ID insertado
                    int lastInsertedId = obtenerUltimoIdInsertado(connection);
                    System.out.println("ID generado para cabecerapadre: " + lastInsertedId);

                    // Insertar los valores de los hijos aquí
                    insertarValoresHijos(connection, lastInsertedId, valoresHijos);
                } else {
                    System.out.println("No se pudo insertar en cabecerapadre.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int obtenerUltimoIdInsertado(Connection connection) {
        String sql = "SELECT last_insert_rowid()";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // En caso de error
    }


    private void insertarValoresHijos(Connection connection, int idPadre, String[] valoresHijos) {
        try {
            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDatosHijos)) {
                for (String valorHijo : valoresHijos) {
                    preparedStatement.setInt(1, idPadre);
                    preparedStatement.setString(2, valorHijo);

                    int filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas == 1) {
                        System.out.println("Registro en datoshijos insertado correctamente.");
                    } else {
                        System.out.println("No se pudo insertar en datoshijos.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarValorArreglo(int idPadre, String[] valores) {
        try {
            // Insertar los datos en datoshijos
            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo) VALUES (?, ?)";
            try (PreparedStatement statementDatosHijos = conexion.prepareStatement(sqlDatosHijos)) {
                for (String valor : valores) {
                    statementDatosHijos.setInt(1, idPadre);
                    statementDatosHijos.setString(2, valor);
                    statementDatosHijos.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}