/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class SocketClient implements Runnable{

     String line = "";
    /**
     *
     * 
     */
     @Override
	public void run(){
            Socket socket = null;
            String host = "127.0.0.1";
            try {
                socket = new Socket(host,  9090);
                File file = new File(".//resources//biography_client.txt");
                InputStream in = new FileInputStream(file);
                OutputStream out = socket.getOutputStream();
                byte[] bytes = new byte[16 * 1024];
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                System.out.println("Chiusura connessione...");
                out.close();
                in.close();
                socket.close();  
            }
            catch (IOException ex) {
                System.out.println("Impossibile ottenere il socket input stream. ");
            }
        }
        
        public String getLine()
        {
            return line;
        }
}
