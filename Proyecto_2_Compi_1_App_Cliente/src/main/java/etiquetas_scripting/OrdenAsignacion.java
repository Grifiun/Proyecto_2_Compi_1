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
public class OrdenAsignacion extends Orden{
    String nombresVariables;
    String operaciones;

    public OrdenAsignacion(String nombresVariables, String operaciones) {
        this.nombresVariables = nombresVariables;
        this.operaciones = operaciones;
    }
    
    public String getNombresVariables() {
        return nombresVariables;
    }

    public void setNombresVariables(String nombresVariables) {
        this.nombresVariables = nombresVariables;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }
    
    
}
