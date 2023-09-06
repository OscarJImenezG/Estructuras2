package Pruebas;

import java.sql.Connection;

public class Inicio {
    private Connection conexion;

    public Inicio(Connection conexion) {
        this.conexion = conexion;
    }

    public void mostrarVentana() {
        PanelPrincipal panelPrincipal = new PanelPrincipal(conexion);
        panelPrincipal.mostrarVentana();
    }

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();
        if (conexion != null) {
            Inicio inicio = new Inicio(conexion);
            inicio.mostrarVentana();
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}