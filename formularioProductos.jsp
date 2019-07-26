<%-- 
    Document   : formularioProductos
    Created on : 15/11/2018, 11:16:35 PM
    Author     : edgar
--%>

<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="resources/css/formularios.css">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="nave">
            <jsp:include page="navegacion.jsp"/>
        </div>
        <div id="container">
            <h1>&bull; Productos &bull;</h1>
            <div class="underline">
            </div>
            <div class="icon_wrapper">
                <i class="fa fa-product-hunt" aria-hidden="true"></i>
            </div>
            
             <%
                String msj = (String) session.getAttribute("msj");                
                int id = 0;
                String descripcion = "", unidad = "", ubicacion = "", fechaCreacion = "",
                        fechaModificacion = "";
                int usuarioModificacion=0, usuarioCreacion=0, existencia=0;
                
                String accion = (String)session.getAttribute("accion");
                System.out.println(accion);
                if (accion == "Consultar") {                    
                        Producto producto = (Producto)session.getAttribute("producto");                        
                        if(producto != null){
                            id = producto.getId_producto();
                            descripcion = producto.getDescripcion();
                            unidad = producto.getUnidad();
                            existencia = producto.getExistencia();
                            ubicacion = producto.getUbicacion();
                            fechaCreacion = producto.getFechaCreacion().toString();
                            usuarioCreacion = producto.getUsuarioCreacion();
                            fechaModificacion = producto.getFechaModificacion().toString();
                            usuarioModificacion = producto.getUsuarioModificacion();                                             
                        }
                    }else if(accion == "Baja"){
                        descripcion = ""; unidad = ""; ubicacion = ""; fechaCreacion = ""; fechaModificacion = "";
                        usuarioModificacion=0; usuarioCreacion=0; existencia=0;
                    }else{
                        descripcion = ""; unidad = ""; ubicacion = ""; fechaCreacion = ""; fechaModificacion = "";
                        usuarioModificacion=0; usuarioCreacion=0; existencia=0;
                    }
            %>
            <center><div class="msj">
                <p>
                    <%
                        if (msj != null) {
                                out.println(msj);
                            }
                        msj="";
                    %>
                </p>
                </div></center>
            
            <form action="SevProductos" method="post" id="contact_form">
                <div class="id">
                    <label for="id"></label>
                    <input type="text" placeholder="Id producto" name="id" id="id_input" value="<%=id%>"> 
                </div>
                <div class="descripcion">
                    <label for="descripcion"></label>
                    <input type="text" placeholder="Descripcion" name="descripcion" id=descripcion_input" value="<%=descripcion%>">
                </div>
                <div class="unidad">
                    <label for="unidad"></label>
                    <input type="text" placeholder="Unidad" name="unidad" id="unidad_input" value="<%=unidad%>">
                </div>
                <div class="existencia">
                    <label for="existencia"></label>
                    Existencia
                    <input type="text" placeholder="Existencia" name="existencia" id="existencia_input" value="<%=existencia%>">
                </div>
                <div class="ubicacion">
                    <label for="ubicacion"></label>
                    <input type="text" placeholder="Ubicacion" name="ubicacion" id="ubicacion_input" value="<%=ubicacion%>">
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
                    <label for="dateC"></label>
                    <input type="text" placeholder="fecha de creacion" name="dateC" id="dateC_input" readonly="readonly" value="<%=fechaCreacion%>">
                </div>
                <div class="userC">
                    <label for="userC"></label>
                    Usuario Creador
                    <input type="text" placeholder="usuario Creador" name="userC" id="userC_input" readonly="readonly" value="<%=usuarioCreacion%>">
                </div>
                <div class="dateU">
                    <label for="dateU"></label>
                    <input type="text" placeholder="fecha de ultima modificación" name="dateU" id="dateU_input" readonly="readonly" value="<%=fechaModificacion%>">
                </div>
                <div class="userU">
                    <label for="userU"></label>
                    Fecha Ultima Modificacion
                    <input type="text" placeholder="usuario de ultima modificación" name="userU" id="userU_input" readonly="readonly" value="<%=usuarioModificacion%>">
                </div>
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
                document.getElementById("descripcion_input").value = "";
                document.getElementById("unidad_input").value = "";
                document.getElementById("existencia_input").value = "";
                document.getElementById("ubicacion_input").value = "";
                document.getElementById("dateC_input").value = "";
                document.getElementById("userC_input").value = "";                
                document.getElementById("dateU_input").value = "";
                document.getElementById("userU_input").value = "";                
            }
            
        </script>
                
    </body>
</html>
