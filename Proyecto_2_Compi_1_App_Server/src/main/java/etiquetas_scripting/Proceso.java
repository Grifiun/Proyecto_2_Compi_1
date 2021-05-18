/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas_scripting;

/**
 * Clase para los procesos/funciones PROCESS_ABC () ....
 * @author 50234
 */
public class Proceso {
    String nombreProceso;
    private BloqueOrdenes bloqueOrdenes;

    public Proceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
        this.bloqueOrdenes = null;
    }

    public Proceso(String nombreProceso, BloqueOrdenes bloqueOrdenes) {
        this.nombreProceso = nombreProceso;
        this.bloqueOrdenes = bloqueOrdenes;
    }    
    
    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public BloqueOrdenes getBloqueOrdenes() {
        return bloqueOrdenes;
    }

    public void setBloqueOrdenes(BloqueOrdenes bloqueOrdenes) {
        this.bloqueOrdenes = bloqueOrdenes;
    }
    
    
}
