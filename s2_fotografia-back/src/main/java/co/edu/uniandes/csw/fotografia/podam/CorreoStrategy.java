/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.podam;

import java.nio.charset.Charset;
import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *Strategia para crear un correo aleatorio
 * 
 * @author Nicolas Rincon
 */
public class CorreoStrategy implements AttributeStrategy<String>     {

    @Override
    public String getValue() {
        return  randString() + "@" + randString();
    }
    
    private String randString() {
    byte[] array = new byte[10]; 
    new Random().nextBytes(array);
    String generatedString = new String(array, Charset.defaultCharset().forName("UTF-8"));
 
    return generatedString;
    }
    
}
