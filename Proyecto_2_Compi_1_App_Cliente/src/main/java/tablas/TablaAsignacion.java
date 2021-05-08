/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author 50234
 */
public class TablaAsignacion {
    
    public static String[][] tablaAsignacionesValidez = {
        {"Asign"  , "integer", "string",  "decimal", "char"   , "boolean"},
        {"integer", "integer", "error" ,  "integer", "integer", "integer"},
        {"string" , "error"  , "string",  "error"  , "error"  , "error"  },
        {"decimal", "decimal", "error" ,  "decimal", "decimal", "decimal"},
        {"char"   , "error"  , "error" ,  "error"  , "char"   , "error"  },
        {"boolean", "boolean", "error" ,  "error"  , "error"  , "boolean"}
    };
    
    /*
        TABLA DE ASIGNACIONES DE VALOR
        Asign   integer string  decimal char    boolean
        integer integer error   integer integer integer
        string  error   string  error   error   error
        decimal decimal error   decimal decimal decimal
        char    error   error   error   char    error  
        boolean boolean error   error   error   boolean
    */
}
