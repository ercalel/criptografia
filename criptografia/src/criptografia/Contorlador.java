package criptografia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Elmer C. Ramos
 */
public class Contorlador {

    // Matriz default
    // 1 2 0 3
    // 0 1 1 1
    // 0 1 0 1
    // 1 2 0 2   
    private final int[][] matriz_a = {{1, 2, 0, 3}, {0, 1, 1, 1}, {0, 1, 0, 1}, {1, 2, 0, 2}};

    // Matriz identidad
    // 1 0 0 0
    // 0 1 0 0
    // 0 0 1 0
    // 0 0 0 1
    private final int[][] matriz_identidad = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

    // Matriz que contiene el resultado de la cadena codificada.
    private String[][] cadena_codificada;

    // Matriz m*4 donde se almacena nuestra cadena con valores enteros
    int[][] matriz_b;

    // Matriz que contiene el resultado de la multiplicacion entre matrices
    int[][] matriz_resultado;

    int filas_matriz_resultado;

    // Temporales para comparar
    String cadenaParaDesencriptar = "";

    // Contructor
    public Contorlador() {
        //Cuerpo del constructor vacío
    }

    /**
     * Menú princial del progrma.
     *
     * @throws IOException
     */
    public void menu() throws IOException {
        // Objeto para la entrada de datos
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        // Valor ingresado por el usuario
        int opcion = 0;

        do {
            // Menu que se muestra en pantalla para el usuario
            System.out.println(""
                    + "---------- Menú de inicio ----------\n"
                    + "------------------------------------\n"
                    + "[1] Encriptar mensaje.\n"
                    + "[2] Desencriptar mensaje.\n"
                    + "[3] Finalizar progrmaa.\n");
            System.out.print("elmer@calel$ ");

            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion) {
                case 1:
                    System.out.println("Escriba un mensaje:");
                    System.out.print("elmer@calel$ ");
                    String cadena = entrada.readLine();
                    codificar(cadena);
                    break;
                case 2:
                    try {
                        //Cadena a desencriptar
                        String cadenaADesencriptar;

                        System.out.println("Ingrese la cadena a desencriptar:");
                        cadenaADesencriptar = entrada.readLine();

                        System.out.println("El mensaje oculto es:");
                        //llamamos al metodo desencriptarCadena
                        desencriptarCadena(cadenaADesencriptar);

                        //limpiamos cadenaParaDesencriptar
                        cadenaParaDesencriptar = "";

                    } catch (Exception exception) {
                        System.err.println("El mensaje no puede desencriptarse!");
                    }
                    break;
                //Fin del programa
                case 3:
                    System.exit(0);
                    break;
                // Opción incorrecta
                default:
                    System.out.println("La opción no es válida!");
                    break;

            }
        } while (opcion != 3);
    }

    /**
     * Realiza todas las operaciones necesarias para la codificación de la
     * cadena ingresada.
     *
     * @param cadena
     */
    private void codificar(String cadena) {
        int m = (cadena.length() / 4) + 1;
        int[][] matriz_valores = new int[m][4];

        System.out.println("<<<=============== CODIFICACIÓN ===============>>>");
        System.out.println("Paso 1:");
        System.out.println("Dividir la cadena de entrada y asociarla con cada uno de los valores numércios establecidos.");
        dividir_cadena(matriz_valores, m, cadena);

        System.out.println("Paso 2:");
        System.out.println("Almacenar los valores en una matriz m*4, esta matriz contiene el código del mensaje a encriptar.");
        almacenar_valores(matriz_valores, m, cadena);

        System.out.println("Paso 3:");
        System.out.println("3.1 - Multiplicar cada elemento de la matriz de valores por la matriz A, para obtener la matriz con el mensaje encriptado.");
        multiplicar_matriz(matriz_valores, m);
        System.out.println("3.2 - Sustiruir cada valor numérico por su correspondiente binario, esto aumenta la seguridad y genera la salida de la cadena encriptada:");
        aumentar_seguridad(matriz_valores, m);
    }

    /**
     * Muestra el resultado del paso 1, pero no es necesario, es únicamente para
     * seguir los pasos.
     *
     * @param matriz_valores
     * @param m
     * @param cadena
     */
    private void dividir_cadena(int[][] matriz_valores, int m, String cadena) {
        System.out.print("\t");
        for (int i = 0; i < cadena.length(); i++) {
            System.out.print(cadena.charAt(i) + "|\t");
        }
        System.out.print("\n\t");
        for (int i = 0; i < cadena.length(); i++) {
            System.out.print(asociar_valor(Character.toUpperCase(cadena.charAt(i))) + "|\t");
        }
        System.out.println("\n");
    }

    /**
     * Crea la matriz de valores numéricos.
     *
     * @param cadena
     */
    private void almacenar_valores(int[][] matriz_valores, int m, String cadena) {
        int k = 0;
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < 4; x++) {
                if (k < cadena.length()) {
                    matriz_valores[y][x] = asociar_valor(Character.toUpperCase(cadena.charAt(k)));
                } else {
                    matriz_valores[y][x] = 0;
                }
                k++;
                System.out.print(matriz_valores[y][x] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * Multiplica la matriz de valores por la matriz A, obteniendo el mensaje
     * encriptado.
     *
     * @param matriz_a
     * @param matriz_valores
     * @param m
     */
    void multiplicar_matriz(int[][] matriz_valores, int m) {
        for (int k = 0; k < m; k++) { // recorre las filas+1 filas de matriz_valores
            for (int j = 0; j < 4; j++) { // recorre las colunas de matriz_a
                for (int i = 0; i < 4; i++) { // recorre las columnas de matriz_valores
                    matriz_valores[k][j] += matriz_valores[k][i] * matriz_a[i][j];// realizamos la multiplicacion                    
                }
                System.out.print(matriz_valores[k][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * Convierte cada celda numérica de la matriz_valores a su correspondiente
     * binario.
     *
     * @param matrizResultado
     * @param m
     */
    private void aumentar_seguridad(int[][] matriz_valores, int m) {
        cadena_codificada = new String[m][4];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < 4; x++) {
                String casilla = String.valueOf(matriz_valores[y][x]);
                cadena_codificada[y][x] = "";
                for (int i = 0; i < casilla.length(); i++) {
                    switch (casilla.charAt(i)) {
                        case '0':
                            cadena_codificada[y][x] += "0000";
                            break;
                        case '1':
                            cadena_codificada[y][x] += "0001";
                            break;
                        case '2':
                            cadena_codificada[y][x] += "0010";
                            break;
                        case '3':
                            cadena_codificada[y][x] += "0011";
                            break;
                        case '4':
                            cadena_codificada[y][x] += "0100";
                            break;
                        case '5':
                            cadena_codificada[y][x] += "0101";
                            break;
                        case '6':
                            cadena_codificada[y][x] += "0110";
                            break;
                        case '7':
                            cadena_codificada[y][x] += "0111";
                            break;
                        case '8':
                            cadena_codificada[y][x] += "1000";
                            break;
                        case '9':
                            cadena_codificada[y][x] += "1001";
                            break;
                    }
                }
                System.out.print(cadena_codificada[y][x]); // Imprimimos la cadena encriptada
            }
        }
        System.out.println("\n");
    }

    //calcula la inversa de matriz_a, realizanod operaciones con matriz identidad
    void calcularInversa(int[][] inversaA) {
        //R4 = R4-R1
        for (int i = 0; i < 4; i++) {
            inversaA[3][i] = inversaA[3][i] - inversaA[0][i];
        }
        //R1 = R1-2*R2
        for (int i = 0; i < 4; i++) {
            inversaA[0][i] = inversaA[0][i] - 2 * inversaA[1][i];
        }
        //R3 = R3-R2
        for (int i = 0; i < 4; i++) {
            inversaA[2][i] = inversaA[2][i] - inversaA[1][i];
        }
        //R3 = -R3
        for (int i = 0; i < 4; i++) {
            inversaA[2][i] = -1 * inversaA[2][i];
        }
        //R2 = R2-R3
        for (int i = 0; i < 4; i++) {
            inversaA[1][i] = inversaA[1][i] - inversaA[2][i];
        }
        //R1 = R1+2*R3
        for (int i = 0; i < 4; i++) {
            inversaA[0][i] = inversaA[0][i] + 2 * inversaA[2][i];
        }
        //R4 = -R4
        for (int i = 0; i < 4; i++) {
            inversaA[3][i] = -1 * inversaA[3][i];
        }
        //R2 = R2-R4
        for (int i = 0; i < 4; i++) {
            inversaA[1][i] = inversaA[1][i] - inversaA[3][i];
        }
        //R1 = R1-R4
        for (int i = 0; i < 4; i++) {
            inversaA[0][i] = inversaA[0][i] - inversaA[3][i];
        }
    }//fin del metodo calcularInversa

    //llena la matrizB con los caracteres de cadena
    void desencriptarCadena(String cadena) {
        //numero de filas de la matrizB
        int nf = (cadena.length() / 4) / 4;

        //asignamos tamaño a matrizB
        matriz_b = new int[nf + 1][4];

        //matriz ue contendra la cadena ingresada
        String[][] matrizBDeBinarios = new String[nf + 1][4];

        //llenamos matrizBDeString con cuartetos de binarios
        int k = 0;
        for (int i = 0; i < nf + 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (k < cadena.length()) {
                    //asigna a cada pocicion de matrizBDeString el caracter correspondiente
                    matrizBDeBinarios[i][j] = cadena.substring(k, k + 4);
                    k += 4;
                }//fin del if
                System.out.print(matrizBDeBinarios[i][j] + "\t");
            }//fin del for de j
            System.out.println();
        }//fin del for de y

        //llama al metodo convertirAEntero para pasar de binario a un valor numerico
        convertirAEntero(matrizBDeBinarios, nf);

        //llenamos la matrizB con valores enteros
//        convertirMatriz(matriz_valores, matrizBDeBinarios, nf);
        //recorremos matrizB para crear una cadena para ue contenga cada numero almacenado en el
        for (int i = 0; i < nf + 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (matriz_b[i][j] != 0) {
                    cadenaParaDesencriptar += matriz_b[i][j];
                }
            }
        }//fin del for

        //fines de prueba despues boorrar
        System.out.println(cadenaParaDesencriptar);

        //calculamos la inversa de la matriz identidad
        calcularInversa(matriz_identidad);

        //matriz ue contiene el resultado de la multiplicacion
        int[][] Resultado = new int[filas_matriz_resultado + 1][4];

        multiplicar(Resultado, filas_matriz_resultado);

        //pasamos una matriz de enteros a matriz de string
        pasarMatrizString(Resultado, matrizBDeBinarios, filas_matriz_resultado);

        //damos valor alfavetico a cada pocicion numerica de la matriz
        traducirMatriz(matrizBDeBinarios, filas_matriz_resultado);

        //imprimimimos la cadena desencriptada
        for (int i = 0; i < filas_matriz_resultado + 1; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrizBDeBinarios[i][j]);
            }
        }//fin del for

        System.out.println();

    }//fin del merodo desencriptarCadena

    //metodo para multiplicar
    void multiplicar(int[][] a, int m) {
        //recorremos filas de matrizB y columnas de matriz_a
        for (int f = 0; f < filas_matriz_resultado + 1; f++) {//recorre las m+1 filas de matrizB
            for (int j = 0; j < 4; j++) {//recorre las colunas de matriz_a
                for (int i = 0; i < 4; i++) {//recorre las columnas de matrizB
                    a[f][j] += matriz_resultado[f][i] * matriz_identidad[i][j];//realizamos la multiplicacion
                }//fin del for de y
                System.out.print(a[f][j] + "\t");
            }//fin del for j
            System.out.println();
        }//fin del for k

    }//fin del metodo

    //pasa un valor binario a su euivalente en entero
    void convertirAEntero(String[][] matrizBDeString, int m) {
        //recorremos la matrizBDeString y cambia los caracteres por numeros
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrizBDeString[i][j] != null) {
                    switch (matrizBDeString[i][j]) {
                        case "0000":
                            matrizBDeString[i][j] = "0";
                            break;
                        case "0001":
                            matrizBDeString[i][j] = "1";
                            break;
                        case "0010":
                            matrizBDeString[i][j] = "2";
                            break;
                        case "0011":
                            matrizBDeString[i][j] = "3";
                            break;
                        case "0100":
                            matrizBDeString[i][j] = "4";
                            break;
                        case "0101":
                            matrizBDeString[i][j] = "5";
                            break;
                        case "0110":
                            matrizBDeString[i][j] = "6";
                            break;
                        case "0111":
                            matrizBDeString[i][j] = "7";
                            break;
                        case "1000":
                            matrizBDeString[i][j] = "8";
                            break;
                        case "1001":
                            matrizBDeString[i][j] = "9";
                            break;
                    }//fin del switch 
                } else {
                    matrizBDeString[i][j] = "0";
                }//fin del if...else
                System.out.print(matrizBDeString[i][j] + "\t");
            }//fin del for de j
            System.out.println();
        }//fin del for de y 
    }//fin del metodo aumentar_seguridad

    //cambia la matrizResultado a matriz de String
    static void pasarMatrizString(int[][] matrizB, String[][] matrizBDeString, int m) {
        //llenamos la matrizB con valores enteros
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                //convertimos cada pocicin a entero
                matrizBDeString[i][j] = String.valueOf(matrizB[i][j]);
            }
        }//fin del for
    }//fin del merodo pasarMatrizString

    /**
     * Asocia cada componente de la cadena ingresada por el usuario con su valor
     * numérico correspondiente.
     *
     * @param ascii
     * @return
     */
    private int asociar_valor(int ascii) {
        switch (ascii) {
            case 32:
                return 0;
            case 65:
                return 1;
            case 66:
                return 2;
            case 67:
                return 3;
            case 68:
                return 4;
            case 69:
                return 5;
            case 70:
                return 6;
            case 71:
                return 7;
            case 72:
                return 8;
            case 73:
                return 9;
            case 74:
                return 10;
            case 75:
                return 11;
            case 76:
                return 12;
            case 77:
                return 13;
            case 78:
                return 14;
            case 79:
                return 15;
            case 80:
                return 16;
            case 81:
                return 17;
            case 82:
                return 18;
            case 83:
                return 19;
            case 84:
                return 20;
            case 85:
                return 21;
            case 86:
                return 22;
            case 87:
                return 23;
            case 88:
                return 24;
            case 89:
                return 25;
            case 90:
                return 26;
            case 46:
                return 27;
        }
        return 0;
    }

    //asigna a matrizBDeString un valor numerico euivalente a cada letra en cada pocicion
    static void traducirMatriz(String[][] matrizBDeString, int m) {
        //recorremos la matrizBDeString y cambia los caracteres por numeros
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                switch (matrizBDeString[i][j]) {
                    case "0":
                        matrizBDeString[i][j] = " ";
                        break;
                    case "1":
                        matrizBDeString[i][j] = "A";
                        break;
                    case "2":
                        matrizBDeString[i][j] = "B";
                        break;
                    case "3":
                        matrizBDeString[i][j] = "C";
                        break;
                    case "4":
                        matrizBDeString[i][j] = "D";
                        break;
                    case "5":
                        matrizBDeString[i][j] = "E";
                        break;
                    case "6":
                        matrizBDeString[i][j] = "F";
                        break;
                    case "7":
                        matrizBDeString[i][j] = "G";
                        break;
                    case "8":
                        matrizBDeString[i][j] = "H";
                        break;
                    case "9":
                        matrizBDeString[i][j] = "I";
                        break;
                    case "10":
                        matrizBDeString[i][j] = "J";
                        break;
                    case "11":
                        matrizBDeString[i][j] = "K";
                        break;
                    case "12":
                        matrizBDeString[i][j] = "L";
                        break;
                    case "13":
                        matrizBDeString[i][j] = "M";
                        break;
                    case "14":
                        matrizBDeString[i][j] = "N";
                        break;
                    case "15":
                        matrizBDeString[i][j] = "O";
                        break;
                    case "16":
                        matrizBDeString[i][j] = "P";
                        break;
                    case "17":
                        matrizBDeString[i][j] = "Q";
                        break;
                    case "18":
                        matrizBDeString[i][j] = "R";
                        break;
                    case "19":
                        matrizBDeString[i][j] = "S";
                        break;
                    case "20":
                        matrizBDeString[i][j] = "T";
                        break;
                    case "21":
                        matrizBDeString[i][j] = "U";
                        break;
                    case "22":
                        matrizBDeString[i][j] = "V";
                        break;
                    case "23":
                        matrizBDeString[i][j] = "W";
                        break;
                    case "24":
                        matrizBDeString[i][j] = "X";
                        break;
                    case "25":
                        matrizBDeString[i][j] = "Y";
                        break;
                    case "26":
                        matrizBDeString[i][j] = "Z";
                        break;
                    case "27":
                        matrizBDeString[i][j] = ".";
                        break;
                }
                System.out.print(matrizBDeString[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
