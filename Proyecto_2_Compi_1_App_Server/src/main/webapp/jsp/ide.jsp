<%-- 
    Document   : ide
    Created on : 21/04/2021, 19:47:53
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String entrada = "";
            String salida = "";
            if(session.getAttribute("entrada") != null){
                entrada = session.getAttribute("entrada").toString();
            }
            if(session.getAttribute("salida") != null){
                salida = session.getAttribute("salida").toString();
            }
            
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/css-bootstrap.html"%>
        <title>IDE</title>       
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
        <link href="../css/style-ide.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../html/navs/nav-usuario.html" %>           
        <%@include file="../html/ide/ide.html" %>
        <%@include file="../html/js-bootstrap.html"%>
        <%@include file="../html/footer/footer.html"%>
    </body>
</html>
