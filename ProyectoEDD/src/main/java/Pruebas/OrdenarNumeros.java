package Pruebas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;

public class OrdenarNumeros {

    private int[] arreglo;
    private String tiempo;
    private int vueltas;
    private long comparaciones;
    private long intercambios;

    public int[] metodoBurbuja(int[] arreglo) {
        intercambios = 0;
        vueltas = 0;
        comparaciones = 0;
        tiempo = "";
        this.arreglo = arreglo;
        int auxiliar;
        //Obtenemos el tiempo antes de la ordenacion:
        long inicio = System.currentTimeMillis();
        this.comparaciones = 0;
        for (int i = 0; i < arreglo.length - 1; i++) {
            boolean bandera = false;
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (this.arreglo[j] > this.arreglo[j + 1]) {
                    auxiliar = this.arreglo[j];
                    this.arreglo[j] = this.arreglo[j + 1];
                    this.arreglo[j + 1] = auxiliar;
                    bandera = true;
                    this.intercambios++;
                }
                this.comparaciones++;
            }
            this.vueltas++;
            if (bandera == false) {
                break;
            }
        }
        //Obtenemos tiempo cuando termine la ordenacion:
        long fin = System.currentTimeMillis();
        double tiempo = (fin - inicio);
        //Invocamos el metodo formatoDeImpresion para mostrar el tiempo que le toma al algoritno ordenar
        this.tiempo = "Tarda: " + Integer.toString((int) (tiempo / 60000)) + " minutos " + Integer.toString((int) ((tiempo % 60000) / 1000)) + " segundos " + Integer.toString((int) ((tiempo % 60000) % 1000)) + " milisegundos";
        return this.arreglo;
    }

    public int[] minimosSucesivos(int[] arreglo) {
        //le damos datos al arreglo y creamos variables para realizar los cambios entre numeros
        intercambios = 0;
        vueltas = 0;
        comparaciones = 0;
        tiempo = "";
        this.arreglo = arreglo;
        int menor = 0, posMenor = 0;
        //Obtenemos el tiempo antes de la ordenacion:
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < arreglo.length; i++) {
            //Damos por hecho que el primer valor es el menor y obtenemos su ubicación en el arreglo
            menor = this.arreglo[i];
            boolean bandera = false;
            //Entramos a un ciclo en el que j = i para que empiece a comparar esos valores excluyendo las casillas fijas
            for (int j = i; j < arreglo.length - 1; j++) {
                //Comparamos si el siguiente numero de arreglo es menor al que propusimos
                if (menor >= this.arreglo[j + 1]) {
                    //El numero resulto ser mayor lo asignamos como menor
                    menor = this.arreglo[j + 1];
                    //Almacenamos su posicion
                    posMenor = j + 1;
                    //El booleano bandera marca que hubo uno o mas cambios excluyendo al mejor caso
                    bandera = true;
                }
                //Contamos los cambios
                this.comparaciones++;
            }
            //Contamos las vueltas
            this.vueltas++;
            if (bandera == true) {
                arreglo[posMenor] = arreglo[i];
                arreglo[i] = menor;
                this.intercambios++;
            } else {
                break;
            }
        }
        //Obtenemos tiempo cuando termine la ordenacion:
        long fin = System.currentTimeMillis();
        double tiempo = (fin - inicio);
        this.tiempo = "Tarda: " + Integer.toString((int) (tiempo / 60000)) + " minutos " + Integer.toString((int) ((tiempo % 60000) / 1000)) + " segundos " + Integer.toString((int) ((tiempo % 60000) % 1000)) + " milisegundos";
        return this.arreglo;
    }

    public String getVueltas() {
        return "Número de vueltas: " + this.vueltas;
    }

    public String getComparaciones() {
        return "Número de comparaciones: " + this.comparaciones;
    }

    public String getIntercambios() {
        return "Número de intercambios: " + this.intercambios;
    }

    public String getTiempo() {
        return this.tiempo;
    }

    public static int[] generarArgPeorcaso(int anchoDelArreglo) {
        int[] arreglo = new int[anchoDelArreglo];
        int uno = 0;
        for (int i = anchoDelArreglo - 1; i > 0; i--) {
            arreglo[uno++] = i;
        }
        return arreglo;
    }

    public static int[] generarArgMejorcaso(int anchoDelArreglo) {
        int[] arreglo = new int[anchoDelArreglo];
        for (int i = 0; i < anchoDelArreglo; i++) {
            arreglo[i] = i;
        }
        return arreglo;
    }

    public static int[] generarArgcasoRandom(int anchoDelArreglo) {
        int[] arreglo = new int[anchoDelArreglo];
        for (int i = 0; i < anchoDelArreglo; i++) {
            arreglo[i] = (int) ((Math.random() * anchoDelArreglo) + 1);
        }
        return arreglo;
    }

    public static int[] generarArgPalindromo(int anchoDelArreglo) {
        int[] arreglo = new int[anchoDelArreglo];
        double mitad = (double) anchoDelArreglo / (double) 2;
        if (mitad % (int) mitad == 0) {
            int contador = 1;
            for (int i = 0; i < anchoDelArreglo; i++) {
                if (i <= mitad - 1) {
                    arreglo[i] = contador;
                    contador++;
                } else {
                    contador--;
                    arreglo[i] = contador;
                }
            }
        } else {
            int contador = 1;
            for (int i = 0; i < anchoDelArreglo; i++) {
                if (i < mitad - 0.5) {
                    arreglo[i] = contador;
                    contador++;
                } else {
                    arreglo[i] = contador;
                    contador--;
                }
            }
        }
        return arreglo;
    }

    public static String[] convertirAStringArg(int[] arreglos){
        String[] arreg = new String[arreglos.length];
        for (int i = 0; i < arreglos.length; i++) {
            arreg[i] = Integer.toString(arreglos[i]);
        }
        return arreg;
    }
    
    public static void probrarMetodoBurbujaMejorCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(OrdenarNumeros.generarArgMejorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(OrdenarNumeros.generarArgMejorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMetodoBurbujaPeorCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(OrdenarNumeros.generarArgPeorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(OrdenarNumeros.generarArgPeorcaso(i))));
        Arrays.toString(ordenarNumeros.metodoBurbuja(OrdenarNumeros.generarArgPeorcaso(i)));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMetodoBurbujaRandomCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = OrdenarNumeros.generarArgcasoRandom(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMinimosSuceMejorCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(OrdenarNumeros.generarArgMejorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(OrdenarNumeros.generarArgMejorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMinimosSucePeorCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(OrdenarNumeros.generarArgPeorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(OrdenarNumeros.generarArgPeorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMinimosSuceRandomCaso(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = OrdenarNumeros.generarArgcasoRandom(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMinimosPlindromo(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = OrdenarNumeros.generarArgPalindromo(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void probrarMetodoBurbujaPalindromo(int i) {
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = OrdenarNumeros.generarArgPalindromo(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %s%nNumero de comparaciones: %s%nNumero de intercambios%s%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones(), ordenarNumeros.getIntercambios());
    }

    public static void main(String[] args) {
        int i = 10;

        System.out.println("Metodo Burbuja");
        OrdenarNumeros.probrarMetodoBurbujaMejorCaso(i);
        OrdenarNumeros.probrarMetodoBurbujaPeorCaso(i);
        OrdenarNumeros.probrarMetodoBurbujaRandomCaso(i);
        OrdenarNumeros.probrarMetodoBurbujaPalindromo(i);
        OrdenarNumeros.probrarMetodoBurbujaPalindromo(i + 1);

        System.out.println("Minimos Sucesivos");
        OrdenarNumeros.probrarMinimosSuceMejorCaso(i);
        OrdenarNumeros.probrarMinimosSucePeorCaso(i);
        OrdenarNumeros.probrarMinimosSuceRandomCaso(i);
        OrdenarNumeros.probrarMinimosPlindromo(i);
        OrdenarNumeros.probrarMinimosPlindromo(i + 1);
    }

    public static JPanel ordenacionDeNumeros() {
        JPanel ordenarPanel = new JPanel(new FlowLayout());
        ordenarPanel.setSize(500, 400);
        JButton btnBurbuja = new JButton("Metodo de la burbuja");
        JButton btnMinimos = new JButton("Minimos sucesivos");
        btnBurbuja.setFont(new Font("Arial", Font.BOLD, 16));
        btnBurbuja.setForeground(new Color(213, 159, 15));
        btnBurbuja.setBackground(new Color(0, 43, 121));
        btnMinimos.setFont(new Font("Arial", Font.BOLD, 16));
        btnMinimos.setForeground(new Color(213, 159, 15));
        btnMinimos.setBackground(new Color(0, 43, 121));

        btnBurbuja.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ordenarPanel.removeAll();
                ordenarPanel.add(OrdenarNumeros.BurbujaPanel(), BorderLayout.CENTER);
                ordenarPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnBurbuja.setBackground(new Color(213, 159, 15));
                btnBurbuja.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBurbuja.setForeground(new Color(213, 159, 15));
                btnBurbuja.setBackground(new Color(0, 43, 121));
            }
        });

        btnMinimos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel tmp = new JPanel(new GridLayout(2, 0));
                JPanel botones = new JPanel(new FlowLayout());
                JPanel impresion = new JPanel(new BorderLayout());
                ordenarPanel.removeAll();
                ordenarPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimos.setBackground(new Color(213, 159, 15));
                btnMinimos.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimos.setForeground(new Color(213, 159, 15));
                btnMinimos.setBackground(new Color(0, 43, 121));
            }
        });

        ordenarPanel.add(btnBurbuja);
        ordenarPanel.add(btnMinimos);

        return ordenarPanel;
    }

    private static JPanel BurbujaPanel() {
        JPanel tmp = new JPanel(new BorderLayout());
        JPanel botones = new JPanel(new FlowLayout());
        botones.setPreferredSize(new Dimension(550, 55));
        JPanel impresion = new JPanel(new BorderLayout());
        JButton btnMejor = new JButton("Mejor caso");
        JButton btnPeor = new JButton("Peor caso");
        JButton btnAleatorio = new JButton("Caso aleatorio");
        JTextField tamanio = new JTextField(5);
        btnMejor.setFont(new Font("Arial", Font.BOLD, 16));
        btnPeor.setFont(new Font("Arial", Font.BOLD, 16));
        btnAleatorio.setFont(new Font("Arial", Font.BOLD, 16));

        btnMejor.setForeground(new Color(213, 159, 15));
        btnMejor.setBackground(new Color(0, 43, 121));
        btnPeor.setForeground(new Color(213, 159, 15));
        btnPeor.setBackground(new Color(0, 43, 121));
        btnAleatorio.setForeground(new Color(213, 159, 15));
        btnAleatorio.setBackground(new Color(0, 43, 121));

        btnMejor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnMejor.setBackground(new Color(213, 159, 15));
                btnMejor.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMejor.setForeground(new Color(213, 159, 15));
                btnMejor.setBackground(new Color(0, 43, 121));
            }
        });

        btnPeor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPeor.setBackground(new Color(213, 159, 15));
                btnPeor.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPeor.setForeground(new Color(213, 159, 15));
                btnPeor.setBackground(new Color(0, 43, 121));
            }
        });

        btnAleatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAleatorio.setBackground(new Color(213, 159, 15));
                btnAleatorio.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAleatorio.setForeground(new Color(213, 159, 15));
                btnAleatorio.setBackground(new Color(0, 43, 121));
            }
        });

        botones.add(btnMejor);
        botones.add(btnPeor);
        botones.add(btnAleatorio);

        tmp.add(botones, BorderLayout.NORTH);
        tmp.add(impresion, BorderLayout.CENTER);

        return tmp;
    }
}
