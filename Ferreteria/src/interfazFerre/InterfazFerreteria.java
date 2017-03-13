
package interfazFerre;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ferreteria.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Clase que ejecuta los componentes para la interfaz de inventario
 * de la ferretería
 * 
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 09/03/2017
 */
public class InterfazFerreteria extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    VBox contiene = new VBox();
    PanelInventario panel = new PanelInventario();
    final Label titu = new Label("Sistema de Ferretería");
    
    titu.setFont(new Font("Arial", 30));
    contiene.setSpacing(10);
    contiene.setPadding(new Insets(10, 10, 10, 10));
    contiene.getChildren().add(titu);
    contiene.getChildren().add(panel.opciones());
    
    Scene salida = new Scene(contiene, 800, 800);
    primaryStage.setTitle("Ferreteria");
    primaryStage.setScene(salida);
    primaryStage.show();
    }

    /**
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
