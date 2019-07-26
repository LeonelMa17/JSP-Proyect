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
import modelo.Producto;

/**
 *
 * @author Leonel
 */
public class ConsultasProductos {
    
    static Conectar baseDatos = new Conectar();
    
    public static LinkedList<Producto> consultarProductos() throws SQLException{
        LinkedList<Producto> listaProductos = new LinkedList<Producto>();
        Statement st;
        ResultSet rs;
        Connection c;
        Producto producto = null;
        String sql = "SELECT * FROM producto";
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()){
            producto = new Producto();                  
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setUnidad(rs.getString("unidad"));
            producto.setExistencia(rs.getInt("existencia"));
            producto.setUbicacion(rs.getString("ubicacion"));
            producto.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            producto.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
            producto.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            producto.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
            listaProductos.add(producto);
            
        }                
        c.close();
        return listaProductos;
    }
    
    public void nuevoProducto(Producto producto) throws SQLException{
        Statement st;
        Connection c;
        String sql = "INSERT INTO producto VALUES(null,'"+producto.getDescripcion()+"','"+producto.getUnidad()+"',"
                +producto.getExistencia()+",'"+producto.getUbicacion()+"','"+producto.getFechaCreacion()+"',"
                +producto.getUsuarioCreacion() + ",'"+ producto.getFechaModificacion()+"',"+ producto.getUsuarioModificacion()
                +")";
        c = baseDatos.abreBD();
        System.out.println(sql);
        st = c.createStatement();
        st.executeUpdate(sql);
        //cerrar la conexion a la base de datos
        c.close();
    }
    
    public Producto consultarP(int id) throws SQLException{
        Statement st;
        Connection c;
        ResultSet rs;
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id_producto = " + id;
        
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("despues de consulta");
        while(rs.next()){
             producto = new Producto();                  
             producto.setId_producto(rs.getInt("id_producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setUnidad(rs.getString("unidad"));
            producto.setExistencia(rs.getInt("existencia"));
            producto.setUbicacion(rs.getString("ubicacion"));
            producto.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            producto.setUsuarioCreacion(rs.getInt("usuarioCreacion"));
            producto.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            producto.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
        }
        //cerrar la conexion a la base de datos
        c.close();
        return producto;
    }
            
    public void Eliminar(int id) throws SQLException{
        Statement st;
        Connection c;
        try{
        String sql = "DELETE FROM producto WHERE id_producto = "+id;
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
        }catch(SQLException ex){
          Logger.getLogger(ConsultasProductos.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
    
    public void Actualizar(Producto producto) throws SQLException{
        Statement st;
        Connection c;
        String sql = "UPDATE producto"
                + " SET descripcion ='"+producto.getDescripcion()+"', unidad ='"+producto.getUnidad()+
                "', existencia ="+producto.getExistencia()+", ubicacion = '"+producto.getUbicacion()+"'"
                +", fechaModificacion ='"+producto.getFechaModificacion()
                +"', usuarioModificacion ="+producto.getUsuarioModificacion()+" WHERE id_producto = " + producto.getId_producto();
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
    }
    
}
