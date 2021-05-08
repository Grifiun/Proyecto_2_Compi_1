/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import clasesDAO.TokenError;
import tabla_simbolos.ValorElemento;
import tablas.TablasOperacionesAritmeticas;

/**
 * clase para realizar operaciones aritmeticas
 * @author grifiun
 */
public class OperacionAritmetica {   
    private TokenError tokenError;
    
    public ValorElemento realizarOperacionAritmetica(ValorElemento valor1, ValorElemento valor2, String tipoOperacion){
        int x, y;
        
        if(valor1 != null && valor2 != null){//valores aceptados
            x = getPosicionIndice(valor1);
            y = getPosicionIndice(valor2);
            String tipoNuevoValorAux = getTipoNuevoValor(x, y, tipoOperacion);
            //tokenError = null;       


            if(tipoNuevoValorAux.equalsIgnoreCase("error")){
                //Error semantico
                String msgError = "La operacion aritmetica <"+tipoOperacion+"> entre <"+valor1.getValorElemento()+"> y <"+valor2.getValorElemento()+"> produce un error de ejecucion";
                tokenError = new TokenError("ERROR SEMANTICO", valor1.getValorElemento()+" + "+valor2.getValorElemento(), msgError, -1, -1);

                return null;//retornamos null que significa que encontro un error
            }else{
                //realizamos la operacion
                String nuevoElementoValor = realizarOperacionAritmetica(valor1, valor2, tipoNuevoValorAux, "suma");
                if(valor2.getTipoValor().equals("integer") && valor2.getValorElemento().equals("0".trim())){
                    //error, num/0
                    String msgError = "La operacion aritmetica <"+tipoOperacion+"> entre <"+valor1.getValorElemento()+"> y <"+valor2.getValorElemento()+"> produce un error de ejecucion, NO DIVIDE ENTRE 0";
                    tokenError = new TokenError("ERROR SEMANTICO", valor1.getValorElemento()+" + "+valor2.getValorElemento(), msgError, -1, -1);

                    return null;
                }else{
                    ValorElemento nuevoElemento = new ValorElemento(nuevoElementoValor, tipoNuevoValorAux);
                    return nuevoElemento;
                }                
                
            }               
        }else{
            return null;
        }               
        
    }
    
    public String realizarOperacionAritmetica(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux, String tipoOperacion){
        String nuevoValorAux = "";
        switch(tipoOperacion){
            case "suma":
                    nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);
                break;
            case "resta":
                    nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
                break;
            case "division":
                    nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
                break;
            case "multiplicacion":
                    nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
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
    public String realizarOperacionSuma(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux){
        /*
        TABLA DE OPERACIONES SUMA
        +       integer string  decimal char    boolean
        integer integer string  decimal integer integer
        string  string  string  string  string  error
        decimal decimal string  decimal decimal decimal
        char    integer string  decimal integer integer
        boolean integer error   decimal integer boolean
        
        */
        if(tipoNuevoValorAux.equalsIgnoreCase("string")){//da un string
            return valor1.getValorElemento() + valor2.getValorElemento();
        }else{//temporal
            return valor1.getValorElemento() + valor2.getValorElemento();
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
            case "suma":
                 tipoNuevoValorAux = TablasOperacionesAritmeticas.tablaOperacionesSuma[x][y];
                break;
            case "resta":
                tipoNuevoValorAux = TablasOperacionesAritmeticas.tablaOperacionesResta[x][y];
                break;
            case "division":
                tipoNuevoValorAux = TablasOperacionesAritmeticas.tablaOperacionesDivision[x][y];
                break;
            case "multiplicacion":
                tipoNuevoValorAux = TablasOperacionesAritmeticas.tablaOperacionesMultiplicacion[x][y];
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
