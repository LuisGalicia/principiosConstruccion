
package interfazFerre;

import ferreteria.Inventario;
import ferreteria.Producto;
import java.util.ArrayList;
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
public class BuscarInter {
    Label labIngresa = new Label("Ingresa nombre o clave del producto");
    TextField clvNombre = new TextField();
    ArrayList<Producto> productos;
    Inventario inv;
    
    public BuscarInter() {
        productos = new ArrayList<>();
        inv = new Inventario();
    }
    
    /**
     * Método que crea el Tab de Buscar
     * @return tab de buscar
     */
    public Tab tabBusca() {
        Tab tab3 = new Tab("Buscar");
        tab3.setClosable(false);
        tab3.setContent(panBusca());
        
        return tab3;
    }
    
    public GridPane panBusca() {
        GridPane exist = new GridPane();
        
        Text titulo = new Text("Buscar Producto");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        exist.setPadding(new Insets(15, 30, 30, 20));
        exist.setVgap(20);
        exist.setHgap(20);
        exist.add(titulo, 0, 0, 2, 1);
        exist.add(labIngresa, 0, 1);
        exist.add(clvNombre, 1, 1);
        exist.add(bBuscar(), 2, 1);
        
        return exist;
    }
    
     public Button bBuscar() {
        Button boto = new Button("Buscar");
        
        return boto;
    }
}
