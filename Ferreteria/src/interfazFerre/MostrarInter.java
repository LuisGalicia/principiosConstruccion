
package interfazFerre;

import ferreteria.Inventario;
import ferreteria.Producto;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Clase que contiene el Tab de Mostrar
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 10/03/2017
 */
public class MostrarInter {
    private ArrayList<Producto> productos;
    private Inventario inv;
    private TableView<Producto> table;
    private Tab tabMuest = new Tab("Mostrar");
    private Button actualizar = new Button("Actualizar");
    
    /**
     * Constructor que inicializa ArrayList, Inventario y TableView
     */
    public MostrarInter() {
        productos = new ArrayList<>();
        inv = new Inventario();
        table = new TableView<>();
    }
    
    /**
     * Tab que contiene información de Mostrar
     * @return 
     */
    public Tab tabMuestra() {
        tabMuest.setContent(panMostrar());
        tabMuest.setClosable(false);
        
        return tabMuest;
    }
    
    /**
     * VBox con vista principal de panel Mostrar
     * @return VBox vista de Mostrar
     */
    public VBox panMostrar() {
        VBox grupo = new VBox();
        
        Text titulo = new Text("Productos en inventario");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        grupo.setSpacing(5);
        grupo.setPadding(new Insets(10, 0, 0,10));
        grupo.getChildren().add(titulo);
        grupo.getChildren().add(tableMuestra());
        grupo.getChildren().add(bActualizar());
        
        return grupo;
    }
    
    /**
     * Método que actualiza información de TableView
     * @return botón de actualizar
     */
    public Button bActualizar() {
        actualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TableView<Producto> pr = new TableView<>();
                table = pr;
                tabMuest.setContent(panMostrar());
                
            }
        });
        
        return actualizar;
    }
    
    /**
     * Método que agrega valores a TableView de Productos
     * @return TableView con valores de productos
     */
    public TableView tableMuestra() {
        productos = inv.lectura();
        
        table.setEditable(true);
        //se agrega información a la tabla
        TableColumn<Producto,String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));
        TableColumn<Producto,String> clave = new TableColumn<>("Clave");
        clave.setCellValueFactory(
                new PropertyValueFactory<>("clave"));
        TableColumn<Producto,String> tiuni = new TableColumn<>("TiUnidad");
        tiuni.setCellValueFactory(
                new PropertyValueFactory<>("tipoUnidad"));
        TableColumn<Producto,String> descrip = new TableColumn<>("Descripcion");
        descrip.setCellValueFactory(
                new PropertyValueFactory<>("descripcion"));
        TableColumn<Producto,Integer> exis = new TableColumn<>("Existencia");
        exis.setCellValueFactory(
                new PropertyValueFactory<>("existencia"));
        TableColumn<Producto,Float> precio = new TableColumn<>("Precio");
        precio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));
        
        table.getItems().addAll(productos);
        table.getColumns().addAll(nombre, clave, tiuni, descrip, exis, precio);
        return table;
    }
}
