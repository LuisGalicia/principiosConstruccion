
package interfazFerre;

import ferreteria.Inventario;
import ferreteria.Producto;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author lugad
 */
public class BuscarInter {
    private Label labIngresa = new Label("Ingresa nombre o clave del producto");
    private TextField clvNombre = new TextField();
    private Tab tabBusca = new Tab("Buscar");
    private ArrayList<Producto> productos;
    private Inventario inv;
    private TextField texName = new TextField();
    private TextField texClv = new TextField();
    private TextField texTipUni = new TextField();
    private TextField texDescrip = new TextField();
    private TextField texExistencia = new TextField();
    private TextField texPre = new TextField();
    private Producto aux = new Producto();
    private boolean bandera = false;
    
    /**
     * Constructor que inicializa arrayList e inventario
     */
    public BuscarInter() {
        productos = new ArrayList<>();
        inv = new Inventario();
    }
    
    /**
     * Método que crea el Tab de Buscar
     * @return tab de buscar
     */
    public Tab tabBusca() {
        tabBusca.setClosable(false);
        tabBusca.setContent(panBusca());
        
        return tabBusca;
    }
    
    /**
     * Vista principal de tab de Buscar
     * @return 
     */
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
    
    /**
     * Botón de buscar con event
     * @return botón de buscar
     */
     public Button bBuscar() {
        Button boto = new Button("Buscar");
        boto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productos = inv.lectura();
                for (int i = 0; i < productos.size(); i++) {
                    if(productos.get(i).getClave().equals(clvNombre.getText())
                            || productos.get(i).getNombre().equals(
                            clvNombre.getText())){
                        aux = productos.get(i);
                        bandera = true;
                    }   
                }
                if(bandera) {
                    tabBusca.setContent(buscarSecun(aux));
                } else {
                    noSeEncontro();
                }
                bandera = false;
                clvNombre.clear();
            }
        });
        return boto;
    }
     
    public Button buscaOtro() {
        Button otro = new Button("Buscar otro");
        otro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabBusca.setContent(panBusca());
            }
        });
        
        return otro;
    }
    /**
     * Método que crea cuadro de diálogo
     * @return cuadro de información
     */
    public Alert noSeEncontro() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("No se encontro el producto");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
     
     /**
     * Método con vista secundaria de Editar
     * @param pr producto a leer
     * @return VBox con información del producto
     */
    public VBox buscarSecun(Producto pr) {
         VBox general = new VBox();
         GridPane guardaPro = new GridPane();
         
         Text titulo = new Text("Eliminar un producto");
         titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
         
         Label nombre = new Label("Nombre");
         Label clv = new Label("Clave");
         Label tipUni = new Label("Tipo de Unidad");
         Label descrip = new Label("Descripcion");
         Label existencia = new Label("Existencia");
         Label pre = new Label("Precio");
         
         
         texName.setText(pr.getNombre());
         texName.setEditable(false);
         texClv.setText(pr.getClave());
         texClv.setEditable(false);
         texTipUni.setText(pr.getTipoUnidad());
         texTipUni.setEditable(false);
         texDescrip.setText(pr.getDescripcion());
         texDescrip.setEditable(false);
         texPre.setText(String.valueOf(pr.getPrecio()));
         texPre.setEditable(false);
         texExistencia.setText(String.valueOf(pr.getExistencia()));
         texExistencia.setEditable(false);
         

         guardaPro.setPadding(new Insets(35, 30, 30, 35));
         guardaPro.setVgap(20);
         guardaPro.setHgap(20);
         guardaPro.add(nombre, 0, 0);
         guardaPro.add(texName, 1, 0);
         guardaPro.add(clv, 0, 1);
         guardaPro.add(texClv, 1, 1);
         guardaPro.add(tipUni, 0, 2);
         guardaPro.add(texTipUni, 1, 2);
         guardaPro.add(descrip, 0, 3);
         guardaPro.add(texDescrip, 1, 3);
         guardaPro.add(existencia, 0, 4);
         guardaPro.add(texExistencia, 1, 4);
         guardaPro.add(pre, 0, 5);
         guardaPro.add(texPre, 1, 5);
         guardaPro.add(buscaOtro(), 1, 6);
         
         general.setPadding(new Insets(15, 0, 0, 20));
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.setVisible(true);
         
         return general;
    }
}
