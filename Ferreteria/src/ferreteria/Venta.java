
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
     * Método que agrega productos vendidos al archivo Ventas y los registra
     * por día 
     * @param produ producto a agregar
     * @param vendido existencias vendidas 
     */
    public void agregaVenta(Producto produ, int vendido) {
        int ulPosi = 0;
        boolean exis = false;
        ArrayList<Producto> arrPro = new ArrayList<>();
        Fecha feActual = new Fecha();
        
        produ.setDia(feActual.getDia());
        produ.setMes(feActual.getMes());
        produ.setAnio(feActual.getAnio());
        produ.setHora(feActual.getHora());
        produ.setMinuto(feActual.getMinuto());
        
        if(arch.exists()) {
            arrPro = lecturaVenta();
            ulPosi = arrPro.size()-1;    //última posición del arrayList
            //compara si la fecha es igual a la última fecha registrada
            if(produ.getDia() == arrPro.get(ulPosi).getDia() && 
                    produ.getMes() == arrPro.get(ulPosi).getMes()) {
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
        
        if (modifica.isEmpty()) {
            System.out.println("No se han realizado ventas");
        }
        
        Collections.sort(modifica, new Compara());
        
        for (int i = 0; i < modifica.size(); i++) {
            System.out.println((i+1)+".NOMBRE:" + modifica.get(i).getNombre() +
                    " CLAVE:" + modifica.get(i).getClave() + 
                    "\n DESCRIPCIÓN:" + modifica.get(i).getDescripcion() + 
                    "\n TIPO DE UNIDAD:" + modifica.get(i).getTipoUnidad() +
                    " Vendidos:" + modifica.get(i).getExistencia() + 
                    " PRECIO:" + modifica.get(i).getPrecio() + 
                    " Fecha:" + modifica.get(i).getDia() + "/" +
                    modifica.get(i).getMes() + "/" + modifica.get(i).getAnio() +
                    "   " + modifica.get(i).getHora() + ":" + 
                    modifica.get(i).getMinuto());
        }
    }
    
    /**
     * Método que calcula las ventas por día y las ganancias de ese día
     * @param dia día en que se realizó la venta 
     * @param mes mes en que se realizó la venta
     */
    public void ventaPorDia(int dia, int mes) {
        float preIva = 0;
        float prePro = 0;
        float preGan = 0;
        float precioTotal = 0;
        float ganancia = 0;
        boolean existe = false;
        ArrayList<Producto> modifica = new ArrayList<>();
        Porcentaje po = new Porcentaje();
        modifica =  lecturaVenta();
        
        if (modifica.isEmpty()) {
            System.out.println("No se han realizado ventas");
        }
        
        Collections.sort(modifica, new Compara());
        
        for (int i = 0; i < modifica.size(); i++) {
            //entra solo en las ventas realizadas el dia y mes indicados
            if(modifica.get(i).getDia() == dia && 
                modifica.get(i).getMes() == mes){
                
                System.out.println((i+1)+
                    ".NOMBRE:" + modifica.get(i).getNombre() +
                    " CLAVE:" + modifica.get(i).getClave() + 
                    "\n DESCRIPCIÓN:" + modifica.get(i).getDescripcion() + 
                    "\n TIPO DE UNIDAD:" + modifica.get(i).getTipoUnidad() +
                    " Vendidos:" + modifica.get(i).getExistencia() + 
                    " PRECIO:" + modifica.get(i).getPrecio() + 
                    " Fecha:" + modifica.get(i).getDia() + "/" +
                    modifica.get(i).getMes() + "/" + modifica.get(i).getAnio() +
                    "   " + modifica.get(i).getHora() + ":" + 
                    modifica.get(i).getMinuto());
                preIva = po.calculaIva(modifica.get(i).getPrecio(),
                        modifica.get(i).getExistencia());
                prePro = po.calculaPre(modifica.get(i).getPrecio(),
                        modifica.get(i).getExistencia());
                precioTotal = precioTotal + preIva + prePro;
                ganancia = ganancia +
                        (prePro - modifica.get(i).getPrecio());
                existe = true;
            }
        }
        System.out.println("\nTotal de precio de ventas: "+ precioTotal+ "\n"
            + "Ganancia total: "+ ganancia);
        if(!existe) {
            System.out.println("No hay ventas en esa fecha");
        }
    }
}

