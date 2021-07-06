/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tabla_simbolos.ValorElemento;

/**
 *
 * @author 50234
 */
public class FuncionesEspeciales {
    
    public ValorElemento ejecutarFuncionEspecial(String tipoFuncion, ValorElemento elemento){    
        ValorElemento resultadoElemento = new ValorElemento("", "");
        if(elemento != null){
            resultadoElemento.setTipoValor(elemento.getTipoValor()); 
        }
        
        String resultado = "";
        String valor = "";
        tipoFuncion = tipoFuncion.replaceAll("“","").replaceAll("”","").replaceAll("\"","").trim();
        try{
            valor = elemento.getValorElemento();
        }catch(Exception ex){}
        System.out.println("FUNCION A EVALUAR: "+tipoFuncion + " = " + valor);
        switch(tipoFuncion){
            case "ASC":
                    resultado = ASC(valor);
                break;
            case "DESC":
                    resultado = DESC(valor);
                break;
            case "LETPAR_NUM":
                    resultado = LETPAR_NUM(valor);
                break;
            case "LETIMPAR_NUM":
                    resultado = LETIMPAR_NUM(valor);
                break;
            case "REVERSE":
                    resultado = REVERSE(valor);
                break;
            case "CARACTER_ALEATORIO":
                    resultado = CARACTER_ALEATORIO();
                    resultadoElemento.setTipoValor("char");
                break;
            case "NUM_ALEATORIO":
                    resultado = NUM_ALEATORIO();
                    resultadoElemento.setTipoValor("integer");
                break;
            default:
                break;
        }
        System.out.println("RESULTADO FUNCION: "+tipoFuncion + " = " + resultado);
        resultadoElemento.setValorElemento(resultado);
        return resultadoElemento;
    }
    
    //Funcion ordenada ascendentemente
    public String ASC(String palabra){
        String resultado = "";
        char[] array = palabra.toCharArray();
        
        char aux;
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 0; j < array.length - 1; j++) {
                if(array[j+1] < array[j]) {
                    aux = array[j+1];
                    array[j+1] = array[j];
                    array[j] = aux;
                }
            }
        }
        
        for(char element : array) {
            resultado += element;
        }
        
        return resultado;
    }
    
    //Funcion ordenada descendentemente
    public String DESC(String palabra){
        String resultado = "";
        char[] array = palabra.toCharArray();
        
        char aux;
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 0; j < array.length - 1; j++) {
                if(array[j+1] > array[j]) {
                    aux = array[j+1];
                    array[j+1] = array[j];
                    array[j] = aux;
                }
            }
        }
        
        for(char element : array) {
            resultado += element;
        }
        
        return resultado;
    }
    
    //LETPAR
    public String LETPAR_NUM(String palabra){
        String nuevaPalabra = "";

        for(int i = 0; i < palabra.length(); i++){
            //const palabraAux = String.fromCharCode(arregloPalabra.indexOf(i));
            char palabraAux = palabra.charAt(i);
            if((i+1)%2 == 0){//par
                //nuevaPalabra += palabraAux.charCodeAt(0);      
                int n = (int)palabraAux;
                nuevaPalabra += n;
            }else{ 
                nuevaPalabra += palabraAux;
            }
        }

        return nuevaPalabra;
    }
    
    //LETIMPAR
    public String LETIMPAR_NUM(String palabra){
        String nuevaPalabra = "";

        for(int i = 0; i < palabra.length(); i++){
            //const palabraAux = String.fromCharCode(arregloPalabra.indexOf(i));
            char palabraAux = palabra.charAt(i);
            if((i+1)%2 == 1){//par
                //nuevaPalabra += palabraAux.charCodeAt(0);      
                int n = (int)palabraAux;
                nuevaPalabra += n;
            }else{ 
                nuevaPalabra += palabraAux;
            }
        }

        return nuevaPalabra;
    }
    
    
    //REVERSE, invertimos la palabra
    public String REVERSE(String input){
        String resultado = "";
        
        char ch[] = input.toCharArray();  
        
        for(int i= ch.length - 1; i >= 0; i--){  
            resultado += ch[i];  
        }         
        
        return resultado;  
    }
    
    //CARACTER_ALEATORIO
    public String CARACTER_ALEATORIO() {
        int random = (int) (Math.random() * 52);
        char base = (random < 26) ? 'A' : 'a';
        char caracter = (char) (base + random % 26);
        String caracterRetornado = "" + caracter;
        return caracterRetornado;
    }
    
    //NUM_ALEATORIO
    public String NUM_ALEATORIO() {
        int num = (int) (Math.random() * 9);
        String numero = "" + num;
        return numero;
    }
}
