/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.servidorpr2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juancho
 */
public class Cuadrado implements Interfaz {

    @Override
    public String getURLResult(String num) {
        URL rapi = null;
        String res = "";
        try {
            rapi = new URL("https://pure-escarpment-60633.herokuapp.com/cuadrado/" + num);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cuadrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(rapi.openStream()))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }

        return res;
    }

}
