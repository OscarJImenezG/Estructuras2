package Pruebas;

public class NodoCircular {

    int dato;
    NodoCircular siguiente, anterior;

    public NodoCircular(int d) {
        dato = d;
        siguiente = this;

    }
}
