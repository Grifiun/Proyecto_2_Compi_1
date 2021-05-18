/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas_scripting;

import etiquetas.Etiqueta;
import java.util.ArrayList;

/**
 *
 * @author 50234
 */
public class EtiquetaScripting extends Etiqueta{
    private int numeroScripting;
    private ArrayList<Proceso> listadoFunciones;
    
    public EtiquetaScripting(){
        super("C_SCRIPTING", null);//tipo y parametros (id, nombre, etc) ya que no varían
    }
    
}
