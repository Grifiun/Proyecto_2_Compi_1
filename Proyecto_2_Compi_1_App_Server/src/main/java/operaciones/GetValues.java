/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import tabla_simbolos.ValorElemento;

/**
 *
 * @author 50234
 */
public class GetValues {
    
       
    public int getIntegerFromValue(ValorElemento valor1){
        int a = 0;
        try{
            String tipo = valor1.getTipoValor();

            switch(tipo){
                case "string":
                case "char":
                        a += getNumFromLetter(valor1.getValorElemento());
                    break;
                case "boolean":
                        a += getNumFromBoolean(valor1.getValorElemento());
                    break;
                case "integer":
                        a += Integer.parseInt(valor1.getValorElemento());
                    break;
                case "decimal":
                        a += Double.parseDouble(valor1.getValorElemento());
                    break;
            }
        }catch(Exception ex){
            System.out.println("Error Integer: " + ex.getLocalizedMessage());
        }
        return a;
    }
    
    public double getDecimalFromValue(ValorElemento valor1){
        double a = 0;
        try{
            String tipo = valor1.getTipoValor();

            switch(tipo){
                case "string":
                case "char":
                        a += getNumFromLetter(valor1.getValorElemento());
                    break;
                case "boolean":
                        a += getNumFromBoolean(valor1.getValorElemento());
                    break;
                case "integer":
                        a += Integer.parseInt(valor1.getValorElemento());
                    break;
                case "decimal":
                        a += Double.parseDouble(valor1.getValorElemento());
                    break;
            }
        }catch(Exception ex){
            System.out.println("Error Double: " + ex.getLocalizedMessage());
        }
        return a;
    }
    
    public boolean getBooleanFromValue(ValorElemento valor1){
        boolean a = true;
        try{
            String tipo = valor1.getTipoValor();

            switch(tipo){
                case "string":
                case "char":
                        a = getBooleanFromLetter(valor1.getValorElemento());
                    break;
                case "boolean":
                        a = getBooleanFromBoolean(valor1.getValorElemento());
                    break;
                case "integer":
                        a = getBooleanFromNumber(valor1.getValorElemento());
                    break;
                case "decimal":
                        a = getBooleanFromNumber(valor1.getValorElemento());
                    break;
            }
        }catch(Exception ex){
            System.out.println("Error boolean: " + ex.getLocalizedMessage());
        }
        return a;
    }
    
    public int getNumFromLetter(String letter){
        char letra = letter.charAt(0);//primera letra
        int n = (int)letra - (int)'a' + 1;
        
        return n;
    }
    
    public int getNumFromBoolean(String bool){        
        if(bool.equalsIgnoreCase("true")){
            return 1;
        }else
            return 0;
    }
    
    public boolean getBooleanFromLetter(String letter){
        char letra = letter.charAt(0);//primera letra
        int n = (int)letra - (int)'a' + 1;
        
        if(n == 1){
            return true;
        }else
            return false;
    }
    
    public boolean getBooleanFromBoolean(String bool){        
        if(bool.equalsIgnoreCase("true")){
            return true;
        }else
            return false;
    }
    
    public boolean getBooleanFromNumber(String number){
        
        int n = Integer.parseInt(number);
        
        if(n == 1){
            return true;
        }else
            return false;
    }
    
}
