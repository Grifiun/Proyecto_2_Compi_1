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
public class OperacionAritmetica{   
    private GetValues getVal = new GetValues();
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
                String nuevoElementoValor = realizarOperacionAritmetica(valor1, valor2, tipoNuevoValorAux, tipoOperacion);
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
                    nuevoValorAux = realizarOperacionResta(valor1, valor2, tipoNuevoValorAux);//temp
                break;
            case "division":
                    nuevoValorAux = realizarOperacionDivision(valor1, valor2, tipoNuevoValorAux);//temp
                break;
            case "multiplicacion":
                    nuevoValorAux = realizarOperacionMultiplicacion(valor1, valor2, tipoNuevoValorAux);//temp
                break;
        }
        System.out.println("RESULTADO: "+ nuevoValorAux);
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
        
        Produce: integer, string, decimal, boolean
        */
        String resultadoAux = "";
        
        switch(tipoNuevoValorAux){
            case "string":
                resultadoAux = valor1.getValorElemento() + valor2.getValorElemento();  
                break;
            case "integer":
                int a = 0;
                //valor 1
                a += getVal.getIntegerFromValue(valor1);
                //valor 2
                a += getVal.getIntegerFromValue(valor2);
                //retornamos
                resultadoAux = "" + a;
                break;
            case "decimal":
                double auxDecimal = 0;
                //valor 1
                auxDecimal += getVal.getDecimalFromValue(valor1);
                //valor 2
                auxDecimal += getVal.getDecimalFromValue(valor2);
                //retornamos
                resultadoAux = "" + auxDecimal;
                break;
            case "boolean":
                //valor 1
                boolean auxBoolean1 = getVal.getBooleanFromValue(valor1);
                //valor 2
                boolean auxBoolean2 = getVal.getBooleanFromValue(valor2);
                //retornamos
                resultadoAux = "" + (auxBoolean1 || auxBoolean2);
                break;
            default:
                //error, nada
                break;
        
        }
        
        return resultadoAux;
    }
    
    /**
     * Realizamos la operacion entre los 2 valores
     * @param valor1
     * @param valor2
     * @return 
     */
    public String realizarOperacionResta(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux){
        /*
        TABLA DE OPERACIONES RESTA        
        -       integer string  decimal char    boolean
        integer integer error   decimal integer integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    integer error   decimal integer error
        boolean integer error   decimal error   error
        */
        String resultadoAux = "";
        
        switch(tipoNuevoValorAux){            
            case "integer":
                int a = 0;
                //valor 1
                a += getVal.getIntegerFromValue(valor1);
                //valor 2
                a -= getVal.getIntegerFromValue(valor2);//restamos
                //retornamos
                resultadoAux = "" + a;
                break;
            case "decimal":
                double auxDecimal = 0;
                //valor 1
                auxDecimal += getVal.getDecimalFromValue(valor1);
                //valor 2
                auxDecimal -= getVal.getDecimalFromValue(valor2);
                //retornamos
                resultadoAux = "" + auxDecimal;
                break;
            default:
                //error, nada
                break;
        
        }
        
        return resultadoAux;
    }
    
    /**
     * Realizamos la operacion entre los 2 valores
     * @param valor1
     * @param valor2
     * @return 
     */
    public String realizarOperacionMultiplicacion(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux){
        /*
        TABLA DE OPERACIONES MULTIPLICACION
        *       integer string  decimal char    boolean
        integer integer error   decimal integer integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    integer error   decimal integer integer
        boolean integer error   decimal integer boolean
        
        Produce: integer, decimal, boolean
        */
        String resultadoAux = "";
        
        switch(tipoNuevoValorAux){
            case "integer":
                int a = 0;
                //valor 1
                a += getVal.getIntegerFromValue(valor1);
                //valor 2
                a *= getVal.getIntegerFromValue(valor2);
                //retornamos
                resultadoAux = "" + a;
                break;
            case "decimal":
                double auxDecimal = 0;
                //valor 1
                auxDecimal += getVal.getDecimalFromValue(valor1);
                //valor 2
                auxDecimal *= getVal.getDecimalFromValue(valor2);
                //retornamos
                resultadoAux = "" + auxDecimal;
                break;
            case "boolean":
                //valor 1
                boolean auxBoolean1 = getVal.getBooleanFromValue(valor1);
                //valor 2
                boolean auxBoolean2 = getVal.getBooleanFromValue(valor2);
                //retornamos
                resultadoAux = "" + (auxBoolean1 && auxBoolean2);
                break;
            default:
                //error, nada
                break;
        
        }
        
        return resultadoAux;
    }
    
    /**
     * Realizamos la operacion entre los 2 valores
     * @param valor1
     * @param valor2
     * @return 
     */
    public String realizarOperacionDivision(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux){
        /*
        TABLA DE OPERACIONES DIVISION
        /       integer string  decimal char    boolean
        integer decimal error   decimal decimal integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    decimal error   decimal decimal integer
        boolean decimal error   decimal decimal error
        
        Produce: integer, decimal
        */
        String resultadoAux = "";
        
        switch(tipoNuevoValorAux){
            case "integer":
                int a = 0;
                //valor 1
                a += getVal.getIntegerFromValue(valor1);
                //valor 2
                a /= getVal.getIntegerFromValue(valor2);
                //retornamos
                resultadoAux = "" + a;
                break;
            case "decimal":
                double auxDecimal = 0;
                //valor 1
                auxDecimal += getVal.getDecimalFromValue(valor1);
                //valor 2
                auxDecimal /= getVal.getDecimalFromValue(valor2);
                //retornamos
                resultadoAux = "" + auxDecimal;
                break;
            default:
                //error, nada
                break;
        
        }
        
        return resultadoAux;
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
