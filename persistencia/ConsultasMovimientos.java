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
import modelo.Movimiento;

/**
 *
 * @author Leonel
 */
public class ConsultasMovimientos {
    
     static Conectar baseDatos = new Conectar();
     
     public static void RealizarBusqueda(int id) throws SQLException{
        Statement st;
        ResultSet rs;
        Connection c;
        String sql = "SELECT * FROM producto INNER JOIN movimiento on producto.id_producto = movimiento.id_producto"
                + "WHERE movimiento.id_movimiento = " + id ;
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
     }
     
     public static void Incrementar(int cant, int id_prod) throws SQLException{
         Statement st;
        ResultSet rs;
        Connection c;
        String sql = "UPDATE producto SET existencia = existencia +" + cant +
                " WHERE id_producto = " + id_prod; 
                
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
     }
     
     public int Ultimo() throws SQLException{
          Statement st;
        ResultSet rs;
        Connection c;
        Movimiento movimiento=null;
        String sql = "SELECT MAX(id_movimiento) as id_movimiento from movimiento"; 
                
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        
         while(rs.next()){
            movimiento = new Movimiento();  
            movimiento.setId_movimiento(rs.getInt("id_movimiento"));       
        }     
        c.close();
        return movimiento.getId_movimiento();
     }
     
      public static void Decrementar(int cant, int id_prod) throws SQLException{
         Statement st;
        ResultSet rs;
        Connection c;
        String sql = "UPDATE producto SET existencia = existencia -" + cant + 
                " WHERE id_producto = " + id_prod;                        
        c = baseDatos.abreBD(); 
        st=c.createStatement();
        st.executeUpdate(sql);
        System.out.print("AQUI SE EJUTA EL UPDATE");
        c.close();
     }
    
    public static LinkedList<Movimiento> consultarMovimientos() throws SQLException{
        LinkedList<Movimiento> listaMovimientos = new LinkedList<Movimiento>();
        Statement st;
        ResultSet rs;
        Connection c;
        Movimiento movimiento = null;
        String sql = "SELECT * FROM movimiento";
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()){
            movimiento = new Movimiento();                  
            movimiento.setDescripcion(rs.getString("descripcion"));
            movimiento.setFecha(rs.getTimestamp("fecha"));
            movimiento.setTipo(rs.getString("tipo"));
            movimiento.setId_usuario(rs.getInt("id_usuario"));
            movimiento.setId_cliente(rs.getInt("id_cliente"));
            movimiento.setOrigenDestino(rs.getString("origenDestino"));
            movimiento.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            movimiento.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
            movimiento.setCantidad(rs.getInt("cantidad"));
            movimiento.setId_producto(rs.getInt("producto"));
            listaMovimientos.add(movimiento);
            
        }                
        c.close();
        return listaMovimientos;
    }
    
    public void nuevoMovimiento(Movimiento movimiento) throws SQLException{
        Statement st;
        Connection c;
        String sql = "INSERT INTO movimiento VALUES(null,'"+movimiento.getDescripcion()+"','"+movimiento.getFecha()+"','"
                +movimiento.getTipo()+"',"+movimiento.getId_usuario()+","+movimiento.getId_cliente()+",'"
                +movimiento.getOrigenDestino()+"','"+movimiento.getFechaModificacion()+"',"+movimiento.getUsuarioModificacion()
                +","+movimiento.getCantidad()+","+movimiento.getId_producto()+")";
        c = baseDatos.abreBD();
        System.out.println(sql);
        st = c.createStatement();
        st.executeUpdate(sql);
        //cerrar la conexion a la base de datos
        c.close();
    }
    
    public Movimiento consultarM(int id) throws SQLException{
        Statement st;
        Connection c;
        ResultSet rs;
        Movimiento movimiento = null;
        String sql = "SELECT * FROM movimiento WHERE id_movimiento = " + id;
        
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("despues de consulta");
        while(rs.next()){
             movimiento = new Movimiento();                  
            movimiento.setId_movimiento(id);
            movimiento.setDescripcion(rs.getString("descripcion"));
            movimiento.setFecha(rs.getTimestamp("fecha"));
            movimiento.setTipo(rs.getString("tipo"));
            movimiento.setId_usuario(rs.getInt("id_usuario"));
            movimiento.setId_cliente(rs.getInt("id_cliente"));
            movimiento.setOrigenDestino(rs.getString("origenDestino"));
            movimiento.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
            movimiento.setUsuarioModificacion(rs.getInt("usuarioModificacion"));
            movimiento.setCantidad(rs.getInt("cantidad"));
            movimiento.setId_producto(rs.getInt("id_producto"));
        }
        //cerrar la conexion a la base de datos
        c.close();
        return movimiento;
    }
            
    public void Eliminar(int id) throws SQLException{
        Statement st;
        Connection c;
        try{
        String sql = "DELETE FROM movimiento WHERE id_movimiento = "+id;
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
        }catch(SQLException ex){
          Logger.getLogger(ConsultasMovimientos.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
    
    public void Actualizar(Movimiento movimiento) throws SQLException{
        Statement st;
        Connection c;
        String sql = "UPDATE movimiento"
                + " SET descripcion ='"+movimiento.getDescripcion()+"',"
                + " tipo ='"+movimiento.getTipo()+"', id_cliente ="+movimiento.getId_cliente()+
                ", origenDestino ='"+movimiento.getOrigenDestino()+"',fechaModificacion ='"+movimiento.getFechaModificacion()
                +"', usuarioModificacion ="+movimiento.getUsuarioModificacion()+","
                + " cantidad = "+movimiento.getCantidad()+", id_producto = "+movimiento.getId_producto()+ " WHERE id_movimiento = " + movimiento.getId_movimiento();
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
    }
    
}
