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
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntermediaryImpl implements Intermediary {

    @Override
    public String getRespuesta(String num, Class clase) {
        
        String datos = "El cuadrado del número es: ";        
        Interfaz operation = null;
        try {
            operation = (Interfaz) Class.forName(clase.getName()).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntermediaryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(IntermediaryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IntermediaryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        datos += operation.getURLResult(num);

       
        return datos;
    }
}
