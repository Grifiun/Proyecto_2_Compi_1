/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

/**
 *
 * @author 50234
 */
public class RemoverComillasSimples {
    public String removerComillasSimples(String texto){
        return texto.replaceAll("´", "").replaceAll("‘", "").replaceAll("’", "").replaceAll("'", "").trim();
    }
}
