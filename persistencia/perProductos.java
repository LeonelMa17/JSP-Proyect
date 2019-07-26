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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Producto;

/**
 *
 * @author Leonel
 */
public class perProductos {
    
    Conectar baseDn = new Conectar();
    EntityManagerFactory emfn = Persistence.createEntityManagerFactory("proyecto_daePU");
    static Conectar baseD = new Conectar();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto_daePU");
    ConsultasProductos udb = new ConsultasProductos();
    
     public boolean altaProducto(Producto producto){
        boolean msj = false;
        try {    
            udb.nuevoProducto(producto);
            msj = true;
          } catch (Exception ex) {
            Logger.getLogger(perProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean bajaProducto(int id_producto){
        boolean msj = false;
        try {
            udb.Eliminar(id_producto);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean modificarProducto(Producto producto){
        boolean msj = false;
        try {
            udb.Actualizar(producto);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msj;
    }
    
    public Producto consultarProducto(int id_producto){
        Producto producto = null;
        try {
            producto = udb.consultarP(id_producto);
                    return producto;
        } catch (Exception ex) {
            Logger.getLogger(perProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
                    
    
    
}
