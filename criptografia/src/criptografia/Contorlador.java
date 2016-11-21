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
                        System.out.println("Escriba la cadena a desencriptar:");
                        String _cadena = entrada.readLine();
                        decodificar(_cadena);
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

    /* . . . . . . . . . . .  INICIO DE LA CODIFICACIÓN . . . . . . . . . . . */
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
        matriz_valores = multiplicar_matriz(matriz_valores, m);
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
    private int[][] multiplicar_matriz(int[][] matriz_valores, int m) {
        int mtemp[][] = new int[m][4];
        for (int k = 0; k < m; k++) { // recorre las filas+1 filas de matriz_valores
            for (int j = 0; j < 4; j++) { // recorre las colunas de matriz_a
                for (int i = 0; i < 4; i++) { // recorre las columnas de matriz_valores
                    mtemp[k][j] += matriz_valores[k][i] * matriz_a[i][j];// realizamos la multiplicacion                    
                }
                System.out.print(mtemp[k][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");

        return mtemp;
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
                cadena_codificada[y][x] += ","; // coma para poder identificar casillas
                System.out.print(cadena_codificada[y][x]); // Imprimimos la cadena encriptada
            }
        }
        System.out.println("\n");
    }

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

    /* . . . . . . . . . . . . FIN DE LA CODIFICACIÓN . . . . . . . . . . . . */
 /* . . . . . . . . . . . INICIO DE LA DECODIFICACIÓN . . . . . . . . . . .*/
    /**
     * Realiza las operaciones necesarias para desencriptar una cadena dada.
     *
     * @param cadena
     */
    private void decodificar(String cadena) {
        // Paso 1: Calcular matriz inversa de de la matriz A
        calcular_inversa();
        // Paso 2: Ordenar el ensaje encriptado
        String m_binario[] = cadena.split(",");
        int m = m_binario.length / 4;
        int[][] matriz_ordenada = new int[m][4];
        ordenar_mensaje(m_binario, matriz_ordenada);
        // Paso 3: Multiplicar matriz_ordenada por matriz_inversa
        matriz_ordenada = obtener_matriz_desencriptada(matriz_ordenada, m);
        // Paso 4: Cambiar los valores encontrados por el valor del alfabeto correspondiente
        System.out.println("El mensaje es:\n");
        mostrar_mensaje(matriz_ordenada, m);
        System.out.println("\n");
    }

    /**
     * Calcula la matriz inversa de la matriz A.
     *
     * @param matirz_inversa
     */
    private void calcular_inversa() {
        //R4 = R4-R1
        for (int i = 0; i < 4; i++) {
            matriz_identidad[3][i] = matriz_identidad[3][i] - matriz_identidad[0][i];
        }
        //R1 = R1-2*R2
        for (int i = 0; i < 4; i++) {
            matriz_identidad[0][i] = matriz_identidad[0][i] - 2 * matriz_identidad[1][i];
        }
        //R3 = R3-R2
        for (int i = 0; i < 4; i++) {
            matriz_identidad[2][i] = matriz_identidad[2][i] - matriz_identidad[1][i];
        }
        //R3 = -R3
        for (int i = 0; i < 4; i++) {
            matriz_identidad[2][i] = -1 * matriz_identidad[2][i];
        }
        //R2 = R2-R3
        for (int i = 0; i < 4; i++) {
            matriz_identidad[1][i] = matriz_identidad[1][i] - matriz_identidad[2][i];
        }
        //R1 = R1+2*R3
        for (int i = 0; i < 4; i++) {
            matriz_identidad[0][i] = matriz_identidad[0][i] + 2 * matriz_identidad[2][i];
        }
        //R4 = -R4
        for (int i = 0; i < 4; i++) {
            matriz_identidad[3][i] = -1 * matriz_identidad[3][i];
        }
        //R2 = R2-R4
        for (int i = 0; i < 4; i++) {
            matriz_identidad[1][i] = matriz_identidad[1][i] - matriz_identidad[3][i];
        }
        //R1 = R1-R4
        for (int i = 0; i < 4; i++) {
            matriz_identidad[0][i] = matriz_identidad[0][i] - matriz_identidad[3][i];
        }
    }

    /**
     * Ordena el mensaje encriptado
     *
     * @param cadena
     */
    private void ordenar_mensaje(String m_binario[], int matriz_ordenada[][]) {
        int y = 0;
        int x = 0;
        for (String b : m_binario) {
            String tmp_valor = "";
            for (int i = 0; i < b.length(); i = i + 4) {
                tmp_valor += obtener_valor_numerico(b.substring(i, i + 4));
            }
            matriz_ordenada[y][x] = Integer.parseInt(tmp_valor);

            x++;
            y = x == 4 ? y + 1 : y;
            x = x < 4 ? x : 0;
        }
    }

    /**
     * Retorna el número correspondiente al vinario asociado.
     *
     * @param valor
     * @return
     */
    private int obtener_valor_numerico(String valor) {
        switch (valor) {
            case "0000":
                return 0;
            case "0001":
                return 1;
            case "0010":
                return 2;
            case "0011":
                return 3;
            case "0100":
                return 4;
            case "0101":
                return 5;
            case "0110":
                return 6;
            case "0111":
                return 7;
            case "1000":
                return 8;
            case "1001":
                return 9;
        }
        return 0;
    }

    /**
     * Multiplica la matriz con los valores encriptados por la matriz inversa de
     * A, para obtener el mensaje en su valor numérico.
     *
     * @param m
     * @param n
     * @return
     */
    private int[][] obtener_matriz_desencriptada(int[][] m, int n) {
        int mtemp[][] = new int[n][4];
        for (int ym = 0; ym < n; ym++) { // recorre las n filas de m
            for (int xinv = 0; xinv < 4; xinv++) { // recorre las colunas de a
                for (int xm = 0; xm < 4; xm++) { // recorre las columnas de m
                    mtemp[ym][xinv] += m[ym][xm] * matriz_identidad[xm][xinv];// realizamos la multiplicacion
                }
                System.out.print(mtemp[ym][xinv] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
        return mtemp;
    }

    /**
     * Imprime el mensaje que se ha desencriptado.
     *
     * @param matriz
     * @param m
     */
    private void mostrar_mensaje(int[][] matriz, int m) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(obtener_letra(matriz[i][j]));
            }
        }
    }

    /**
     * Retorna el caracter correspondiente de acuerdo al valor numérico.
     *
     * @param matrizBDeString
     * @param m
     */
    private char obtener_letra(int valor) {
        switch (valor) {
            case 0:
                return ' ';
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'D';
            case 5:
                return 'E';
            case 6:
                return 'F';
            case 7:
                return 'G';
            case 8:
                return 'H';
            case 9:
                return 'I';
            case 10:
                return 'J';
            case 11:
                return 'K';
            case 12:
                return 'L';
            case 13:
                return 'M';
            case 14:
                return 'N';
            case 15:
                return 'O';
            case 16:
                return 'P';
            case 17:
                return 'Q';
            case 18:
                return 'R';
            case 19:
                return 'S';
            case 20:
                return 'T';
            case 21:
                return 'U';
            case 22:
                return 'V';
            case 23:
                return 'W';
            case 24:
                return 'X';
            case 25:
                return 'Y';
            case 26:
                return 'Z';
            case 27:
                return '.';
        }
        return 0;
    }
}
