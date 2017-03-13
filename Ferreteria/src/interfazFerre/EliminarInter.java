
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
public class EliminarInter {
    Label labIngresa = new Label("Ingresa nombre o clave del producto");
    TextField clvNombre = new TextField();
    
    /**
     * Método que crea el Tab de Eliminar
     * @return tab de eliminar
     */
    public Tab tabElimina() {
        Tab tab1 = new Tab("Eliminar");
        tab1.setClosable(false);
        tab1.setContent(panElimina());
        
        return tab1;
    }
    
    public GridPane panElimina() {
        GridPane existencia = new GridPane();
        
        Text titulo = new Text("Elimina un Producto");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        existencia.setPadding(new Insets(15, 30, 30, 20));
        existencia.setVgap(20);
        existencia.setHgap(20);
        existencia.add(titulo, 0, 0, 2, 1);
        existencia.add(labIngresa, 0, 1);
        existencia.add(clvNombre, 1, 1);
        existencia.add(bBuscar(), 2, 1);
        
        return existencia;
    }
    
    public Button bBuscar() {
        Button boto = new Button("Buscar");
        
        return boto;
    }
    
}
