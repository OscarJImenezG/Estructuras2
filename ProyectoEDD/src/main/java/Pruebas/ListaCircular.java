package Pruebas;

import Pruebas.NodoCircular;
import javax.swing.JOptionPane;

public class ListaCircular {

    NodoCircular ultimo;

    public ListaCircular() {
        ultimo = null;
    }

    public boolean estaVacia() {
        return ultimo == null;
    }

    //Metodo para insertar nodos
    public ListaCircular insertar(int elemento) {
        NodoCircular nuevo = new NodoCircular(elemento);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        return this;
    }

    //Metodo para mostrar lista 
    public void mostrarLista() {
        NodoCircular auxiliar = ultimo.siguiente;
        String cadena = "";
        do {
            cadena = cadena + "[" + auxiliar.dato + "]->";
            auxiliar = auxiliar.siguiente;
        } while (auxiliar != ultimo.siguiente);
        JOptionPane.showMessageDialog(null, cadena, "Mostrar la lista circular", JOptionPane.INFORMATION_MESSAGE);
    }

    //Metodo para eliminar un Nodo de la Lista CIRCULAR
    public boolean eliminar(int elemento) {
        NodoCircular actual;
        boolean encontrado = false;
        actual = ultimo;
        while (actual.siguiente != ultimo && !encontrado) {
            encontrado = (actual.siguiente.dato == elemento);
            if (!encontrado) {
                actual = actual.siguiente;
            }
        }
        encontrado = (actual.siguiente.dato == elemento);
        if (encontrado) {
            NodoCircular auxiliar = actual.siguiente;
            if (ultimo == ultimo.siguiente) {
                ultimo = null;

                if (auxiliar == ultimo) {
                    ultimo = actual;
                }
                actual.siguiente = auxiliar.siguiente;
            }
            auxiliar =  null;
        }
        return encontrado == true;
    }
}
