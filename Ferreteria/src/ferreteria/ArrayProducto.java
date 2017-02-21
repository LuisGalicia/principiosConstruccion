
package ferreteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Esta clase permite realizar acciones sobre el arreglo del producto
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class ArrayProducto implements Serializable{
    //campos del array
    private String nombre;
    private String clave;
    private String descripcion;
    private String tipoUnidad;
    private int existencia;
    private float precio;
    private int aux;
    private ArrayList<Producto> modifica;
    private Producto guarda;
    Scanner sc = new Scanner(System.in);
    private Inventario inv = new Inventario();
    
    /**
     * Constructor de array de productos inicializando el ArrayList
     */
    public ArrayProducto() {
        this.modifica = new ArrayList<>();
    }
    /**
     * Método que agrega productos al inventario
     */
    public void agrega() {
        do {
            System.out.println("Ingresa el nombre del producto:");
            nombre = sc.nextLine();
            System.out.println("Ingresa la clave del producto:");
            clave = sc.nextLine();
            System.out.println("Ingresa la descripción del producto:");
            descripcion = sc.nextLine();
            System.out.println("Ingresa el tipo de unidad:");
            tipoUnidad = sc.nextLine();
            System.out.println("Ingresa el número de existencias:");
            existencia = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingresa el precio:");
            precio = sc.nextFloat();
            
            guarda = new Producto(nombre, clave, descripcion, tipoUnidad,
            existencia, precio);
            modifica.add(guarda); //agrega productos al arraylist
            
            System.out.println("Producto agregado adecuadamente");
            System.out.println("¿Deseas agregar otro producto? (1).Si (2).No");
            aux = sc.nextInt();
            sc.nextLine();
        } while(aux == 1);
        inv.arrayAux(modifica); //comprueba que exista o no un archivo
        
        
    }
    /**
     * Método que muestra todos los productos del inventario
     */
    public void muestra() {
        modifica =  inv.lectura();
        if(modifica.isEmpty()){
            System.out.println("Inventario vacio");
        }
        Collections.sort(modifica, new Compara());
        
        for (int i = 0; i < modifica.size(); i++) {
            System.out.println((i+1)+".NOMBRE:" + modifica.get(i).getNombre() +
                    " CLAVE:" + modifica.get(i).getClave() + 
                    "\n DESCRIPCIÓN:" + modifica.get(i).getDescripcion() + 
                    "\n TIPO DE UNIDAD:" + modifica.get(i).getTipoUnidad() +
                    " EXISTENCIA:" + modifica.get(i).getExistencia() + 
                    " PRECIO:" + modifica.get(i).getPrecio());
        }
    }
    /**
     * Método que elimina un producto dado su nombre
     * @param name
     */
    public void elimina(String name) {
        modifica = inv.lectura();
        boolean existe = false;
        for (int i = 0; i < modifica.size(); i++) {
            if(modifica.get(i).getNombre().equals(name)) { 
                modifica.remove(i);
                existe = true;
            }
        }
        if(existe){
            inv.escritura(modifica);
            System.out.println("Se eliminó correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
    /**
     * Método que edita un producto dado su nombre
     * @param name 
     */
    public void edita(String name) {
        String almacena;
        int opcion;
        int cantidad;
        float prec;
        boolean existe = false;
        MenuOp mE = new MenuOp();
        modifica = inv.lectura();
        for (int i = 0; i < modifica.size(); i++) {
            if(modifica.get(i).getNombre().equals(name)){
                existe = true;
                do {
                    mE.menuEditar();
                    opcion = mE.opci();
                    switch(opcion) {
                        case 1:
                            System.out.println("Nuevo nombre: ");
                            almacena = sc.nextLine();
                            modifica.get(i).setNombre(almacena);
                            System.out.println("llego");
                            break;
                        case 2:
                            System.out.println("Nueva clave: ");
                            almacena = sc.nextLine();
                            modifica.get(i).setClave(almacena);
                            break;
                        case 3:
                            System.out.println("Nueva descripción: ");
                            almacena = sc.nextLine();
                            modifica.get(i).setDescripcion(almacena);
                            break;
                        case 4:
                            System.out.println("Nuevo tipo de unidad: ");
                            almacena = sc.nextLine();
                            modifica.get(i).setTipoUnidad(almacena);
                            break;
                        case 5:
                            System.out.println("Nueva existencia: ");
                            cantidad = sc.nextInt();
                            sc.nextLine();
                            modifica.get(i).setExistencia(cantidad);
                            sc.nextLine();
                            break;
                        case 6:
                            System.out.println("Nuevo precio: ");
                            prec = sc.nextFloat();
                            modifica.get(i).setPrecio(prec);
                            break;
                        case 7:
                            System.out.println("Edición terminada");
                            break;
                        default:
                            System.out.println("Opción no válida .-.");
                            break;
                    } //fin SWITCH
                } while (opcion !=7); //fin DO
            } //fin IF
        } //fin FOR
        if(existe){
            inv.escritura(modifica);
            System.out.println("Se editó correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
        
    } //fin METODO
    
    /**
     * Permite al usuario buscar un producto por clave o nombre de producto
     * @param name nombre o clave ingresada por el usuario
     */
    public void busca(String name) {
        boolean existe = false;
        modifica = inv.lectura();
        for (int i = 0; i < modifica.size(); i++) {
            if(modifica.get(i).getNombre().equals(name) || 
                    modifica.get(i).getClave().equals(name)) {
                existe = true;
                 System.out.println("Nombre: " + modifica.get(i).getNombre() +
                    " Clave: " + modifica.get(i).getClave() + 
                    " Descripción: " + modifica.get(i).getDescripcion() + 
                    " Tipo de Unidad: " + modifica.get(i).getTipoUnidad() +
                    " Existencia: " + modifica.get(i).getExistencia() + 
                    " Precio: " + modifica.get(i).getPrecio());   
            } 
        }
        if(!existe){
            System.out.println("Producto no encontrado");
        }
    }
    /**
     * Método que realiza venta de producto(s)
     * @param clave clave del producto a vender
     */
    public void venta(String clave) {
        int pos = 0;
        int vende = 0;
        float tPrecio = 0;
        float tIva = 0;
        boolean existe = false;
        modifica = inv.lectura();
        Porcentaje ptje = new Porcentaje();
        Venta vendido = new Venta();
        for (int i = 0; i < modifica.size(); i++) {
            if(modifica.get(i).getClave().equals(clave)) {
                //muestra datos
                System.out.println("Nombre: "+ modifica.get(i).getNombre()
                        + "  Clave: "+ modifica.get(i).getClave()
                        + "  Existencias: "+ modifica.get(i).getExistencia()+"\n"
                        + "Descripción: "+ modifica.get(i).getDescripcion()+"\n"
                        + "Precio por "+ modifica.get(i).getTipoUnidad()+ ": "
                        + ptje.calculaPre(modifica.get(i).getPrecio(), 1)
                        + "     Iva:"+
                        + ptje.calculaIva(modifica.get(i).getPrecio(), 1));
                pos = i;
                existe = true;
            }
        }
        //entra solo si existe el producto
        if(existe) {
            if(modifica.get(pos).getExistencia() == 0) {
                System.out.println("No hay existencias");
            } else {
                System.out.println("¿Cuántas existencias venderás?...");
                vende = sc.nextInt();
                
                if(vende <= modifica.get(pos).getExistencia()) {
                    //guarda el precio de venta
                    tPrecio = ptje.calculaPre(modifica.get(pos).getPrecio(), vende);
                    //guarda el precio del IVA del producto
                    tIva = ptje.calculaIva(modifica.get(pos).getPrecio(), vende);
                    System.out.println("Precio total: "+ tPrecio
                            + "     Iva total: "+ tIva+"\n"
                            + "Total a pagar: "+ (tPrecio+tIva));
                    modifica.get(pos).setExistencia( //actualiza las existencias
                            modifica.get(pos).getExistencia()-vende);
                    inv.escritura(modifica);
                    vendido.agregaVenta(modifica.get(pos), vende);
                } else {
                    System.out.println("No hay suficiente producto en existencia.");
                }
            }
        } else {
            System.out.println("Producto no encontrado :/");
        }
    }
    /**
     * Método que agrega existencias al archivo
     * @param clv clave del producto
     */
    public void agregarExistencia(String clv) {
        int agrega = 0;
        boolean entra = false;
        modifica = inv.lectura();
        for (int i = 0; i < modifica.size(); i++) {
            if(modifica.get(i).getClave().equals(clv)) {
                System.out.println((i+1)+".NOMBRE:" + 
                        modifica.get(i).getNombre() +
                    " CLAVE:" + modifica.get(i).getClave() + 
                    "\n DESCRIPCIÓN:" + modifica.get(i).getDescripcion() + 
                    "\n TIPO DE UNIDAD:" + modifica.get(i).getTipoUnidad() +
                    " EXISTENCIA:" + modifica.get(i).getExistencia() + 
                    " PRECIO:" + modifica.get(i).getPrecio());
                System.out.println("\n ¿Qué cantidad de "+
                        modifica.get(i).getTipoUnidad()+ " vas a agregar?");
                agrega = sc.nextInt();
                modifica.get(i).setExistencia( //modifica las existencias
                modifica.get(i).getExistencia()+agrega);
                entra = true;
            } 
        }
        if(true) {
            inv.escritura(modifica);
            System.out.println("Se agregaron existencias");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
}//fin de la clase

