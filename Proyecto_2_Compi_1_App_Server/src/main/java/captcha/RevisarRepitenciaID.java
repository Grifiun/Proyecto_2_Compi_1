/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha;

import ManejoArchivos.CargarCaptcha;
import clasesDAO.TokenError;
import java.util.ArrayList;

/**
 *
 * @author 50234
 */
public class RevisarRepitenciaID {
    
    public TokenError revisarExistenciaCaptchaConId(String id){
        if(id == null){
            return null;
        }
        
        id.replaceAll("\"", "");
        
        CargarCaptcha cargarCaptchaAux = new CargarCaptcha();
        ArrayList<Captcha> listadoCaptchaAux = cargarCaptchaAux.leerDatos();
        
        //generamos codigo guardado
        try{       
            for(int i = 0; i < listadoCaptchaAux.size(); i++){//recorremos el listado
                if(listadoCaptchaAux.get(i).getId().replaceAll("\"", "").equals(id)){//si es igual
                    return new TokenError("ERROR SEMANTICO", "Id del captcha", "El id del captcha esta repetido", 1, 1);
                }
            }
        }catch(Exception ex){
            System.out.println("Error al generar codigo captchas: " + ex.getMessage());
        }
        
        return null;
    }
}
