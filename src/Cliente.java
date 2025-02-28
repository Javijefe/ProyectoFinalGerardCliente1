import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Random dado = new Random();
            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Servidor: " + mensaje);

                if (mensaje.equals("Tira el dado")) {
                    System.out.print("Escribe 'tirar' para lanzar el dado: ");
                    while (!scanner.nextLine().equalsIgnoreCase("tirar")) {
                        System.out.print("Debes escribir 'tirar' para lanzar el dado: ");
                    }
                    int tiro = dado.nextInt(6) + 1;
                    System.out.println("Tiraste un: " + tiro);
                    salida.println(tiro);
                } else if (mensaje.contains("¡Ganaste!") || mensaje.contains("Perdiste.")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}