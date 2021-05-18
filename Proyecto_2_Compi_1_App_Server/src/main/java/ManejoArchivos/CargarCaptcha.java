/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoArchivos;

import captcha.Captcha;
import clasesDAO.TokenError;
import gramatica_guardado.LexerGuardado;
import gramatica_guardado.ParserGuardado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 50234
 */
public class CargarCaptcha {   
       
    /**
     * Funcion dedicada a leer archivos
     */
    public ArrayList<Captcha> leerDatos(){
        ArrayList<Captcha> listadoCaptcha = new ArrayList();
        String path = "archivos_captcha/listadoCaptcha.txt";        
        
        try {           
            File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                System.out.println("No existe el archivo requerido");
            }else{
                BufferedReader br = new BufferedReader(new FileReader(path));
                Reader reader = br;
                LexerGuardado lexer = new LexerGuardado(reader);                
                ParserGuardado parser = new ParserGuardado(lexer);
                
                try {
                    parser.parse();
                    System.out.println("ERRORES");
                    String errores = "";
                    ArrayList<TokenError> listadoErroresLexicos = lexer.obtenerListadoErroresLexicos();
                    ArrayList<TokenError> listadoErroresSintacticos = parser.getListadoErroresSintacticos();
                    
                    try{
                        for(TokenError te: listadoErroresLexicos){
                            errores += te.getTipoToken()+" Linea"+te.getLinea()+" Columna: "+te.getColumna()+" Lexema :"+te.getLexema()+" Mensaje: "+te.getMsgError() + "\n";                                                     
                        }
                    }catch(Exception ex){}
                    try{
                        for(TokenError te: listadoErroresSintacticos){
                            errores += te.getTipoToken()+" Linea"+te.getLinea()+" Columna: "+te.getColumna()+" Lexema :"+te.getLexema()+" Mensaje: "+te.getMsgError() + "\n";                                                     
                        }
                    }catch(Exception ex){}                    
                    System.out.println(errores);
                    System.out.println("EL ARCHIVO DE CARGADO SE PROCESO SIN ERROR \n");
                
                    return parser.getListadoCaptchas();
                        
                } catch (Exception ex) {
                    System.out.println("Error en el parser/lexer: "+ex);
                }                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo leer el archivo requerido: ");
        }
    
        return listadoCaptcha;
    }
    
    public String cargarHTMLCaptcha(String idCaptcha){
        idCaptcha.trim();
        
        String path = "archivos_captcha/" + idCaptcha + ".txt";        
        
        try {           
            File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                System.out.println("No existe el archivo requerido");
                
                return "No existe un captcha con ese id";
            }else{
                BufferedReader br = new BufferedReader(new FileReader(path));
                
                String line = null;
                String message = new String();
                final StringBuffer buffer = new StringBuffer(2048);
                while ((line = br.readLine()) != null) {
                    // buffer.append(line);
                    message += line;
                }
                
                return message;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo leer el archivo requerido: ");
        }
        
        return "";
    }
    
    public List<ArrayList<String>> cargarCaptchasTablas(){
        List<ArrayList<String>> datosTabla = new ArrayList();
        
        ArrayList<Captcha> listadoCaptcha = leerDatos();
                
        try{
            for(Captcha captchaAux: listadoCaptcha){
                ArrayList<String> datosCaptchaAux = new ArrayList();
                datosCaptchaAux.add(captchaAux.getId());//id
                datosCaptchaAux.add(captchaAux.getName());//nombre
                datosCaptchaAux.add(captchaAux.getVecesAbierto());//veces abierto
                datosCaptchaAux.add(captchaAux.getAciertos());//aciertos
                datosCaptchaAux.add(captchaAux.getFallos());//fallos
                datosCaptchaAux.add(captchaAux.getUltimoRegistro());//ultima fecha de modifiacion
                
                datosTabla.add(datosCaptchaAux);//agregamos los datos del captcha                
            }
        }catch(Exception ex){
            System.out.println("Error al tabular datos: " + ex.getMessage());
        }
        
        return datosTabla;
    }
    
}
