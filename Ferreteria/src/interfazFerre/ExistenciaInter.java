
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author lugad
 */
public class ExistenciaInter {
    TextField clvNombre = new TextField();
    TextField exisNueva = new TextField();
    VBox general = new VBox();
    Tab tab5 = new Tab("Existencia");
    ArrayList<Producto> productos;
    Producto aux = new Producto();
    private Inventario inv;
    boolean bandera = false;
    private int exisAgregada = 0;
    private int pos;
    
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
                clvNombre.clear();
            }
        });
        return boto;
    }
     
     public Button agregaExis() {
         Button existen = new Button("Agregar");
         existen.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                productos = inv.lectura();
                try{
                    System.out.println("entra try");
                    exisAgregada = Integer.parseInt(exisNueva.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("mal");
                }
                productos.get(pos).setExistencia(
                        productos.get(pos).getExistencia() + exisAgregada);

                System.out.println("asigna");
                inv.escritura(productos);
                tab5.setContent(panExistencia());
                agregadaCorrectamente();
                exisNueva.clear();
             }
         });
         
         return existen;
     }
     
    public Alert noSeEncontro() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("No se encontro el producto");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    public Alert agregadaCorrectamente() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("Se agregan correctamente las existencias");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
     
     public VBox productoBuscado(Producto pr) {
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
         
         agregando.getChildren().add(agregaExis);
         agregando.getChildren().add(exisNueva);
         agregando.getChildren().add(agregaExis());
         
         guardaPro.setPadding(new Insets(15, 30, 30, 20));
         guardaPro.setVgap(20);
         guardaPro.setHgap(20);
         guardaPro.add(nombre, 0, 0);
         guardaPro.add(texName, 1, 0);
         guardaPro.add(clv, 0, 1);
         guardaPro.add(texClv, 1, 1);
         guardaPro.add(existencia, 0, 2);
         guardaPro.add(texExist, 1, 2);
         
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.getChildren().add(agregando);
         general.setVisible(true);
         
         return general;
     }
}
