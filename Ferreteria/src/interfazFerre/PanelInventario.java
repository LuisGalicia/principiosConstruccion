
package interfazFerre;

import javafx.scene.control.TabPane;

/**
 *
 * @author lugad
 */
public class PanelInventario {
    
    public TabPane opciones() {
        TabPane panel = new TabPane();
        AgregaInter agrega = new AgregaInter();
        EditarInter edita = new EditarInter();
        MostrarInter muestra = new MostrarInter();
        EliminarInter elimina = new EliminarInter();
        BuscarInter busca = new BuscarInter();
        
        panel.getTabs().add(agrega.tabAgrega());
        panel.getTabs().add(edita.tabEdita());
        panel.getTabs().add(muestra.tabMuestra());
        panel.getTabs().add(elimina.tabElimina());
        panel.getTabs().add(busca.tabBusca());
        
        return panel;
    }
}
