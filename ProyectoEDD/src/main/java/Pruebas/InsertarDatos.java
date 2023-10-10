package Pruebas;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

                System.out.println("Valores recibidos en insertarDatosCampopadre:");
                System.out.println("Número de conteo: " + numeroConteo);
                System.out.println("Categoría Padre: " + categoriaPadre);
                System.out.println("Valores de hijos:");

                for (String valorHijo : valoresHijos) {
                    System.out.println("  - " + valorHijo);
                }

                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas == 1) {
                    System.out.println("Registro en cabecerapadre insertado correctamente.");

                    int lastInsertedId = obtenerUltimoIdInsertado(connection);
                    System.out.println("ID generado para cabecerapadre: " + lastInsertedId);

                    Date fechaHoraActual = new Date();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                    fechaHoraActual.setHours((fechaHoraActual.getHours()) - 1);
                    String fechaActual = formatoFecha.format(fechaHoraActual);
                    String horaActual = formatoHora.format(fechaHoraActual);
                    System.out.println("fechaActual = " + fechaActual);
                    System.out.println("horaActual = " + horaActual);
                    insertarValoresHijos(connection, lastInsertedId, valoresHijos, fechaActual, horaActual);
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
        return -1;
    }

    private void insertarValoresHijos(Connection connection, int idPadre, String[] valoresHijos, String fecha, String hora) {
        try {
            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo, fecha, hora) VALUES (?, ?, ?, ?)";

            StringBuilder datosConcatenados = new StringBuilder();

            for (String valorHijo : valoresHijos) {
                if (valorHijo != null) {
                    if (datosConcatenados.length() > 0) {
                        datosConcatenados.append(", ");
                    }
                    datosConcatenados.append(valorHijo);
                } else {
                    System.out.println("Valor de datosHijo nulo, no se insertará en datoshijos.");
                }

                System.out.println("ValorHijo recibido: " + valorHijo);
            }

            System.out.println("ID Padre: " + idPadre);
            System.out.println("Datos concatenados: " + datosConcatenados.toString());
            System.out.println("Fecha: " + fecha);
            System.out.println("Hora: " + hora);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDatosHijos)) {
                preparedStatement.setInt(1, idPadre);
                preparedStatement.setString(2, datosConcatenados.toString()); // Concatenar los valores
                preparedStatement.setString(3, fecha);
                preparedStatement.setString(4, hora);

                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas == 1) {
                    System.out.println("Registro en datoshijos insertado correctamente.");
                } else {
                    System.out.println("No se pudo insertar en datoshijos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}