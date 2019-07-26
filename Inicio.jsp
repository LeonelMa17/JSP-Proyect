<%-- 
    Document   : Inicio
    Created on : 29/11/2018, 08:54:26 PM
    Author     : Leonel
--%>

<%@page import="persistencia.ConsultasProductos"%>
<%@page import="java.util.LinkedList"%>
<%@page import="modelo.Producto"%>
<%@page import="persistencia.perProductos"%>
<%@page import="modelo.Usuario"%>
<%@page import="persistencia.UsuarioDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="resources/css/formularios.css">        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">    
    
        <title>Inicio</title>
    </head>     	    
    <header>
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>
    </header>
    <body> 
        
       <%                                                                        
            //RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            // dispatcher.forward(request, response);
            //response.sendRedirect("login.jsp");
            
            
             String nombre="", rol="";
            if (session.getAttribute("login") != null) {
                 nombre = (String)session.getAttribute("login");
            }
            rol = (String)session.getAttribute("rol");
            session.setAttribute("Error","");
            %>
        
            <%if(rol.equals("Cliente")){%>          
            <div>
            <img src="resources/images/fondoclientes.jpg" style="position:absolute;opacity: 0.4;">                        
            <h1 style="padding-top: 120px; padding-left: 50px; padding-bottom: 50px; font-size: 32px;
                position:relative; color:#185A24"> Bienvenido <%=nombre%> </h1> 
            <p style=" padding-left: 50px; font-size: 32px; 
               font-family: fantasy; color:#185A24; text-align:initial;
               position:absolute;">
            ESTÁ ES UNA SECCIÓN PARA TI.<br>
            CONSULTA LOS MOVIMIENTOS QUE HAS SOLICITADO.<br>
            TAMBIÉN PUEDES VER LOS USUARIOS REGISTRADOS Y LOS PRODUCTOS QUE ESTÁN
            DISPONIBLES EN NUESTRO CATALOGO.<br>
            
            </p>              
            <br><br><br><br><br><br><br><br>   
        </div>
            <br><br><br><br><br><br><br><br><br><br><br><br>   
            <div id="img" style="background-color: #f8f8f8">
		<h1><p style="font-size:35px; background-color: yellow;">BIENVENIDO AL PORTAL</p> </h1>	
               <div style="text-align:center;">
               <img src="resources/images/apple.png" 
                     class="img-responsive" style=" opacity: 1.4; margin-left:26%; height: 80%; width: 50%;" >
               </div>
            </div>
            <div class="main" id="lista-productos">
              <%
         ConsultasProductos nc = new ConsultasProductos();
          LinkedList<Producto> lista = nc.consultarProductos();
          out.println("<br><br><br><br>");
          out.println("<h1 style='background-color: red'><p> Verifica algunos de nuestros productos </p></h1>");
          out.println("<br>");
          for (int i = 0; i < 4; i++) {
              out.println("<div class=\"col-xs-1 col-md-3 text-center\" style='background-color: #f8f8f8'><br>");
              out.println("<div class=\"card\" style='margin-bottom:20px'; >");
              out.println("<img src='" + lista.get(i).getUbicacion() + "' class='img-responsive'>");
              out.println("<h4 style='color:red;'>" + lista.get(i).getDescripcion() + "</h4>");
              out.println("<h5>" + "Unidad: " + lista.get(i).getUnidad() + "</h5>");
              out.println("<h5 class='precio' style='color:blue;'>" + "Existencia: "
                      + lista.get(i).getExistencia() + "</h5>");
              out.println("</div>");
              out.println("</div>");
          }
      %>
            </div>
            <%}else if(rol.equals("Empleado")){%>
                 <div>
            <img src="resources/images/recepcion.jpg" style="position:absolute; witdh:100%; height:820px; opacity: 0.4;">                        
            <h1 style="padding-top: 120px; padding-left: 50px; padding-bottom: 50px; font-size: 32px;
                position:relative; color:#1D4A80"> Hola <%=nombre%> </h1> 
            <p style=" padding-left: 50px; font-size: 32px; 
               font-family: fantasy; color:#1D4A80; text-align:initial;
               position:absolute;">
               NO OLVIDES REGISTRAR EL MOVIMIENTO DEL CLIENTE.<br>
            CONSULTA, ACTUALIZA, ELIMINA LOS PRODUCTOS DEL ALMACEN<br>
            NO OLVIDES DAR UN VISTAZO AL CATALOGO DE CLIENTES.
        </p>        
        </div>            
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <div id="img" style="background-color: black">
		<h1><p style="font-size:35px; background-color: yellow;">BIENVENIDO AL PORTAL</p> </h1>	
               <div style="text-align:center;">
               <img src="resources/images/apple.png" 
                     class="img-responsive" style=" margin-left:26%; height: 800%; width: auto; opacity: 1;" >
               </div>
            </div>
            
            <div class="main" id="lista-productos">
            <%                                
         ConsultasProductos nc = new ConsultasProductos();
          LinkedList<Producto> lista = nc.consultarProductos();
          out.println("<br><br><br><br>");
          out.println("<h1 style='background-color:yellow'><p> Verifica los productos </p></h1>");
          out.println("<br>");
          for (int i = 0; i < 4; i++) {
              out.println("<div class=\"col-xs-1 col-md-3 text-center\">");
              out.println("<div class=\"card\" style='margin-bottom:20px'; >");
              out.println("<img src='" + lista.get(i).getUbicacion() + "' class='img-responsive'>");
              out.println("<h4 style='color:red;'>" + lista.get(i).getDescripcion() + "</h4>");
              out.println("<h5>" + "Unidad: " + lista.get(i).getUnidad() + "</h5>");
              out.println("<h5 class='precio' style='color:blue;'>" + "Existencia: "
                      + lista.get(i).getExistencia() + "</h5>");
              out.println("</div>");
              out.println("</div>");
          }
      %>
            </div>
            
            <div class="main" id="lista-productos">
      <%
      UsuarioDB ncd = new UsuarioDB();
      LinkedList<Usuario> lista2 = ncd.consultarUsuarios();
      out.println("<br><br><br><br>");
      out.println("<h1 style='background-color:black; color:black'>USUARIOS<p></p></h1>");
      out.println("<br><br>");
      out.println("<h1 style='background-color:yellow'><p> Algunos Usuarios Registrados </p></h1>");
      for (int i = 0; i < 4; i++) {                            
              out.println("<div class=\"col-xs-1 col-md-3 text-center\" style='background-color: #f8f8f8'><br>");
              out.println("<div class=\"card\" style='margin-bottom:20px'; >");
              out.println("<h2>" + lista2.get(i).getNombre()+ " " +lista2.get(i).getApellidos()+"</h2>");
              out.println("<h4 style='color:red;'>"+lista2.get(i).getRol()+"</h4>");
              out.println("<h5>"+lista2.get(i).getEmail()+ "</h5>");                          
              out.println("</div>");
              out.println("</div>");
          }
      %>
      </div>      
            
            <%}else if(rol.equals("Administrador")){%>
                 <div>
            <img src="resources/images/fondo.jpg" style="position:absolute; opacity: 0.4;">                        
            <h1 style="padding-top: 120px; padding-left: 50px; padding-bottom: 50px; font-size: 32px;
                position:relative; color:#1D4A80"> Hola <%=nombre%> </h1> 
            <p style=" padding-left: 50px; font-size: 32px; 
               font-family: fantasy; color:#1D4A80; text-align:initial;
               position:absolute;">
               HOLA ADMINISTRADOR.<br>
            RECUERDA UTILIZAR CONSULTAS EN EL ALMACEN<br>
            VERIFICA Y TOMA EL CONTROL DE LOS CLIENTES.
        </p>
        </div>
            <%}%>
   
    </body>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br>
    
        <footer style="background-color: #898C8F" >
	<center>
            <div><br><br><br>
            <a target="_blank" href="https://www.facebook.com/" title="Presiona para ir a facebook"><input name="facebook" type="image" value="" src="resources/images/facebook.png" width="50" height="50"></a> 
            <a target="_blank" href="https://www.instagram.com/" title="Presiona para ir a instagram"><input name="insta" type="image" value="" src="resources/images/instagram.png" width="50" height="50"></a>
            <a target="_blank" href="https://www.twitter.com/" title="Presiona para ir a twitter"><input name="twitter" type="image" value="" src="resources/images/twitter.png" width="50" height="50"></a>
        </div>
        </center><br><br>
        <center><Label style="color:white;">Sucursal León Guanajuato </label></center>
        <br>
        <center><label style="color:white;">Telefono: (477) 501-88-23</label></center>
        <br>             
        <center><label style="color:white;">Telefono: (477) 751-11-06</label></center>
        <br><br>
        <center><label style="color:white; font-size:18px; font-family:cursive;  ">Desarrolladores </label>
        <br>                    
        <label style="color:white;">Juan Martin Hernández López, Edson Leonel López Quiroga, Edgar Raziel Olvera Barrozo. </label><br></center>
        <br>
        <center><label style="color:white;">2018 Inc.</label></center>
    </footer>
    
    
</html>
