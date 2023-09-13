package mx.unam.algebraLineal;

public class Matrices {
    private double[] matriz = {2.0, 3.0, 4.0, 0.0, 9.0, 0.0, 1.0, 6.0, 8.0};
    private String codigo = "hola mundo";
    private String[] matriztext = {""};
    private String codigoEncriptado;

    public Matrices() {
    }
    
    public double[] getMatriz() {
        return matriz;
    }

    public void setMatrizeta(double[] matriz) {
        this.matriz = matriz;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setMatriz(double[] matriz) {
        this.matriz = matriz;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoEncriptado() {
        return codigoEncriptado;
    }

    public void setCodigoEncriptado(String codigoEncriptado) {
        this.codigoEncriptado = codigoEncriptado;
    }

    public String[] getMatriztext() {
        return matriztext;
    }

    public void setMatriztext(String[] matriztext) {
        this.matriztext = matriztext;
    }

    public double[] convertANum() {
        char[] digitos = this.getCodigo().toCharArray();
        double[] numeros = new double[digitos.length];
        for (int i = 0; i < digitos.length; i++) {
            if (digitos[i] == ' ') {
                numeros[i] = 0;
            }
            if (digitos[i] == 'a') {
                numeros[i] = 1;
            }
            if (digitos[i] == 'b') {
                numeros[i] = 2;
            }
            if (digitos[i] == 'c') {
                numeros[i] = 3;
            }
            if (digitos[i] == 'd') {
                numeros[i] = 4;
            }
            if (digitos[i] == 'e') {
                numeros[i] = 5;
            }
            if (digitos[i] == 'f') {
                numeros[i] = 6;
            }
            if (digitos[i] == 'g') {
                numeros[i] = 7;
            }
            if (digitos[i] == 'h') {
                numeros[i] = 8;
            }
            if (digitos[i] == 'i') {
                numeros[i] = 9;
            }
            if (digitos[i] == 'j') {
                numeros[i] = 10;
            }
            if (digitos[i] == 'k') {
                numeros[i] = 11;
            }
            if (digitos[i] == 'l') {
                numeros[i] = 12;
            }
            if (digitos[i] == 'm') {
                numeros[i] = 13;
            }
            if (digitos[i] == 'n') {
                numeros[i] = 14;
            }
            if (digitos[i] == 'ñ') {
                numeros[i] = 15;
            }
            if (digitos[i] == 'o') {
                numeros[i] = 16;
            }
            if (digitos[i] == 'p') {
                numeros[i] = 17;
            }
            if (digitos[i] == 'q') {
                numeros[i] = 18;
            }
            if (digitos[i] == 'r') {
                numeros[i] = 19;
            }
            if (digitos[i] == 's') {
                numeros[i] = 20;
            }
            if (digitos[i] == 't') {
                numeros[i] = 21;
            }
            if (digitos[i] == 'u') {
                numeros[i] = 22;
            }
            if (digitos[i] == 'v') {
                numeros[i] = 23;
            }
            if (digitos[i] == 'w') {
                numeros[i] = 24;
            }
            if (digitos[i] == 'x') {
                numeros[i] = 25;
            }
            if (digitos[i] == 'y') {
                numeros[i] = 26;
            }
            if (digitos[i] == 'z') {
                numeros[i] = 27;
            }
            if (digitos[i] == '.') {
                numeros[i] = 28;
            }
            if (digitos[i] == ',') {
                numeros[i] = 29;
            }
            if (digitos[i] == '1') {
                numeros[i] = 30;
            }
            if (digitos[i] == '2') {
                numeros[i] = 31;
            }
            if (digitos[i] == '3') {
                numeros[i] = 32;
            }
            if (digitos[i] == '4') {
                numeros[i] = 33;
            }
            if (digitos[i] == '5') {
                numeros[i] = 34;
            }
            if (digitos[i] == '6') {
                numeros[i] = 35;
            }
            if (digitos[i] == '7') {
                numeros[i] = 36;
            }
            if (digitos[i] == '8') {
                numeros[i] = 37;
            }
            if (digitos[i] == '9') {
                numeros[i] = 38;
            }
            if (digitos[i] == '0') {
                numeros[i] = 39;
            }
        }
        return numeros;
    }

    public double[] convertMatANumeros() {
        String[] codigo = this.getMatriztext();
        double[] matrice = new double[codigo.length];

        for (int i = 0; i < matrice.length; i++) {
            matrice[i] = Double.parseDouble(codigo[i]);
        }
        return matrice;
    }
    public String convertALetras() {
    double[] matrice = this.desencriptar();
    String[] letras = new String[matrice.length];
    String mensaje = "";
    for (int i = 0; i < matrice.length; i++){
        if (matrice[i] == 0) {
            letras[i] = " ";
        }
        if (matrice[i] == 1) {
            letras[i] = "a";
        }
        if (matrice[i] == 2) {
            letras[i] = "b";
        }
        if (matrice[i] == 3) {
            letras[i] = "c";
        }
        if (matrice[i] == 4) {
            letras[i] = "d";
        }
        if (matrice[i] == 5) {
            letras[i] = "e";
        }
        if (matrice[i] == 6) {
            letras[i] = "f";
        }
        if (matrice[i] == 7) {
            letras[i] = "g";
        }
        if (matrice[i] == 8) {
            letras[i] = "h";
        }
        if (matrice[i] == 9) {
            letras[i] = "i";
        }
        if (matrice[i] == 10) {
            letras[i] = "j";
        }
        if (matrice[i] == 11) {
            letras[i] = "k";
        }
        if (matrice[i] == 12) {
            letras[i] = "l";
        }
        if (matrice[i] == 13) {
            letras[i] = "m";
        }
        if (matrice[i] == 14) {
            letras[i] = "n";
        }
        if (matrice[i] == 15) {
            letras[i] = "ñ";
        }
        if (matrice[i] == 16) {
            letras[i] = "o";
        }
        if (matrice[i] == 17) {
            letras[i] = "p";
        }
        if (matrice[i] == 18) {
            letras[i] = "q";
        }
        if (matrice[i] == 19) {
            letras[i] = "r";
        }
        if (matrice[i] == 20) {
            letras[i] = "s";
        }
        if (matrice[i] == 21) {
            letras[i] = "t";
        }
        if (matrice[i] == 22) {
            letras[i] = "u";
        }
        if (matrice[i] == 23) {
            letras[i] = "v";
        }
        if (matrice[i] == 24) {
            letras[i] = "w";
        }
        if (matrice[i] == 25) {
            letras[i] = "x";
        }
        if (matrice[i] == 26) {
            letras[i] = "y";
        }
        if (matrice[i] == 27) {
            letras[i] = "z";
        }
        if (matrice[i] == 28) {
            letras[i] = ".";
        }
        if (matrice[i] == 29) {
            letras[i] = ",";
        }
        if (matrice[i] == 30) {
            letras[i] = "1";
        }
        if (matrice[i] == 31) {
            letras[i] = "2";
        }
        if (matrice[i] == 32) {
            letras[i] = "3";
        }
        if (matrice[i] == 33) {
            letras[i] = "4";
        }
        if (matrice[i] == 34) {
            letras[i] = "5";
        }
        if (matrice[i] == 35) {
            letras[i] = "6";
        }
        if (matrice[i] == 36) {
            letras[i] = "7";
        }
        if (matrice[i] == 37) {
            letras[i] = "8";
        }
        if (matrice[i] == 38) {
            letras[i] = "9";
        }
        if (matrice[i] == 39) {
            letras[i] = "0";
        }
    }
    for (int i = 0; i < letras.length; i++){
        mensaje += letras[i];
    }
        return mensaje;
    }

   public double[] encriptar() {
       double[][] matrice = Matrices.ConvertMatriz(this.getMatriz());
       double[] digitos = this.convertANum();
       int filas = (int) (Math.sqrt(this.getMatriz().length));
       double tamanioInt = Math.ceil(((double) digitos.length) / (double) filas);
       double tamanio = 0;
       int[] matrizTemp;
       int tamanioDeMatriz = 0;

       for (int i = 0; i < 1000; i++) {
           matrizTemp = new int[i];
           tamanio = (matrizTemp.length / filas);
           if (tamanio == tamanioInt) {
               tamanioDeMatriz = matrizTemp.length;
               i = 1001;
               break;
           }
       }
       double[] digitosGrand = new double[tamanioDeMatriz];
       double[] albacea = new double[digitosGrand.length];

       for (int i = 0; i < digitos.length; i++) {
           digitosGrand[i] = digitos[i];
       }

       int j = 0, incre = 0;
       for (int i = 0; i < digitosGrand.length; ) {
           for (int l = 0; l < filas; l++) {
               for (int m = 0; m < filas; m++) {
                   albacea[i] += digitosGrand[j] * matrice[m][l];
                   if (m == filas - 1) {
                       j = incre;
                   } else {
                       j++;
                   }
               }
               i++;
           }
           j += filas;
           incre += filas;
       }
       return albacea;
   }


    public double[] desencriptar() {
        double[] codigo = this.convertMatANumeros();
        double[][] matrice = this.ConvertMatriz(this.matrizInversa());
        double[] albacea = new double[codigo.length];
        int filas = (int) Math.sqrt(this.getMatriz().length);

       int j = 0, incre = 0;
       for (int i = 0; i < codigo.length;) {
          for (int l = 0; l < filas; l++) {
              for (int m = 0; m < filas; m++) {
                  albacea[i] += (int) (codigo[j] * matrice[m][l]);
                    if(m == filas - 1){
                        j = incre;
                    }else{
                       j++;
                    }
              }
              i++;
          }
          j += filas;
          incre += filas;
       }
        return albacea;
    }

    public static double[][] ConvertMatriz(double[] matriz) {
        int filas = (int) Math.sqrt(matriz.length);
        double[][] arreglo = new double[filas][filas];
        int suma = 0;
        // asigna a cada espacio de la matriz cuadrada un numero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                arreglo[i][j] = matriz[suma];
                suma++;
            }
        }
        return arreglo;
    }

    public double[] matrizInversa() {
        double determinante = Matrices.calcularDeterminante(Matrices.ConvertMatriz(this.getMatriz()));
        double[] adjunta = Matrices.procesarMatrizAdjunta(this.getMatriz());

        for (int i = 0; i < adjunta.length; i++) {
            adjunta[i] = adjunta[i] / determinante;
        }
        return adjunta;
    }

    public static double calcularDeterminante(double[][] matriz) {
        int n = matriz.length;

        if (n == 1) {
            return matriz[0][0];
        }

        double determinante = 0;
        int signo = 1;

        for (int i = 0; i < n; i++) {
            double[][] submatriz = generarSubmatriz(matriz, i, 0);
            determinante += signo * matriz[i][0] * calcularDeterminante(submatriz);
            signo *= -1;
        }

        return determinante;
    }

    public static double[][] generarSubmatriz(double[][] matriz, double filaExcluida, double columnaExcluida) {
        int n = matriz.length;
        double[][] submatriz = new double[n - 1][n - 1];

        int filaSubmatriz = 0;
        int columnaSubmatriz = 0;

        for (int i = 0; i < n; i++) {
            if (i != filaExcluida) {
                for (int j = 0; j < n; j++) {
                    if (j != columnaExcluida) {
                        submatriz[filaSubmatriz][columnaSubmatriz] = matriz[i][j];
                        columnaSubmatriz++;
                    }
                }
                filaSubmatriz++;
                columnaSubmatriz = 0;
            }
        }

        return submatriz;
    }

    public static double[] procesarMatrizAdjunta(double[] matrizA) {
        int filas = (int) Math.sqrt(matrizA.length);
        double[][] matriz = new double[filas][filas];
        int suma = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                matriz[i][j] = matrizA[suma];
                suma++;
            }
        }
        suma = 0;
        double[][] adjunta = calcularMatrizAdjunta(matriz);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                matrizA[suma] = adjunta[i][j];
                suma++;
            }
        }
        return matrizA;
    }

    public static double[][] calcularMatrizAdjunta(double[][] matriz) {
        int n = matriz.length;
        double[][] adjunta = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] submatriz = obtenerSubmatriz(matriz, i, j);
                int signo = (int) Math.pow(-1, i + j);
                double determinante = obtenerDeterminante(submatriz);
                adjunta[j][i] = signo * determinante;
            }
        }

        return adjunta;
    }

    public static double[][] obtenerSubmatriz(double[][] matriz, double filaExcluida, double columnaExcluida) {
        int n = matriz.length;
        double[][] submatriz = new double[n - 1][n - 1];

        int filaDestino = 0;
        int columnaDestino = 0;

        for (int i = 0; i < n; i++) {
            if (i != filaExcluida) {
                for (int j = 0; j < n; j++) {
                    if (j != columnaExcluida) {
                        submatriz[filaDestino][columnaDestino] = matriz[i][j];
                        columnaDestino++;
                    }
                }
                filaDestino++;
                columnaDestino = 0;
            }
        }

        return submatriz;
    }

    public static double obtenerDeterminante(double[][] matriz) {
        int n = matriz.length;

        if (n == 1) {
            return matriz[0][0];
        }

        if (n == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }

        double determinante = 0;

        for (int j = 0; j < n; j++) {
            double[][] submatriz = obtenerSubmatriz(matriz, 0, j);
            int signo = (int) Math.pow(-1, j);
            determinante += signo * matriz[0][j] * obtenerDeterminante(submatriz);
        }

        return determinante;
    }

    public void introducirMatriz(String matriz){
        boolean dig = true;
        String[] digitos = matriz.split(",");
        double[] numeros = new double[digitos.length];
        for (int j = 0; j < digitos.length; j++) {
            for (int k = 0; k < digitos.length; k++) {
                if (digitos.equals("a") || digitos.equals("b") || digitos.equals("c") || digitos.equals("d") || digitos.equals("e") || digitos.equals("f") || digitos.equals("g") || digitos.equals("h") || digitos.equals("i") || digitos.equals("j") || digitos.equals("k") || digitos.equals("l") || digitos.equals("m") || digitos.equals("n") || digitos.equals("ñ") || digitos.equals("o") || digitos.equals("p") || digitos.equals("q") || digitos.equals("r") || digitos.equals("s") || digitos.equals("t") || digitos.equals("u") || digitos.equals("v") || digitos.equals("x") || digitos.equals("y") || digitos.equals("z")) {
                    dig = false;
                    break;
                } else {
                    numeros[k] = Double.parseDouble(digitos[k]);
                }
            }
            if (dig == true) {
                if (numeros[j] >= 0 && numeros[j] <= 1000) {
                    int nones = 0;
                    for (int i = 1; i <= 1000; i += nones) {
                        if (numeros.length == i) {
                            double determinante = Matrices.calcularDeterminante(Matrices.ConvertMatriz(numeros));
                            if (determinante != 0) {
                                this.setMatriz(numeros);
                                System.out.println("LA MATRIZ SE CARGO CON EXITO!!");
                                break;
                            } else {
                                System.out.println("EL DETERMINANTE ES 0");
                                break;
                            }
                        } else {
                            if (i >= numeros.length) {
                                System.out.println("LA MATRIZ NO ES CUADRADA!!");
                                break;
                            }
                        }
                        if (i == 1) {
                            nones = 3;
                        } else {
                            nones += 2;
                        }
                    }
                    break;
                }
            } else {
                System.out.println("SOLO DEBE ESCRIBIR NUMEROS");
            }
            break;
        }

    }


    public static void main(String[] args) {
        Matrices matriz = new Matrices();
       // System.out.println(Arrays.toString(matriz.convertANum()));
       // System.out.println(Arrays.toString(matriz.matrizInversa()));
        //System.out.println(Arrays.toString(matriz.encriptar()));
        //System.out.println(matriz.convertALetras());
        //System.out.println((matriz.convertALetras()));
        matriz.introducirMatriz("");

    }

}