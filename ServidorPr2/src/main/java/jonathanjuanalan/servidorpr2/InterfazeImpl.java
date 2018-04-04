/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.servidorpr2;

/**
 *
 * @author Juancho
 */

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class InterfazeImpl implements Interfaz{
     @Override
    public String getResult(String num){
        URL restApi = null;
        String res = "";
        try {
            restApi = new URL("https://pure-escarpment-60633.herokuapp.com/cuadrado/"+num);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InterfazeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(restApi.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine;
            }
        }catch (IOException x) {
            System.err.println(x);
        }
        
        return res;
    }
}
