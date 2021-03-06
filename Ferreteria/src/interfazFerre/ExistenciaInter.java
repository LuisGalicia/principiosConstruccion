
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Clase que contiene el Tab de Existencia
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 9/03/2017
 */
public class ExistenciaInter {
    private TextField clvNombre = new TextField();
    private TextField exisNueva = new TextField();
    private Tab tab5 = new Tab("Existencia");
    private ArrayList<Producto> productos;
    private Producto aux = new Producto();
    private Inventario inv;
    boolean bandera = false;
    private int exisAgregada = 0;
    private int pos;
    
    /**
     * Constructor que inicializa Inventario y arrayList tipo producto
     */
    public ExistenciaInter() {
        this.inv = new Inventario();
        this.productos = new ArrayList<>();
    }
    
     /**
     * Método que crea el Tab para Agregar Existencia
     * @return tab para existencia
     */
    public Tab tabExistencia() {
        tab5.setClosable(false);
        tab5.setContent(panExistencia());
        return tab5;
    }
    
    /**
     * Pane que contiene información para Tab de eliminar
     * @return GridPane de existencia
     */
    public GridPane panExistencia() {
        GridPane exis = new GridPane();
        Label labIngresa = new Label("Ingresa nombre o clave del producto");
        Text titulo = new Text("Agrega existencias a un producto");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        exis.setPadding(new Insets(15, 30, 30, 20));
        exis.setVgap(20);
        exis.setHgap(20);
        exis.add(titulo, 0, 0, 2, 1);
        exis.add(labIngresa, 0, 1);
        exis.add(clvNombre, 1, 1);
        exis.add(bBuscar(), 2, 1);
        
        return exis;
    }
    
    /**
     * Método que crea botón para buscar producto
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
                    tab5.setContent(productoBuscado(aux));
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
     * Método que crea botón para agregar existencia a producto
     * @return botón de agregar existencia
     */
     public Button agregaExis() {
         Button existen = new Button("Agregar");
         existen.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                productos = inv.lectura();
                try{
                    exisAgregada = Integer.parseInt(exisNueva.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("mal");
                }
                if(exisAgregada == 0 || exisNueva.getText().isEmpty()) {
                    noHayProducto();
                    tab5.setContent(panExistencia());
                    exisNueva.clear();
                } else {
                    productos.get(pos).setExistencia(
                            productos.get(pos).getExistencia() + exisAgregada);

                    System.out.println("asigna");
                    inv.escritura(productos);
                    tab5.setContent(panExistencia());
                    agregadaCorrectamente();
                    exisNueva.clear();
                }
             }
         });
         
         return existen;
     }
    
    /**
     * Método que crea un Alert de información cuando no se encuentra producto
     * @return Alert de no se encontró información
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
     * Método que crea Alert de información cuando se agrega correctamente 
     * las existencias
     * @return Alert de existencias agregadas correctamente
     */
    public Alert agregadaCorrectamente() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("Se agregan correctamente las existencias");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método que  crea cuadro dialogo de no hay existencias
     * @return cuadro de información
     */
    public Alert noHayProducto() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Nada agregado");
        agregado.setHeaderText("No se han agregado existencias al producto");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    /**
     * Método que crea VBox para mostrar cuando el producto se encuentra
     * @param pr producto
     * @return VBox de producto encontrado
     */
    public VBox productoBuscado(Producto pr) {
         VBox general = new VBox();
         GridPane guardaPro = new GridPane();
         HBox agregando = new HBox();
         
         Text titulo = new Text("Agrega existencias a un producto");
         titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
         
         Label nombre = new Label("Nombre");
         Label clv = new Label("Clave");
         Label existencia = new Label("Existencia");
         Label agregaExis = new Label("Agrega las existencias:");
         
         TextField texName = new TextField();
         texName.setEditable(false);
         TextField texClv = new TextField();
         texClv.setEditable(false);
         TextField texExist = new TextField();
         texExist.setEditable(false);
         
         texName.setText(pr.getNombre());
         texClv.setText(pr.getClave());
         texExist.setText(String.valueOf(pr.getExistencia()));
         exisNueva.setOnKeyTyped(new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent event) {
                 String caracter = event.getCharacter();
                 if(!(caracter.equals("0") || caracter.equals("1")
                         || caracter.equals("2") || caracter.equals("3")
                         || caracter.equals("4") || caracter.equals("5")
                         || caracter.equals("6") || caracter.equals("7")
                         || caracter.equals("8") || caracter.equals("9"))) {
                    event.consume();
                 }
             }
         }
         );
         
         agregando.setSpacing(10);
         agregando.getChildren().add(agregaExis);
         agregando.getChildren().add(exisNueva);
         agregando.getChildren().add(agregaExis());
         
         guardaPro.setPadding(new Insets(35, 30, 30, 20));
         guardaPro.setVgap(20);
         guardaPro.setHgap(20);
         guardaPro.add(nombre, 0, 0);
         guardaPro.add(texName, 1, 0);
         guardaPro.add(clv, 0, 1);
         guardaPro.add(texClv, 1, 1);
         guardaPro.add(existencia, 0, 2);
         guardaPro.add(texExist, 1, 2);
         
         general.setPadding(new Insets(15, 0, 0, 20));
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.getChildren().add(agregando);
         general.setVisible(true);
         
         return general;
     }
}
