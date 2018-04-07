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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try { 
            serverSocket = new ServerSocket(8080);
            ExecutorService executor = Executors.newFixedThreadPool(1); 
            Intermediary intermediario = new IntermediaryImpl();
            executor.execute(new HttpServer(serverSocket,intermediario));
        } catch (IOException e) {
            System.err.println("Could not listen on port.");
            System.exit(1);
        }  
             
    }  
}
