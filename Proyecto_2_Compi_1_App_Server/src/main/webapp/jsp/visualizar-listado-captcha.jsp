<%-- 
    Document   : visualizar-listado-captcha
    Created on : 18/05/2021, 03:14:18
    Author     : 50234
--%>

<%@page import="ManejoArchivos.CargarCaptcha"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%        
        //titulos
        ArrayList<String> titulo = new ArrayList<>(Arrays.asList("Id captcha","Nombre","Veces abierto","Aciertos","Fallos", "Ultimo registro", "Ver"));
                
        CargarCaptcha cargar = new CargarCaptcha();
        List<ArrayList<String>> lista = cargar.cargarCaptchasTablas();
        
        %>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Captcha</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>
        <%@include file="../html/ingresos/parte-superior.html" %>
        <%@include file="../html/mostrar-registro/tabla-generica.html" %>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
