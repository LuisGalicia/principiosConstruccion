
package ferreteria;

import java.util.Scanner;

/**
 * Clase que muestra un menú de opciones
 * 
 * @author Luis Galicia
 * @version 1.0
 */
public class MenuOp {
    Scanner sc = new Scanner(System.in);
    /**
     * constructor de menu
     */
    public MenuOp() {
        
    }
    /**
     * Opciones del menú
     */
    public void opciones() {
        System.out.println(" ________________________\n"
                + "|INVENTARIO DE FERRETERIA|\n"
                + " ************************\n"
                + "1.AGREGAR \n"
                + "2.MOSTRAR\n"
                + "3.ELIMINAR\n"
                + "4.BUSCAR POR NOMBRE O CLAVE\n"
                + "5.EDITAR\n"
                + "6.VENTA\n"
                + "7.MOSTRAR VENTAS\n"
                + "9.AGREGAR EXISTENCIA\n"
                + "8.SALIR");
    }
    /**
     * Ejecuta la opcion seleccionada
     * @param op opcion ingresada por el usuario
     */
    public void ejecuta(int op) {
        ArrayProducto prod = new ArrayProducto();
        Venta vendido = new Venta();
        String name;
        switch(op) {
            case 1:
                prod.agrega();
                break;
            case 2:
                prod.muestra();
                break;
            case 3:
                System.out.println("Ingresa el nombre del producto");
                name = sc.nextLine();
                prod.elimina(name);
                break;
            case 4:
                System.out.println("Ingresa el nombre "
                        + "o la clave del producto: ");
                name = sc.nextLine();
                prod.busca(name);
                break;
            case 5:
                System.out.println("Ingresa el nombre del producto");
                prod.edita(sc.nextLine());
                break;
            case 6:
                System.out.println("Ingresa la clave del producto");
                prod.venta(sc.nextLine());
                break;
            case 7:
                vendido.muestraVentas();
                break;
            case 8:
                System.out.println("Ingresa la clave del producto: ");
                name = sc.nextLine();
                prod.agregarExistencia(name);
                break;
            case 9:
                System.out.println("Vuelve pronto");
                break;
            default:
                System.out.println("Opcion fuera de rango");
                break;
        }
    }
    /**
     * Método que devuelve opción elegida
     * @return opcion elegida
     */
    public int opci() {
        int op;
        System.out.println("Elige una opción: ");
        op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    /**
     * Método que muestra las opciones al seleccionar Editar
     */
    public void menuEditar() {
        System.out.println("¿Qué deseas editar?\n"
                        + "1.Nombre\n"
                        + "2.Clave\n"
                        + "3.Descripcion\n"
                        + "4.Tipo de unidad\n"
                        + "5.Existencia\n"
                        + "6.Precio\n"
                        + "7.Salir\n");
    }
}
