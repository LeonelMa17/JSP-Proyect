<%-- 
    Document   : navegacion
    Created on : 16/11/2018, 12:39:29 AM
    Author     : edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">		
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,600|Open+Sans" rel="stylesheet"> 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
	<link rel="stylesheet" href="resources/css/navegacion.css">		
		
	<title>Menu de Navegación</title>
</head>
        <header style="top:0;">
  <%
            HttpSession sesion = request.getSession(true);
            String nombre="", rol="";
            if (sesion.getAttribute("login") != null) {
                 nombre = (String)sesion.getAttribute("login");
            }
            
            rol = (String)sesion.getAttribute("rol");

            if(rol.equals("Administrador")){
                out.println("<nav class='nav'>"
                    +"<ul style='padding-top:20px;'>"
                    +"<li><a href=\"Inicio.jsp\">INICIO</a></li>"
                    +"<li><a href=\"formularioUsuarios.jsp\">USUARIOS</a></li>"
                    +"<li><a href=\"formularioMovimientos.jsp\">MOVIMIENTOS</a></li>"
                    +"<li><a href=\"formularioProductos.jsp\">PRODUCTOS</a></li>"
                    +"</li> </ul> </nav>");                
            }else if(rol.equals("Empleado")){
                 out.println("<nav class='nav'>"
                    +"<ul style='padding-top:20px;'>"
                    +"<li><a href=\"Inicio.jsp\">INICIO</a></li>"                    
                    +"<li><a href=\"ConsultarUsuarios.jsp\">USUARIOS</a></li>"
                    +"<li><a href=\"formularioMovimientos.jsp\">MOVIMIENTOS</a></li>"
                    +"<li><a href=\"formularioProductos.jsp\">PRODUCTOS</a></li>"
                    +"</li> </ul> </nav>");  
            }else if(rol.equals("Cliente")){
                 out.println("<nav class='nav'>"
                    +"<ul style='padding-top:20px;'>"
                    +"<li><a href=\"Inicio.jsp\">INICIO</a></li>"                    
                    +"<li><a href=\"ConsultarUsuarios.jsp\">CONSULTAR USUARIOS</a></li>"
                    +"<li><a href=\"ConsultarProductos.jsp\">CONSULTAR PRODUCTOS</a></li>"
                    +"</li> </ul> </nav>");  
            }

            %>
	  
            <div class="dropdown">
            <ul class="nav nav-pills" style="padding-top: 30px; ">           
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
                       role="button" aria-haspopup="true" aria-expanded="false">Hola, <%=nombre%></a>
                    <div class="dropdown-menu">
                        <%if(rol.equals("Cliente")){%>
                        <a class="dropdown-item" href="#">Mis movimientos</a> 
                        <%}%>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="logout">Salir</a>
                    </div>
                </li>
                <%if(rol.equals("Cliente")){%>
                <li class="nav-item">
                    <a class="nav-link active" href="ConsultarMovimientos.jsp">Mis Movimientos</a>
                </li>
                <%}%>
                <li class="nav-item">
                    <a class="nav-link active" href="logout">Salir</a>
                </li>
            </ul>
          </div>
        </header>

            
                        
              <!--<li><a class="dropdow-item" id="btn-abrir-popup" href="logout"><img src="resources/images/cliente.png" id="img-carrito" > SALIR </a></li>-->
              
              <!--<div class="dropdown-menu" x-placement="bottom-start">                 -->
                
                <!--</div>-->
	  <!--</ul>-->
            <!--</div>	-->
               <!-- </ul>-->

<body>

</body>
</html>
