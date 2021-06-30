/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ManejoArchivos.CargarCaptcha;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 50234
 */
@WebServlet(name = "ControladorRedirect", urlPatterns = {"/ControladorRedirect"})
public class ControladorRedirect extends HttpServlet {

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
        //Obtenemos el id
        String idCaptcha = request.getSession().getAttribute("idCaptchaRedirect").toString();
        
        //Reiniciamos el getSession() de la session
        request.getSession().setAttribute("idCaptchaRedirect", "");
                  
        //Actualizamos la cantidad de aciertos
        CargarCaptcha cargarCaptcha = new CargarCaptcha(); 
        cargarCaptcha.actualizarAciertoCaptcha(idCaptcha);
        
        //REDIRECCIONAMOS
        // Set response content type
        response.setContentType("text/html");

        // New location to be redirected
        String site = new String("https://www.facebook.com/");

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //Obtenemos el id
        String idCaptcha = request.getSession().getAttribute("idCaptchaRedirect").toString();
        
        //Obtenemos la direccion
        String direccionRedirect = request.getSession().getAttribute("direccionRedirect").toString();
        System.out.println("Direccion 2: " + direccionRedirect);
        //Reiniciamos el getSession() de la session
        //request.getSession().setAttribute("idCaptchaRedirect", "");
        //request.getSession().setAttribute("direccionRedirect", "");
                  
        //Actualizamos la cantidad de aciertos
        CargarCaptcha cargarCaptcha = new CargarCaptcha(); 
        cargarCaptcha.actualizarAciertoCaptcha(idCaptcha);
        
        //REDIRECCIONAMOS
        response.setContentType("text/html");

        //Nueva direccion
        String site = new String(direccionRedirect);

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
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
