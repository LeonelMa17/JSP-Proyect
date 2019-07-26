<%-- 
    Document   : ConsultarMovimientos
    Created on : 5/12/2018, 02:46:21 AM
    Author     : Leonel
--%>

<%@page import="modelo.DetalleMovimiento"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="persistencia.ConsultasDetalleMovimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tus Movimientos Registrados</title>
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
          int id_usuario = (Integer)session.getAttribute("id");
      ConsultasDetalleMovimiento nc = new ConsultasDetalleMovimiento();
      LinkedList<DetalleMovimiento> lista = nc.consultarDetallesMovimientos(id_usuario);
      out.println("<br><br><br>");
      out.println("<h1><p> Tus movimientos registrados </p></h1>");
      out.println("<br><br>");
      for (int i = 0; i < lista.size(); i++) {              
              out.println("<div class=\"col-xs-1 col-md-3 text-center\" style='background-color: #f8f8f8'><br>");
              out.println("<div class=\"card\" style='margin-bottom:20px'; >");
              out.println("<h3> Movimento: "+lista.get(i).getId_movimiento()+"</h3>");
              out.println("<h3> Producto: "+lista.get(i).getId_producto()+"</h3>");              
              out.println("<h4 style='color:red;'>"+"Cantidad: " + lista.get(i).getCantidad()+"</h4>");
              out.println("<h5>"+ "Descripcion: " + lista.get(i).getDescripcion()+ "</h5>");
              out.println("<h5 class='precio' style='color:blue;'>"+ "Tipo: " + 
                      lista.get(i).getTipo()+"</h5>");                
              out.println("</div>");
              out.println("</div>");
          }
      %>
      </div>
    </body>
</html>
