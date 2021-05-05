/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_simbolos_etiquetas;

/**
 *
 * @author 50234
 */
public class ParametroEtiqueta {
    private int numeroEtiqueta; 
    private String etiqueta;
    private String nombreParametro;
    private String valorParametro;

    public ParametroEtiqueta(int numeroEtiqueta, String etiqueta, String nombreParametro, String valorParametro) {
        this.numeroEtiqueta = numeroEtiqueta;
        this.etiqueta = etiqueta;
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
    }

    public String elementToString(){
        String espacio = "";
        String val = "";
        
        for(int i = 0; i < 15 - nombreParametro.length(); i++ ){
            espacio+= " ";
        }
        if(valorParametro != null){
            val = valorParametro;
        }else{
            val = "---";
        }
        
        return "Etiqueta: "+ etiqueta +" parametro: "+nombreParametro+espacio+" n. Etiqueta: "+numeroEtiqueta + " Valor: "+valorParametro;
                
    }
    
    public int getNumeroEtiqueta() {
        return numeroEtiqueta;
    }

    public void setNumeroEtiqueta(int numeroEtiqueta) {
        this.numeroEtiqueta = numeroEtiqueta;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
    
}
