/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_simbolos;

import clasesDAO.TokenError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tablas.TablaAsignacion;
import tablas.TablasOperacionesAritmeticas;


/**
 *
 * @author grifiun
 */
public class TablaSimbolos {
    private ArrayList<Elemento> elementosTS = new ArrayList();
    private ArrayList<TokenError> listadoErroresSemanticos = new ArrayList();
    
    /**
     * Para insertar funciones
     * @param identificador
     * @param tipo
     * @param scripting
     * @param fila
     * @param columna 
     */
    public void declararNuevoElemento(String identificador, String tipo, int scripting, int fila, int columna){
        Elemento elementoFuncionExistente = buscarElementoId(identificador, scripting);
        if(elementoFuncionExistente != null){//existe, entonces se repite, mostramos error
            String ambito = "";
            if(elementoFuncionExistente.getIdentificador().equalsIgnoreCase("on_load")){//las funciones onload pueden venir varias dependiendo del bloque scripting en el que vengan
                //mismo procedimiento onload y scripting                
                ambito = "dentro del mismo bloque de scripting <"+scripting+">";
            }else{//las funciones normales importan en cualquier scripting
                ambito = "dentro de la tabla de simbolos";
            }
         
            String msgError = "Ya existe una funcion con el nombre <"+identificador+"> en "+ambito+"";
            TokenError tokenErrorAux = new TokenError("ERROR SEMANTICO", identificador, msgError, fila, columna);
            listadoErroresSemanticos.add(tokenErrorAux);
            
            return;//termminamos la funcion
        }else{//no existe
            Elemento elementAux = new Elemento(identificador, tipo, scripting, fila, columna);
            elementosTS.add(elementAux);
        }
    }
    
    /**
     * Declaramos un nuevo elemento en la tabla de simbolos con sus respectivos valoress
     * @param identificador
     * @param tipo
     * @param modo
     * @param procedimiento
     * @param scripting
     * @param fila
     * @param columna
     * @param valor 
     */
    public void declararNuevoElemento(String identificador, String tipo, String modo, String procedimiento, int scripting, int fila, int columna, ValorElemento valor){
        String msgError = "";
        Elemento elementAux;
        Elemento elementoExistente = buscarElementoId(identificador, procedimiento, scripting);
                
        int tipoVariable = getPosicionIndice(tipo);//
        int valorAsignar = (valor != null)? getPosicionIndice(valor) : 0;//   
        
        String nuevoTipoValor = TablaAsignacion.tablaAsignacionesValidez[tipoVariable][valorAsignar];//
        
        ///VERIFICAMOS QUE NO HAYA UNA VARIABLE YA DECLARADA CON ESE ID
        if(elementoExistente != null){//existe
            String ambito = "";
            if(elementoExistente.getModo().equalsIgnoreCase("@global")){//declaradao en el ambito global
                ambito = elementoExistente.getModo();
            }else{//declarado en la funcion
                if( elementoExistente.getProcedimiento().equalsIgnoreCase("ON_LOAD")){//onload, revisamos que tengan el mismo bloque de scripting
                    if( elementoExistente.getScripting() == scripting){//mismo bloque y mismo onload
                        ambito = elementoExistente.getProcedimiento() + ", scripting: "+scripting;
                    }
                }else{
                    ambito = elementoExistente.getProcedimiento();                
                }
            }
            
            msgError = "Ya existe una variable con el id <"+identificador+"> en el ambito <"+ambito+">";
            TokenError tokenErrorAux = new TokenError("ERROR SEMANTICO", identificador, msgError, fila, columna);
            listadoErroresSemanticos.add(tokenErrorAux);
            
            return;//terminamos la funcion
        }//si existe, sigue la funcion        
        
        if(valor != null && nuevoTipoValor.equalsIgnoreCase("error") == false){//si son del mismo tipo y no es error
            elementAux = new Elemento(identificador, tipo, modo, procedimiento, scripting, fila, columna, valor);
            elementosTS.add(elementAux);
        }else if(valor == null){//declaracion sin asginar valor
            elementAux = new Elemento(identificador, tipo, modo, procedimiento, scripting, fila, columna, null);//valor nullo, porque se declara asin valor
            elementosTS.add(elementAux);
        }else{//error
            msgError = "esta intentando agregarle a la variabale <"+identificador+"> un valor <"+valor.getValorElemento()+", tipo:"+valor.getTipoValor()+"> que no coincide con su tipo <"+tipo+">";
            TokenError tokenErrorAux = new TokenError("ERROR SEMANTICO", identificador, msgError, fila, columna);
            listadoErroresSemanticos.add(tokenErrorAux);
        }        
    }
    
    /**
     * Asigamos el valor de un elemento, se recibe un valor y se le asigna a una variable, revisamos su tipo
     * @param identificador
     * @param procedimiento
     * @param valor
     * @param fila
     * @param columna 
     */
    public void asignarValorElemento(String identificador, String procedimiento, int scripting, ValorElemento valor, int fila, int columna){
        String msgError;
        Elemento elementAux = buscarElementoId(identificador, procedimiento, scripting);        
        int tipoVariable = (elementAux != null)? getPosicionIndice(elementAux.getTipo()) : 0;//
        int valorAsignar = (valor != null) ? getPosicionIndice(valor) : 0;//
        
        String nuevoTipoValor = TablaAsignacion.tablaAsignacionesValidez[tipoVariable][valorAsignar];//        
        
        if(elementAux != null){//existe          
            if(valor != null && nuevoTipoValor.equalsIgnoreCase("error") == false){//si son del mismo tipo
                elementAux.setValor(valor);//seteamos el valor           
            }else{//marcamos un error
                
                String val;
                String tipo;
                if(valor != null){//tiene valor
                    val = valor.getValorElemento();
                    tipo = valor.getTipoValor();
                }else{
                    val = "sin valor, nulo";
                    tipo = "nulo";
                }
                msgError = "esta intentando agregarle a la variabale <"+identificador+"> un valor <"+ val +", tipo:"+ tipo +"> que no coincide con su tipo <"+elementAux.getTipo()+">";
                TokenError tokenErrorAux = new TokenError("ERROR SEMANTICO", identificador, msgError, fila, columna);
                listadoErroresSemanticos.add(tokenErrorAux);
            }           
            
        }else{//no existe, error
            msgError = "No EXISTE una variable con el id <"+identificador+"> en el ambito global o en la funcion";
            TokenError tokenErrorAux = new TokenError("ERROR SEMANTICO", identificador, msgError, fila, columna);
            listadoErroresSemanticos.add(tokenErrorAux);
        }
    }
    
    /**
     * Funciond dedicada a retornar un elemento por el id, retornara null si no existe en la tabla de simbolos (global o en el procedimiento dado)
     * @param identificador
     * @param procedimiento
     * @return 
     */
    public Elemento buscarElementoId(String identificador, String procedimiento, int scripting){
        for(Elemento elementAux: elementosTS){//recorremos el listado
            if(elementAux.getIdentificador().equalsIgnoreCase(identificador)){//mismo id
                if(elementAux.getModo().equalsIgnoreCase("@global")//declaradaa en el ambito global
                    || ((elementAux.getProcedimiento().equalsIgnoreCase(procedimiento)) && elementAux.getScripting() == scripting)){//mismo procedimiento y scripting
                        return elementAux;
                }
            }
        }
        
        //return null si no encuentra nada
        return null;            
    }
    
    /**
     * Funciond dedicada a retornar un elemento (procedimiento) por el id, retornara null si no existe en la tabla de simbolos (global o en el procedimiento dado)
     * @param identificador
     * @param procedimiento
     * @return 
     */
    public Elemento buscarElementoId(String identificador, int scripting){
        for(Elemento elementAux: elementosTS){//recorremos el listado
            if(elementAux.getIdentificador().equalsIgnoreCase(identificador)){//mismo id
                if(elementAux.getIdentificador().equalsIgnoreCase("on_load")){//las funciones onload pueden venir varias dependiendo del bloque scripting en el que vengan
                    //mismo procedimiento onload y scripting
                    if(( elementAux.getScripting() == scripting)){
                        return elementAux;
                    }                        
                }else{//las funciones normales importan en cualquier scripting
                    return elementAux;
                }
            }
        }
        
        //return null si no encuentra nada
        return null;            
    }
    
    
    /**
     * Funciond dedicada a retornar el valor de un elemento por el id, si no esta declarada o no le ha sido asignado un nuevo valor retornara null
     * @param identificador
     * @param procedimiento
     * @return 
     */
    public ValorElemento buscarValorElementoPorId(String identificador, String procedimiento, int scripting){
        Elemento elementAux = buscarElementoId(identificador, procedimiento, scripting);
        
        if(elementAux != null){//existe
            return elementAux.getValor();//retornamos su valor
        }else{
            return null;//no existe
        }
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
    public int getPosicionIndice(String valor1){
        int indice = 0;
        switch(valor1){
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
    
    public ArrayList<Elemento> getElementosTS() {
        return elementosTS;
    }

    public void setElementosTS(ArrayList<Elemento> elementosTS) {
        this.elementosTS = elementosTS;
    }

    public ArrayList<TokenError> getListadoErroresSemanticos() {
        return listadoErroresSemanticos;
    }

    public void setListadoErroresSemanticos(ArrayList<TokenError> listadoErroresSemanticos) {
        this.listadoErroresSemanticos = listadoErroresSemanticos;
    }
    
    public void addErrorSemantico(TokenError error) {
        this.listadoErroresSemanticos.add(error);
    }
    
    public void imprimirTabla(){
        String datosTabla = "";
        try{             
            for(Elemento elementAux: elementosTS){
                datosTabla += elementAux.elementToString() + "\n";
            }
            System.out.println("DATOS TABLA");
            System.out.println(datosTabla);
        }catch(Exception ex){
        
        }
    }
    
    public String generarTablaHTML(){        
        String[] array =  {"Identificador", "Tipo", "Modo", "Procedimiento", "Fila", "Columna", "Valor actual", "N. Ejecucion"};
        List<String> titulos = Arrays.asList(array);
        
        String datosTabla = "\n<br><H3>TABLA DE SIMBOLOS</H3>\n" +
                            "\n<table class=\"table\">\n" +
                            "  <thead class=\"thead-dark\">\n" +
                            "    <tr>\n" +
                            "      <th scope=\"col\">#</th>\n";
        
        //Generamos titulos
        for(String tituloAux: titulos){
            datosTabla += "\t<th scope=\"row\">" +
                         tituloAux + ": " +
                         "</th>\n";            
        }
        datosTabla += "      </tr>\n" +
                      "  </thead>\n" +
                      "  <tbody>\n";
        
        //Agregamos los datos
        try{             
            int iterador = 1;
            for(Elemento elementAux: elementosTS){
                datosTabla += "\t<tr>\n" +
                              "\t\t<th scope=\"row\">" + iterador + "</th>\n";
                datosTabla += elementAux.elementToHTML()+ "\n";
                datosTabla += "\t</tr>\n";
                iterador++;
            }
            datosTabla += "</tbody>\n" +
                          "</table>";
            System.out.println("DATOS TABLA");
            System.out.println(datosTabla);
        }catch(Exception ex){ }        
        return datosTabla;
    }    
    
    public String generarTablaScript(){   
        String codigoHTML = "\n<script>\n";
        
        //Agregamos los datos
        try{             
            for(Elemento elementAux: elementosTS){
                codigoHTML += elementAux.elementToSript()+ "\n";
            }
            codigoHTML += "</script>\n";
        }catch(Exception ex){ }        
        return codigoHTML;
    }
    
    public String generarHTML (){
        return generarTablaHTML() + generarTablaScript();
    }
    
}
