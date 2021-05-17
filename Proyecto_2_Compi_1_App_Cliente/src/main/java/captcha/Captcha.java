/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha;

/**
 *
 * @author 50234
 */
public class Captcha {
    private String id;
    private String name;
    private int vecesAbierto;
    private int aciertos;
    private int fallos;
    private String ultimoRegistro;

    public Captcha(String id, String name, int vecesAbierto, int aciertos, int fallos, String ultimoRegistro) {
        this.id = id;
        this.name = name;
        this.vecesAbierto = vecesAbierto;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.ultimoRegistro = ultimoRegistro;
    }

    public Captcha(String name, int vecesAbierto, int aciertos, int fallos, String ultimoRegistro) {
        this.id = "auxiliar";
        this.name = name;
        this.vecesAbierto = vecesAbierto;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.ultimoRegistro = ultimoRegistro;
    }
    
    public Captcha(String id, String name) {
        this.id = id;
        this.name = name;
        this.vecesAbierto = 0;
        this.aciertos = 0;
        this.fallos = 0;
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
        codigoGuardado += tab + "name = \"" + name + "\"," + newLine;
        codigoGuardado += tab + "veces_abierto = \"" + vecesAbierto + "\"," + newLine;
        codigoGuardado += tab + "aciertos = \"" + aciertos + "\"," + newLine;
        codigoGuardado += tab + "fallos = \"" + fallos + "\"," + newLine;
        codigoGuardado += tab + "ultimo_registro = \"" + ultimoRegistro + "\"" + newLine;
        codigoGuardado += "}";
        
        return codigoGuardado;
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

    public int getVecesAbierto() {
        return vecesAbierto;
    }

    public void setVecesAbierto(int vecesAbierto) {
        this.vecesAbierto = vecesAbierto;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public String getUltimoRegistro() {
        return ultimoRegistro;
    }

    public void setUltimoRegistro(String ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }

    
}
