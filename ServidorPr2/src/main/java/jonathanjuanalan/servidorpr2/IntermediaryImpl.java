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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntermediaryImpl implements Intermediary {
    
    @Autowired
    private Interfaz api;
    
    public IntermediaryImpl(){

    }
    
    @Override
    public String getRespuesta(String num) {
        return api.getResult(num);
    }
}
