/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_simbolos_etiquetas;

import clasesDAO.TokenError;
import java.util.ArrayList;
import tabla_simbolos_etiquetas.*;

/**
 *
 * @author 50234
 */
public class TablaSimbolosEtiquetas {
    private ArrayList<ParametroEtiqueta> elementosTS = new ArrayList();
    private TokenError tokenErrorAuxiliar = null;
    //private ArrayList<TokenError> listadoErroresSemanticos = new ArrayList();    
    
    /**
     * Declaramos un nuevo elemento en la tabla de simbolos con sus respectivos valoress
     * @param nombreParametro
     * @param valor
     * @param numeroEtiqueta
     * @param fila
     * @param columna
     */
    public void declararNuevoElemento(String etiqueta, String nombreParametro, String valor, int numeroEtiqueta, int fila, int columna){
        String msgError = "";
        String valAux = valor.substring(1, valor.length() - 1).trim();
        ParametroEtiqueta elementAux;
        ParametroEtiqueta elementoExistente = buscarElementoId(nombreParametro, valAux, numeroEtiqueta);
        
        ///VERIFICAMOS QUE NO HAYA UNA VARIABLE YA DECLARADA CON ESE ID
        if(elementoExistente != null){//existe
            if(nombreParametro.equalsIgnoreCase("id") && elementoExistente.getValorParametro().equals(valAux)){//si es un id
                //Cuando es id y su valor sea igual al enviado, retornamos
                 msgError = "El id con valor <"+valAux+"> ya existe";
            }else {//las funciones normales importan en cualquier scripting
                //si es otro parametro, revisamos el numero de etiqueta
                 msgError = "La etiqueta <"+etiqueta+"> ya posee un parametro del tipo <"+nombreParametro+">";
            }                  
            
            tokenErrorAuxiliar = new TokenError("ERROR SEMANTICO", nombreParametro, msgError, fila, columna);
            //listadoErroresSemanticos.add(tokenErrorAux);
            
            return;//terminamos la funcion
        }//si existe, sigue la funcion
        //agregamos un nuevo elemento       
        elementAux = new ParametroEtiqueta(numeroEtiqueta, etiqueta, nombreParametro, valAux);
        elementosTS.add(elementAux);     
    }
    
    
    
    /**
     * Funciond dedicada a retornar un elemento (procedimiento) por el id, retornara null si no existe en la tabla de simbolos (global o en el procedimiento dado)
     * @param identificador
     * @param procedimiento
     * @return 
     */
    public ParametroEtiqueta buscarElementoId(String tipoEtiqueta, String valor, int numeroEtiqueta){
        for(ParametroEtiqueta elementAux: elementosTS){//recorremos el listado
            if(elementAux.getNombreParametro().equalsIgnoreCase(tipoEtiqueta)){//mismo parametro
                if(tipoEtiqueta.equalsIgnoreCase("id") && elementAux.getValorParametro().equals(valor)){//si es un id
                    //Cuando es id y su valor sea igual al enviado, retornamos
                    return elementAux;
                }else {//las funciones normales importan en cualquier scripting
                    //si es otro parametro, revisamos el numero de etiqueta
                    if(elementAux.getNumeroEtiqueta() == numeroEtiqueta){//mismo numero de etiqueta
                    //Cuando es id y su valor sea igual al enviado, retornamos
                        return elementAux;
                    }
                }
            }
        }
        
        //return null si no encuentra nada
        return null;            
    }    

    public ArrayList<ParametroEtiqueta> getElementosTS() {
        return elementosTS;
    }

    public void setElementosTS(ArrayList<ParametroEtiqueta> elementosTS) {
        this.elementosTS = elementosTS;
    }    
    
    /*
    public ArrayList<TokenError> getListadoErroresSemanticos() {
        return listadoErroresSemanticos;
    }

    public void setListadoErroresSemanticos(ArrayList<TokenError> listadoErroresSemanticos) {
        this.listadoErroresSemanticos = listadoErroresSemanticos;
    }
    
    public void addErrorSemantico(TokenError error) {
        this.listadoErroresSemanticos.add(error);
    }*/
    
    public TokenError getTokenErrorAuxiliar() {
        return tokenErrorAuxiliar;
    }

    public void resetTokenErrorAuxiliar() {
        this.tokenErrorAuxiliar = null;
    }
    
    
    public void imprimirTabla(){
        String datosTabla = "";
        try{             
            for(ParametroEtiqueta elementAux: elementosTS){
                datosTabla += elementAux.elementToString() + "\n";
            }
            System.out.println("DATOS TABLA");
            System.out.println(datosTabla);
        }catch(Exception ex){
        
        }
    }

    
   
    
}
