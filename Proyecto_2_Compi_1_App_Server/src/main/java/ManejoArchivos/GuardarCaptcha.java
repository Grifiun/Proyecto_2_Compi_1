/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoArchivos;

import captcha.Captcha;
import java.util.ArrayList;

/**
 *
 * @author 50234
 */
public class GuardarCaptcha {
    
    public void GuardarCaptcha(Captcha captchaAux){
        //Obtenemos el listado actual de captchas
        CargarCaptcha cargarCaptchaAux = new CargarCaptcha();
        ArrayList<Captcha> listadoCaptchaAux = cargarCaptchaAux.leerDatos();
        
        //Agregamos
        listadoCaptchaAux.add(captchaAux);
        
        //generamos codigo guardado
        String textoGuardar = "";//texto a guardar
        try{       
            for(int i = 0; i < listadoCaptchaAux.size(); i++){//recorremos el listado
                System.out.println("Iteracion : " + i);
                textoGuardar += listadoCaptchaAux.get(i).generarCodigoGuardado();
                if((i + 1) < listadoCaptchaAux.size()){
                    textoGuardar += ",";//agregamos coma si no es un solo dato y excepto para el ultimo parametro
                }
                textoGuardar += "\n";//salto linea
            }
        }catch(Exception ex){
            System.out.println("Error al generar codigo captchas: " + ex.getMessage());
        }
        System.out.println("------------------------TEXTO A GUARDAR CAPTCHA:----------------------\n\n\n\n");
        System.out.println(textoGuardar);
        System.out.println("----------------------------------------------------------------------\n");
        //Guardamos
        Guardar guardar = new Guardar();
        guardar.guardarDatos(textoGuardar, "listadoCaptcha");
    }
}
