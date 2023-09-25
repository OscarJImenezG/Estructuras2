package Pruebas;

import javax.swing.JOptionPane;

public class Pilas {

    private Integer[] pilas;
    private Pilas pila;

    public Pilas() {
        //Creamos el arreglo de enteros para trabajar sobre el.
        pilas = new Integer[100];
    }

    public void push(int entrada) {
        if (pilas[pilas.length - 1] == null) {
            pilas[pilas.length - 1] = entrada;
            for (int i = pilas.length - 1; i > 0; i--) {
                if (pilas[i - 1] == null) {
                    pilas[i - 1] = pilas[i];
                    pilas[i] = null;
                } else {
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La pilas esta llena", "Pila llena", JOptionPane.OK_OPTION);
        }
    }

    public void pop() {
        if (pilas[pilas.length - 1] == null) {
            for (int i = pilas.length - 1; i > 0; i--) {
                if (pilas[i - 1] != null) {
                    pilas[i - 1] = null;
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La pila esta vacia", "Pila vacia", JOptionPane.OK_OPTION);
        }

    }

    public Integer peek() {
        for (int i = pilas.length - 1; i > 0; i--) {
            if (pilas[i] != null) {
                return pilas[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String valores = " ";
        for (int i = 0; i < pilas.length; i++) {
            if (pilas[i] != null) {
                if (i == 0) {
                    valores += "[" + pilas[i];
                } else if (i != pilas.length - 1 && pilas[i + 1] != null) {
                    valores += ", " + pilas[i];
                } else {
                    valores += ", " + pilas[i] + "]";
                }
            } else {
                if (i == 1) {
                    valores += "]";
                }
                break;
            }
        }
        return valores;
    }

    public static void main(String[] args) {
        Pilas pila;
        int key = 0;
        pila = new Pilas();
        pila.crearPila();
        for (int i = 0; i <= 0;) {
            key = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero del metodo output:\n1. Peek\n2. Push\n3. Pop\n4. Salir"));
            char[] digitos = Integer.toString(key).toCharArray();
            for (int j = 0; j < Integer.toString(key).length(); j++) {
                if (digitos[j] == '0' || digitos[j] == '1' || digitos[j] == '2' || digitos[j] == '3' || digitos[j] == '4' || digitos[j] == '5' || digitos[j] == '6' || digitos[j] == '7' || digitos[j] == '8' || digitos[j] == '9') {
                    pila.metodosOut(key);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad numerica", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }

    }

    public void crearPila() {
        pila = new Pilas();
    }

    public void metodosOut(int k) {
        switch (k) {
            case 1:
                JOptionPane.showMessageDialog(null, "El valor es: " + pila.peek());
                break;

            case 2:
                int eque = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el valor a agregar:"));
                pila.push(eque);
                JOptionPane.showMessageDialog(null, "Pila actualizada:" + pila.toString());
                break;

            case 3:
                int deque = Integer.parseInt(JOptionPane.showInputDialog(null, "¡Esta seguro de eliminar un dato de la cola?", JOptionPane.YES_NO_OPTION));
                if (deque == JOptionPane.YES_OPTION) {
                    pila.pop();
                    JOptionPane.showMessageDialog(null, "Pila actualizada:" + pila.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Pila actualizada:" + pila.toString());
                }
                break;

            case 4:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Por favor ingresa un caso valido", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
