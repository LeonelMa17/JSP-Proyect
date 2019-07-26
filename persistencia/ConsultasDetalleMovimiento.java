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
import modelo.DetalleMovimiento;
import modelo.Movimiento;

/**
 *
 * @author Leonel
 */
public class ConsultasDetalleMovimiento {
    
    static Conectar baseDatos = new Conectar();
    
    public static LinkedList<DetalleMovimiento> consultarDetallesMovimientos(int id_cliente) throws SQLException{
        LinkedList<DetalleMovimiento> listaDetalleMovimiento = new LinkedList<DetalleMovimiento>();
        Statement st;
        ResultSet rs;
        Connection c;
        DetalleMovimiento detallemovimiento = null;
        String sql = "SELECT * FROM movimiento WHERE id_cliente = " + id_cliente;
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()){
            detallemovimiento = new DetalleMovimiento();                  
            detallemovimiento.setId_movimiento(rs.getInt("id_movimiento"));
            detallemovimiento.setId_producto(rs.getInt("id_producto"));
            detallemovimiento.setCantidad(rs.getInt("cantidad"));
            detallemovimiento.setDescripcion(rs.getString("descripcion"));
            detallemovimiento.setTipo(rs.getString("tipo"));
            listaDetalleMovimiento.add(detallemovimiento);
            
        }                
        c.close();
        return listaDetalleMovimiento;
    }
    
    public void nuevoDetalleMovimiento(DetalleMovimiento movimiento) throws SQLException{
        Statement st;
        Connection c;
        String sql = "INSERT INTO detallesmovimiento VALUES("+movimiento.getId_movimiento()+","
                +movimiento.getId_producto()+","+movimiento.getCantidad()+",'"
                +movimiento.getDescripcion()+"','"+movimiento.getTipo()+"')";
        c = baseDatos.abreBD();
        System.out.println(sql);
        st = c.createStatement();
        st.executeUpdate(sql);
        //cerrar la conexion a la base de datos
        c.close();
    }
    
    public DetalleMovimiento consultarM(int id_mov, int id_prod) throws SQLException{
        Statement st;
        Connection c;
        ResultSet rs;
        DetalleMovimiento detallemovimiento = null;
        String sql = "SELECT * FROM detallesmovimiento WHERE id_movimiento = " + id_mov + " and id_producto = " + id_prod;
        
        c = baseDatos.abreBD();
        st = c.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("despues de consulta");
        while(rs.next()){
             detallemovimiento = new DetalleMovimiento();                  
            detallemovimiento.setId_movimiento(rs.getInt("id_movimiento"));
            detallemovimiento.setId_producto(rs.getInt("id_producto"));
            detallemovimiento.setCantidad(rs.getInt("cantidad"));
            detallemovimiento.setDescripcion(rs.getString("descripcion"));
            detallemovimiento.setTipo(rs.getString("tipo"));
        }
        //cerrar la conexion a la base de datos
        c.close();
        return detallemovimiento;
    }
            
    public void Eliminar(int id_mov, int id_prod) throws SQLException{
        Statement st;
        Connection c;
        try{
        String sql = "DELETE FROM detallesmovimiento WHERE id_movimiento = "+id_mov+ " and id_producto = "+ id_prod;
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
        }catch(SQLException ex){
          Logger.getLogger(ConsultasDetalleMovimiento.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
    
    public void Actualizar(DetalleMovimiento movimiento) throws SQLException{
        Statement st;
        Connection c;
        String sql = "UPDATE detallesmovimiento"
                + " SET cantidad ="+movimiento.getCantidad()+","
                + " descripcion ='"+movimiento.getDescripcion()+"', tipo ='"+movimiento.getTipo()+"'"
                +  " WHERE id_movimiento = " + movimiento.getId_movimiento() + " and id_producto= " + movimiento.getId_producto();
        c = baseDatos.abreBD();
        st=c.createStatement();
        st.executeUpdate(sql);
        c.close();
    }
    
}
