package criptografia;

import java.io.*;

/**
 * Programa que permite encriptar y desencriptar mensajes, pidiendo al usuario 
 * el mensaje para encriptarlo o bien solicitar una cadena encriptada para poder
 * decifrar el menseje.
 * 
 * @author Elmer C. Ramos™
 */
public class Criptografia {

    //objeto para la entrada de datos
    static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    //matriz default
    static int[][] matrizA = {{1, 2, 0, 3}, {0, 1, 1, 1}, {0, 1, 0, 1}, {1, 2, 0, 2}};

    //matriz identidad
    static int[][] matrizIdentidad = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

    //matriz m*4 donde se almacena nuestra cadena con valores enteros
    static int[][] matrizB;

    //matriz ue contiene el resultado de la multiplicacion entre matrices
    static int[][] matrizResultado;

    static int filasMatrizResultado;

    //temporales para comparar
    static String cadenaParaEncriptar = "";
    static String cadenaParaDesencriptar = "";

    //metodo principal main
    public static void main(String[] args) throws IOException {

        int opcion;//valor ingresado por el usuario

        do {//inicio del  do...while ue permite ue la aplicacion se ejecute una y otra vez
            //menu ue se muestra en pantalla para el usuario
            System.out.println();
            System.out.println("Elija una opcion:");
            System.out.println("1. Encriptar mensaje.");
            System.out.println("2. Desencriptar mensaje.");
            System.out.println("3. Salir.");

            //castea un string a int para poder usarlo en el switch
            opcion = Integer.parseInt(entrada.readLine());

            System.out.println();//imprime una linea en blanco

            switch (opcion) {//inicio del switch, realiza lo indicado de acuerdo al numero ingresado
                case 1:
                    //reiniciamos cadenaParaEncriptar
                    cadenaParaEncriptar = "";

                    //cadena a encriptar
                    String cadenaAEncriptar;

                    System.out.println("Ingrese el mensaje a encriptar:");
                    cadenaAEncriptar = entrada.readLine();

                    System.out.println("La el mensaje encriptado es:");
                    //llamamos al metodo encriptarCadena
                    encriptarCadena(cadenaAEncriptar);

                    break;

                case 2: {
                    try {
                        //cadena a desencriptar
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
                }
                break;

                case 3:
                    System.exit(0);//termina la ejecución del programa
                    break;

                //opcion ue indica ue la opcion elegida es incorrecta y solicita un valor correcto
                default:
                    if (opcion < 1 || opcion > 3) {
                        System.out.println("La opción no es válida!");
                    }
                    break;

            }//fin del switch
        } while (opcion != 3);//fin del do...while
    }//fin del metodo main

    //llena la matrizB con los caracteres de cadena
    static void encriptarCadena(String cadena) {
        //numero de filas de la matrizB
        int m = cadena.length() / 4;
        filasMatrizResultado = m;

        //asignamos tamaño a matrizB
        matrizB = new int[m + 1][4];

        //matriz ue contendra la cadena ingresada
        String[][] matrizBDeString = new String[m + 1][4];

        //llenamos matrizBDeString con los caracteres de cadena
        int k = 0;
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (k < cadena.length()) {
                    //asigna a cada pocicion de matrizBDeChar el caracter correspondiente
                    matrizBDeString[i][j] = cadena.substring(k, k + 1);
                    k++;
                } else {
                    matrizBDeString[i][j] = " ";
                }//fin del if...else
            }//fin del for de j
        }//fin del for de i

        //convertimos cada pocicion de matrizBDeString a un valor numerico
        cambiarMatriz(matrizBDeString, m);

        //llenamos la matrizB con valores enteros
        convertirMatriz(matrizB, matrizBDeString, m);

        //multiplicamos la matrizB por matrizA
        multiplicarMatriz(matrizA, matrizB, m);

        //creamos una nueva cadena con matrizResultado ue se obtiene con el producto de las matrices B y A
        convertirCadenaABinario(matrizResultado, m);

    }//fin del merodo encriptarCadena

    //asigna a matrizBDeString un valor numerico euivalente a cada letra en cada pocicion
    static void cambiarMatriz(String[][] matrizBDeString, int m) {
        //recorremos la matrizBDeString y cambia los caracteres por numeros
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                switch (matrizBDeString[i][j]) {
                    case " ":
                        matrizBDeString[i][j] = "0";
                        break;
                    case "a":
                    case "A":
                        matrizBDeString[i][j] = "1";
                        break;
                    case "b":
                    case "B":
                        matrizBDeString[i][j] = "2";
                        break;
                    case "c":
                    case "C":
                        matrizBDeString[i][j] = "3";
                        break;
                    case "d":
                    case "D":
                        matrizBDeString[i][j] = "4";
                        break;
                    case "e":
                    case "E":
                        matrizBDeString[i][j] = "5";
                        break;
                    case "f":
                    case "F":
                        matrizBDeString[i][j] = "6";
                        break;
                    case "g":
                    case "G":
                        matrizBDeString[i][j] = "7";
                        break;
                    case "h":
                    case "H":
                        matrizBDeString[i][j] = "8";
                        break;
                    case "i":
                    case "I":
                        matrizBDeString[i][j] = "9";
                        break;
                    case "j":
                    case "J":
                        matrizBDeString[i][j] = "10";
                        break;
                    case "k":
                    case "K":
                        matrizBDeString[i][j] = "11";
                        break;
                    case "l":
                    case "L":
                        matrizBDeString[i][j] = "12";
                        break;
                    case "m":
                    case "M":
                        matrizBDeString[i][j] = "13";
                        break;
                    case "n":
                    case "N":
                        matrizBDeString[i][j] = "14";
                        break;
                    case "o":
                    case "O":
                        matrizBDeString[i][j] = "15";
                        break;
                    case "p":
                    case "P":
                        matrizBDeString[i][j] = "16";
                        break;
                    case "q":
                    case "Q":
                        matrizBDeString[i][j] = "17";
                        break;
                    case "r":
                    case "R":
                        matrizBDeString[i][j] = "18";
                        break;
                    case "s":
                    case "S":
                        matrizBDeString[i][j] = "19";
                        break;
                    case "t":
                    case "T":
                        matrizBDeString[i][j] = "20";
                        break;
                    case "u":
                    case "U":
                        matrizBDeString[i][j] = "21";
                        break;
                    case "v":
                    case "V":
                        matrizBDeString[i][j] = "22";
                        break;
                    case "w":
                    case "W":
                        matrizBDeString[i][j] = "23";
                        break;
                    case "x":
                    case "X":
                        matrizBDeString[i][j] = "24";
                        break;
                    case "y":
                    case "Y":
                        matrizBDeString[i][j] = "25";
                        break;
                    case "z":
                    case "Z":
                        matrizBDeString[i][j] = "26";
                        break;
                    case ".":
                        matrizBDeString[i][j] = "27";
                        break;
                }//fin del switch 
            }//fin del for de j
        }//fin del for de i 
    }//fin del metodo convertirMatriz

    //convierte a matrizBDeString a matrizB ue es de enteros
    static void convertirMatriz(int[][] matrizB, String[][] matrizBDeString, int m) {
        //llenamos la matrizB con valores enteros
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                //convertimos cada pocicin a entero
                matrizB[i][j] = Integer.parseInt(matrizBDeString[i][j]);
            }
        }//fin del for
    }//fin del merodo convertirMatriz

    //metodo para multiplicar la matrizA por la matrizB
    static void multiplicarMatriz(int[][] matrizA, int[][] matrizB, int m) {
        //matriz ue contiene el resultado de la multiplicacion
        matrizResultado = new int[m + 1][4];

        //recorremos filas de matrizB y columnas de matrizA
        for (int k = 0; k < m + 1; k++) {//recorre las m+1 filas de matrizB
            for (int j = 0; j < 4; j++) {//recorre las colunas de matrizA
                for (int i = 0; i < 4; i++) {//recorre las columnas de matrizB
                    matrizResultado[k][j] += matrizB[k][i] * matrizA[i][j];//realizamos la multiplicacion
                }//fin del for de i
            }//fin del for j
        }//fin del for k
    }//fin del metodo multiplicarMatriz

    //encriptamos la matrizResultado
    static void convertirCadenaABinario(int[][] matrizResultado, int m) {
        //recorremos matrizResultado para crear una cadena para ue contenga cada numero almacenado en el
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < 4; j++) {
                cadenaParaEncriptar += matrizResultado[i][j];
            }
        }//fin del for

        //cadena final encriptada convalores binarios
        String cadenaEncriptada = "";

        //convertimos cada numero de cadenaAEncriptar a su correspondiente binario 
        convertirABinario(cadenaParaEncriptar, cadenaEncriptada);
    }//fin del metodo encriptarCadena

    //obtiene el resultado final de la encriptacion
    static void convertirABinario(String cadenaAEncriptar, String cadenaEncriptada) {
        //recorremos cadenaAEncriptar y creamos la cadena encriptada
        for (int i = 0; i < cadenaAEncriptar.length(); i++) {
            if (cadenaAEncriptar.indexOf('0', i) == i) {
                cadenaEncriptada += "0000";
            } else if (cadenaAEncriptar.indexOf('1', i) == i) {
                cadenaEncriptada += "0001";
            } else if (cadenaAEncriptar.indexOf('2', i) == i) {
                cadenaEncriptada += "0010";
            } else if (cadenaAEncriptar.indexOf('3', i) == i) {
                cadenaEncriptada += "0011";
            } else if (cadenaAEncriptar.indexOf('4', i) == i) {
                cadenaEncriptada += "0100";
            } else if (cadenaAEncriptar.indexOf('5', i) == i) {
                cadenaEncriptada += "0101";
            } else if (cadenaAEncriptar.indexOf('6', i) == i) {
                cadenaEncriptada += "0110";
            } else if (cadenaAEncriptar.indexOf('7', i) == i) {
                cadenaEncriptada += "0111";
            } else if (cadenaAEncriptar.indexOf('8', i) == i) {
                cadenaEncriptada += "1000";
            } else if (cadenaAEncriptar.indexOf('9', i) == i) {
                cadenaEncriptada += "1001";
            }//fin del if...else
        }//fin del for de 

        //imprime la cadena ingresada por el usuario respectivamente encriptada
        System.out.println(cadenaEncriptada);
    }//fin del metodo convertirABinario

    //calcula la inversa de matrizA, realizanod operaciones con matriz identidad
    static void calcularInversa(int[][] inversaA) {
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
    static void desencriptarCadena(String cadena) {
        //numero de filas de la matrizB
        int nf = (cadena.length() / 4) / 4;

        //asignamos tamaño a matrizB
        matrizB = new int[nf + 1][4];

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
        }//fin del for de i

        //llama al metodo convertirAEntero para pasar de binario a un valor numerico
        convertirAEntero(matrizBDeBinarios, nf);

        //llenamos la matrizB con valores enteros
        convertirMatriz(matrizB, matrizBDeBinarios, nf);

        //recorremos matrizB para crear una cadena para ue contenga cada numero almacenado en el
        for (int i = 0; i < nf + 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrizB[i][j] != 0) {
                    cadenaParaDesencriptar += matrizB[i][j];
                }
            }
        }//fin del for

        //fines de prueba despues boorrar
        System.out.println(cadenaParaDesencriptar);

        //calculamos la inversa de la matriz identidad
        calcularInversa(matrizIdentidad);

        //matriz ue contiene el resultado de la multiplicacion
        int[][] Resultado = new int[filasMatrizResultado + 1][4];

        multiplicar(Resultado, filasMatrizResultado);

        //pasamos una matriz de enteros a matriz de string
        pasarMatrizString(Resultado, matrizBDeBinarios, filasMatrizResultado);

        //damos valor alfavetico a cada pocicion numerica de la matriz
        traducirMatriz(matrizBDeBinarios, filasMatrizResultado);

        //imprimimimos la cadena desencriptada
        for (int i = 0; i < filasMatrizResultado + 1; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrizBDeBinarios[i][j]);
            }
        }//fin del for

        System.out.println();

    }//fin del merodo desencriptarCadena

    //metoso para multiplicar
    static void multiplicar(int[][] a, int m) {
        //recorremos filas de matrizB y columnas de matrizA
        for (int f = 0; f < filasMatrizResultado + 1; f++) {//recorre las m+1 filas de matrizB
            for (int j = 0; j < 4; j++) {//recorre las colunas de matrizA
                for (int i = 0; i < 4; i++) {//recorre las columnas de matrizB
                    a[f][j] += matrizResultado[f][i] * matrizIdentidad[i][j];//realizamos la multiplicacion
                }//fin del for de i
                System.out.print(a[f][j] + "\t");
            }//fin del for j
            System.out.println();
        }//fin del for k

    }//fin del metodo

    //pasa un valor binario a su euivalente en entero
    static void convertirAEntero(String[][] matrizBDeString, int m) {
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
        }//fin del for de i 
    }//fin del metodo convertirABinario

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
                }//fin del switch 
                System.out.print(matrizBDeString[i][j] + "\t");
            }//fin del for de j
            System.out.println();
        }//fin del for de i 
    }//fin del metodo convertirMatriz    

}//fin de la clase c201213600
