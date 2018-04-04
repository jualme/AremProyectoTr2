/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.servidorpr2;

/**
 *
 * @author 2118270
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class HttpServer implements Runnable {
   private ServerSocket socket;
    private Intermediary inter;
    

    HttpServer(ServerSocket socket, Intermediary inter) {
        this.socket = socket;
        this.inter = inter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                init();
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() throws IOException{
        Socket clientSocket = null;
        try {            
            clientSocket = socket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if(inputLine.startsWith("GET")){
                String path = inputLine.split(" ")[1];
                if(path.equals("/") || path.equals("/index.html")){
                    Resource indexFile = new ClassPathXmlApplicationContext("applicationContext.xml").getResource("/index.html");
                    String output = "";
                    try {
                        InputStream input = indexFile.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(input));
                        String text;
                        while ((text = br.readLine()) != null) {
                           output+=text;                        
                        }           
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n" + output;
                    
                    out.println(outputLine);
                }
                else if(path.contains("cuadrado")){
                   
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n"                                        
                    + inter.getRespuesta(String.valueOf(path.split("/")[2]));
                    
                    out.println(outputLine);
                    
                    
                }
            }
            
            if (!in.ready()) {
                break;
            }
            
            if (inputLine.equals("")) break;
        }
        
        out.close();
        in.close();
        clientSocket.close();
    }
     
}
