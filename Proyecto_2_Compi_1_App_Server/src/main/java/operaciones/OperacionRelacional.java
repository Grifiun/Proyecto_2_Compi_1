/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import clasesDAO.TokenError;
import tabla_simbolos.ValorElemento;
import tablas.*;

/**
 *
 * @author 50234
 */
public class OperacionRelacional {
    private GetValues getVal = new GetValues();
    private TokenError tokenError;
    
    public ValorElemento realizarOperacionRelacional(ValorElemento valor1, ValorElemento valor2, String tipoOperacion){
        int x, y;
        
        if(valor1 != null && valor2 != null){//valores aceptados
            x = getPosicionIndice(valor1);
            y = getPosicionIndice(valor2);
            String tipoNuevoValorAux = getTipoNuevoValor(x, y, tipoOperacion);
            //tokenError = null;       


            if(tipoNuevoValorAux.equalsIgnoreCase("error")){
                //Error semantico
                String msgError = "La operacion relacional <"+tipoOperacion+"> entre <"+valor1.getValorElemento()+"> y <"+valor2.getValorElemento()+"> produce un error de ejecucion";
                tokenError = new TokenError("ERROR SEMANTICO", valor1.getValorElemento()+" + "+valor2.getValorElemento(), msgError, -1, -1);

                return null;//retornamos null que significa que encontro un error
            }else{
                //realizamos la operacion
                String nuevoElementoValor = realizarOperacionRelacional(valor1, valor2, tipoNuevoValorAux, tipoOperacion);
                ValorElemento nuevoElemento = new ValorElemento(nuevoElementoValor, tipoNuevoValorAux);
                return nuevoElemento;
            }               
        }else{
            return null;
        }               
        
    }
    
    public String realizarOperacionRelacional(ValorElemento valor1, ValorElemento valor2, String tipoNuevoValorAux, String tipoOperacion){
        String nuevoValorAux = "";
        double auxDecimal = 0, auxDecima2 = 0;
        String cadena1 = "", cadena2 = "";
        //valor 1
        if(valor1.getTipoValor().equals("string") == false){
            auxDecimal = getVal.getDecimalFromValue(valor1);
            System.out.println("VALOR 1: " + auxDecimal);
        }
        //valor 2
        if(valor2.getTipoValor().equals("string") == false){
            auxDecima2 = getVal.getDecimalFromValue(valor2);
            System.out.println("VALOR 2: " + auxDecima2);
        }
        
        //retornamos        
        switch(tipoOperacion){
            case "MENOR":
                    //nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);
                    nuevoValorAux = "" + (auxDecimal < auxDecima2);
                break;
            case "MAYOR":
                    //nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
                    nuevoValorAux = "" + (auxDecimal > auxDecima2);
                break;
            case "MENOR_IGUAL":
                    //nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
                    nuevoValorAux = "" + (auxDecimal <= auxDecima2);
                break;
            case "MAYOR_IGUAL":
                    //nuevoValorAux = realizarOperacionSuma(valor1, valor2, tipoNuevoValorAux);//temp
                    nuevoValorAux = "" + (auxDecimal >= auxDecima2);
                break;
            case "IGUAL_IGUAL":
                //Revisamos valores si son cadenas
                if(valor1.getTipoValor().equals("string") == true && valor2.getTipoValor().equals("string") == true){
                    //Si ambas son cadenas, entonces realizamos una comparacion de cadenas
                    nuevoValorAux = "" + (valor1.getValorElemento().trim().equals(valor2.getValorElemento().trim()));
                }else{
                    nuevoValorAux = "" + (auxDecimal == auxDecima2);
                }
                break;
            case "NO_IGUAL":
                //Revisamos valores si son cadenas
                if(valor1.getTipoValor().equals("string") == true && valor2.getTipoValor().equals("string") == true){
                    //Si ambas son cadenas, entonces realizamos una comparacion de cadenas
                    nuevoValorAux = "" + (valor1.getValorElemento().trim().equals(valor2.getValorElemento().trim()) == false);
                }else{
                    nuevoValorAux = "" + (auxDecimal != auxDecima2);
                }                
                break;
        }
        
        System.out.println("OPERACION: " + tipoOperacion);
        System.out.println("RESPUSTA: " + nuevoValorAux);
        
        return nuevoValorAux;
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
            case "MENOR"://Comparacion, tienen mismas condicionales
            case "MAYOR":
            case "MENOR_IGUAL":
            case "MAYOR_IGUAL":
                    tipoNuevoValorAux = TablasOperacionesRelacionales.tablaOperacionesComparacionMenorMayorIgual[x][y];
                break;
            case "IGUAL_IGUAL"://tienen mismas restricciones
            case "NO_IGUAL":
                    tipoNuevoValorAux = TablasOperacionesRelacionales.tablaOperacionesComparacionIgualIgualDiferente[x][y];
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
