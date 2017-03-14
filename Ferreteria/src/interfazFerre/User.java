
package interfazFerre;

import java.io.Serializable;

/**
 * Clase que define la usuarios con atributos serializables
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 13/03/2017
 */
public class User implements Serializable {
    private String usuario;
    private String contraseña;
    
    /**
     * Constructor vacío
     */
    public User() {
        
    }
    
    /**
     * Constructor que recibe todos los atributos de un usuario
     * @param usuario usuario
     * @param contraseña contraseña
     */
    public User(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
