/*Primera seccion, librerias */
package gramatica_guardado;
import java_cup.runtime.*;
import static gramatica_guardado.ParserGuardadoSym.*;
import clasesDAO.Token;
import clasesDAO.TokenError;
import java.util.ArrayList;

/*Segunda seccion, config*/
%%
%class LexerGuardado
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
IGUAL       = "="
LLAVE_INI   = "{"
LLAVE_FIN   = "}"
COMA        = ","

//PARAMETROS
NAME            = name
VECES_ABIERTO   = veces_abierto
ACIERTOS        = aciertos
FALLOS          = fallos   
ULTIMO_REGISTRO = ultimo_registro 

//DATA VALUES
INTEGER_VALUE   = (0 | ([1-9] [0-9]*) ) //aceptamos 0 o un numero valido, es decir no aceptamos 0 a la izquierda
DECIMAL_VALUE   = (0 | ([1-9] [0-9]*) ) (\. ([0-9]{1,3} [1-9]?)) //? Numero decimal, no aceptamos 0 como ultimo digito
//TEXT VALUE, se excluye el codigo ascii 42, el cual representa a << " >>
TEXT_VALUE          = (\" | \“ |  \”)   ([\0-\41] | [\43-\134] | [\136-\176])+ (\" | \“ |  \”) 
ID_VALUE        = ([a-zA-Z] | "_"  ) ([a-zA-Z0-9] | "_" )* //una letra o simbolo seguido de letras, numeros o simbolos _, -, $ sin contener espacios

%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
    //SIMBOLOS
    {IGUAL           }           { return retornarSimbolo(IGUAL           , "IGUAL"           , yytext(), yyline + 1, yycolumn + 1); }    
    {LLAVE_INI       }           { return retornarSimbolo(LLAVE_INI       , "LLAVE_INI"       , yytext(), yyline + 1, yycolumn + 1); }
    {LLAVE_FIN       }           { return retornarSimbolo(LLAVE_FIN       , "LLAVE_FIN"       , yytext(), yyline + 1, yycolumn + 1); }
    {COMA            }           { return retornarSimbolo(COMA            , "COMA"            , yytext(), yyline + 1, yycolumn + 1); }    

    //PARAMETROS
    {NAME            }           { return retornarSimbolo(NAME            , "NAME"            , yytext(), yyline + 1, yycolumn + 1); }
    {VECES_ABIERTO   }           { return retornarSimbolo(VECES_ABIERTO   , "VECES_ABIERTO"   , yytext(), yyline + 1, yycolumn + 1); }
    {ACIERTOS        }           { return retornarSimbolo(ACIERTOS        , "ACIERTOS"        , yytext(), yyline + 1, yycolumn + 1); }
    {FALLOS          }           { return retornarSimbolo(FALLOS          , "FALLOS"          , yytext(), yyline + 1, yycolumn + 1); }
    {ULTIMO_REGISTRO }           { return retornarSimbolo(ULTIMO_REGISTRO , "ULTIMO_REGISTRO" , yytext(), yyline + 1, yycolumn + 1); }

    //DATA VALUES
    {DECIMAL_VALUE} | {INTEGER_VALUE}   { return retornarSimbolo(DECIMAL_VALUE   , "DECIMAL_VALUE"   , yytext(), yyline + 1, yycolumn + 1); }
    {TEXT_VALUE      }           { return retornarSimbolo(TEXT_VALUE      , "TEXT_VALUE"      , yytext(), yyline + 1, yycolumn + 1); }
    {ID_VALUE        }           { return retornarSimbolo(ID_VALUE        , "ID_VALUE"        , yytext(), yyline + 1, yycolumn + 1); }

    //{}           { return retornarSimbolo(HEX, "HEX" , yytext(), yyline + 1, yycolumn + 1); }
    
    {SEPARADORES }           { /*                                                                                     */ }
}

[^] { addErrorLexico ("LEXICO", yytext(), "Token no valido", yyline + 1, yycolumn + 1);}





