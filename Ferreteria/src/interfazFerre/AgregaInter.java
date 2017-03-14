
package interfazFerre;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Clase que contiene el tab de Agregar
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 9/03/2017
 */
public class AgregaInter {
    
    /**
     * Método que contiene el Tab de Agregar
     * @return tab de agregar
     */
    public Tab tabAgrega() {
       
        Tab tab = new Tab("Agregar");
        tab.setContent(panAgrega());
        tab.setClosable(false);
        
        return tab;
    }
    
    /**
     * Método que almacena los tabas para el Tab Agregar
     * @return el panel de componentes
     */
    public TabPane panAgrega() {
        TabPane agrega = new TabPane();
        NuevoInter nuevo = new NuevoInter();
        ExistenciaInter existencia = new ExistenciaInter();
        
        agrega.getTabs().add(nuevo.tabNuevo());
        agrega.getTabs().add(existencia.tabExistencia());
        
        return agrega;
    }
    
}
