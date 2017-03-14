
package interfazFerre;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase que define la GUI para ingresar al sistema
 * @author Luis Galicia
 * @version 0.1
 * Fecha: 13/03/2017
 */
public class UsuarioInter extends Application {
    Button aceptar = new Button("Aceptar");
    TextField fieldUsua = new TextField();
    PasswordField fieldPass = new PasswordField();
    ControlUser usuarios = new ControlUser();
    ArrayList<User> user = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        GridPane panel = new GridPane();
        HBox paraBoton = new HBox(20);
        paraBoton.setAlignment(Pos.BOTTOM_RIGHT);
        paraBoton.getChildren().add(aceptar);
        
        Text titulo = new Text("Ingresa al Sistema");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        Label usua = new Label("Usuario:");
        Label pass = new Label("Contraseña:");
        
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(fieldUsua.getText().equals("administrador") && 
                        fieldPass.getText().equals("12345")) {
                    Stage nue = new Stage();
                    InterfazFerreteria inFe = new InterfazFerreteria();
                    inFe.start(nue);
                    primaryStage.close();
                }
                
                if(fieldUsua.getText().equals("empleado") && 
                        fieldPass.getText().equals("12345")) {
                    Stage nue = new Stage();
                    InterfazEmpleado inFe = new InterfazEmpleado();
                    inFe.start(nue);
                    primaryStage.close();
                }
                
                if(!(fieldUsua.getText().equals("empleado") ||
                        fieldUsua.getText().equals("administrador"))) {
                    Alert agregado = new Alert(Alert.AlertType.INFORMATION);
                    agregado.setTitle("Cuadro de Informacion");
                    agregado.setHeaderText("No se encontró usuario");
                    agregado.showAndWait();
                    
                }
            }
        });
        
        panel.setAlignment(Pos.CENTER);
        panel.setVgap(10);
        panel.setHgap(10);
        panel.setPadding(new Insets(25, 25, 25, 25));
        panel.add(titulo, 0, 0, 2, 1);
        panel.add(usua, 1, 1);
        panel.add(fieldUsua, 2, 1);
        panel.add(pass, 1, 2);
        panel.add(fieldPass, 2, 2);
        panel.add(paraBoton, 2, 3);
        
        Scene salida = new Scene(panel, 500, 250);
        primaryStage.setTitle("Ingreso al Sistema");
        primaryStage.setScene(salida);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
