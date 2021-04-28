/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import gramatica_gcic.LexerGCIC;
import gramatica_gcic.parser;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.Normalizer;
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
        
        try ( PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            try{
                String entrada = request.getParameter("texto");                     
                //Palabra descorrompida
                String palabraOriginal = new String(entrada.getBytes("ISO-8859-1"), "UTF-8");
                //Entrada sin marcas diacriticas
                String normalized_string = Normalizer.normalize(palabraOriginal, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

                StringReader sr = new StringReader(normalized_string);
                LexerGCIC lexer = new LexerGCIC(sr);
                parser pars = new parser(lexer);
                try{                        
                    pars.parse();     
                }catch(Exception ex){
                    System.out.println("Error en el lenguajes de etqiuetas");
                }

                System.out.println(" Parser Ejecutado");
                
            }catch(Exception ex){
                System.out.println("Error al ejecutar el parser: "+ex.getLocalizedMessage());
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
        
        
        request.getSession().setAttribute("mensajeError", "recibido");     
        String direccion = "jsp/home.jsp";
        //response.sendRedirect(direccion);
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
