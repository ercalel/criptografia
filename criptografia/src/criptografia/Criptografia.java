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

    // Metodo principal main
    public static void main(String[] xyz) throws IOException {
        Contorlador controlador = new Contorlador();
        controlador.menu();
    }
}