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

//COLOR VALUES
COLOR_HEX   = \" [\0-\40]* ("#" | "0x") [a-zA-Z0-9]+ [\0-\40]* \"
COLOR_VALUE = \" [\0-\40]* (black | olive | teal | red | blue | maroon | navy | gray | lime | fuchsia | green | white | green | purple | silver | yellow | aqua) [\0-\40]* \"

//PIXELS
PIXEL_VALUE = \" [\0-\40]* [0-9]+ [\0-\40]* px [\0-\40]* \" 

//FONT VALUE
FONT_VALUE  = \" [\0-\40]* (([cC] [oO] [uU] [rR] [iI] [eE] [rR]) | ([vV] [eE] [rR] [dD] [aA] [nN] [aA]) | ([aA] [rR] [iI] [aA] [lL]) | ([gG] [eE] [nN] [eE] [vV] [aA]) | ([sS] [aA] [nN] [sS] "-" [sS] [eE] [rR] [iI] [fF])) [\0-\40]* \"

//TEXT ALIGN
ALIGN_VALUE = \" [\0-\40]* (left | right | center | justify) [\0-\40]* \"

//TYPE INPUT
TEXT        = \" [\0-\40]* text     [\0-\40]* \"
NUMBER      = \" [\0-\40]* number   [\0-\40]* \"
RADIO       = \" [\0-\40]* radio    [\0-\40]* \"
CHECKBOX    = \" [\0-\40]* checkbox [\0-\40]* \"

//NUMBERS
DIGIT       = [0-9]+
DIGIT_VALUE = \" [\0-\40]* [0-9]+ [\0-\40]* \"

//CLASS VALUE
ROW         = row
COLUMN      = column




%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
    //{HEX}           { return retornarSimbolo(HEX, "HEX" , yytext(), yyline + 1, yycolumn + 1); }

    {SEPARADORES}   {/*  */}
}

[^] { addErrorLexico ("LEXICO", yytext(), "Token no valido",yyline + 1, yycolumn + 1);}





