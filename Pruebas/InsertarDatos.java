package Pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertarDatos {
    private Connection conexion;

    public InsertarDatos(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarDatosCampopadre(String categoriaPadre, int numeroConteo, String[] valores) {
        try {
            // Insertar los datos en cabecerapadre
            String sqlCabeceraPadre = "INSERT INTO cabecerapadre (listaCreada, categoriaPadre) VALUES (?, ?)";
            PreparedStatement statementCabeceraPadre = conexion.prepareStatement(sqlCabeceraPadre, PreparedStatement.RETURN_GENERATED_KEYS);
            statementCabeceraPadre.setInt(1, numeroConteo);
            statementCabeceraPadre.setString(2, categoriaPadre);
            statementCabeceraPadre.executeUpdate();

            // Obtener el ID generado para el padre
            int idPadre = -1;
            ResultSet generatedKeys = statementCabeceraPadre.getGeneratedKeys();
            if (generatedKeys.next()) {
                idPadre = generatedKeys.getInt(1);
            }

            // Llamar a insertarValorArreglo con el ID generado y los valores
            insertarValorArreglo(idPadre, valores);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarValorArreglo(int idPadre, String[] valores) {
        try {
            // Insertar los datos en datoshijos
            String sqlDatosHijos = "INSERT INTO datoshijos (idPadre, datosHijo) VALUES (?, ?)";
            PreparedStatement statementDatosHijos = conexion.prepareStatement(sqlDatosHijos);

            for (String valor : valores) {
                statementDatosHijos.setInt(1, idPadre);
                statementDatosHijos.setString(2, valor);
                statementDatosHijos.addBatch();
            }

            statementDatosHijos.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}