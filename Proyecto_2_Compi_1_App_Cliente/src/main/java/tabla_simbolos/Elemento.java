/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_simbolos;

/**
 *
 * @author grifiun
 */
public class Elemento {
    private String identificador;//id
    private String tipo; //variable, funcion, ciclo o if/else/else if
    private String modo;//ambito de declaracion, funcion  o global
    private String procedimiento;
    private int scripting;
    private int fila;
    private int columna;
    private ValorElemento valor;
    private int nEjecucion;

    public Elemento(String identificador, String tipo, String modo, String procedimiento, int scripting, int fila, int columna, ValorElemento valor) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.modo = modo;
        this.procedimiento = procedimiento;
        this.scripting = scripting;
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.nEjecucion = 1;//empiezan en 1 siempre
    }

    /**
     * Constructor para recibir funciones
     * @param identificador
     * @param tipo
     * @param modo
     * @param procedimiento
     * @param scripting
     * @param fila
     * @param columna
     * @param valor 
     */
    public Elemento(String identificador, String tipo, int scripting, int fila, int columna) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.modo = "---";
        this.procedimiento = "---";
        this.scripting = scripting;
        this.fila = fila;
        this.columna = columna;
        this.valor = null;
        this.nEjecucion = 1;//empiezan en 1 siempre
    }
    
    public String elementToString(){
        String espacio = "";
        String val = "";
        
        for(int i = 0; i < 25 - identificador.length(); i++ ){
            espacio+= " ";
        }
        if(valor != null){
            val = valor.getValorElemento();
        }else{
            val = "---";
        }
        
        return "identificador: "+identificador+espacio+" tipo: "+tipo+" modo: "+modo+" procedimiento: "+procedimiento+" scripting: "+scripting+" fila: "+fila+" columna: "+columna + "valor actual: "+val+" N.Ejec: "+nEjecucion;
                
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }
    
    public int getScripting() {
        return scripting;
    }

    public void setScripting(int scripting) {
        this.scripting = scripting;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public ValorElemento getValor() {
        return valor;
    }

    public void setValor(ValorElemento valor) {
        this.valor = valor;
    }

    public int getnEjecucion() {
        return nEjecucion;
    }

    public void setnEjecucion(int nEjecucion) {
        this.nEjecucion = nEjecucion;
    }

    
    
    
    
    
    
    
}
