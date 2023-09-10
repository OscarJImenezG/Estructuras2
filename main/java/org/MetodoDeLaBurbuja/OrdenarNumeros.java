package org.MetodoDeLaBurbuja;

import java.util.Arrays;

public class OrdenarNumeros {
    private int[] arreglo;
    private String tiempo;
    private int vueltas;
    private long comparaciones;

    public int[] metodoBurbuja(int[] arreglo){
        this.arreglo = arreglo;
        int auxiliar;
        //Obtenemos el tiempo antes de la ordenacion:
        long inicio = System.currentTimeMillis();
        this.comparaciones = 0;
            for(int i = 0; i < arreglo.length - 1; i++){
                boolean bandera = false;
                for(int j = 0; j < arreglo.length - 1; j++){
                    if (this.arreglo[j] > this.arreglo[j + 1]){
                        auxiliar = this.arreglo[j];
                        this.arreglo[j] = this.arreglo[j + 1];
                        this.arreglo[j + 1] = auxiliar;
                        bandera = true;
                    }
                    this.comparaciones++;
                }
                this.vueltas++;
                if(bandera == false){
                    break;
                }
            }
            //Obtenemos tiempo cuando termine la ordenacion:
        long fin = System.currentTimeMillis();
        double tiempo = (fin - inicio);
        //Invocamos el metodo formatoDeImpresion para mostrar el tiempo que le toma al algoritno ordenar
        this.tiempo = "Tarda: " + Integer.toString((int) (tiempo / 60000)) + " minutos "+ Integer.toString( (int) ((tiempo % 60000) / 1000)) + " segundos " + Integer.toString ((int)((tiempo % 60000) % 1000)) + " milisegundos";
        return this.arreglo;
    }
    public int[] minimosSucesivos(int[] arreglo){
        //le damos datos al arreglo y creamos variables para realizar los cambios entre numeros
        this.arreglo = arreglo;
        int menor = 0, posMenor = 0, i = 0;
        //Obtenemos el tiempo antes de la ordenacion:
        long inicio = System.currentTimeMillis();
        for(i = 0; i < arreglo.length - 1; i++){
            //Damos por hecho que el primer valor es el menor y obtenemos su ubicación en el arreglo
            menor = this.arreglo[i];
            boolean bandera = false;
            //Entramos a un ciclo en el que j = i para que empiece a comparar esos valores excluyendo las casillas fijas
            for(int j = i; j < arreglo.length - 1; j++){
                //Comparamos si el siguiente numero de arreglo es menor al que propusimos
                if (this.arreglo[j] > this.arreglo[j + 1] && this.arreglo[j + 1] < menor) {
                    //El numero resulto ser mayor lo asignamos como menor
                    menor = this.arreglo[j + 1];
                    //Almacenamos su posicion
                    posMenor = j + 1;
                    //El booleano bandera marca que hubo uno o mas cambios excluyendo al mejor caso
                    bandera = true;
                }else if(this.arreglo[j + 1] < menor){
                    i = 0;
                }
                //Al acabar el ciclo hacemos el cambio del menor a la posicion que le toque
                if(j == arreglo.length - 2 && arreglo[i] != menor){
                    arreglo[posMenor] = arreglo[i];
                    arreglo[i] = menor;
                }
                //Contamos los cambios
                this.comparaciones++;
            }
            //Contamos las vueltas
            this.vueltas++;
            //Si bandera continua en false termina la ejecución porque ya esta ordenado el arreglo
            if(bandera == false){
                break;
            }
        }
        //Obtenemos tiempo cuando termine la ordenacion:
        long fin = System.currentTimeMillis();
        double tiempo = (fin - inicio);
        this.tiempo = "Tarda: " + Integer.toString((int) (tiempo / 60000)) + " minutos "+ Integer.toString( (int) ((tiempo % 60000) / 1000)) + " segundos " + Integer.toString ((int)((tiempo % 60000) % 1000)) + " milisegundos";
        return this.arreglo;
    }
    public int getVueltas(){
        return this.vueltas;
    }
    public long getComparaciones(){
        return this.comparaciones;
    }
    public String getTiempo(){
        return this.tiempo;
    }
    public static int[] generarArgPeorcaso(int anchoDelArreglo){
        int[] arreglo = new int[anchoDelArreglo];
        int uno = 0;
        for(int i = anchoDelArreglo - 1; i > 0; i--){
            arreglo[uno++] = i;
        }
      return arreglo;
    }
    public static int[] generarArgMejorcaso(int anchoDelArreglo){
        int[] arreglo = new int[anchoDelArreglo];
        for(int i = 0; i < anchoDelArreglo; i++){
            arreglo[i] = i;
        }
        return arreglo;
    }
    public static int[] generarArgcasoRandom(int anchoDelArreglo){
        int[] arreglo = new int[anchoDelArreglo];
        for (int i = 0; i < anchoDelArreglo; i++){
            arreglo[i] = (int) ((Math.random() * anchoDelArreglo) + 1);
        }
        return arreglo;
    }
    public static void probrarMetodoBurbujaMejorCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(ordenarNumeros.generarArgMejorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(ordenarNumeros.generarArgMejorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }
    public static void probrarMetodoBurbujaPeorCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(ordenarNumeros.generarArgPeorcaso(i)));
        Arrays.toString(ordenarNumeros.metodoBurbuja(ordenarNumeros.generarArgPeorcaso(i)));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }
    public static void probrarMetodoBurbujaRandomCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = ordenarNumeros.generarArgcasoRandom(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.metodoBurbuja(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }
    public static void probrarMinimosSuceMejorCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(ordenarNumeros.generarArgMejorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(ordenarNumeros.generarArgMejorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }
    public static void probrarMinimosSucePeorCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        System.out.println("Arreglo propuesto:" + Arrays.toString(ordenarNumeros.generarArgPeorcaso(i)));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(ordenarNumeros.generarArgPeorcaso(i))));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }
    public static void probrarMinimodSuceRandomCaso (int i){
        OrdenarNumeros ordenarNumeros = new OrdenarNumeros();
        int[] arreglo = ordenarNumeros.generarArgcasoRandom(i);
        System.out.println("Arreglo propuesto:" + Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenarNumeros.minimosSucesivos(arreglo)));
        System.out.printf("%s%nNumero de vueltas: %d%nNumero de comparaciones: %d%n%n%n", ordenarNumeros.getTiempo(), ordenarNumeros.getVueltas(), ordenarNumeros.getComparaciones());
    }


    public static void main(String[] args) {
        int i = 10000;
        OrdenarNumeros on = new OrdenarNumeros();
        System.out.println(Arrays.toString(on.metodoBurbuja(new int[] {1,2,3,4,5,4,3,2,1})));
        System.out.println(on.getComparaciones());
        System.out.println(on.getTiempo());
        System.out.println(on.getVueltas());

        OrdenarNumeros ou = new OrdenarNumeros();
        System.out.println(Arrays.toString(ou.minimosSucesivos(new int[] {1,5,4,3,2,1})));
        System.out.println(ou.getComparaciones());
        System.out.println(ou.getTiempo());
        System.out.println(ou.getVueltas());

        /*System.out.println("Metodo Burbuja");
        OrdenarNumeros.probrarMetodoBurbujaMejorCaso(i);
        OrdenarNumeros.probrarMetodoBurbujaPeorCaso(i);
        OrdenarNumeros.probrarMetodoBurbujaRandomCaso(i);

        System.out.println("Minimos Sucesivos");
        OrdenarNumeros.probrarMinimosSuceMejorCaso(i);
        OrdenarNumeros.probrarMinimosSucePeorCaso(i);
        OrdenarNumeros.probrarMinimodSuceRandomCaso(i);*/
    }
}
