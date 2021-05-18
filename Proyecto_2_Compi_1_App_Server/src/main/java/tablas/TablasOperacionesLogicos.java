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
public class TablasOperacionesLogicos {
    public static String[][] tablaOperacionesComparacionLogica = {
        {"<"      , "integer", "string",  "decimal", "char" ,   "boolean"},
        {"integer", "error"  , "error" ,  "error"  , "error",   "error"  },
        {"string" , "error"  , "error" ,  "error"  , "error",   "error"  },
        {"decimal", "error"  , "error" ,  "error"  , "error",   "error"  },
	{"char"   , "error"  , "error" ,  "error"  , "error",   "error"  }, 
        {"boolean", "error"  , "error" ,  "error"  , "error",   "boolean"}
    };
    /*
        TABLA DE OPERACIONES AND, OR
        <       integer string  decimal char    boolean
        integer error   error   error   error   error  
        string  error   error   error   error   error  
        decimal error   error   error   error   error  
        char    error   error   error   error   error    
        boolean error   error   error   error   boolean
    
    */
}
