/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas_scripting;

/**
 * Clase para if, while y repeat, variables comunes
 * @author 50234
 */
public class OrdenSentenciaControl extends Orden{
    private BloqueOrdenes bloqueOrdenes;
    private String condicion;    

    public BloqueOrdenes getBloqueOrdenes() {
        return bloqueOrdenes;
    }

    public void setBloqueOrdenes(BloqueOrdenes bloqueOrdenes) {
        this.bloqueOrdenes = bloqueOrdenes;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
    
}
