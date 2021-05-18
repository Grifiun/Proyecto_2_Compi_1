/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 50234
 */
public class CGCICAHTML {
    private List<String> etiquetasGCICListado =  new ArrayList<String>();
    private String[] etiquetasGCIC = {
        "C_GCIC"      ,  
        "C_HEAD"      ,
        "C_TITLE"     ,
        "C_LINK"      ,
        "C_BODY"      ,
        "C_SPAM"      ,
        "C_INPUT"     ,
        "C_TEXTAREA"  ,
        "C_SELECT"    ,
        "C_OPTION"    ,
        "C_DIV"       ,
        "C_IMG"       ,
        "C_BR"        ,
        "C_BUTTON"    ,
        "C_H1"        ,
        "C_P"         ,
        "C_SCRIPTING"  
    };
    
    private String[] etiquetasHTML = {
        "html"      ,  
        "head"      ,
        "title"     ,
        "link"      ,
        "body"      ,
        "span"      ,
        "input"     ,
        "textarea"  ,
        "select"    ,
        "option"    ,
        "div"       ,
        "img"       ,
        "br"        ,
        "button"    ,
        "H1"        ,
        "p"         ,
        "script"  
    };
    
    public CGCICAHTML(){
        Collections.addAll(etiquetasGCICListado, etiquetasGCIC);
    }
    
    public String parseGCICToHTML(String etiquetaGCIC){
        int pos = etiquetasGCICListado.indexOf(etiquetaGCIC);
        return etiquetasHTML[pos];
    }
    
}
