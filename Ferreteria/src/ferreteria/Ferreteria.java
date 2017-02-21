
package ferreteria;

/**
 * Clase principal de ferretería
 * @author Luis Galicia
 */
public class Ferreteria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op;
        do {
        MenuOp me = new MenuOp();
        me.opciones();
        op = me.opci();
        me.ejecuta(op);
        } while (op != 10);
    }
    
}
