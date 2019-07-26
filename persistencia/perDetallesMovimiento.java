/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DetalleMovimiento;

/**
 *
 * @author Leonel
 */
public class perDetallesMovimiento {
    ConsultasDetalleMovimiento udb = new ConsultasDetalleMovimiento();
    
     public boolean altaMovimiento(DetalleMovimiento movimiento){
        boolean msj = false;
        try {    
            udb.nuevoDetalleMovimiento(movimiento);
            msj = true;
          } catch (Exception ex) {
            Logger.getLogger(perDetallesMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean bajaDetalleMovimiento(int id_movimiento, int id_producto){
        boolean msj = false;
        try {
            udb.Eliminar(id_movimiento, id_producto);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perDetallesMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean modificarMovimiento(DetalleMovimiento movimiento){
        boolean msj = false;
        try {
            udb.Actualizar(movimiento);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perDetallesMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msj;
    }
    
    public DetalleMovimiento consultarMovimiento(int id_movimiento, int id_producto){
        DetalleMovimiento movimiento = null;
        try {
            movimiento = udb.consultarM(id_movimiento, id_producto);
                    return movimiento;
        } catch (Exception ex) {
            Logger.getLogger(perMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimiento;
    }
}
