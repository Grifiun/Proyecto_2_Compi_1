/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas_scripting;

/**
 *
 * @author 50234
 */
public class OrdenDeclaracion extends Orden{
    String nombresVariables;

    public OrdenDeclaracion(String nombresVariables) {
        this.nombresVariables = nombresVariables;
    }   
    
    public String getNombresVariables() {
        return nombresVariables;
    }

    public void setNombresVariables(String nombresVariables) {
        this.nombresVariables = nombresVariables;
    }
    
    
}
