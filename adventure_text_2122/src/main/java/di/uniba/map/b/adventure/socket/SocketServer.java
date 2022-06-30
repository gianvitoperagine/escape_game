/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.socket;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class SocketServer implements Runnable {

        @Override
        public void run(){
         ServerSocket serverSocket = null;
         try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Connessione avviata: " + serverSocket);
         } catch (IOException ex) {
            System.out.println("Non puoi avviare il server su questo numero di porta. ");
         }
         Socket socket = null;
         InputStream in = null;
         OutputStream out = null;

         try {
            socket = serverSocket.accept();
            System.out.println(
                    "Connessione accettata: " + socket);
         } catch (IOException ex) {
            System.out.println("Connessione al client non accettata ");
         }

         try {
            in = socket.getInputStream();
         } catch (IOException ex) {
            System.out.println("Impossibile ottenere il socket input stream. ");
         }

         try {
            out = new FileOutputStream(".//resources//biography_server.txt");
         } catch (FileNotFoundException ex) {
            System.out.println("File non trovato. ");
         }
         byte[] bytes = new byte[16*1024];

         try {
            int count;
            while ((count = in.read(bytes)) > 0) {
               out.write(bytes, 0, count);
            }
         } catch (IOException ex) {
            System.out.println("Impossibile ottenere il socket input stream. ");
         }

         try {
            out.close();
            in.close();
            socket.close();
            serverSocket.close();
         } catch (IOException ex) {
            System.out.println("Impossibile ottenere il socket input stream. ");
         }
    }
}
