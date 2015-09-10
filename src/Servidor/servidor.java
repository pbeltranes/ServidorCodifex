package Servidor;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import Login.*;
import Interfaz.interfaz;
import java.util.List;

public class servidor {
    public static login L;
    public static interfaz I;
    public static String Mensajes;
    public static String Cod;
    protected static DataOutputStream dosS; // CHAT
    protected static DataInputStream disS; //CHAT
    static ServerSocket ss;
    public static String History;
    public static String Nombre;

    
    public static void main(String args[]) throws IOException {
        System.out.print("Inicializando servidor... ");
        try {

            L = new login();
            I = new interfaz();
            ss = new ServerSocket(5000);
            History = "";
            Cod = "";
            Mensajes = "";
            Nombre = "";
            System.out.println("\t[OK]");
            boolean entrar = true;
            while(entrar){
            int idSession1 = 1;
            Socket so;
            System.out.print("Esperando Cliente "+ idSession1 +" ...");
            so = ss.accept();
            L.setVisible(true);
            System.out.println("\t[OK]");
            dosS = new DataOutputStream((so).getOutputStream());
            disS = new DataInputStream((so).getInputStream());
            ((ServidorHilo)new ServidorHilo(so,idSession1)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         
}