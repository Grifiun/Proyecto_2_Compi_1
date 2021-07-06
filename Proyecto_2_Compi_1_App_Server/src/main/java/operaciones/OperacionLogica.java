/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import clasesDAO.TokenError;
import tabla_simbolos.ValorElemento;
import tablas.TablasOperacionesLogicos;

/**
 *
 * @author 50234
 */
public class OperacionLogica {
    private GetValues getVal = new GetValues();
    private TokenError tokenError;
    
    /**
     * operacion relacional not
     * @param valor1
     * @return 
     */
    public ValorElemento realizarOperacionLogica(ValorElemento valor1){
        String tipoOperacion = "NOT";       
        String tipoNuevoValorAux = "boolean";
        
        if(valor1 != null){//valores aceptados  
            if(valor1.getTipoValor().equalsIgnoreCase("boolean") == false){//no es booleano
                String msgError = "La operacion logica <"+tipoOperacion+"> produce un error de ejecucion, ya que solo acepta valores booleanos";
                tokenError = new TokenError("ERROR SEMANTICO", valor1.getValorElemento(), msgError, -1, -1);

                return null;//retornamos null que significa que encontro un error
            }else{
                //realizamos la operacion
                String nuevoElementoValor = realizarOperacionLogica(valor1, null, tipoNuevoValorAux, tipoOperacion);
                ValorElemento nuevoElemento = new ValorElemento(nuevoElementoValor, tipoNuevoValorAux);
                return nuevoElemento;
            }               
        }else{
            return null;
        }               
        
    }   
    
    /**
     * Funcion para and y or
     * @param valor1
     * @param valor2
     * @param tipoOperacion
     * @return 
     */
    public ValorElemento realizarOperacionLogica(ValorElemento valor1, ValorElemento valor2, String tipoOperacion){
        int x, y;
        
        if(valor1 != null && valor2 != null){//valores aceptados
            x = getPosicionIndice(valor1);
            y = getPosicionIndice(valor2);
            String tipoNuevoValorAux = getTipoNuevoValor(x, y, tipoOperacion);
            //tokenError = null;       


            if(tipoNuevoValorAux.equalsIgnoreCase("error")){
                //Error semantico
                String msgError = "La operacion logica <"+tipoOperacion+"> entre <"+valor1.getValorElemento()+"> y <"+valor2.getValorElemento()+"> produce un error de ejecucion";
                tokenError = new TokenError("ERROR SEMANTICO", valor1.getValorElemento()+" + "+valor2.getValorElemento(), msgError, -1, -1);

                return null;//retornamos null que significa que encontro un error
            }else{
                //realizamos la operacion
                String nuevoElementoValor = realizarOperacionLogica(valor1, valor2, tipoNuevoValorAux, tipoOperacion);
                ValorElemento nuevoElemento = new ValorElemento(nuevoElementoValor, tipoNuevoValorAux);
                return nuevoElemento;
            }               
        }else{
            return null;
        }               
        
    }
    
    public String realizarOperacionLogica(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux, String tipoOperacion){
        String nuevoValorAux = "";
        boolean auxiliar;
        //valor 1
        boolean auxBoolean1 = getVal.getBooleanFromValue(valor1);
        //valor 2
        boolean auxBoolean2 = getVal.getBooleanFromValue(valor2);
        
        switch(tipoOperacion){
            case "AND":
                    //retornamos
                    nuevoValorAux = "" + (auxBoolean1 && auxBoolean2);
                break;
            case "OR":
                    //retornamos
                    nuevoValorAux = "" + (auxBoolean1 || auxBoolean2);
                break;
            case "NOT":
                    auxiliar = !(Boolean.parseBoolean(valor1.getValorElemento()));//negamos
                    nuevoValorAux = "" + auxiliar;
                break;
        }
        
        return nuevoValorAux;
    }
    
    /**
     * Realizamos la operacion entre los 2 valores
     * @param valor1
     * @param valor2
     * @return 
     */
    public boolean realizarOperacionAnd(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux){
        
        if(tipoNuevoValorAux.equalsIgnoreCase("boolean")){//da un boolean
            //return valor1.getValorElemento() < valor2.getValorElemento();
            return true;//temporal
        }else{//temporal
            //return valor1.getValorElemento() + valor2.getValorElemento();
            return false;//temporal
        }
        
        //return "";
    }
    
    /**
     * Retornamos el nuevo tipo, dependiendo del tipo de operacion aritmetica a realizar
     * @param x
     * @param y
     * @param tipoOperacion
     * @return 
     */
    public String getTipoNuevoValor(int x, int y, String tipoOperacion){
        String tipoNuevoValorAux = "";
        switch(tipoOperacion){
            case "AND"://Comparacion, tienen mismas condicionales
            case "OR":
                    tipoNuevoValorAux = TablasOperacionesLogicos.tablaOperacionesComparacionLogica[x][y];
                break;
            case "NOT":
                     tipoNuevoValorAux = "boolean";
                break;        
        }
        
        return tipoNuevoValorAux;
    
    }
    
    /**
     * Funcion dedicada a calcular el indice segun su tipo
     * integer = 1
     * string  = 2
     * decimal = 3
     * char    = 4
     * boolean = 5
     * @param valor1
     * @return 
     */
    public int getPosicionIndice(ValorElemento valor1){
        int indice = 0;
        switch(valor1.getTipoValor()){
            case "integer":
                indice = 1;
               break;     
            case "string":
                indice = 2;
               break;     
            case "decimal":
                indice = 3;
               break;     
            case "char":
                indice = 4;
               break;     
            case "boolean":
                indice = 5;
               break;     
        }
        
        return indice;
    }

    public TokenError getTokenError() {
        return tokenError;
    }

    public void setTokenError(TokenError tokenError) {
        this.tokenError = tokenError;
    }
}
