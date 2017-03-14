
package interfazFerre;

import ferreteria.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Clase que contiene métodos para guardar usuarios
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 13/03/2017
 */
public class ControlUser {
    
    private static ArrayList<User> usuario;
    private static File arch;
    FileOutputStream salida;
    ObjectOutputStream obj;
    FileInputStream fi;
    ObjectInputStream entra;
    
    /**
     * Constructor que inicializa archivo
     */
    public ControlUser() {
        this.arch = new File("Control de Usuario.txt");
        this.usuario = new ArrayList<>();
    }
    /**
     * Metodo que guarda un objeto en un archivo
     * @param usuarios objeto arraylis
     */
    public void escritura(ArrayList<User> usuarios) { 
            try {
                salida = new FileOutputStream(arch);
                obj = new ObjectOutputStream(salida);
                obj.writeObject(usuarios);
                obj.close();
                salida.close();
                System.out.println("Inventario actualizado correctamente");
            } catch (IOException e) {
                System.out.println("No se pudo actualizar");
            } 
    }
    
    /**
     * Metodo que lee un archivo serializado
     * @return arraylist tipo User
     */
    public ArrayList<User> lectura() {
        try {
            fi = new FileInputStream(arch);
            entra = new ObjectInputStream(fi);
            usuario = (ArrayList<User>)entra.readObject();
            entra.close();
            return usuario; 
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en deserializacion");
            return null;
        } 
    }
    /**
     * Método que almacena datos de un nuevo arrayList dentro de un arrayList
     * almacenado
     * @param nuevo ArrayList para agregar
     */
    public void arrayAux(ArrayList<User> nuevo) {
        if(arch.exists()) {
            ArrayList<User> aux = new ArrayList<>();
            User userAux = new User();
            aux = lectura();
            //agrega productos al arrayList del archivo
            for (int i = 0; i < nuevo.size(); i++) {
                userAux = nuevo.get(i);
                aux.add(userAux);
            }
            escritura(aux);
         } else {
            escritura(nuevo);
        }
    }
    
    /**
     * Método que crea usuarios
     */
    public void CreaUsuarios() {
        ArrayList<User> guarda = new ArrayList<>();
        User ad = new User("administrados", "12345");
        User em = new User("empleado", "12345");
        
        guarda.add(ad);
        guarda.add(em);
        arrayAux(guarda);
    }
}
