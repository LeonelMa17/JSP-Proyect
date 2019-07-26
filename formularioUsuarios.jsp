<%-- 
    Document   : formularioUsuarios
    Created on : 15/11/2018, 10:08:18 PM
    Author     : edgar
--%>

<%@page import="modelo.Usuario"%>
<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="resources/css/formularios.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>
        <div id="container">
            <h1>&bull; Usuarios &bull;</h1>
            <div class="underline">
            </div>
            <div class="icon_wrapper">
                <i class="fa fa-users" aria-hidden="true"></i>
            </div>
            <%
                String msj = (String) session.getAttribute("msj");                
                int id = 0;
                String nombre = "", apellidos = "", rol = "", telefono = "", email = "", password = "", fechaCreacion = "", fechaModificacion = "";
                int usuarioCreacion = 0, usuarioModificacion = 0;
                String accion = (String)session.getAttribute("accion");
                System.out.println(accion);
                if (accion == "Consultar") {
                        Usuario usuario = (Usuario)session.getAttribute("usuario");
                        if(usuario!=null){
                        id = usuario.getId_usuario();
                            nombre = usuario.getNombre();
                            apellidos = usuario.getApellidos();
                            rol = usuario.getRol();
                            telefono = usuario.getTelefono();
                            email = usuario.getEmail();
                            password = usuario.getPass();
                            fechaCreacion = usuario.getFechaCreacion().toString();
                            usuarioCreacion = usuario.getUsuarioCreacion();
                            fechaModificacion = usuario.getFechaModificacion().toString();
                            usuarioModificacion = usuario.getUsuarioModificacion();
                        }
                    }else if(accion == "Baja"){
                        id = 0;
                        nombre = ""; apellidos = ""; rol = ""; telefono = ""; email = "";
                        password = ""; fechaCreacion = ""; fechaModificacion = "";
                        usuarioCreacion = 0; usuarioModificacion = 0;                
                    }else{
                        id = 0;
                        nombre = ""; apellidos = ""; rol = ""; telefono = ""; email = "";
                        password = ""; fechaCreacion = ""; fechaModificacion = "";
                        usuarioCreacion = 0; usuarioModificacion = 0;                
                    }
            %>
            <div class="msj">
                <p>
                    <%
                        if (msj != null) {
                                out.println(msj);
                            }
                        msj="";
                    %>
                </p>
            </div>
            <form action="SevUsuarios" method="post" id="contact_form">
                <div class="id">
                    <label for="id">Id</label>
                    <input type="text" name="id" id="id_input" value="<%=id%>">
                </div>
                <div class="name">
                    <label for="name">Nombre(s)</label>
                    <input type="text" name="nombre" id="name_input" value="<%=nombre%>">
                </div>
                <div class="lastName">
                    <label for="lastName">Apellidos</label>
                    <input type="text" name="apellidos" id="lastName_input" value="<%=apellidos%>">
                </div>
                <div class="rol">
                    <label for="rol">Rol</label>
                    <input type="text" name="rol" id="rol_input" value="<%=rol%>">
                </div>
                <div class="telephone">
                    <label for="telephone">Telefono</label>
                    <input type="text" name="telephone" id="telephone_input" value="<%=telefono%>">
                </div>
                <div class="email">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email_input" value="<%=email%>">
                </div>
                <div class="password">
                    <label for="password">Contraseña</label>
                    <input type="password" name="password" id="password_input" value="<%=password%>">
                </div>
                <!--<div class="subject">
                    <label for="subject"></label>
                    <select placeholder="Subject line" name="subject" id="subject_input" required>
                        <option disabled hidden selected>Subject line</option>
                        <option>I'd like to start a project</option>
                        <option>I'd like to ask a question</option>
                        <option>I'd like to make a proposal</option>
                    </select>
                </di>-->
                <div class="dateC">
                    <label for="dateC">Fecha De Creacion</label>
                    <input type="text" name="dateC" id="dateC_input" value="<%=fechaCreacion%>" readonly="readonly">
                </div>
                <div class="userC">
                    <label for="userC">Usuario Creador</label>
                    <input type="text" name="userC" id="userC_input" value="<%=usuarioCreacion%>" readonly="readonly">
                </div>
                <div class="dateU">
                    <label for="dateU">Fecha De Ultima Modificación</label>
                    <input type="text" name="dateU" id="dateU_input" value="<%=fechaModificacion%>" readonly="readonly">
                </div>
                <div class="userU">
                    <label for="userU">Usuario De Ultima Modificación</label>
                    <input type="text" name="userU" id="userU_input" value="<%=usuarioModificacion%>" readonly="readonly">
                </div>
                <br><br><br><br>
                <div class="submit">
                    <input type="submit" name="accion" value="Alta" id="alta_button" />
                    <input type="submit" name="accion" value="Baja" id="baja_button" />
                    <input type="submit" name="accion" value="Modificar" id="modific_button" />
                    <input type="submit" name="accion" value="Consultar" id="consultar_button" />
                    <input type="button" value="Limpiar" onclick="javascript:limpiar()">
                </div>
            </form><!-- // End form -->
        </div><!-- // End #container -->
        
        <script>
            function limpiar(){
                document.getElementById("id_input").value ="";
                document.getElementById("name_input").value = "";
                document.getElementById("lastName_input").value = "";
                document.getElementById("rol_input").value = "";
                document.getElementById("telephone_input").value = "";
                document.getElementById("email_input").value = "";
                document.getElementById("password_input").value = "";
                document.getElementById("dateC_input").value = "";
                document.getElementById("userC_input").value = "";
                document.getElementById("dateU_input").value = "";
                document.getElementById("userU_input").value = "";
            }
            
        </script>
    </body>
</html>
