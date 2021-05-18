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
public class TablasOperacionesAritmeticas {
    
    public static String[][] tablaOperacionesSuma = {
        {"+"      , "integer", "string", "decimal", "char"   , "boolean"}, 
        {"integer", "integer", "string", "decimal", "integer", "integer"},
        {"string" , "string" , "string", "string" , "string" , "error"  },
        {"decimal", "decimal", "string", "decimal", "decimal", "decimal"},
        {"char"   , "integer", "string", "decimal", "integer", "integer"},
        {"boolean", "integer", "error" , "decimal", "integer", "boolean"}
    };
    
    public static String[][] tablaOperacionesResta = {
        {"-"      , "integer", "string",  "decimal", "char"   , "boolean"},
        {"integer", "integer", "error" ,  "decimal", "integer", "integer"},
        {"string" , "error"  , "error" ,  "error"  , "error"  , "error"  },
        {"decimal", "decimal", "error" ,  "decimal", "decimal", "decimal"},
        {"char"   , "integer", "error" ,  "decimal", "integer", "error"  },
        {"boolean", "integer", "error" ,  "decimal", "error"  , "error"  }        
    };
    
    public static String[][] tablaOperacionesMultiplicacion = {
        {"*"      , "integer", "string",  "decimal", "char"   , "boolean"},
        {"integer", "integer", "error" ,  "decimal", "integer", "integer"},
        {"string" , "error"  , "error" ,  "error"  , "error"  , "error"  },
        {"decimal", "decimal", "error" ,  "decimal", "decimal", "decimal"},
        {"char"   , "integer", "error" ,  "decimal", "integer", "integer"},
        {"boolean", "integer", "error" ,  "decimal", "integer", "boolean"}        
    };
    
    public static String[][] tablaOperacionesDivision = {
        {"/"      , "integer", "string",  "decimal", "char"   , "boolean"},
        {"integer", "decimal", "error" ,  "decimal", "decimal", "integer"},
        {"string" , "error"  , "error" ,  "error"  , "error"  , "error"  },
        {"decimal", "decimal", "error" ,  "decimal", "decimal", "decimal"},
        {"char"   , "decimal", "error" ,  "decimal", "decimal", "integer"},
        {"boolean", "decimal", "error" ,  "decimal", "decimal", "error"  }        
    };
    
    /*
        TABLA DE OPERACIONES SUMA
        +       integer string  decimal char    boolean
        integer integer string  decimal integer integer
        string  string  string  string  string  error
        decimal decimal string  decimal decimal decimal
        char    integer string  decimal integer integer
        boolean integer error   decimal integer boolean
    
        TABLA DE OPERACIONES RESTA        
        -       integer string  decimal char    boolean
        integer integer error   decimal integer integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    integer error   decimal integer error
        boolean integer error   decimal error   error
    
        TABLA DE OPERACIONES MULTIPLICACION
        *       integer string  decimal char    boolean
        integer integer error   decimal integer integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    integer error   decimal integer integer
        boolean integer error   decimal integer boolean
    
        TABLA DE OPERACIONES DIVISION
        /       integer string  decimal char    boolean
        integer decimal error   decimal decimal integer
        string  error   error   error   error   error
        decimal decimal error   decimal decimal decimal
        char    decimal error   decimal decimal integer
        boolean decimal error   decimal decimal error
    
    */
}
