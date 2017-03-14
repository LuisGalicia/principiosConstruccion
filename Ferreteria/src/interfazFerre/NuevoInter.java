
package interfazFerre;

import ferreteria.Inventario;
import ferreteria.Producto;
import java.io.Serializable;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Clase que contiene el Tab de Nuevo
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 9/03/2017
 */
public class NuevoInter implements Serializable{
    private Producto prod;
    private final ArrayList<Producto> almacena;
    private final Inventario inventario;
    private int exis = 0;
    private float prec = 0;
    private final Label labNombre = new Label("Nombre:");
    private final Label labClave = new Label("Clave:");
    private final Label labDescripcion = new Label("Descripcion:");
    private final Label labExistencia = new Label("Existencia:");
    private final Label labTipoUnidad = new Label("Tipo de Unidad:");
    private final Label labPrecio = new Label("Precio:");
    private final Label labIngresa = new Label("Ingresa nombre o clave del producto");
    private TextField texNombre = new TextField();
    private TextField texClave = new TextField();
    private TextField texDescripcion = new TextField();
    private TextField texExistencia = new TextField();
    private TextField texTipoUnidad = new TextField();
    private TextField texPrecio = new TextField();
    private TextField clvNombre = new TextField();
    
    /**
     * Constructor que inicializa ArrayList tipo producto e Inventario
     */
    public NuevoInter() {
        almacena = new ArrayList<>();
        inventario = new Inventario();
    }
    
    /**
     * Método que crea el Tab para Agregar Nuevo
     * @return tab para nuevo
     */
    public Tab tabNuevo() {
        Tab tab4 = new Tab("Nuevo");
        tab4.setClosable(false);
        tab4.setContent(panNuevo());
        
        return tab4;
    }
    
    /**
     * Método que muestra el contenido del Tab Nuevo
     * 
     */
    public GridPane panNuevo() {
        GridPane campo = new GridPane();
        
        Text titulo = new Text("Agrega producto nuevo");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        texExistencia.setOnKeyTyped(new EventHandler<KeyEvent>() {
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
         texPrecio.setOnKeyTyped(new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent event) {
                 String caracter = event.getCharacter();
                 if(!(caracter.equals("0") || caracter.equals("1")
                         || caracter.equals("2") || caracter.equals("3")
                         || caracter.equals("4") || caracter.equals("5")
                         || caracter.equals("6") || caracter.equals("7")
                         || caracter.equals("8") || caracter.equals("9")
                         || caracter.equals(".") || caracter.equals(","))) {
                    event.consume();
                 }
             }
         }
         );
        
        campo.setPadding(new Insets(15, 30, 30, 20));
        campo.setVgap(20);
        campo.setHgap(20);
        campo.add(titulo, 0, 0, 2, 1);
        campo.add(labNombre, 0, 1);
        campo.add(texNombre, 1, 1);
        campo.add(labClave, 0, 2);
        campo.add(texClave, 1, 2);
        campo.add(labDescripcion, 0, 3);
        campo.add(texDescripcion, 1, 3);
        campo.add(labExistencia, 0, 4);
        campo.add(texExistencia, 1, 4);
        campo.add(labTipoUnidad, 0, 5);
        campo.add(texTipoUnidad, 1, 5);
        campo.add(labPrecio, 0, 6);
        campo.add(texPrecio, 1, 6);
        campo.add(bAgregar(), 1, 7);
        
        return campo;
    }
    
    /**
     * Método que crea botón para agregar contenido a ArrayList tipo producto
     * @return botón de agregar
     */
    public Button bAgregar() {
        Button boto = new Button("Agregar");
        
        boto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    exis = Integer.parseInt(texExistencia.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("mal");
                }
                try{
                    prec = Float.parseFloat(texPrecio.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("mal");
                }
                prod = new Producto(texNombre.getText(),
                    texClave.getText(),
                    texDescripcion.getText(), 
                    texTipoUnidad.getText(), 
                    exis, 
                    prec);
                almacena.add(prod);
                inventario.arrayAux(almacena);
                System.out.println(texClave.getText());
                
                agregadoCorrec();
                
                texNombre.clear();
                texClave.clear();
                texDescripcion.clear();
                texTipoUnidad.clear();
                texExistencia.clear();
                texPrecio.clear();
            }
        });
        return boto;
    }
    
    /**
     * Método que crea Alert de información cuando se agrega correctamente 
     * las existencias
     * @return Alert de existencias agregadas correctamente
     */
    public Alert agregadoCorrec() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("Producto agregado adecuadamente");
        agregado.showAndWait();
        
        return agregado;
    }
}
