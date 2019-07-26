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
import modelo.Movimiento;

/**
 *
 * @author edgar
 */
public class perMovimiento {
    
Conectar baseDn = new Conectar();
    EntityManagerFactory emfn = Persistence.createEntityManagerFactory("proyecto_daePU");
    static Conectar baseD = new Conectar();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto_daePU");
    ConsultasMovimientos udb = new ConsultasMovimientos();
           
    public boolean altaMovimiento(Movimiento movimiento){
        boolean msj = false;
        try {    
            udb.nuevoMovimiento(movimiento);
            msj = true;
          } catch (Exception ex) {
            Logger.getLogger(perMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean bajaMovimiento(int id_movimiento){
        boolean msj = false;
        try {
            udb.Eliminar(id_movimiento);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean modificarMovimiento(Movimiento movimiento){
        boolean msj = false;
        try {
            udb.Actualizar(movimiento);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msj;
    }
    
    public Movimiento consultarMovimiento(int id_movimiento){
        Movimiento movimiento = null;
        try {
            movimiento = udb.consultarM(id_movimiento);
                    return movimiento;
        } catch (SQLException ex) {
            Logger.getLogger(perMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimiento;
    }
    
    
}
