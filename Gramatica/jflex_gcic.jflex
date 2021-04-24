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
PROCESS_AUX = (PROCESS_)([a-zA-Z0-9])+ 

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
COLOR_VALUE = \" [\0-\40]* (black | olive | teal | red | blue | maroon | navy | gray | lime | fuchsia | green | white | green | purple | silver | yellow | aqua) [\0-\40]* \"

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
ID          = ([a-zA-Z] | "_" | "-" | "$") ([a-zA-Z0-9] | "_" | "-" | "$")* //una letra o simbolo seguido de letras, numeros o simbolos _, -, $ sin contener espacios

%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
    //{HEX}           { return retornarSimbolo(HEX, "HEX" , yytext(), yyline + 1, yycolumn + 1); }

    {SEPARADORES}   {/*  */}
}

[^]+ { addErrorLexico ("LEXICO", yytext(), "Token no valido",yyline + 1, yycolumn + 1);}





