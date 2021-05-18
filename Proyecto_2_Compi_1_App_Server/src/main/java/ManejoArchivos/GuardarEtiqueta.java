/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoArchivos;

import captcha.Captcha;
import etiquetas.Etiqueta;

/**
 *
 * @author 50234
 */
public class GuardarEtiqueta {
    
    public void guardarEtiqueta(Etiqueta etiquetaGCIC, String codigoScripting/*, String direccionRedirect*/){
        if(etiquetaGCIC == null){
            return;//terminamos la funcion si es nulo
        }
        
        //OBTENEMOS LOS DATOS
        //generamos numero aleatorio por si no existe algun parametro
        int max = 50000, min = 10000;
        int getRandomValue = (int) (Math.random()*(max-min)) + min;

        String id = "", name = "";

        //Obtenemos datos
        String idAuxEtiqueta = etiquetaGCIC.getParametroEtiqueta("id");
        String nameAuxEtiqueta = etiquetaGCIC.getParametroEtiqueta("name");

        if(idAuxEtiqueta != null){//existe id
            id = idAuxEtiqueta.replaceAll("\"", "").trim();
        }else{//generamos idAuxiliar
            id = "id_auxiliar_" + getRandomValue;
        }
        
        if(nameAuxEtiqueta != null){//existe nombre
            name = nameAuxEtiqueta.replaceAll("\"", "").trim();
        }else{//generamos nombreAuxiliar
            name = "name_auxiliar_" + getRandomValue;
        }
        
        ////////////////////////////////////////GUARDAR ARCHIVO HTML
        try{
            //codigo html
            String codigoHTMLEtiqueta = etiquetaGCIC.generarCodigoHTML(0);
            
            if(codigoHTMLEtiqueta == null){
                codigoHTMLEtiqueta = "";           
            }
            
            //REDIRECT
            //String inputRedirect = "<input id=\"direccion\"  type=\"text\" style = \"  visibility: hidden;\" > " + direccionRedirect.replaceAll("\"", "") + " </input>\n";
            //codigoHTMLEtiqueta += inputRedirect;
            
            //Agregamos el codigo scripting
            if(codigoScripting != null){
                codigoHTMLEtiqueta += "<script>\n";
                codigoHTMLEtiqueta += codigoScripting.replaceAll("“","\"").replaceAll("”","\"").replaceAll("‘","\'").replaceAll("’","\'");
                codigoHTMLEtiqueta += "</script>\n";
            }
            
            System.out.println("------------------------TEXTO A GUARDAR ETIQUETA HTML:----------------------\n\n\n\n");
            System.out.println(codigoHTMLEtiqueta.replaceAll("“","\"").replaceAll("”","\"").replaceAll("‘","\'").replaceAll("’","\'"));            
            System.out.println("----------------------------------------------------------------------------\n");
            //Guardamos la etiqueta
            Guardar guardar = new Guardar();
            guardar.guardarDatos(codigoHTMLEtiqueta, id);
            
        }catch(Exception ex){
            System.out.println("Error bloque guardar archivo html");
        }
        
        
        ////////////////////////////////////////GUARDAR CAPTCHA
        try{           
            
            //Generamos el captchaAuxiliar
            Captcha captchaAux = new Captcha(id, name);
            
            //Guardamos el captcha auxiliar
            GuardarCaptcha guardarCaptchaAux = new GuardarCaptcha();
            guardarCaptchaAux.GuardarCaptcha(captchaAux);
        }catch(Exception ex){
            System.out.println("Error bloque guardar archivo captcha");
        }
    }
}
