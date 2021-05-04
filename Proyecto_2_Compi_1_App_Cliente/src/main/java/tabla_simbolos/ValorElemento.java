/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_simbolos;

/**
 * Valor para un elemento
 * @author grifiun
 */
public class ValorElemento {
    private String valorElemento;
    private String tipoValor;
    
    public ValorElemento(String valorElemento, String tipoValor) {
        this.valorElemento = valorElemento;
        this.tipoValor = tipoValor;
    }      

    public String getValorElemento() {
        return valorElemento;
    }

    public void setValorElemento(String valorElemento) {
        this.valorElemento = valorElemento;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }
    
    
}
