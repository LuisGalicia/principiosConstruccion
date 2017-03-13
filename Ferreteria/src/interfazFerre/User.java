
package interfazFerre;

import java.io.Serializable;

/**
 *
 * @author lugad
 */
public class User implements Serializable {
    private String usuario;
    private String contraseña;
    
    public User() {
        
    }
    
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
