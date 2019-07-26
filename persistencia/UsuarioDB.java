/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author Leonel
 */
public class UsuarioDB {
    static Conectar baseDatos = new Conectar();
    
    public static LinkedList<Usuario> consultarUsuarios() throws SQLException{
        LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
        Statement st;
        ResultSet rs;
        Connection c;
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario";
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()){
            usuario = new Usuario();                  
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setRol(rs.getString("rol"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPass(rs.getString("pass"));
            usuario.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            usuario.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
            usuario.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            usuario.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
            listaUsuarios.add(usuario);
            
        }                
        c.close();
        return listaUsuarios;
    }
    
    public void nuevoUsuario(Usuario usuario) throws SQLException{
        Statement st;
        Connection c;
        String sql = "INSERT INTO usuario VALUES(null,'"+usuario.getNombre()+"','"+usuario.getApellidos()+"','"
                +usuario.getRol()+"','"+usuario.getEmail()+"','"
                +usuario.getPass() + "','" + usuario.getFechaCreacion()+"',"
                +usuario.getUsuarioCreacion()+",'"+usuario.getFechaModificacion()+"',"
                +usuario.getUsuarioModificacion()+",'"+usuario.getTelefono()+"')";
        c = baseDatos.abreBD();
        System.out.println(sql);
        st = c.createStatement();
        st.executeUpdate(sql);
        //cerrar la conexion a la base de datos
        c.close();
    }
    
    public Usuario consultarU(String email) throws SQLException{
        Statement st;
        Connection c;
        ResultSet rs;
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = '"+email+"'";
        
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("despues de consulta");
        while(rs.next()){
            usuario = new Usuario();
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setRol(rs.getString("rol"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPass(rs.getString("pass"));
            usuario.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            usuario.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
            usuario.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            usuario.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
        }
        //cerrar la conexion a la base de datos
        c.close();
        return usuario;
    }
    
    public Usuario consultar(int id_usuario) throws SQLException{
        Statement st;
        Connection c;
        ResultSet rs;
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id_usuario = "+id_usuario;
        
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("despues de consulta");
        while(rs.next()){
            usuario = new Usuario();
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setRol(rs.getString("rol"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPass(rs.getString("pass"));
            usuario.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            usuario.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
            usuario.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            usuario.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
        }
        //cerrar la conexion a la base de datos
        c.close();
        return usuario;
    }
    
    public void Eliminar(int id) throws SQLException{
        Statement st;
        Connection c;
        try{
        String sql = "DELETE FROM usuario WHERE id_usuario = "+id;
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
        }catch(SQLException ex){
          Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
    
    public void Actualizar(Usuario usuario) throws SQLException{
        Statement st;
        Connection c;
        String sql = "UPDATE usuario"
                + " SET nombre ='"+usuario.getNombre()+"', apellidos ='"+usuario.getApellidos()+
                "', rol ='"+usuario.getRol()+"', email ='"+usuario.getEmail()+
                "',pass ='"+usuario.getPass()+"',usuarioCreacion ="+usuario.getUsuarioCreacion()
                +",fechaModificacion ='"+usuario.getFechaModificacion()+"'"
                + ",usuarioModificacion ="+usuario.getUsuarioModificacion()+",telefono = '"+usuario.getTelefono()+"'"+
                " WHERE id_usuario = " + usuario.getId_usuario();
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
    }
}
