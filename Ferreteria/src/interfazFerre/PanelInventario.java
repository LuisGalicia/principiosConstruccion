
package interfazFerre;

import javafx.scene.control.TabPane;

/**
 * Clase que contiene Paneles de inventario
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 09/03/2017
 */
public class PanelInventario {
    
    /**
     * Panel con opciones para administrador
     * @return TabPane para administrador
     */
    public TabPane opciones() {
        TabPane panel = new TabPane();
        AgregaInter agrega = new AgregaInter();
        EditarInter edita = new EditarInter();
        MostrarInter muestra = new MostrarInter();
        EliminarInter elimina = new EliminarInter();
        BuscarInter busca = new BuscarInter();
        VentaInter venta = new VentaInter();
        
        panel.getTabs().add(agrega.tabAgrega());
        panel.getTabs().add(edita.tabEdita());
        panel.getTabs().add(muestra.tabMuestra());
        panel.getTabs().add(elimina.tabElimina());
        panel.getTabs().add(busca.tabBusca());
        panel.getTabs().add(venta.tabVenta());
        
        return panel;
    }
    
    /**
     * Panel con opciones para empleado
     * @return TabPane para empleado
     */
    public TabPane opcionesEmpleado() {
        TabPane tabEmpleado = new TabPane();
        AgregaInter agreEm = new AgregaInter();
        MostrarInter mosEm = new MostrarInter();
        BuscarInter busEm = new BuscarInter();
        VentaInter venEm = new VentaInter();
        
        tabEmpleado.getTabs().add(agreEm.tabAgrega());
        tabEmpleado.getTabs().add(mosEm.tabMuestra());
        tabEmpleado.getTabs().add(busEm.tabBusca());
        tabEmpleado.getTabs().add(venEm.tabVenta());
        
        return tabEmpleado;
    }
}
