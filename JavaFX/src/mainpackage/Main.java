
package mainpackage;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main ( String[] args) {
     Application.launch(args);
}

    @Override
    public void start(Stage stage) throws Exception {
        
       Label lbl = new Label("Kako je labude?"); 
       TextField tf1 = new TextField();
       Button btn = new Button("Klikniiii meeeee");
       
       btn.setOnAction(new EventHandler<ActionEvent> (){
           @Override
           public void handle(ActionEvent eventt) {
               String msg = "";
               String name = tf1.getText();
               lbl.setText("Dobrodosao beze moj "+msg);
               
               if(name.trim().length()>0){
                   msg = "Zdravo "+ name + " pasa moj solidni";
                   System.out.println();
               }
               else {
                   msg = "Nisi nista unio, ne budi levat!!!";
               }
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Informacija");
               alert.setHeaderText("Kako ide pasa moj "+name);
               alert.setContentText(msg);
               alert.showAndWait();
           }
       });
       
       VBox vbox = new VBox(lbl,tf1,btn);
       
       Scene scena = new Scene(vbox);
       
       stage.setScene(scena);
       
      stage.show();
        
    }
    
}
