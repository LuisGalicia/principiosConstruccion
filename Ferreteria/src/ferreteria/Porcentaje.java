
package ferreteria;

/**
 * Esta clase calcula porcentajes respecto a un precio dado
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class Porcentaje {
    static final float IVA = (float) 0.16;
    static final float PORC_GANAN = (float) 0.50;
    private float total;
    /**
     * Constructor de la clase sin parámetros
     */
    public Porcentaje(){
        
    }
    /**
     * Método que calcula el precio con ganancia
     * @param precio precio del producto
     * @param cant cantidad de productos comprados
     * @return precio total con ganancia de 50%
     */
    public float calculaPre(float precio, int cant) {
        total = precio*PORC_GANAN;
        return (total + precio)*cant;
    }
    /**
     * Método que calcula el iva dado un precio y la cantidad
     * @param precio del producto a ingresar
     * @param cant de productos a vender
     * @return iva resultante
     */
    public float calculaIva(float precio, int cant) {
        total = precio*IVA;
        return total*cant;
    }
}
