/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

//import ServiciosWeb.WebAlmacen_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import modelo.Usuario;
import persistencia.perUsuarios;

/**
 *
 * @author edgar
 */
@WebServlet(name = "SevUsuarios", urlPatterns = {"/SevUsuarios"})
public class SevUsuarios extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebServiceAlmacen/webAlmacen.wsdl")
    //private WebAlmacen_Service service;
    perUsuarios pu = new perUsuarios();

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
        
        HttpSession sesion = request.getSession(true);
            String accion = request.getParameter("accion");
            boolean status;
            int id;
            String nombre = "", apellidos = "", telephone = "", email = "", password = "";
            Timestamp dateC, dateU;
            int userC, userU;
            Usuario usuario;
            System.out.println("primer print: accion = "+accion);
            int id_usuario = (Integer)sesion.getAttribute("id"); 
            if (accion.equals("Alta")) {                
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                
                usuario = new Usuario();
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellidos(request.getParameter("apellidos"));
                usuario.setRol(request.getParameter("rol"));                
                usuario.setEmail(request.getParameter("email"));
                usuario.setPass(request.getParameter("password"));
                usuario.setFechaCreacion(timestamp);
                usuario.setUsuarioCreacion(id_usuario);
                usuario.setFechaModificacion(timestamp);
                usuario.setUsuarioModificacion(Integer.parseInt(request.getParameter("userU")));
                usuario.setTelefono(request.getParameter("telephone"));
                
                status = pu.altaUsuario(usuario);
                sesion.setAttribute("accion", "Alta");
                if (status) {
                    sesion.setAttribute("msj", "Alta exitosa");
                }else{
                    sesion.setAttribute("msj", "Alta Fallida");
                }
            }else if (accion.equals("Baja")) {
                id = Integer.parseInt(request.getParameter("id"));
                status = pu.bajaUsuario(id);
                sesion.setAttribute("accion", "Baja");
                if (status) {
                    sesion.setAttribute("msj", "Baja exitosa");
                }else{
                    sesion.setAttribute("msj", "Baja Fallida");
                }
            }else if (accion.equals("Modificar")) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp);
                usuario = new Usuario();
                usuario.setId_usuario(Integer.parseInt(request.getParameter("id")));
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellidos(request.getParameter("apellidos"));
                usuario.setEmail(request.getParameter("email"));
                usuario.setRol(request.getParameter("rol"));
                usuario.setTelefono(request.getParameter("telephone"));
                usuario.setEmail(request.getParameter("email"));
                usuario.setPass(request.getParameter("password"));
                usuario.setUsuarioCreacion(id_usuario);
                usuario.setFechaModificacion(timestamp);
                usuario.setUsuarioModificacion(id_usuario);
                
                status = pu.modificarUsuario(usuario);
                sesion.setAttribute("accion", "Modificar");
                if (status) {
                    sesion.setAttribute("msj", "Modificacion exitosa");
                }else{
                    sesion.setAttribute("msj", "Modificacion Fallida");
                }
            }else if (accion.equals("Consultar")) {
                id = Integer.parseInt(request.getParameter("id"));
                usuario = pu.consultarUsuario(id);
                sesion.setAttribute("accion", "Consultar");
                if (usuario != null) {
                    System.out.println("usuario encontrado");
                    sesion.setAttribute("accion", "Consultar");
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("msj", "usuario encontrado");
                }
                else{
                    sesion.setAttribute("msj", "usuario no encontrado");
                }
            }
            
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("formularioUsuarios.jsp");
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
