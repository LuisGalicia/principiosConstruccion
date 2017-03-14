
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
 * Clase que contiene el Tab de Editar
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 11/03/2017
 */
public class EditarInter {
    private Label labIngresa = new Label("Ingresa nombre o clave del producto");
    private Tab tabEdita = new Tab("Editar");
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
    private int exis;
    private float prec;
    
    /**
     * Constructor que inicializa el arrayList y el inventario
     */
    public EditarInter() {
        this.productos = new ArrayList<>();
        this.inv = new Inventario();
    }
    
    /**
     * Método que crea el Tab de Editar con la vista principal
     * @return tab de editar
     */
    public Tab tabEdita() {
        tabEdita.setClosable(false);
        tabEdita.setContent(panEdita());
        
        return tabEdita;
    }
    /**
     * Panel que incluye la vista principal de Editar
     * @return 
     */
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
    
    /**
     * Método que crea el botón de buscar 
     * @return busca botón que busca
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
                    tabEdita.setContent(editaSecun(aux));
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
     * Método que crea botón de actualizar
     * @return botón de actualizar
     */
    public Button bActualizar() {
        Button actua = new Button("Actualizar");
        actua.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productos = inv.lectura();
                try{
                    exis = Integer.parseInt(texExistencia.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("Mal: " + e.getMessage());
                }
                try{
                    prec = Float.parseFloat(texPre.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("Mal: " + e.getMessage());
                }
                productos.get(pos).setNombre(texName.getText());
                productos.get(pos).setClave(texClv.getText());
                productos.get(pos).setDescripcion(texDescrip.getText());
                productos.get(pos).setTipoUnidad(texTipUni.getText());
                productos.get(pos).setPrecio(prec);
                productos.get(pos).setExistencia(exis);

                inv.escritura(productos);
                tabEdita.setContent(panEdita());
                actualizadoCorrectamente();
            }
        });
        return actua;
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
                tabEdita.setContent(panEdita());
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
     * Método que  crea cuadro dialogo de actualizado
     * @return cuadro de información
     */
    public Alert actualizadoCorrectamente() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("Producto actualizado correctamente");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método con vista secundaria de Editar
     * @param pr producto a leer
     * @return VBox con información del producto
     */
    public VBox editaSecun(Producto pr) {
         VBox general = new VBox();
         GridPane guardaPro = new GridPane();
         
         Text titulo = new Text("Editar información de un Producto");
         titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
         
         Label nombre = new Label("Nombre");
         Label clv = new Label("Clave");
         Label tipUni = new Label("Tipo de Unidad");
         Label descrip = new Label("Descripcion");
         Label existencia = new Label("Existencia");
         Label pre = new Label("Precio");
         
         
         texName.setText(pr.getNombre());
         texClv.setText(pr.getClave());
         texTipUni.setText(pr.getTipoUnidad());
         texDescrip.setText(pr.getDescripcion());
         texPre.setText(String.valueOf(pr.getPrecio()));
         texExistencia.setText(String.valueOf(pr.getExistencia()));
         

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
         guardaPro.add(bActualizar(), 1, 6);
         guardaPro.add(bCancelar(), 0, 6);
         
         general.setPadding(new Insets(15, 0, 0, 20));
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.setVisible(true);
         
         return general;
    }
}
