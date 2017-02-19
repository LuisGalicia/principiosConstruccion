package ferreteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase define productos con atributos serializables
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class Producto implements Serializable{
    //campos de la clase
    private String nombre;
    private String clave;
    private String descripcion;
    private String tipoUnidad;
    private int existencia;
    private float precio;
    /**
     * Constructor vacío para producto
     */
    public Producto() {
        
    }
    /**
     * Constructor que recibe todo los campos de producto
     */
    public Producto(String nombre, String clave, String descripcion, 
            String tipoUnidad, int existencia, float precio) {
        this.nombre = nombre;
        this.clave = clave;
        this.descripcion = descripcion;
        this.tipoUnidad = tipoUnidad;
        this.existencia = existencia;
        this.precio = precio;
    }
    /**
     * Devuelve el nombre del producto
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre de un producto
     * @param nombre nuevo del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Devuelve la clave del producto
     * @return clave del producto
     */
    public String getClave() {
        return clave;
    }
    /**
     * Modifica la clave de un producto
     * @param clave nueva del producto
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    /**
     * Devuelve la descripción del producto
     * @return la descripcion del producto
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Modifica la descripcion de un producto
     * @param descripcion nueva del producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Devuelve el tipo de unidad del producto
     * @return Tipo de unidad
     */
    public String getTipoUnidad() {
        return tipoUnidad;
    }
    /**
     * Modifica el tipo de unidad de un producto
     * @param tipoUnidad nueva del producto
     */
    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    /**
     * Devuelve la existencia del producto
     * @return existencia del producto
     */
    public int getExistencia() {
        return existencia;
    }
    /**
     * Modifica la existencia de un producto
     * @param existencia nueva del producto
     */
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    /**
     * Devuelve el precio del producto
     * @return precio del producto
     */
    public float getPrecio() {
        return precio;
    }
    /**
     * Muestra el precio de un producto
     * @param precio nuevo del producto
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
