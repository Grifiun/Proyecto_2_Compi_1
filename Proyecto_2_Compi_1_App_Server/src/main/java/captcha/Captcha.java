/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 50234
 */
public class Captcha {
    private String id;
    private String name;
    private String vecesAbierto;
    private String aciertos;
    private String fallos;
    private String ultimoRegistro;

    public Captcha(String id, String name, String vecesAbierto, String aciertos, String fallos, String ultimoRegistro) {
        this.id = id;
        this.name = name;
        this.vecesAbierto = vecesAbierto;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.ultimoRegistro = ultimoRegistro;
    }

    public Captcha(String name, String vecesAbierto, String aciertos, String fallos, String ultimoRegistro) {
        this.id = "auxiliar";
        this.name = name;
        this.vecesAbierto = vecesAbierto;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.ultimoRegistro = ultimoRegistro;
        System.out.println("Nuevo captcha desde el pasre:");
    }
    
    public Captcha(String id, String name) {
        this.id = id;
        this.name = name;
        this.vecesAbierto = "0";
        this.aciertos = "0";
        this.fallos = "0";
        this.ultimoRegistro = "2021-05-20";
    }
    
    /**
     * Generamos el codigo para guardar la informacion
     * Ejemplo:
     * 
     *      captcha_matematico_2{
                name = "Captcha Matematico 1",
                veces_abierto = 0,
                aciertos = 0,
                fallos = 0,   
                ultimo_registro = "2021-05-16"   
            }
     * @return 
     */
    public String generarCodigoGuardado(){
        String newLine = "\n";
        String tab = "\t";
        String codigoGuardado = id + "{" + newLine;
        codigoGuardado += tab + "name = \""            + name           + "\"," + newLine;
        codigoGuardado += tab + "veces_abierto = "     + vecesAbierto   + ","   + newLine;
        codigoGuardado += tab + "aciertos =  "         + aciertos       + ","   + newLine;
        codigoGuardado += tab + "fallos =  "           + fallos         + ","   + newLine;
        codigoGuardado += tab + "ultimo_registro = \"" + ultimoRegistro + "\""  + newLine;
        codigoGuardado += "}";
        
        return codigoGuardado;
    }
    
    /**
     * Aumentamos el veces abierto una vez
     */    
    public void aumentarUso(){
        int numero = 0;
        try{
            numero = Integer.parseInt(vecesAbierto);
            numero++;//aumentamos el numero
            
            vecesAbierto = "" + numero;
        }catch(Exception ex){
            System.out.println("Error al aumentar el numero de vecesa abierto");
        }
    }
    
    /**
     * Aumentamos el veces acertados
     */    
    public void aumentarAcierto(){
        int numero = 0;
        try{
            numero = Integer.parseInt(aciertos);
            numero++;//aumentamos el numero
            
            aciertos = "" + numero;
        }catch(Exception ex){
            System.out.println("Error al aumentar el numero de aciertos");
        }
    }
    
    /**
     * Aumentamos el veces acertados
     */    
    public void actualizarFecha(){
        String fechaAux;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        fechaAux = formatter.format(date);
        try{
            ultimoRegistro = fechaAux;
        }catch(Exception ex){
            System.out.println("Error al actualizar la fecha");
        }
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVecesAbierto() {
        return vecesAbierto;
    }

    public void setVecesAbierto(String vecesAbierto) {
        this.vecesAbierto = vecesAbierto;
    }

    public String getAciertos() {
        return aciertos;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

    public String getFallos() {
        return fallos;
    }

    public void setFallos(String fallos) {
        this.fallos = fallos;
    }
    
    public String getUltimoRegistro() {
        return ultimoRegistro;
    }

    public void setUltimoRegistro(String ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }

    
}
