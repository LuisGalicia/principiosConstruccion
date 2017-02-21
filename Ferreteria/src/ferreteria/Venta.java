
package ferreteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase almacena las ventas realizadas en un archivo
 * @author Luis Galicia
 * @version 1.0
 */
public class Venta {
    private static ArrayList<Producto> productos;
    private static File arch;
    FileOutputStream salida;
    ObjectOutputStream obj;
    FileInputStream fi;
    ObjectInputStream entra;
    /**
     * Constructor que inicializa el archivo
     */
    public Venta() {
        Venta.arch = new File("Ventas.txt");
    }
    /**
     * Metodo que guarda un objeto en un archivo
     * @param prod objeto arraylis
     */
    public void escrituraVenta(ArrayList<Producto> prod) { 
            try {
                salida = new FileOutputStream(arch);
                obj = new ObjectOutputStream(salida);
                obj.writeObject(prod);
                obj.close();
                salida.close();
                System.out.println("Ventas actualizadas correctamente");
            } catch (IOException e) {
                System.out.println("No se pudo actualizar");
            } 
    }
    
    /**
     * Metodo que lee un archivo serializado
     * @return arraylist tipo producto
     */
    public ArrayList<Producto> lecturaVenta() {
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
     * Método que agrega productos vendidos al archivo Ventas
     * @param produ producto a agregar
     * @param vendido existencias vendidas 
     */
    public void agregaVenta(Producto produ, int vendido) {
        ArrayList<Producto> arrPro = new ArrayList<>();
        boolean exis = false;
        
        if(arch.exists()) {
            arrPro = lecturaVenta();
            //busca si el producto ya se ha vendido
            for (int i = 0; i < arrPro.size(); i++) {
                if(produ.getClave().equals(arrPro.get(i).getClave())) {
                    //agrega existencias a las ventas
                    arrPro.get(i).setExistencia(
                    arrPro.get(i).getExistencia()+vendido);
                    exis = true;
            }  
            }
            if(exis){
                escrituraVenta(arrPro);
            } else {
                produ.setExistencia(vendido);
                arrPro.add(produ);
                escrituraVenta(arrPro);
            }
        } else {
            produ.setExistencia(vendido);
            arrPro.add(produ);
            escrituraVenta(arrPro);
        }
    }
    /**
     * Método que muestra las ventas realizadas
     */
    public void muestraVentas() {
        ArrayList<Producto> modifica = new ArrayList<>();
        modifica =  lecturaVenta();
        if(modifica.isEmpty()){
            System.out.println("No se han realizado ventas");
        }
        Collections.sort(modifica, new Compara());
        
        for (int i = 0; i < modifica.size(); i++) {
            System.out.println((i+1)+".NOMBRE:" + modifica.get(i).getNombre() +
                    " CLAVE:" + modifica.get(i).getClave() + 
                    "\n DESCRIPCIÓN:" + modifica.get(i).getDescripcion() + 
                    "\n TIPO DE UNIDAD:" + modifica.get(i).getTipoUnidad() +
                    " Vendidos:" + modifica.get(i).getExistencia() + 
                    " PRECIO:" + modifica.get(i).getPrecio());
        }
    }
}

