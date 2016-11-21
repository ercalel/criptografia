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

    // Contructor
    public Contorlador() {
        //Cuerpo del constructor vacío
    }

    /**
     * Menú princial del progrma.
     *
     * @throws java.io.IOException
     */
    public void menu() throws IOException {
        // Objeto para la entrada de datos
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        // Valor ingresado por el usuario
        int opcion = 0;
        // Cadena ingresada por el usuario
        String cadena;

        do {
            System.out.println(""
                    + "---------- Menú de inicio ----------\n"
                    + "------------------------------------\n"
                    + "[1] Encriptar mensaje.\n"
                    + "[2] Desencriptar mensaje.\n"
                    + "[3] Finalizar progrma.\n");
            System.out.print("elmer@calel$ ");

            try {
                opcion = Integer.parseInt(entrada.readLine());
            } catch (IOException | NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                // Encriptar
                case 1:
                    System.out.println("Escriba un mensaje:\n");
                    cadena = entrada.readLine();
                    encriptar_cadena(cadena);
                    break;
                // Desencriptar
                case 2:
                    try {
                        System.out.println("Escriba la cadena a desencriptar:");
                        cadena = entrada.readLine();
                        desencriptar_cadena(cadena);
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
     */
    private void encriptar_cadena(String cadena) {
        int m = (cadena.length() / 4) + 1; // filas de la matriz
        int[][] matriz_valores = new int[m][4];

        // Paso 1 y 2
        asociar_valores_numericos(matriz_valores, m, cadena);
        // Paso 3
        obtener_mensaje_encriptado(matriz_valores, m);
    }

    /**
     * Divide la cadena de entrada y la asocia con cada uno de los valores
     * numéricos establecidos en el enunciado. Almacena los valores en una
     * matriz de m*4, esta matriz conotiene el cócigo del mensaje a encriptar.
     */
    private void asociar_valores_numericos(int[][] matriz_valores, int m, String cadena) {
        int k = 0;
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < 4; x++) {
                if (k < cadena.length()) {
                    matriz_valores[y][x] = asociar_valor_numerico(Character.toUpperCase(cadena.charAt(k)));
                } else {
                    matriz_valores[y][x] = 0;
                }
                k++;
            }
        }
    }

    /**
     * Multiplica cada elemento de la matriz de valores por la matirz A, obtiene
     * la matriz con el mensaje encriptado.
     */
    private void obtener_mensaje_encriptado(int[][] matriz_valores, int m) {
        int mtemp[][] = new int[m][4];
        for (int k = 0; k < m; k++) { // recorre las m filas de matriz_valores
            for (int j = 0; j < 4; j++) { // recorre las colunas de matriz_a
                for (int i = 0; i < 4; i++) { // recorre las columnas de matriz_valores
                    mtemp[k][j] += matriz_valores[k][i] * matriz_a[i][j];// realizamos la multiplicacion                    
                }
            }
        }
        // Aumenta la seguridad e imprime el mensaje
        aumentar_seguridad(mtemp, m);
    }

    /**
     * Convierte cada celda numérica de la matriz_valores a su correspondiente
     * binario. Siendo esta la salida de la cadena encriptada.
     */
    private void aumentar_seguridad(int[][] matriz_valores, int m) {
        System.out.println("La cadena encriptada es:");
        String[][] matriz_codificada = new String[m][4];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < 4; x++) {
                String casilla = String.valueOf(matriz_valores[y][x]);
                matriz_codificada[y][x] = "";
                for (int i = 0; i < casilla.length(); i++) {
                    switch (casilla.charAt(i)) {
                        case '0':
                            matriz_codificada[y][x] += "0000";
                            break;
                        case '1':
                            matriz_codificada[y][x] += "0001";
                            break;
                        case '2':
                            matriz_codificada[y][x] += "0010";
                            break;
                        case '3':
                            matriz_codificada[y][x] += "0011";
                            break;
                        case '4':
                            matriz_codificada[y][x] += "0100";
                            break;
                        case '5':
                            matriz_codificada[y][x] += "0101";
                            break;
                        case '6':
                            matriz_codificada[y][x] += "0110";
                            break;
                        case '7':
                            matriz_codificada[y][x] += "0111";
                            break;
                        case '8':
                            matriz_codificada[y][x] += "1000";
                            break;
                        case '9':
                            matriz_codificada[y][x] += "1001";
                            break;
                    }
                }
                matriz_codificada[y][x] += ","; // coma para poder identificar casillas
                System.out.print(matriz_codificada[y][x]); // Imprimimos la cadena encriptada
            }
        }
        System.out.println("\n");
    }

    /**
     * Asocia cada componente de la cadena ingresada por el usuario con su valor
     * numérico correspondiente.
     */
    private int asociar_valor_numerico(int ascii) {
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
     */
    private void desencriptar_cadena(String cadena) {
        String[] array_binarios = cadena.split(",");
        int m = array_binarios.length / 4;
        int[][] matriz_encriptada = new int[m][4];        
        int matriz_inversa[][] = new int[4][4];
        colonar_identidad(matriz_inversa);
        // Paso 1
        calcular_inversa(matriz_inversa);
        // Paso 2      
        ordenar_mensaje_encriptado(array_binarios, matriz_encriptada);
        // Paso 3
        matriz_encriptada = obtener_matriz_desencriptada(matriz_encriptada, matriz_inversa, m);
        // Paso 4        
        mostrar_mensaje(matriz_encriptada, m);
    }
    
    /**
     * Clona la matriz identidad.
     */
    private void colonar_identidad(int matriz_inversa[][]){
        for (int y = 0; y < 4; y++) {
            System.arraycopy(matriz_identidad[y], 0, matriz_inversa[y], 0, 4);            
        }
    }

    /**
     * Calcula la matriz inversa de la matriz A.
     */
    private void calcular_inversa(int matriz_inversa[][]) {

        //R4 = R4-R1
        for (int i = 0; i < 4; i++) {
            matriz_inversa[3][i] = matriz_inversa[3][i] - matriz_inversa[0][i];
        }
        //R1 = R1-2*R2
        for (int i = 0; i < 4; i++) {
            matriz_inversa[0][i] = matriz_inversa[0][i] - 2 * matriz_inversa[1][i];
        }
        //R3 = R3-R2
        for (int i = 0; i < 4; i++) {
            matriz_inversa[2][i] = matriz_inversa[2][i] - matriz_inversa[1][i];
        }
        //R3 = -R3
        for (int i = 0; i < 4; i++) {
            matriz_inversa[2][i] = -1 * matriz_inversa[2][i];
        }
        //R2 = R2-R3
        for (int i = 0; i < 4; i++) {
            matriz_inversa[1][i] = matriz_inversa[1][i] - matriz_inversa[2][i];
        }
        //R1 = R1+2*R3
        for (int i = 0; i < 4; i++) {
            matriz_inversa[0][i] = matriz_inversa[0][i] + 2 * matriz_inversa[2][i];
        }
        //R4 = -R4
        for (int i = 0; i < 4; i++) {
            matriz_inversa[3][i] = -1 * matriz_inversa[3][i];
        }
        //R2 = R2-R4
        for (int i = 0; i < 4; i++) {
            matriz_inversa[1][i] = matriz_inversa[1][i] - matriz_inversa[3][i];
        }
        //R1 = R1-R4
        for (int i = 0; i < 4; i++) {
            matriz_inversa[0][i] = matriz_inversa[0][i] - matriz_inversa[3][i];
        }
    }

    /**
     * Ordena el mensaje encriptado en una matriz de m*4, Separa la cadena de
     * binarios y los separa por cuartetos, Combierte cada binario a su
     * correspondiente valor numérico.
     */
    private void ordenar_mensaje_encriptado(String[] array_binarios, int[][] matriz_encriptada) {
        int y = 0;
        int x = 0;
        for (String b : array_binarios) {
            String tmp_valor = "";
            for (int i = 0; i < b.length(); i = i + 4) {
                tmp_valor += cambiar_binario_numerico(b.substring(i, i + 4));
            }
            matriz_encriptada[y][x] = Integer.parseInt(tmp_valor);

            x++;
            y = x == 4 ? y + 1 : y;
            x = x < 4 ? x : 0;
        }
    }

    /**
     * Retorna el número correspondiente al binario asociado.
     */
    private int cambiar_binario_numerico(String valor) {
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
     */
    private int[][] obtener_matriz_desencriptada(int[][] matriz_encriptada, int matriz_inversa[][], int m) {
        int mtemp[][] = new int[m][4];
        for (int ym = 0; ym < m; ym++) { // recorre las m filas de matriz_encriptada
            for (int xinv = 0; xinv < 4; xinv++) { // recorre las colunas de a
                for (int xm = 0; xm < 4; xm++) { // recorre las columnas de matriz_encriptada
                    mtemp[ym][xinv] += matriz_encriptada[ym][xm] * matriz_inversa[xm][xinv];// realizamos la multiplicacion
                }
            }
        }
        return mtemp;
    }

    /**
     * Imprime el mensaje que se ha desencriptado.
     */
    private void mostrar_mensaje(int[][] matriz, int m) {
        System.out.println("El mensaje es:\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cambiar_numero_caracter(matriz[i][j]));
            }
        }
        System.out.println("\n");
    }

    /**
     * Cambia los valores encontrados por el valor del alfabeto correspondiente.
     */
    private char cambiar_numero_caracter(int valor) {
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
