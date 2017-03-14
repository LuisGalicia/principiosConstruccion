
package interfazFerre;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Clase pincipal para GUI de empleado
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 13/03/2017
 */
public class InterfazEmpleado extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    VBox contiene = new VBox();
    PanelInventario panel = new PanelInventario();
    final Label titu = new Label("Sistema de Ferretería empleado");
    
    titu.setFont(new Font("Arial", 30));
    contiene.setSpacing(10);
    contiene.setPadding(new Insets(10, 10, 10, 10));
    contiene.getChildren().add(titu);
    contiene.getChildren().add(panel.opcionesEmpleado());
    
    Scene salida = new Scene(contiene, 800, 800);
    primaryStage.setTitle("Ferreteria empleado");
    primaryStage.setScene(salida);
    primaryStage.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
