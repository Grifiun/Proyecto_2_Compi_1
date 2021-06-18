/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repeat_insert;

import java.util.ArrayList;
import tabla_simbolos.ValorElemento;

/**
 * Clase a utliziar para generar múltiples codigos de insert en ciclos repeat
 * @author 50234
 */
public class RepeatInsert {
    /***
     * Generamos código de una serie de insert una N cantidad de veces
     * dependiendo del ciclo for
     * @return 
     */
    public String generarCodigo(ArrayList<ValorElemento> elementosRepeat, String nombreVariable, ValorElemento inicio, ValorElemento fin){
        String codigoInsert = "";//variable auxiliar que contendra el codigo insert
        
        int inicioCiclo = 0;
        int finCiclo = 1;
        
        System.out.println("Nombre variable: " + nombreVariable);
        
        if(inicio != null){
            try{
                System.out.println("Inicio: " + inicio.getTipoValor() + " : " + inicio.getValorElemento());
                inicioCiclo = Integer.parseInt(inicio.getValorElemento());//valor inicio, en teoría tiene que ser un integer
                System.out.println("Inicio recibido: " + inicioCiclo);
            }catch(Exception ex){
                System.out.println("Error al recibir el inicio");
            }
        }
        if(fin != null){
            try{
                System.out.println("Fin: " + fin.getTipoValor() + " : " + fin.getValorElemento());
                finCiclo = Integer.parseInt(fin.getValorElemento());//valor fin en teoría tiene que ser un integer
                System.out.println("Fin recibido: " + finCiclo);
            }catch(Exception ex){
                System.out.println("Error al recibir el fin");
            }
        }
        
        try{
            System.out.println("Cantidad de elementos: " + elementosRepeat.size());
        }catch(Exception ex){
        
        }
                        
        //construimos el ciclo for
        for(int i = inicioCiclo; i <= finCiclo; i++){
            try{
                for(ValorElemento auxiliarElemento: elementosRepeat){
                    String textoElemento = auxiliarElemento.getValorElemento();
                    
                    //revisamos si es un texto de insert
                    if(auxiliarElemento.getTipoValor().equalsIgnoreCase("texto")){//texto insert a ingresar
                        codigoInsert += textoElemento;
                    }else{//es una variable
                        //parche 1: solo aceptaremos como identificadores cambiantes, o variables dinámicas al mismo iterador
                        System.out.println("Nombre variable ciclo: " + nombreVariable + " nombre elemento: " + textoElemento);
                        if(textoElemento.equalsIgnoreCase(nombreVariable)){//tienen el mismo nombre
                           //entonces agregamos el iterador al codigo insert
                           codigoInsert += i;
                        }else{//si es una variable cualquiera agregamos su texto de forma normal
                            codigoInsert += textoElemento;
                        }                   
                    }           
                }
            }catch(Exception ex){
                System.out.println("Error al intentar obtener los elementos ingresados");
            }
        }
        
        return codigoInsert;
    }    
}
