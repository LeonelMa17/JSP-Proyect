/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DetalleMovimiento;
import modelo.Movimiento;
import persistencia.ConsultasMovimientos;
import persistencia.perDetallesMovimiento;
import persistencia.perMovimiento;

/**
 *
 * @author Leonel
 */
@WebServlet(name = "SevMovimientos", urlPatterns = {"/SevMovimientos"})
public class SevMovimientos extends HttpServlet {

    perMovimiento pu = new perMovimiento();    
    perDetallesMovimiento det = new perDetallesMovimiento();
    ConsultasMovimientos con = new ConsultasMovimientos();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         HttpSession sesion = request.getSession(true);
            String accion = request.getParameter("accion");
            boolean status;
            int id, id_prod;
            String descripcion = "", tipo = "", origenDestino = "", email = "", password = "";
            Timestamp date, dateU;
            int userC, userU, userUpdate, cantidad;
            Movimiento movimiento;
            DetalleMovimiento detalle;
            
            int id_usuario = (Integer)sesion.getAttribute("id");                        
            
            System.out.println("primer print: accion = "+accion);
            if (accion.equals("Alta")) {                
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                
                movimiento = new Movimiento();
                movimiento.setDescripcion(request.getParameter("descripcion"));
                movimiento.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
                movimiento.setFecha(timestamp);
                movimiento.setTipo(request.getParameter("tipo"));
                movimiento.setId_usuario(id_usuario);
                movimiento.setId_cliente(Integer.parseInt(request.getParameter("idCliente")));
                movimiento.setOrigenDestino(request.getParameter("origenDestino"));
                movimiento.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                movimiento.setFechaModificacion(timestamp);                                
                movimiento.setUsuarioModificacion(id_usuario);                                
                                                
                status = pu.altaMovimiento(movimiento);
                                
                if(movimiento.getTipo().equals("entrada")){
                        int can = movimiento.getCantidad();
                        int id_prodo = movimiento.getId_producto();
                        
                        try{
                            con.Incrementar(can, id_prodo);
                        }catch(Exception e){
                            System.out.print("Entrada " + e);
                        }
                    }else if(movimiento.getTipo().equals("salida")){
                        int can = movimiento.getCantidad();
                        int id_prodo = movimiento.getId_producto();
                        try{
                            con.Decrementar(can, id_prodo);
                        }catch(Exception e){
                            System.out.print("Entrada " + e);
                        }
                    }
                
            //traer   
                int id_mov=0;
            try {
                id_mov = con.Ultimo();
            } catch (SQLException ex) {
                Logger.getLogger(SevMovimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
                 //AGREGAR AL DETALLE MOVIMIENTO
                    detalle = new DetalleMovimiento();                    
                    detalle.setId_movimiento(id_mov);
                    detalle.setId_producto(movimiento.getId_producto());
                    detalle.setCantidad(movimiento.getCantidad());
                    detalle.setDescripcion(movimiento.getDescripcion());
                    detalle.setTipo(movimiento.getTipo());
                                        
                    //Insertar el detalle
                    det.altaMovimiento(detalle);  
                sesion.setAttribute("accion", "Alta");
                if (status) {
                   sesion.setAttribute("msj", "Alta exitosa");                                                                                                                     
                }else{
                    sesion.setAttribute("msj", "Alta Fallida");
                }
            }else if (accion.equals("Baja")) {
                id = Integer.parseInt(request.getParameter("id"));
                id_prod = Integer.parseInt(request.getParameter("id_producto"));
                status = pu.bajaMovimiento(id);
                sesion.setAttribute("accion", "Baja");
                if (status) {                    
                    sesion.setAttribute("msj", "Baja exitosa");                                               
                    
                    //DAR DE BAJA EL DETALLE DEL MOVIMIENTO                                        
                    det.bajaDetalleMovimiento(id,id_prod);                                        
                    
                }else{
                    sesion.setAttribute("msj", "Baja Fallida");
                }
            }else if (accion.equals("Modificar")) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                movimiento = new Movimiento();
                movimiento.setId_movimiento(Integer.parseInt(request.getParameter("id")));
                movimiento.setDescripcion(request.getParameter("descripcion"));
                movimiento.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
                //movimiento.setFecha(timestamp);
                movimiento.setTipo(request.getParameter("tipo"));
                //movimiento.setId_usuario(id_usuario);
                movimiento.setId_cliente(Integer.parseInt(request.getParameter("idCliente")));
                movimiento.setOrigenDestino(request.getParameter("origenDestino"));
                movimiento.setFechaModificacion(timestamp);                                
                movimiento.setUsuarioModificacion(id_usuario);
                movimiento.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                
                status = pu.modificarMovimiento(movimiento);
                sesion.setAttribute("accion", "Modificar");                                                                        
                
                    if(movimiento.getTipo().equals("entrada")){
                        int can = movimiento.getCantidad();
                        int ex = (Integer)sesion.getAttribute("existencia");
                        int id_prodo = movimiento.getId_producto();
                        int nuevo =0;
                        
                        if(ex >= can){
                            nuevo = ex-can;
                            try{
                                con.Decrementar(nuevo, id_prodo);
                            }catch(SQLException e){                                
                            }
                        }else{
                            nuevo = can - ex;
                            try{
                                con.Incrementar(nuevo, id_prodo);
                            }catch(SQLException e){                                
                            }
                        }
                                                
                    }else if(movimiento.getTipo().equals("salida")){
                        int can = movimiento.getCantidad();
                        int ex = (Integer)sesion.getAttribute("existencia");
                        int id_prodo = movimiento.getId_producto();
                        int nuevo =0;
                        
                        if(ex >= can){
                            nuevo = ex-can;
                            try{
                                con.Decrementar(nuevo, id_prodo);
                            }catch(SQLException e){                                
                            }
                        }else{
                            nuevo = can - ex;
                            try{
                                con.Decrementar(nuevo, id_prodo);
                            }catch(SQLException e){                                
                            }
                        }
                    }
                                                      
                 //AGREGAR AL DETALLE MOVIMIENTO
                    detalle = new DetalleMovimiento();                    
                    detalle.setId_movimiento(Integer.parseInt(request.getParameter("id")));
                    detalle.setId_producto(movimiento.getId_producto());
                    detalle.setCantidad(movimiento.getCantidad());
                    detalle.setDescripcion(movimiento.getDescripcion());
                    detalle.setTipo(movimiento.getTipo());
                                                           
                    //Modificar el detalle
                    status = det.modificarMovimiento(detalle);                                        
                                                            
                    
                if (status) {
                    sesion.setAttribute("msj", "Modificacion exitosa");
                }else{
                    sesion.setAttribute("msj", "Modificacion Fallida");
                }
            }else if (accion.equals("Consultar")) {
                id = Integer.parseInt(request.getParameter("id"));
                movimiento = pu.consultarMovimiento(id);
                sesion.setAttribute("accion", "Consultar");                                
                
                if (movimiento != null) {
                    System.out.println("movimiento encontrado");
                    sesion.setAttribute("accion", "Consultar");
                    sesion.setAttribute("movimiento", movimiento);
                    sesion.setAttribute("existencia", movimiento.getCantidad());
                    sesion.setAttribute("msj", "movimiento encontrado");
                }
                else{
                    sesion.setAttribute("msj", "movimiento no encontrado");
                }
            }
            
            response.setContentType("text/html;charset=UTF-8");
                                                                                                                                                
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("formularioMovimientos.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
