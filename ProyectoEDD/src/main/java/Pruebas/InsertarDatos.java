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

                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas == 1) {
                    System.out.println("Registro en cabecerapadre insertado correctamente.");

                    // Obtener el último ID insertado
                    int lastInsertedId = obtenerUltimoIdInsertado(connection);
                    System.out.println("ID generado para cabecerapadre: " + lastInsertedId);

                    // Imprimir los valores que se están insertando
                    System.out.println("Valores insertados en cabecerapadre:");
                    System.out.println("Número de conteo: " + numeroConteo);
                    System.out.println("Categoría Padre: " + categoriaPadre);

                    Date fechaHoraActual = new Date();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                    fechaHoraActual.setHours((fechaHoraActual.getHours()) - 1);
                    String fechaActual = formatoFecha.format(fechaHoraActual);
                    String horaActual = formatoHora.format(fechaHoraActual);
                    System.out.println("fechaActual = " + fechaActual);
                    System.out.println("horaActual = " + horaActual);
                    // Insertar los valores de los hijos aquí
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
        return -1; // En caso de error
    }

    private void insertarValoresHijos(Connection connection, int idPadre, String[] valoresHijos, String fecha, String hora) {
        try {

            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo, fecha, hora) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDatosHijos)) {
                for (String valorHijo : valoresHijos) {
                    preparedStatement.setInt(1, idPadre);
                    preparedStatement.setString(2, valorHijo);
                    preparedStatement.setString(3, fecha);
                    preparedStatement.setString(4, hora);

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
            // Insertar los datos en datoshijos junto con la fecha y hora actual en el formato correcto
            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo, fechayhora) VALUES (?, ?, ?)";
            try (PreparedStatement statementDatosHijos = conexion.prepareStatement(sqlDatosHijos)) {
                for (String valor : valores) {
                    statementDatosHijos.setInt(1, idPadre);
                    statementDatosHijos.setString(2, valor);

                    // Obtener la fecha y hora actual de la base de datos
                    String fechaHoraDesdeBD = obtenerFechaHoraDesdeBD();

                    // Convertir la fecha y hora al formato correcto ("yyyy-MM-dd HH:mm:ss.SSS")
                    String fechaHoraFormateada = formatearFechaHora(fechaHoraDesdeBD);

                    // Crear un objeto Timestamp desde la fecha y hora formateada
                    Timestamp timestamp = Timestamp.valueOf(fechaHoraFormateada);

                    statementDatosHijos.setTimestamp(3, timestamp); // Agregar la fecha y hora actual

                    statementDatosHijos.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Función para obtener la fecha y hora actual desde la base de datos
    private String obtenerFechaHoraDesdeBD() {
        String sql = "SELECT CURRENT_TIMESTAMP";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(1); // La columna 1 contiene la fecha y hora en el formato correcto
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Función para formatear la fecha y hora al formato esperado
    private String formatearFechaHora(String fechaHora) {
        // Aquí debes implementar la lógica para transformar el formato de fecha y hora si es necesario.
        // Por ejemplo, si el formato actual es "12 sept 2023 16:28:37 p. m." a "yyyy-MM-dd HH:mm:ss.SSS".
        // Puedes usar SimpleDateFormat o expresiones regulares para realizar esta transformación.
        // Luego, devuelve la fecha y hora en el formato correcto.
        // Aquí tienes un ejemplo de cómo podría verse:

        // Transformar el formato de fecha
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date fecha = (Date) formatoEntrada.parse(fechaHora);
            return formatoSalida.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }




}