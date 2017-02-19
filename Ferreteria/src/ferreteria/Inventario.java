
package ferreteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Esta clase guarda los productos en un archivo
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class Inventario {
    private static ArrayList<Producto> productos;
    private static File arch;
    FileOutputStream salida;
    ObjectOutputStream obj;
    FileInputStream fi;
    ObjectInputStream entra;
    
    /**
     * Constructor que inicializa archivo
     */
    public Inventario() {
        this.arch = new File("Inventario.txt");
        this.productos = new ArrayList<>();
    }
    /**
     * Metodo que guarda un objeto en un archivo
     * @param prod objeto arraylis
     */
    public void escritura(ArrayList<Producto> prod) { 
            try {
                salida = new FileOutputStream(arch);
                obj = new ObjectOutputStream(salida);
                obj.writeObject(prod);
                obj.close();
                salida.close();
                System.out.println("Inventario actualizado correctamente");
            } catch (IOException e) {
                System.out.println("No se pudo actualizar");
            } 
    }
    
    /**
     * Metodo que lee un archivo serializado
     * @return arraylist tipo producto
     */
    public ArrayList<Producto> lectura() {
        try {
            fi = new FileInputStream(arch);
            entra = new ObjectInputStream(fi);
            productos = (ArrayList<Producto>)entra.readObject();
            entra.close();
            return productos; 
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
    public void arrayAux(ArrayList<Producto> nuevo) {
        if(arch.exists()) {
            ArrayList<Producto> aux = new ArrayList<>();
            Producto produAux = new Producto();
            aux = lectura();
            //agrega productos al arrayList del archivo
            for (int i = 0; i < nuevo.size(); i++) {
                produAux = nuevo.get(i);
                aux.add(produAux);
            }
            escritura(aux);
         } else {
            escritura(nuevo);
        }
    }
}
