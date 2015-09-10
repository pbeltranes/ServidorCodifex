package Servidor;
import static Servidor.servidor.Cod;
import static Servidor.servidor.History;
import static Servidor.servidor.I;
import static Servidor.servidor.Mensajes;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.*;


public class ServidorHilo extends Thread {

    private  static Socket socket1;
    private DataOutputStream dos1;
    private DataInputStream dis1;
    private int idSessio;
   
    
    public ServidorHilo(Socket socket, int id) {
        this.socket1 = socket;
        this.idSessio = id;
        try {
            dos1 = new DataOutputStream(socket1.getOutputStream());
            dis1 = new DataInputStream(socket1.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void desconnectar() {
        try {
            socket1.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            servidor.Mensajes = "";
            servidor.Cod = " ";
            servidor.History = " ";
            while(true){
                recibir(); // POR APP
                /* POR TERMINAL!
            Scanner n = new Scanner(System.in);
            servidor.Mensajes = n.nextLine();
            dos1.writeUTF(servidor.Mensajes);
            servidor.Mensajes =dis1.readUTF();
            System.out.println("Cliente :" + servidor.Mensajes);*/
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
                    desconnectar();

    }
		/*
	 * Este método recibe lo enviado por el cliente
	 * y lo manda hacia la interfáz gráfica mientras
	 * lo recibido no sea nulo.
	 */
         private static void recibir() throws IOException{
         String n = servidor.disS.readUTF();
          if( n.charAt(0) == '®'){
             n = n.substring(1, n.length());
              Cod = n;
              recibirCodigo();
          }
          else {
              Mensajes = n;
               recibirChat();}
         }     
         
         
        public static void recibirChat() throws IOException{
		if (!Mensajes.equals("")){
			I.setRecibido(""+ Mensajes);
	       }
	}
         public static void enviarChat() throws IOException{
		servidor.dosS.writeUTF(Mensajes);
		servidor.dosS.flush();
		Mensajes = "";
          }
          public static void recibirCodigo() throws IOException{
			I.setCodigo(""+Cod);
	}

         public static void enviarCodigo() throws IOException{
		servidor.dosS.writeUTF("®" + Cod);
		servidor.dosS.flush();
          }


        
          
}