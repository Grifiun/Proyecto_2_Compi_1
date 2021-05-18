/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 * Tabla de operaciones aritmeticas
 * @author grifiun
 */
public class TablasOperacionesRelacionales {
    
    public static String[][] tablaOperacionesComparacionMenorMayorIgual = {
        {"<"      , "integer", "string",  "decimal", "char"   , "boolean"},
        {"integer", "boolean", "error" ,  "boolean", "boolean", "error"  },
        {"string" , "error"  , "error" ,  "error"  , "error"  , "error"  },
        {"decimal", "boolean", "error" ,  "boolean", "boolean", "error"  },
        {"char"   , "boolean", "error" ,  "boolean", "boolean", "error"  },
        {"boolean", "error"  , "error" ,  "error"  , "error"  , "error"  }
    };
    
    public static String[][] tablaOperacionesComparacionIgualIgualDiferente = {
       {"="      , "integer", "string" , "decimal", "char" ,   "boolean"},
        {"integer", "boolean", "error"  , "boolean", "error",   "boolean"},
        {"string" , "error"  , "boolean", "error"  , "error",   "error"  },
        {"decimal", "boolean", "error"  , "boolean", "error",   "boolean"},
        {"char"   , "error"  , "error"  , "error"  , "error",   "error"  },
        {"boolean", "boolean", "error"  , "boolean", "error",   "boolean"}
    };
    
    /*
        TABLA DE OPERACIONES MAYOR/MENOR/MAYOR IGUAL/MENOR IGUAL
        <       integer string  decimal char    boolean
        integer boolean error   boolean boolean error
        string  error   error   error   error   error
        decimal boolean error   boolean boolean error
        char    boolean error   boolean boolean error
        boolean error   error   error   error   error
    
        TABLA DE OPERACIONES IGUAL/NO IGUAL        
        =       integer string  decimal char    boolean
        integer boolean error   boolean error   boolean
        string  error   boolean error   error   error
        decimal boolean error   boolean error   boolean
        char    error   error   error   error   error  
        boolean boolean error   boolean error   boolean
       
    
    */
}
