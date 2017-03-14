
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
 * Clase que contiene el Tab de eliminar
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 12/03/2017
 */
public class EliminarInter {
    private Label labIngresa = new Label("Ingresa nombre o clave del producto");
    private Tab tabEliminar = new Tab("Eliminar");
    private TextField clvNombre = new TextField();
    private TextField texName = new TextField();
    private TextField texClv = new TextField();
    private TextField texTipUni = new TextField();
    private TextField texDescrip = new TextField();
    private TextField texExistencia = new TextField();
    private TextField texPre = new TextField();
    private Producto aux = new Producto();
    private ArrayList<Producto> productos;
    private Inventario inv;
    private boolean bandera = false;
    private int pos;
    
    /**
     * Constructor que inicializa arrayList e inventario
     */
    public EliminarInter() {
        this.productos = new ArrayList<>();
        this.inv = new Inventario();
    }
    
    /**
     * Método que crea el Tab de Eliminar
     * @return tab de eliminar
     */
    public Tab tabElimina() {
        tabEliminar.setClosable(false);
        tabEliminar.setContent(panElimina());
        
        return tabEliminar;
    }
    
    /**
     * Método que crea vista principal de Eliminar
     * @return gridpane con vista principal
     */
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
    
    /**
     * Método que crea el botón de buscar
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
                        pos = i;
                    }   
                }
                if(bandera) {
                    tabEliminar.setContent(eliminarSecun(aux));
                } else {
                    noSeEncontro();
                }
                bandera = false;
                clvNombre.clear();
            }
        });
        return boto;
    }
    
    /**
     * Método que crea botón de eliminar
     * @return botón de eliminar
     */
    public Button bEliminar() {
        Button eli = new Button("Eliminar");
        eli.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productos = inv.lectura();
                productos.remove(pos);
                inv.escritura(productos);
                eliminadoCorrectamente();
                tabEliminar.setContent(panElimina());
                
            }
        });
        return eli;
    }
    
    /**
     * Método que cancela opción y vuelve a vista principal del tab Eliminar
     * @return botón cancelar
     */
    public Button bCancelar() {
        Button cance = new Button("Cancelar");
        cance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabEliminar.setContent(panElimina());
            }
        });
        return cance;
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
     * Método que  crea cuadro dialogo de eliminado
     * @return cuadro de información
     */
    public Alert eliminadoCorrectamente() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("Producto eliminado correctamente");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método con vista secundaria de Eliminar
     * @param pr producto a leer
     * @return VBox con información del producto
     */
    public VBox eliminarSecun(Producto pr) {
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
         guardaPro.add(bEliminar(), 1, 6);
         guardaPro.add(bCancelar(), 0, 6);
         
         general.setPadding(new Insets(15, 0, 0, 20));
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.setVisible(true);
         
         return general;
    }
    
}
