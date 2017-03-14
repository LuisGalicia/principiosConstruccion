
package interfazFerre;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Esta clase contien los Tab para mostrar el Tab principal de ventas
 * @author Luis Galicia 
 * @version 0.1
 * Fecha: 14/03/2017
 */
public class VentaInter {
    
    /**
     * método que crea tab de Venta
     * @return 
     */
    public Tab tabVenta() {
        Tab tabVenta = new Tab("Venta");
        tabVenta.setContent(panVenta());
        tabVenta.setClosable(false);
        
        return tabVenta;
    }
    
    /**
     * Método que almacena los tabas para el Tab Agregar
     * @return el panel de componentes
     */
    public TabPane panVenta() {
        TabPane agrega = new TabPane();
        RalizaVentaInter nuevo = new RalizaVentaInter();
        MostrarVentaInter vent = new MostrarVentaInter();
        
        agrega.getTabs().add(vent.tabMuestra());
        agrega.getTabs().add(nuevo.tabRealiza());
        
        return agrega;
    }
}
