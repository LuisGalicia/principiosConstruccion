
package interfazFerre;

import ferreteria.Inventario;
import ferreteria.Porcentaje;
import ferreteria.Producto;
import ferreteria.Venta;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Esta clase realiza contiene métodos para mostrar Tab de Realizar venta
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 14/03/2017
 */
public class RalizaVentaInter {
    private Label labIngresa = new Label("Ingresa nombre o clave del producto");
    private Tab tabEdita = new Tab("Editar");
    private TextField clvNombre = new TextField();
    private TextField texName = new TextField();
    private TextField texClv = new TextField();
    private TextField texTipUni = new TextField();
    private TextField texDescrip = new TextField();
    private TextField texExistencia = new TextField();
    private TextField texPre = new TextField();
    private TextField texCantidad = new TextField();
    private Producto aux = new Producto();
    private ArrayList<Producto> productos;
    private Inventario inv;
    private boolean bandera = false;
    private int pos;
    private int exis;
    private int exisVendida;
    private float prec;
    private Tab reaVenta = new Tab("RealizarVenta");
    Porcentaje ptje = new Porcentaje();
    Venta vendido = new Venta();
    float tPrecio;
    float tIva;
    
    /**
     * Constructor que inicializa el arrayList e inventario
     */
    public RalizaVentaInter() {
        this.productos = new ArrayList<>();
        this.inv = new Inventario();
    }
    
    /**
     * Método que devuelve Tab de realizar venta
     * @return tab de realizar venta
     */
    public Tab tabRealiza() {
        reaVenta.setClosable(false);
        reaVenta.setContent(panRealiza());
        
        return reaVenta;
    }
    
    /**
     * Panel que incluye la vista principal de Editar
     * @return 
     */
    public GridPane panRealiza() {
        GridPane existen = new GridPane();
        
        Text titulo = new Text("Venta de un Producto");
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
                    reaVenta.setContent(realizaSecun(aux));
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
     * Método que  crea cuadro dialogo de actualizado
     * @return cuadro de información
     */
    public Alert ventaCorrectamente() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Venta realizada correctamente");
        agregado.setHeaderText("Precio total: " + (tPrecio + tIva) +
                "\nIva: " + tIva);
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método que  crea cuadro dialogo de no hay productos
     * @return cuadro de información
     */
    public Alert noHayProducto() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Nada vendido");
        agregado.setHeaderText("No se han agregado productos para vender");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método que  crea cuadro dialogo de no hay suficientes productos
     * @return cuadro de información
     */
    public Alert noSuficienteProducto() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Nada vendido");
        agregado.setHeaderText("No se hay suficientes productos para vender");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método que crea cuadro de diálogo
     * @return cuadro de información
     */
    public Alert noSeEncontro() {
        Alert agregado = new Alert(Alert.AlertType.INFORMATION);
        agregado.setTitle("Cuadro de Informacion");
        agregado.setHeaderText("No se encontró el producto");
        agregado.setContentText(null);
        agregado.showAndWait();
        
        return agregado;
    }
    
    /**
     * Método que cancela opción y vuelve a vista principal del tab Eliminar
     * @return botón cancelar
     */
    public Button bCancelar() {
        Button cance = new Button("Vender otro");
        cance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabRealiza().setContent(panRealiza());
            }
        });
        return cance;
    }
    
    /**
     * Método que crea botón de vender con acciones
     * @return botón de vender
     */
    public Button bVender() {
        Button actua = new Button("Vender");
        actua.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productos = inv.lectura();
                try{
                    exisVendida = Integer.parseInt(texCantidad.getText());
                } catch (NumberFormatException e ) {
                    System.out.println("Mal: " + e.getMessage());
                }
                if(exisVendida == 0 || texCantidad.getText().isEmpty()) {
                    noHayProducto();
                }else{
                    if(exisVendida <= productos.get(pos).getExistencia()) {
                        tPrecio = ptje.calculaPre(productos.get(pos).getPrecio(),
                                exisVendida);
                        tIva = ptje.calculaIva(productos.get(pos).getPrecio(), 
                                exisVendida);

                        productos.get(pos).setExistencia( 
                                productos.get(pos).getExistencia()-exisVendida);
                        inv.escritura(productos);
                        vendido.agregaVenta(productos.get(pos), exisVendida);
                        ventaCorrectamente(); 
                    } else {
                        noSuficienteProducto();
                    }
                }
            }
        });
        return actua;
    }
    
    /**
     * Método con vista secundaria de Realizar Venta
     * @param pr producto a leer
     * @return VBox con información del producto
     */
    public VBox realizaSecun(Producto pr) {
         VBox general = new VBox();
         GridPane guardaPro = new GridPane();
         
         Text titulo = new Text("Hacer venta de un producto");
         titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
         
         Label nombre = new Label("Nombre");
         Label clv = new Label("Clave");
         Label tipUni = new Label("Tipo de Unidad");
         Label descrip = new Label("Descripcion");
         Label existencia = new Label("Existencia");
         Label pre = new Label("Precio");
         Label cantidad = new Label("Cantidad de productos a vender");
         
         texName.setText(pr.getNombre());
         texName.setEditable(false);
         texClv.setText(pr.getClave());
         texName.setEditable(false);
         texTipUni.setText(pr.getTipoUnidad());
         texTipUni.setEditable(false);
         texDescrip.setText(pr.getDescripcion());
         texDescrip.setEditable(false);
         texPre.setText(String.valueOf(pr.getPrecio()));
         texPre.setEditable(false);
         texExistencia.setText(String.valueOf(pr.getExistencia()));
         texExistencia.setEditable(false);

         texCantidad.setOnKeyTyped(new EventHandler<KeyEvent>() {
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
         guardaPro.add(bVender(), 2, 2);
         guardaPro.add(bCancelar(), 1, 6);
         guardaPro.add(cantidad, 2, 0);
         guardaPro.add(texCantidad, 2, 1);
         
         general.setPadding(new Insets(15, 0, 0, 20));
         general.getChildren().add(titulo);
         general.getChildren().add(guardaPro);
         general.setVisible(true);
         
         return general;
    }
    
    
}
