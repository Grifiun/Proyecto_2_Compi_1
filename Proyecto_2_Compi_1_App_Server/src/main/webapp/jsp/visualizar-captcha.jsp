<%-- 
    Document   : visualizar-captcha
    Created on : 18/05/2021, 03:31:38
    Author     : 50234
--%>

<%@page import="ManejoArchivos.GuardarCaptcha"%>
<%@page import="ManejoArchivos.CargarCaptcha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String index = request.getParameter("index");
            String idCaptcha = session.getAttribute("id_captcha" + index).toString();
            
            //Agregamos el id del captcha en la session
            session.setAttribute("idCaptchaRedirect", idCaptcha);
            
            CargarCaptcha cargarCaptcha = new CargarCaptcha();            
            String codigoHTMLFormulario = cargarCaptcha.cargarHTMLCaptcha(idCaptcha);
            
            //obtenemos la direccion a redirigir
            String direccion = "";
            direccion = codigoHTMLFormulario.substring(0, codigoHTMLFormulario.indexOf("<html"));
            codigoHTMLFormulario = codigoHTMLFormulario.replaceFirst(direccion, "");
            
            direccion = direccion.replaceAll("“", "").replaceAll("”", "").replaceAll("\"", "").trim();
            
            //seteamos la session
            session.setAttribute("direccionRedirect", direccion);
            System.out.println("Direccion 1: " + direccion);
            //Actualizamos el uso en la base de datos
            cargarCaptcha.actualizarCargaCaptcha(idCaptcha);
        %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso administrador</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>    
        <%@include file="../html/ingresos/parte-superior.html" %>
        
        <h5>ID: <%=idCaptcha%></h5>
        
        <% out.print(codigoHTMLFormulario);%>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <script src="../scrpits/funciones-especiales.js" type="text/javascript"></script>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>