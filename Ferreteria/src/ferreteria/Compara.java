
package ferreteria;

import java.util.Comparator;

/**
 * Clase que sobreescribe el método compare para poder recibir objeto tipo
 * producto
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class Compara implements Comparator<Producto>{

    @Override
    public int compare(Producto o1, Producto o2) {
        return o1.getNombre().compareToIgnoreCase(o2.getNombre());
    }
    
}
