package Pruebas;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Pilas {

     private String[] pilas;
    private int cima = 0;
    boolean bandera = false;
    private static final int TAMANIO_ARREGLO = 10;

    public Pilas() {
        pilas = new String[TAMANIO_ARREGLO];
    }

    public void push(String entrada) {
        if (pilas[cima] == null && cima != TAMANIO_ARREGLO) {
            pilas[cima] = entrada;
            System.out.println("Ingresa: " + entrada);
            if (cima != TAMANIO_ARREGLO - 1) {
                cima++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La pila esta llena", "Pila llena", JOptionPane.OK_OPTION);
        }
    }

    public void pop() {
        if (cima != 0 && cima != TAMANIO_ARREGLO - 1) {
            if (bandera != true) {
                cima--;
            } else {
                bandera = false;
            }
            System.out.println("Sale: " + pilas[cima]);
            pilas[cima] = null;
        } else {
            if (cima == TAMANIO_ARREGLO - 1) {
                System.out.println("Sale: " + pilas[cima]);
                pilas[cima] = null;
                cima--;
                bandera = true;
            } else {
                JOptionPane.showMessageDialog(null, "La pila esta vacia", "Pila vacia", JOptionPane.OK_OPTION);
            }
        }
    }

    public String peek() {
        if (cima == 0 || cima == TAMANIO_ARREGLO - 1) {
            return pilas[cima];
        } else {
            return pilas[cima - 1];
        }
    }

    @Override
    public String toString() {
        String valor = "";
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (pilas[i] != null) {
                valor += "[" + pilas[i] + "] ";
            } else {
                break;
            }
        }
        return valor;
    }

    public String[] conexionAIG() {
        int contador = 0;
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (pilas[i] != null) {
                contador++;
            } else {
                break;
            }
        }
        String[] valores = new String[contador];
        if (contador > 0) {
            for (int i = 0; i < contador; i++) {
                valores[i] = pilas[i];
            }
        }
        return valores;
    }

    public static void main(String[] args) {
        Pilas pila = new Pilas();
        System.out.println(pila.peek());
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        pila.push("hola");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.pop();
        System.out.println(pila.peek());
        pila.pop();
        pila.pop();
        pila.push("hola1");
        pila.push("hola2");
        pila.push("hola3");
        pila.push("hola4");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("hola5");
        pila.pop();
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.pop();
        pila.pop();
        pila.pop();
        pila.pop();
        pila.push("hola5");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("not null");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("not name");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("try");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("sin");
        System.out.println(pila.toString());
        System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.pop();
        pila.pop();
        pila.pop();
        System.out.println(pila.toString());
          System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.pop();
        pila.pop();
        System.out.println(pila.toString());
          System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
        pila.push("sin");
        System.out.println(pila.toString());
          System.out.println(Arrays.toString(pila.conexionAIG()));
        System.out.println(pila.peek());
    
    }
}
