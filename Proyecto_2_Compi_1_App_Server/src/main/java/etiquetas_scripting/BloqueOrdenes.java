/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas_scripting;

import java.util.ArrayList;

/**
 *
 * @author 50234
 */
public class BloqueOrdenes {
    private ArrayList<Orden> listadoOrdenes;

    public BloqueOrdenes() {
        this.listadoOrdenes = new ArrayList();
    }
    
    public BloqueOrdenes(ArrayList<Orden> listadoOrdenes) {
        this.listadoOrdenes = listadoOrdenes;
    }
    
    
}
