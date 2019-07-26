<%-- 
    Document   : ConsultarProductos
    Created on : 30/11/2018, 01:08:47 AM
    Author     : Leonel
--%>

<%@page import="persistencia.ConsultasProductos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.LinkedList"%>
<%@page import="persistencia.perProductos"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos Registrados</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index.css">
    <link rel="stylesheet" href="resources/css/nav.css">
    <link rel="stylesheet" href="resources/css/categorias.css">
    <link rel="stylesheet" href="resources/css/formularios.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">		        
    </head>
    
    <header>         
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>      
    </header>
        
    <body>
          <div class="main" id="lista-productos">
      <%
      ConsultasProductos nc = new ConsultasProductos();
      LinkedList<Producto> lista = nc.consultarProductos();
      out.println("<br><br><br>");
      out.println("<h1><p> Productos </p></h1>");
      out.println("<br><br>");
      for (int i = 0; i < lista.size(); i++) {              
              out.println("<div class=\"col-xs-1 col-md-3 text-center\" style='background-color: #f8f8f8'><br>");
              out.println("<div class=\"card\" style='margin-bottom:20px'; >");
              out.println("<img src='"+lista.get(i).getUbicacion()+"' class='img-responsive'>");              
              out.println("<h4 style='color:red;'>"+lista.get(i).getDescripcion()+"</h4>");
              out.println("<h5>"+ "Unidad: " + lista.get(i).getUnidad()+ "</h5>");
              out.println("<h5 class='precio' style='color:blue;'>"+ "Existencia: " + 
                      lista.get(i).getExistencia()+"</h5>");                
              out.println("</div>");
              out.println("</div>");
          }
      %>
      </div>
    </body>
</html>
