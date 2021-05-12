/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etiquetas;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author 50234
 */
public class Etiqueta {
    private String tipo;//C_GCIC, C_H1, etc
    private String texto;// Texto, si tienen
    private ArrayList<ParametroEtiqueta> listadoParametros;//listado de parametros, tipo nombre
    private ArrayList<Etiqueta> listadoEtiquetas;// para contenedores, pueden tener más etiquetas dentro

    public Etiqueta(String tipo, String texto, ArrayList<ParametroEtiqueta> listadoParametros, ArrayList<Etiqueta> listadoEtiquetas) {
        this.tipo = tipo;
        this.texto = texto;
        this.listadoParametros = listadoParametros;
        this.listadoEtiquetas = listadoEtiquetas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ArrayList<ParametroEtiqueta> getListadoParametros() {
        return listadoParametros;
    }

    public void setListadoParametros(ArrayList<ParametroEtiqueta> listadoParametros) {
        this.listadoParametros = listadoParametros;
    }

    public ArrayList<Etiqueta> getListadoEtiquetas() {
        return listadoEtiquetas;
    }

    public void setListadoEtiquetas(ArrayList<Etiqueta> listadoEtiquetas) {
        this.listadoEtiquetas = listadoEtiquetas;
    }
  
}
