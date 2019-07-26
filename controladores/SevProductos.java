/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Movimiento;
import modelo.Producto;
import persistencia.perProductos;

/**
 *
 * @author Leonel
 */
@WebServlet(name = "SevProductos", urlPatterns = {"/SevProductos"})
public class SevProductos extends HttpServlet {
    
    perProductos pu = new perProductos();

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
            int id;
            Producto producto;
            
            int id_usuario = (Integer)sesion.getAttribute("id");
            
            System.out.println("primer print: accion = "+accion);
            if (accion.equals("Alta")) {                
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                
                producto = new Producto();
                producto.setId_producto(Integer.parseInt(request.getParameter("id")));
                producto.setDescripcion(request.getParameter("descripcion"));
                producto.setUnidad(request.getParameter("unidad"));
                producto.setExistencia(Integer.parseInt(request.getParameter("existencia")));
                producto.setUbicacion(request.getParameter("ubicacion"));
                producto.setFechaCreacion(timestamp);
                producto.setUsuarioCreacion(id_usuario); 
                producto.setFechaModificacion(timestamp);
                producto.setUsuarioModificacion(id_usuario);                                
                
                status = pu.altaProducto(producto);
                sesion.setAttribute("accion", "Alta");
                if (status) {
                    sesion.setAttribute("msj", "Alta exitosa");
                }else{
                    sesion.setAttribute("msj", "Alta Fallida");
                }
            }else if (accion.equals("Baja")) {
                id = Integer.parseInt(request.getParameter("id"));
                status = pu.bajaProducto(id);
                sesion.setAttribute("accion", "Baja");
                if (status) {
                    sesion.setAttribute("msj", "Baja exitosa");
                }else{
                    sesion.setAttribute("msj", "Baja Fallida");
                }
            }else if (accion.equals("Modificar")) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                producto = new Producto();                
                producto.setId_producto(Integer.parseInt(request.getParameter("id")));
                producto.setDescripcion(request.getParameter("descripcion"));
                producto.setUnidad(request.getParameter("unidad"));                                                
                producto.setExistencia(Integer.parseInt(request.getParameter("existencia")));                    
                producto.setUbicacion(request.getParameter("ubicacion"));                
                producto.setFechaModificacion(timestamp);                                
                producto.setUsuarioModificacion(id_usuario);                
                
                status = pu.modificarProducto(producto);
                sesion.setAttribute("accion", "Modificar");
                if (status) {
                    sesion.setAttribute("msj", "Modificacion exitosa");
                }else{
                    sesion.setAttribute("msj", "Modificacion Fallida");
                }
            }else if (accion.equals("Consultar")) {
                id = Integer.parseInt(request.getParameter("id"));
                producto = pu.consultarProducto(id);
                sesion.setAttribute("accion", "Consultar");                                
                
                if (producto != null) {
                    System.out.println("producto encontrado");
                    sesion.setAttribute("accion", "Consultar");
                    sesion.setAttribute("producto", producto);
                    sesion.setAttribute("msj", "producto encontrado");
                }
                else{
                    sesion.setAttribute("msj", "producto no encontrado");
                }
            }
            
            response.setContentType("text/html;charset=UTF-8");
            
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("formularioProductos.jsp");
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
