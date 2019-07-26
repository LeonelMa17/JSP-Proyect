<%-- 
    Document   : formulatioMovimientos
    Created on : 15/11/2018, 11:36:10 PM
    Author     : edgar
--%>

<%@page import="modelo.Movimiento"%>
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
            <h1>&bull; Movimientos &bull;</h1>
            <div class="underline">
            </div>
            <div class="icon_wrapper">
                <i class="fa fa-arrows-alt" aria-hidden="true"></i>
            </div>
            
            <%
                String msj = (String) session.getAttribute("msj");                
                int id = 0;
                String descripcion = "", fecha = "", tipo = "", origenDestino = "", 
                        fechaModificacion = "";
                int id_usuario=0, id_cliente=0, usuarioModificacion=0, cantidad=0, id_producto=0;
                
                String accion = (String)session.getAttribute("accion");
                System.out.println(accion);
                if (accion == "Consultar") {
                        Movimiento movimiento = (Movimiento)session.getAttribute("movimiento");
                        id = movimiento.getId_movimiento();
                        id_producto = movimiento.getId_producto();
                        descripcion = movimiento.getDescripcion();
                        fecha = movimiento.getFecha().toString();
                        tipo = movimiento.getTipo();
                        id_usuario = movimiento.getId_usuario();
                        id_cliente = movimiento.getId_cliente();
                        origenDestino = movimiento.getOrigenDestino();                        
                        fechaModificacion = movimiento.getFechaModificacion().toString();
                        usuarioModificacion = movimiento.getUsuarioModificacion();
                        cantidad = movimiento.getCantidad();
                    }else if(accion == "Baja"){
                        descripcion = ""; fecha = ""; tipo = ""; origenDestino = ""; 
                        fechaModificacion = "";
                        id_usuario=0; id_cliente=0; usuarioModificacion=0; cantidad=0; id_producto=0;
                    }else{
                         descripcion = ""; fecha = ""; tipo = ""; origenDestino = ""; 
                        fechaModificacion = "";
                        id_usuario=0; id_cliente=0; usuarioModificacion=0; cantidad=0; id_producto=0;
                    }
            %>
            <center>
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
            </center>
            
            <form action="SevMovimientos" method="post" id="contact_form">
                <div class="id">
                    <label for="id"></label>
                    <input type="text" placeholder="id Movimiento" name="id" id="id_input" value="<%=id%>">
                </div>
                <div class="id">
                    <label for="id"></label>
                    ID Del Producto
                    <input type="text" placeholder="id Producto" name="id_producto" id="idProducto_input" value="<%=id_producto%>">
                </div>
                <div class="descripcion">
                    <label for="descripcion"></label>
                    <input type="text" placeholder="Descripcion" name="descripcion" id=descripcion_input" value="<%=descripcion%>">
                </div>
                <div class="fecha">
                    <label for="fecha"></label>
                    <input type="text" placeholder="Fecha" name="fecha" id="unidad_input" value="<%=fecha%>">
                </div>
                <div class="tipo">
                    <label for="tipo"></label>
                    <input type="text" placeholder="Tipo" name="tipo" id="tipo_input" value="<%=tipo%>">
                </div>
                <div class="idUsuario">
                    <label for="idUsuario"></label>
                    ID Del Usuario
                    <input type="text" placeholder="Id Usuario" name="idUsuario" id="idUsuario_input" value="<%=id_usuario%>" readonly="readonly">
                </div>
                <div class="idCliente">
                    <label for="idCliente"></label>
                    ID Del Cliente
                    <input type="text" placeholder="id Cliente" name="idCliente" id="idCliente_input"  value="<%=id_cliente%>">
                </div>
                <div class="origenDestino">
                    <label for="origenDestino"></label>
                    <input type="text" placeholder="Origen Destino" name="origenDestino" id="origenDestino_input" value="<%=origenDestino%>">
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
                <div class="dateU">
                    <label for="dateU"></label>
                    <input type="text" placeholder="fecha de ultima modificación" name="fechaModificacion" id="dateU_input" value="<%=fechaModificacion%>" readonly="readonly">
                </div>
                <div class="userU">
                    <label for="userU"></label>
                    Usuario De Ultima Modificacion
                    <input type="text" placeholder="usuario de ultima modificación" name="usuarioModificacion" id="userU_input" value="<%=usuarioModificacion%>" readonly="readonly">
                </div>
                <div class="cantidad">
                    <label for="cantidad"></label>                    
                    <input type="text" placeholder="cantidad" name="cantidad" id="cantidad_input" value="<%=cantidad%>">
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
                document.getElementById("tipo_input").value = "";
                document.getElementById("idUsuario_input").value = "";
                document.getElementById("idCliente_input").value = "";
                document.getElementById("origenDestino_input").value = "";                
                document.getElementById("dateU_input").value = "";
                document.getElementById("userU_input").value = "";
                document.getElementById("cantidad_input").value = "";
                document.getElementById("idProducto_input").value = "";
                
            }
            
        </script>
    </body>
</html>
