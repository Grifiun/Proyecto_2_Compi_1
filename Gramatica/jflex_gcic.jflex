/*Primera seccion, librerias */
package gramatica;
import java_cup.runtime.*;
import static gramatica.sym.*;
import DAO.Token;
import DAO.TokenError;
import java.util.ArrayList;

/*Segunda seccion, config*/
%%
%class LexerGCIC
%cup
%cupdebug
%unicode
%line
%public
%column


%{
    //Creamos un listado de llos errores lexicos
    ArrayList<TokenError> listadoErroresLexicos = new ArrayList();

%}

%{
    //Funciones
    //retorna un simbolo despues de crear un nuevo token y agregarlo al listado
    private Symbol retornarSimbolo(int tipo, String tipoToken, String lexema, int fila, int columna){
        //creamos un  token auxiliar
        Token tokenAux = new Token(tipoToken, lexema, fila, columna);
        System.out.println("Fila: "+fila+" Columa: "+columna+" Token: "+tipoToken+ " Lexema: "+lexema);
        //retornamos el token aux como simbolo
        return new Symbol(tipo, tokenAux);
    }
    
    //Agregamos un token al array list de errores lexicos
    private void addErrorLexico(String tipoToken, String lexema, String msgError, int fila, int columna){
        //creamos un  token auxiliar
        TokenError tokenErrorAux = new TokenError(tipoToken, lexema, msgError, fila, columna);
        //Agregamos al listado
        listadoErroresLexicos.add(tokenErrorAux);
    }

    //Obtenemos el arrLust de los errores lexicos
    public ArrayList<TokenError> obtenerListadoErroresLexicos(){
        return listadoErroresLexicos;
    }
%}

//SEPARADORES
SEPARADORES = [ \r\t\b\f\n]

//SIMBOLOS
PERCENT     = "%"
IGUAL       = "="
MENOR       = "<"
MAYOR       = ">"
SLASH       = "/"
CORCH_INI   = "["
CORCH_FIN   = "]"
PAREN_INI   = "("
PAREN_FIN   = ")"
LLAVE_INI   = "{"
LLAVE_FIN   = "}"
COMA        = ","
PUNTO_COMA  = ";"
DOS_PUNTOS  = ":"

//OPERADORES LOGICOS
SIMBOL_OR   = "|"
SIMBOL_AND  = "&"
SIMBOL_NOT  = "!"

//OPERADORES MATEMATICOS
SIGNO_MAS   = "+"
SIGNO_MIN   = "-"
SIGNO_POR   = "*"
SIGNO_DIV   = "/"

//ETIQUETAS HTML
C_GCIC      = [cC] [_] [gG] [cC] [iI] [cC]
C_HEAD      = [cC] [_] [hH] [eE] [aA] [dD]
C_TITLE     = [cC] [_] [tT] [iI] [tT] [lL] [eE]
C_LINK      = [cC] [_] [lL] [iI] [nN] [kK]
C_BODY      = [cC] [_] [bB] [oO] [dD] [yY]
C_SPAM      = [cC] [_] [sS] [pP] [aA] [mM]
C_INPUT     = [cC] [_] [iI] [nN] [pP] [uU] [tT]
C_TEXTAREA  = [cC] [_] [tT] [eE] [xX] [tT] [aA] [rR] [eE] [aA]
C_SELECT    = [cC] [_] [sS] [eE] [lL] [eE] [cC] [tT]
C_OPTION    = [cC] [_] [oO] [pP] [tT] [iI] [oO] [nN]
C_DIV       = [cC] [_] [dD] [iI] [vV]
C_IMG       = [cC] [_] [iI] [mM] [gG]
C_BR        = [cC] [_] [bB] [rR]
C_BUTTON    = [cC] [_] [bB] [uU] [tT] [tT] [oO] [nN]
C_H1        = [cC] [_] [hH] [1 ]
C_P         = [cC] [_] [pP]
//SCRIPTING
C_SCRIPTING = [cC] [_] [sS] [cC] [rR] [iI] [pP] [tT] [iI] [nN] [gG]

//PARAMETROS
HREF        = href
BACKGROUND  = background
COLOR       = color
FONT_SIZE   = font"-"size
FONT_FAMILY = font"-"family
TEXT_ALIGN  = text"-"align
TYPE        = type
ID          = id
NAME        = name
COLS        = cols
ROWS        = rows
CLASS       = class
SRC         = src
WIDTH       = width
HEIGHT      = height
ALT         = alt
ONCLICK     = onclick

//PARAMETROS SCRIPTING
ON_LOAD     = ON_LOAD
PROCESS_ABC = (PROCESS_)([a-zA-Z0-9])+ 

//TIPO DATOS
INTEGER     = integer
DECIMAL     = decimal
BOOLEAN     = boolean
CHAR        = char
STRING      = string

//FUNCIONES ESPECIALES LENGUAJE CLC
FUNCTION_ASC                = ASC
FUNCTION_DESC               = DESC
FUNCTION_LETPAR_NUM         = LETPAR_NUM
FUNCTION_LETIMPAR_NUM       = LETIMPAR_NUM
FUNCTION_REVERSE            = REVERSE
FUNCTION_CARACTER_ALEATORIO = CARACTER_ALEATORIO
FUNCTION_NUM_ALEATORIO      = NUM_ALEATORIO
FUNCTION_ALERT_INFO         = ALERT_INFO
FUNCTION_EXIT               = EXIT

//ACCESO A IDENTIFICADORES
GET_ELEMEN_BY_ID            = getElemenById

//BLOQUE INSTRUCCIONES
INIT        = INIT
END         = END

//SENTENCIAS
IF          = IF
ELSE        = ELSE
THEN        = THEN

//CICLOS
REPEAT      = REPEAT 
HUNTIL      = HUNTIL
WHILE       = WHILE
THENWHILE   = THENWHILE

//INSERT
INSERT      = INSERT

//MODO DECLARACION
MODO_GLOBAL = "@" global // no aceptamos @ global

//COMENTARIOS, los ignoramos
COMENT_BLOCK= ("<!--") [\0-\176]+ ("-->")  //acepta saltos de linea y todo si anda encapsulado
COMENT_LINE = ("!!"  ) [\40-\176]+            //acepta espacios y caracteres imprimibles

//LLAMADA A UN PROCESO
PROCESS_VAL = \" [\0-\40]* (PROCESS_)([a-zA-Z0-9])+ [\0-\40]* "(" [\0-\40]* ")" [\0-\40]* \" 

////////////////////////////////////VALUES C_HTML
//COLOR VALUES
COLOR_HEX   = \" [\0-\40]* ("#" | "0x") [a-zA-Z0-9]+ [\0-\40]* \"
COLOR_VALUE = \" [\0-\40]* (black | olive | teal | red | blue | maroon | navy | gray | lime | fuchsia | green | white | purple | silver | yellow | aqua) [\0-\40]* \"

//PIXELS VALUE
PIXEL_VALUE = \" [\0-\40]* [0-9]+ [\0-\40]* px  [\0-\40]* \" 
PERCE_VALUE = \" [\0-\40]* [0-9]+ [\0-\40]* "%" [\0-\40]* \" 

//FONT VALUE
FONT_VALUE  = \" [\0-\40]* (([cC] [oO] [uU] [rR] [iI] [eE] [rR]) | ([vV] [eE] [rR] [dD] [aA] [nN] [aA]) | ([aA] [rR] [iI] [aA] [lL]) | ([gG] [eE] [nN] [eE] [vV] [aA]) | ([sS] [aA] [nN] [sS] "-" [sS] [eE] [rR] [iI] [fF])) [\0-\40]* \"

//TEXT ALIGN VALUE
ALIGN_VALUE = \" [\0-\40]* (left | right | center | justify) [\0-\40]* \"

//TYPE INPUT VALUE
TEXT        = \" [\0-\40]* text     [\0-\40]* \"
NUMBER      = \" [\0-\40]* number   [\0-\40]* \"
RADIO       = \" [\0-\40]* radio    [\0-\40]* \"
CHECKBOX    = \" [\0-\40]* checkbox [\0-\40]* \"

//NUMBERS VALUE
DIGIT_VALUE = \" [\0-\40]* [0-9]+ [\0-\40]* \"

//CLASS VALUE
ROW         = \" [\0-\40]* row    [\0-\40]* \"
COLUMN      = \" [\0-\40]* column [\0-\40]* \"

//URL VALUE
//URL_VALUE   = \" [\0-\40]* "https://www." [\41-\176]+    [\0-\40]* \" //excluimos el espacio

//ID VALUE, inicia con "_" | "-" | "$", alfabeto y sigue con alfanum o los mismos simbolos
ID_VALUE_STR  = \" [\0-\40]* ([a-zA-Z] | "_" | "-" | "$") ([a-zA-Z0-9] | "_" | "-" | "$")* [\0-\40]* \"
ID_VALUE_CHAR = \' [\0-\40]* ([a-zA-Z] | "_" | "-" | "$") ([a-zA-Z0-9] | "_" | "-" | "$")* [\0-\40]* \'

//TEXT VALUE, se excluye el codigo ascii 42, el cual representa a << " >>
TEXT_VALUE          = \"  ([\0-\41] | [\43-\176])+ \"
INSERT_TOKEN_VALUE  = \'  ([\0-\41] | [\43-\176])+ \'

//DATA VALUES
INTEGER_VALUE   = (0 | ([1-9] [0-9]*) ) //aceptamos 0 o un numero valido, es decir no aceptamos 0 a la izquierda
DECIMAL_VALUE   = (0 | ([1-9] [0-9]*) ) (\. ([0-9]{1,3} [1-9]?)) //? Numero decimal, no aceptamos 0 como ultimo digito
BOOLEAN_VALUE   = true | false
CHARACT_VALUE   = \' ([\40-\53] | [\55-\176]) \' //excluimos la 54, que es la misma comilla simple
//DECIMAL_VALUE = (0 | ([1-9] [0-9]*) ) (\. (0 [1-9] | [1-9])+) //? Numero decimal, no aceptamos 0 como ultimo digito

//////////////////////////////////IDS
ID_VALUE        = ([a-zA-Z] | "_" | "-" | "$") ([a-zA-Z0-9] | "_" | "-" | "$")* //una letra o simbolo seguido de letras, numeros o simbolos _, -, $ sin contener espacios
ALL_CHARACTERS  = ([\41-\176])* ([\41-\73] | [\75-\176]) //ACEPTAMOS cualquer cadena de caracter, sin espacios y que nunca termine en < (74 OCTAL)

%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
    //SIMBOLOS
    {PERCENT     }           { return retornarSimbolo(PERCENT     , "PERCENT"     , yytext(), yyline + 1, yycolumn + 1); }
    {IGUAL       }           { return retornarSimbolo(IGUAL       , "IGUAL"       , yytext(), yyline + 1, yycolumn + 1); }
    {MENOR       }           { return retornarSimbolo(MENOR       , "MENOR"       , yytext(), yyline + 1, yycolumn + 1); }
    {MAYOR       }           { return retornarSimbolo(MAYOR       , "MAYOR"       , yytext(), yyline + 1, yycolumn + 1); }
    {SLASH       }           { return retornarSimbolo(SLASH       , "SLASH"       , yytext(), yyline + 1, yycolumn + 1); }
    {CORCH_INI   }           { return retornarSimbolo(CORCH_INI   , "CORCH_INI"   , yytext(), yyline + 1, yycolumn + 1); }
    {CORCH_FIN   }           { return retornarSimbolo(CORCH_FIN   , "CORCH_FIN"   , yytext(), yyline + 1, yycolumn + 1); }
    {PAREN_INI   }           { return retornarSimbolo(PAREN_INI   , "PAREN_INI"   , yytext(), yyline + 1, yycolumn + 1); }
    {PAREN_FIN   }           { return retornarSimbolo(PAREN_FIN   , "PAREN_FIN"   , yytext(), yyline + 1, yycolumn + 1); }
    {LLAVE_INI   }           { return retornarSimbolo(LLAVE_INI   , "LLAVE_INI"   , yytext(), yyline + 1, yycolumn + 1); }
    {LLAVE_FIN   }           { return retornarSimbolo(LLAVE_FIN   , "LLAVE_FIN"   , yytext(), yyline + 1, yycolumn + 1); }
    {COMA        }           { return retornarSimbolo(COMA        , "COMA"        , yytext(), yyline + 1, yycolumn + 1); }
    {PUNTO_COMA  }           { return retornarSimbolo(PUNTO_COMA  , "PUNTO_COMA"  , yytext(), yyline + 1, yycolumn + 1); }
    {DOS_PUNTOS  }           { return retornarSimbolo(DOS_PUNTOS  , "DOS_PUNTOS"  , yytext(), yyline + 1, yycolumn + 1); }

    //OPERADORES LOGICOS
    {SIMBOL_OR   }           { return retornarSimbolo(SIMBOL_OR   , "SIMBOL_OR"   , yytext(), yyline + 1, yycolumn + 1); }
    {SIMBOL_AND  }           { return retornarSimbolo(SIMBOL_AND  , "SIMBOL_AND"  , yytext(), yyline + 1, yycolumn + 1); }
    {SIMBOL_NOT  }           { return retornarSimbolo(SIMBOL_NOT  , "SIMBOL_NOT"  , yytext(), yyline + 1, yycolumn + 1); }

    //OPERADORES MATEMATICOS
    {SIGNO_MAS   }           { return retornarSimbolo(SIGNO_MAS   , "SIGNO_MAS"   , yytext(), yyline + 1, yycolumn + 1); }
    {SIGNO_MIN   }           { return retornarSimbolo(SIGNO_MIN   , "SIGNO_MIN"   , yytext(), yyline + 1, yycolumn + 1); }
    {SIGNO_POR   }           { return retornarSimbolo(SIGNO_POR   , "SIGNO_POR"   , yytext(), yyline + 1, yycolumn + 1); }
    {SIGNO_DIV   }           { return retornarSimbolo(SIGNO_DIV   , "SIGNO_DIV"   , yytext(), yyline + 1, yycolumn + 1); }

    //ETIQUETAS HTML
    {C_GCIC      }           { return retornarSimbolo(C_GCIC      , "C_GCIC"      , yytext(), yyline + 1, yycolumn + 1); }
    {C_HEAD      }           { return retornarSimbolo(C_HEAD      , "C_HEAD"      , yytext(), yyline + 1, yycolumn + 1); }
    {C_TITLE     }           { return retornarSimbolo(C_TITLE     , "C_TITLE"     , yytext(), yyline + 1, yycolumn + 1); }
    {C_LINK      }           { return retornarSimbolo(C_LINK      , "C_LINK"      , yytext(), yyline + 1, yycolumn + 1); }
    {C_BODY      }           { return retornarSimbolo(C_BODY      , "C_BODY"      , yytext(), yyline + 1, yycolumn + 1); }
    {C_SPAM      }           { return retornarSimbolo(C_SPAM      , "C_SPAM"      , yytext(), yyline + 1, yycolumn + 1); }
    {C_INPUT     }           { return retornarSimbolo(C_INPUT     , "C_INPUT"     , yytext(), yyline + 1, yycolumn + 1); }
    {C_TEXTAREA  }           { return retornarSimbolo(C_TEXTAREA  , "C_TEXTAREA"  , yytext(), yyline + 1, yycolumn + 1); }
    {C_SELECT    }           { return retornarSimbolo(C_SELECT    , "C_SELECT"    , yytext(), yyline + 1, yycolumn + 1); }
    {C_OPTION    }           { return retornarSimbolo(C_OPTION    , "C_OPTION"    , yytext(), yyline + 1, yycolumn + 1); }
    {C_DIV       }           { return retornarSimbolo(C_DIV       , "C_DIV"       , yytext(), yyline + 1, yycolumn + 1); }
    {C_IMG       }           { return retornarSimbolo(C_IMG       , "C_IMG"       , yytext(), yyline + 1, yycolumn + 1); }
    {C_BR        }           { return retornarSimbolo(C_BR        , "C_BR"        , yytext(), yyline + 1, yycolumn + 1); }
    {C_BUTTON    }           { return retornarSimbolo(C_BUTTON    , "C_BUTTON"    , yytext(), yyline + 1, yycolumn + 1); }
    {C_H1        }           { return retornarSimbolo(C_H1        , "C_H1"        , yytext(), yyline + 1, yycolumn + 1); }
    {C_P         }           { return retornarSimbolo(C_P         , "C_P"         , yytext(), yyline + 1, yycolumn + 1); }

    //SCRIPTING
    {C_SCRIPTING }           { return retornarSimbolo(C_SCRIPTING , "C_SCRIPTING" , yytext(), yyline + 1, yycolumn + 1); }

    //PARAMETROS
    {HREF        }           { return retornarSimbolo(HREF        , "HREF"        , yytext(), yyline + 1, yycolumn + 1); }
    {BACKGROUND  }           { return retornarSimbolo(BACKGROUND  , "BACKGROUND"  , yytext(), yyline + 1, yycolumn + 1); }
    {COLOR       }           { return retornarSimbolo(COLOR       , "COLOR"       , yytext(), yyline + 1, yycolumn + 1); }
    {FONT_SIZE   }           { return retornarSimbolo(FONT_SIZE   , "FONT_SIZE"   , yytext(), yyline + 1, yycolumn + 1); }
    {FONT_FAMILY }           { return retornarSimbolo(FONT_FAMILY , "FONT_FAMILY" , yytext(), yyline + 1, yycolumn + 1); }
    {TEXT_ALIGN  }           { return retornarSimbolo(TEXT_ALIGN  , "TEXT_ALIGN"  , yytext(), yyline + 1, yycolumn + 1); }
    {TYPE        }           { return retornarSimbolo(TYPE        , "TYPE"        , yytext(), yyline + 1, yycolumn + 1); }
    {ID          }           { return retornarSimbolo(ID          , "ID"          , yytext(), yyline + 1, yycolumn + 1); }
    {NAME        }           { return retornarSimbolo(NAME        , "NAME"        , yytext(), yyline + 1, yycolumn + 1); }
    {COLS        }           { return retornarSimbolo(COLS        , "COLS"        , yytext(), yyline + 1, yycolumn + 1); }
    {ROWS        }           { return retornarSimbolo(ROWS        , "ROWS"        , yytext(), yyline + 1, yycolumn + 1); }
    {CLASS       }           { return retornarSimbolo(CLASS       , "CLASS"       , yytext(), yyline + 1, yycolumn + 1); }
    {SRC         }           { return retornarSimbolo(SRC         , "SRC"         , yytext(), yyline + 1, yycolumn + 1); }
    {WIDTH       }           { return retornarSimbolo(WIDTH       , "WIDTH"       , yytext(), yyline + 1, yycolumn + 1); }
    {HEIGHT      }           { return retornarSimbolo(HEIGHT      , "HEIGHT"      , yytext(), yyline + 1, yycolumn + 1); }
    {ALT         }           { return retornarSimbolo(ALT         , "ALT"         , yytext(), yyline + 1, yycolumn + 1); }
    {ONCLICK     }           { return retornarSimbolo(ONCLICK     , "ONCLICK"     , yytext(), yyline + 1, yycolumn + 1); }

    //PARAMETROS SCRIPTING
    {ON_LOAD     }           { return retornarSimbolo(ON_LOAD     , "ON_LOAD"     , yytext(), yyline + 1, yycolumn + 1); }
    {PROCESS_ABC }           { return retornarSimbolo(PROCESS_ABC , "PROCESS_ABC" , yytext(), yyline + 1, yycolumn + 1); }

    //TIPO DATOS
    {INTEGER     }           { return retornarSimbolo(INTEGER     , "INTEGER"     , yytext(), yyline + 1, yycolumn + 1); }
    {DECIMAL     }           { return retornarSimbolo(DECIMAL     , "DECIMAL"     , yytext(), yyline + 1, yycolumn + 1); }
    {BOOLEAN     }           { return retornarSimbolo(BOOLEAN     , "BOOLEAN"     , yytext(), yyline + 1, yycolumn + 1); }
    {CHAR        }           { return retornarSimbolo(CHAR        , "CHAR"        , yytext(), yyline + 1, yycolumn + 1); }
    {STRING      }           { return retornarSimbolo(STRING      , "STRING"      , yytext(), yyline + 1, yycolumn + 1); }

    //FUNCIONES ESPECIALES LENGUAJE CLC
    {FUNCTION_ASC                }           { return retornarSimbolo(FUNCTION_ASC                , "FUNCTION_ASC"  		      , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_DESC               }           { return retornarSimbolo(FUNCTION_DESC               , "FUNCTION_DESC" 		      , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_LETPAR_NUM         }           { return retornarSimbolo(FUNCTION_LETPAR_NUM         , "FUNCTION_LETPAR_NUM" 	      , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_LETIMPAR_NUM       }           { return retornarSimbolo(FUNCTION_LETIMPAR_NUM       , "FUNCTION_LETIMPAR_NUM" 	  , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_REVERSE            }           { return retornarSimbolo(FUNCTION_REVERSE            , "FUNCTION_REVERSE"            , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_CARACTER_ALEATORIO }           { return retornarSimbolo(FUNCTION_CARACTER_ALEATORIO , "FUNCTION_CARACTER_ALEATORIO" , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_NUM_ALEATORIO      }           { return retornarSimbolo(FUNCTION_NUM_ALEATORIO      , "FUNCTION_NUM_ALEATORIO"      , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_ALERT_INFO         }           { return retornarSimbolo(FUNCTION_ALERT_INFO         , "FUNCTION_ALERT_INFO"         , yytext(), yyline + 1, yycolumn + 1); }
    {FUNCTION_EXIT               }           { return retornarSimbolo(FUNCTION_EXIT               , "FUNCTION_EXIT"               , yytext(), yyline + 1, yycolumn + 1); }

    //ACCESO A IDENTIFICADORES
    {GET_ELEMEN_BY_ID            }           { return retornarSimbolo(GET_ELEMEN_BY_ID            , "GET_ELEMEN_BY_ID"            , yytext(), yyline + 1, yycolumn + 1); }

    //BLOQUE INSTRUCCIONES
    {INIT        }           { return retornarSimbolo(INIT        , "INIT"        , yytext(), yyline + 1, yycolumn + 1); }
    {END         }           { return retornarSimbolo(END         , "END"         , yytext(), yyline + 1, yycolumn + 1); }

    //SENTENCIAS
    {IF          }           { return retornarSimbolo(IF          , "IF"          , yytext(), yyline + 1, yycolumn + 1); }
    {ELSE        }           { return retornarSimbolo(ELSE        , "ELSE"        , yytext(), yyline + 1, yycolumn + 1); }
    {THEN        }           { return retornarSimbolo(THEN        , "THEN"        , yytext(), yyline + 1, yycolumn + 1); }

    //CICLOS
    {REPEAT      }           { return retornarSimbolo(REPEAT      , "REPEAT"      , yytext(), yyline + 1, yycolumn + 1); }
    {HUNTIL      }           { return retornarSimbolo(HUNTIL      , "HUNTIL"      , yytext(), yyline + 1, yycolumn + 1); }
    {WHILE       }           { return retornarSimbolo(WHILE       , "WHILE"       , yytext(), yyline + 1, yycolumn + 1); }
    {THENWHILE   }           { return retornarSimbolo(THENWHILE   , "THENWHILE"   , yytext(), yyline + 1, yycolumn + 1); }

    //INSERT
    {INSERT      }           { return retornarSimbolo(INSERT      , "INSERT"      , yytext(), yyline + 1, yycolumn + 1); }

    //MODO DECLARACION
    {MODO_GLOBAL }           { return retornarSimbolo(MODO_GLOBAL , "MODO_GLOBAL" , yytext(), yyline + 1, yycolumn + 1); }

    //COMENTARIOS, los ignoramos
    {COMENT_BLOCK}           { return retornarSimbolo(COMENT_BLOCK, "COMENT_BLOCK", yytext(), yyline + 1, yycolumn + 1); }
    {COMENT_LINE }           { return retornarSimbolo(COMENT_LINE , "COMENT_LINE" , yytext(), yyline + 1, yycolumn + 1); }

    //LLAMADA A UN PROCESO
    {PROCESS_VAL }           { return retornarSimbolo(PROCESS_VAL , "PROCESS_VAL" , yytext(), yyline + 1, yycolumn + 1); }

    ////////////////////////////////////VALUES C_HTML
    //COLOR VALUES
    {COLOR_HEX   }           { return retornarSimbolo(COLOR_HEX   , "COLOR_HEX"   , yytext(), yyline + 1, yycolumn + 1); }
    {COLOR_VALUE }           { return retornarSimbolo(COLOR_VALUE , "COLOR_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //PIXELS VALUE
    {PIXEL_VALUE }           { return retornarSimbolo(PIXEL_VALUE , "PIXEL_VALUE" , yytext(), yyline + 1, yycolumn + 1); }
    {PERCE_VALUE }           { return retornarSimbolo(PERCE_VALUE , "PERCE_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //FONT VALUE
    {FONT_VALUE  }           { return retornarSimbolo(FONT_VALUE  , "FONT_VALUE"  , yytext(), yyline + 1, yycolumn + 1); }

    //TEXT ALIGN VALUE
    {ALIGN_VALUE }           { return retornarSimbolo(ALIGN_VALUE , "ALIGN_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //TYPE INPUT VALUE
    {TEXT        }           { return retornarSimbolo(TEXT        , "TEXT"        , yytext(), yyline + 1, yycolumn + 1); }
    {NUMBER      }           { return retornarSimbolo(NUMBER      , "NUMBER"      , yytext(), yyline + 1, yycolumn + 1); }
    {RADIO       }           { return retornarSimbolo(RADIO       , "RADIO"       , yytext(), yyline + 1, yycolumn + 1); }
    {CHECKBOX    }           { return retornarSimbolo(CHECKBOX    , "CHECKBOX"    , yytext(), yyline + 1, yycolumn + 1); }

    //NUMBERS VALUE
    {DIGIT_VALUE }           { return retornarSimbolo(DIGIT_VALUE , "DIGIT_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //CLASS VALUE
    {ROW         }           { return retornarSimbolo(ROW         , "ROW"         , yytext(), yyline + 1, yycolumn + 1); }
    {COLUMN      }           { return retornarSimbolo(COLUMN      , "COLUMN"      , yytext(), yyline + 1, yycolumn + 1); }

    //URL VALUE
    //URL_VALUE

    //ID VALUE, inicia con "_" | "-" | "$", alfabeto y sigue con alfanum o los mismos simbolos
    {ID_VALUE_STR  }           { return retornarSimbolo(ID_VALUE_STR  , "ID_VALUE_STR"    , yytext(), yyline + 1, yycolumn + 1); }
    {ID_VALUE_CHAR }           { return retornarSimbolo(ID_VALUE_CHAR , "ID_VALUE_CHAR"   , yytext(), yyline + 1, yycolumn + 1); }

    //TEXT VALUE, se excluye el codigo ascii 42, el cual representa a << " >>
    {TEXT_VALUE         }           { return retornarSimbolo(TEXT_VALUE          , "TEXT_VALUE"         , yytext(), yyline + 1, yycolumn + 1); }
    {INSERT_TOKEN_VALUE }           { return retornarSimbolo(INSERT_TOKEN_VALUE  , "INSERT_TOKEN_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //DATA VALUES
    {INTEGER_VALUE }           { return retornarSimbolo(INTEGER_VALUE   , "INTEGER_VALUE" , yytext(), yyline + 1, yycolumn + 1); }
    {DECIMAL_VALUE }           { return retornarSimbolo(DECIMAL_VALUE   , "DECIMAL_VALUE" , yytext(), yyline + 1, yycolumn + 1); }
    {BOOLEAN_VALUE }           { return retornarSimbolo(BOOLEAN_VALUE   , "BOOLEAN_VALUE" , yytext(), yyline + 1, yycolumn + 1); }
    {CHARACT_VALUE }           { return retornarSimbolo(CHARACT_VALUE   , "CHARACT_VALUE" , yytext(), yyline + 1, yycolumn + 1); }

    //////////////////////////////////IDS
    {ID_VALUE      }           { return retornarSimbolo(ID_VALUE        , "ID_VALUE"      , yytext(), yyline + 1, yycolumn + 1); }
    {ALL_CHARACTERS}           { return retornarSimbolo(ALL_CHARACTERS  , "ALL_CHARACTERS", yytext(), yyline + 1, yycolumn + 1); }


    //{HEX}           { return retornarSimbolo(HEX, "HEX" , yytext(), yyline + 1, yycolumn + 1); }
    //{}           { return retornarSimbolo(HEX, "HEX" , yytext(), yyline + 1, yycolumn + 1); }
    
    {SEPARADORES }           { /*                                                                                     */ }
}

[^]+ { addErrorLexico ("LEXICO", yytext(), "Token no valido",yyline + 1, yycolumn + 1);}





