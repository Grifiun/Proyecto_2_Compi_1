/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author 50234
 */
public class Etiqueta {
    private String tipo;//C_GCIC, C_H1, etc
    private String texto;// Texto, si tienen
    private ArrayList<ParametroEtiquetaInterno> listadoParametros;//listado de parametros, tipo nombre
    private ArrayList<Etiqueta> listadoEtiquetas;// para contenedores, pueden tener más etiquetas dentro
    private CGCICAHTML conversor = new CGCICAHTML();

    public Etiqueta(String tipo, String texto, ArrayList<ParametroEtiquetaInterno> listadoParametros, ArrayList<Etiqueta> listadoEtiquetas) {
        this.tipo = tipo;
        this.texto = texto;
        this.listadoParametros = listadoParametros;
        this.listadoEtiquetas = listadoEtiquetas;
    }
    
    public Etiqueta(String tipo, ArrayList<ParametroEtiquetaInterno> listadoParametros) {
        this.tipo = tipo;
        this.texto = "";
        this.listadoParametros = listadoParametros;
        this.listadoEtiquetas = null;
    }

    public String generarCodigoHTML(int nivel){//el nivel servira para dejar las tabulaciones
        //Codigo finales
        String codigoHTML = "";
        String tabulacion = "";
        //Tabulaciones y codigo
        String tab = "\t";
        String newLine = "\n";
        //Tabulacion
        for(int i = 0; i < nivel; i++){//agregamos la tabulacion dependiendo del nivel en el que se encuentra
            tabulacion += tab;
        }
        
        //Auxiliares
        String etiquetaApertura = "";
        String etiquetasInternas = "";
        String etiquetaHTML = conversor.parseGCICToHTML(tipo);// del tipo </C_GCIC> hacia </html>
        String etiquetaCierre = newLine + tabulacion + "</" + etiquetaHTML + ">";//etiqueta de cierre del tipo </C_GCIC> hacia </html>
                       
        //Codigo inicial con parametros
        etiquetaApertura += newLine + tabulacion + "<" + etiquetaHTML;
        if(listadoParametros != null){
            String styleParametros = "";
            for(ParametroEtiquetaInterno paramAux: listadoParametros){
                String nombreParamAux = paramAux.getNombreParametro().toLowerCase().replaceAll("_", "-");
                String valorParamAux = paramAux.getValorParametro();
                
                switch(nombreParamAux){
                    case "background":
                    case "color":
                    case "font-size":
                    case "font-family":
                    case "text-align":
                    case "type":
                    case "width":
                    case "height":
                        //si son parametros de tipo Style agregamos al style 
                        styleParametros += nombreParamAux + " : " + valorParamAux.substring(1, valorParamAux.length() - 1).trim() + "; ";
                        break;
                    default:
                        etiquetaApertura += " " + nombreParamAux + " = " + valorParamAux;//Parametros
                }
                
            }
            
            if(styleParametros.equalsIgnoreCase("") == false){//tiene datos
                etiquetaApertura += " style = \"" + styleParametros + "\"";
            }
        }        
        etiquetaApertura +=  ">";
        
        //Etiquetas internos
        if(listadoEtiquetas != null){
            for(Etiqueta paramEtiquetas: listadoEtiquetas){
                etiquetasInternas += paramEtiquetas.generarCodigoHTML(nivel + 1);//Codigo html con un nivel diferente
            }
        }
        
        String textoInterno = "";
        if(texto != null){
            textoInterno = newLine + tabulacion + tab + texto;//texto interno
        }
        
        //Codigo HTML final
        codigoHTML = etiquetaApertura + textoInterno + etiquetasInternas + etiquetaCierre;
        
        return codigoHTML;//retornamos el codigo html
    }
    
    public String getParametroEtiqueta(String nombreParametro){        
        try{
            for(int i = 0; i < listadoParametros.size(); i++){
                if(listadoParametros.get(i).getNombreParametro().equalsIgnoreCase(nombreParametro)){
                    return listadoParametros.get(i).getValorParametro();
                }
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        
        return null;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ArrayList<ParametroEtiquetaInterno> getListadoParametros() {
        return listadoParametros;
    }

    public void setListadoParametros(ArrayList<ParametroEtiquetaInterno> listadoParametros) {
        this.listadoParametros = listadoParametros;
    }

    public ArrayList<Etiqueta> getListadoEtiquetas() {
        return listadoEtiquetas;
    }

    public void setListadoEtiquetas(ArrayList<Etiqueta> listadoEtiquetas) {
        this.listadoEtiquetas = listadoEtiquetas;
    }
  
}
