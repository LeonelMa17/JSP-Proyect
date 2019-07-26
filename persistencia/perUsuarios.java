/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;
import persistencia.perUsuarios;

/**
 *
 * @author Leonel
 */
public class perUsuarios {

    Conectar baseDn = new Conectar();
    EntityManagerFactory emfn = Persistence.createEntityManagerFactory("proyecto_daePU");
    static Conectar baseD = new Conectar();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto_daePU");
    UsuarioDB udb = new UsuarioDB();
    
    public boolean altaUsuario(Usuario usuario){
        boolean msj = false;
        try {    
            udb.nuevoUsuario(usuario);
            msj = true;
          } catch (Exception ex) {
            Logger.getLogger(perUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean bajaUsuario(int id_usuario){
        boolean msj = false;
        try {
            udb.Eliminar(id_usuario);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }
    
    public boolean modificarUsuario(Usuario usuario){
        boolean msj = false;
        try {
            udb.Actualizar(usuario);
            msj = true;
        } catch (Exception ex) {
            Logger.getLogger(perUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msj;
    }
    
    public Usuario consultarUsuario(int id_usuario){
        Usuario usuario = null;
        try {
            usuario = udb.consultar(id_usuario);
                    return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(perUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}