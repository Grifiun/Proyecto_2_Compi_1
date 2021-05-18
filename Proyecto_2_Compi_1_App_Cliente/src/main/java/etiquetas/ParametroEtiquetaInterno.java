/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas;

/**
 *
 * @author 50234
 */
public class ParametroEtiquetaInterno {
    private String nombreParametro;
    private String valorParametro;

    public ParametroEtiquetaInterno(String nombreParametro, String valorParametro) {
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
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
    
    
}
