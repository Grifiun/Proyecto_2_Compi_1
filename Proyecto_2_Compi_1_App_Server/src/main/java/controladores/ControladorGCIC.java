/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ManejoArchivos.GuardarEtiqueta;
import captcha.RevisarRepitenciaID;
import clasesDAO.TokenError;
import gramatica_gcic.LexerGCIC;
import gramatica_gcic.parser;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorGCIC", urlPatterns = {"/ControladorGCIC"})
public class ControladorGCIC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {              
        response.setContentType("text/html;charset=UTF-8");
        
        String salida = "xersedsd";
        try ( PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            
            try{
                String entrada = request.getParameter("texto");                     
                //Palabra descorrompida
                String palabraOriginal = new String(entrada.getBytes("ISO-8859-1"), "UTF-8");
                //Entrada sin marcas diacriticas
                String normalized_string = Normalizer.normalize(palabraOriginal, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

                //REMOVEMOS REDIRECT(); y ¿
                normalized_string = normalized_string.replaceAll("¿", "");
                normalized_string = normalized_string.replaceAll("REDIRECT", "EXIT");
                
                System.out.println("ANALIZAR: \n");
                System.out.println(normalized_string);
                
                StringReader sr = new StringReader(normalized_string);
                LexerGCIC lexer = new LexerGCIC(sr);
                parser pars = new parser(lexer);
                try{                        
                    pars.parse();                       
                    String errores = "";
                    ArrayList<TokenError> listadoErroresLexicos = lexer.obtenerListadoErroresLexicos();
                    ArrayList<TokenError> listadoErroresSintacticos = pars.getListadoErroresSintacticos();
                    ArrayList<TokenError> listadoErroresSemanticos = pars.getListadoErroresSemanticos();
                    
                    RevisarRepitenciaID rrid = new RevisarRepitenciaID();
                    String idAux = pars.getEtiquetaGCIC().getParametroEtiqueta("id").replaceAll("\"", "").trim();
                    if(rrid.revisarExistenciaCaptchaConId(idAux) != null){//tiene error
                        listadoErroresSemanticos.add(rrid.revisarExistenciaCaptchaConId(idAux));
                    }
                    
                    System.out.println("\n\n\n/////////////////////////////////////////////////");
                    
                    try{
                        for(TokenError te: listadoErroresLexicos){
                            errores += te.getTipoToken()+" Linea"+te.getLinea()+" Columna: "+te.getColumna()+" Lexema :"+te.getLexema()+" Mensaje: "+te.getMsgError() + "\n";                                                     
                        }
                    }catch(Exception ex){
                        
                    }
                    try{
                        for(TokenError te: listadoErroresSintacticos){                            
                            if(te.getMsgError().equalsIgnoreCase("La etiqueta de cierre debe ser <C_GCIC>")){//fin del archivo mal cerrado                                
                                String tokens[]=normalized_string.split("\n");   
                                int line = tokens.length, col = tokens[tokens.length - 1].length() + 1;
                                
                                te.setLinea(line);
                                te.setColumna(col);

                            }
                            errores += te.getTipoToken()+" Linea"+te.getLinea()+" Columna: "+te.getColumna()+" Lexema :"+te.getLexema()+" Mensaje: "+te.getMsgError() + "\n";                            
                        }                        
                    }catch(Exception ex){ 
                        
                    }
                    
                    try{
                        for(TokenError te: listadoErroresSemanticos){
                            errores += te.getTipoToken()+" Linea"+te.getLinea()+" Columna: "+te.getColumna()+" Lexema :"+te.getLexema()+" Mensaje: "+te.getMsgError()+ "\n";
                        }
                    }catch(Exception ex){                        
                              
                    }
                    
                    pars.getTablaSimbolos().imprimirTabla();
                    System.out.println("///////////////TABLA DE SIMBOLOS");
                    pars.getTablaSimbolos().generarTablaHTML();
                    System.out.println("///////////////ETIQUETAS");
                    pars.getTablaSimbolosEtiquetas().imprimirTabla();
                    
                    
                    System.out.println("//////////////////////CODIGO HTML: ");
                    System.out.println(pars.getEtiquetaGCIC().generarCodigoHTML(0));//nivel 0
                    
                    //Guardamos si no hay errires}
                    if(errores.equals("")){
                        errores = "Sin errores de compilacion, captcha agregado al listado";
                        GuardarEtiqueta guardarEtiqueta = new GuardarEtiqueta();
                        guardarEtiqueta.guardarEtiqueta(pars.getEtiquetaGCIC(), pars.getContenidoScripting()/*, pars.getDireccionRedirect()*/,  pars.getTablaSimbolos().generarHTML());
                    }
                    
                    //mostramos errores
                    System.out.println(errores); 
                    
                    System.out.println("SCRIPTING::");
                    //System.out.println(pars.getContenidoScripting());
                    salida = errores;
                    
                }catch(Exception ex){
                    System.out.println("Error en el lenguajes de etqiuetas: "+ex.getMessage());
                    ex.printStackTrace();
                }

                System.out.println(" Parser Ejecutado");
                request.getSession().setAttribute("entrada", normalized_string);
                request.getSession().setAttribute("salida", salida);
                
                String contador = request.getParameter("contador");
                request.getSession().setAttribute("contador", contador);
                               
            }catch(Exception ex){
                System.out.println("Error al ejecutar el parser: "+ex.getLocalizedMessage());
            }finally{
                request.getSession().setAttribute("mensajeError", "recibido");     
                String direccion = "jsp/ide.jsp";
                response.sendRedirect(direccion);
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
