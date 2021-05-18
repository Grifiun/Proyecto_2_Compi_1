/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoArchivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author 50234
 */
public class Guardar {
     /**
     * Funcion dedicada a guardar archivos
     * @param contenido
     * @param tipoGuardado 
     */
    public void guardarDatos(String contenido, String nombreArchivo){
    
        String path;        
        
        try {
            File directorio = new File("archivos_captcha");
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");                    
                } else {
                    System.out.println("Error al crear directorio");
                }
            }
            
            if(directorio.exists()){//si existe el directorio
                String ruta = "archivos_captcha/"+nombreArchivo+".txt";
                File file = new File(ruta);
                // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(contenido);
                bw.close();
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar");
        }
    
    }
}
