
package interfazFerre;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author lugad
 */
public class EditarInter {
    Label labIngresa = new Label("Ingresa nombre o clave del producto");
    TextField clvNombre = new TextField();
    
    /**
     * Método que crea el Tab de Editar
     * @return tab de editar
     */
    public Tab tabEdita() {
        Tab tab3 = new Tab("Editar");
        tab3.setClosable(false);
        tab3.setContent(panEdita());
        
        return tab3;
    }
    
    public GridPane panEdita() {
        GridPane existen = new GridPane();
        
        Text titulo = new Text("Editar información de un Producto");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        existen.setPadding(new Insets(15, 30, 30, 20));
        existen.setVgap(20);
        existen.setHgap(20);
        existen.add(titulo, 0, 0, 2, 1);
        existen.add(labIngresa, 0, 1);
        existen.add(clvNombre, 1, 1);
        existen.add(bBuscar(), 2, 1);
        
        return existen;
    }
    
    public Button bBuscar() {
        Button boto = new Button("Buscar");
        
        return boto;
    }
    
}
