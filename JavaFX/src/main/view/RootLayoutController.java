/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import main.MainApp;

/**
 *
 * @author Adi
 */
public class RootLayoutController {
    
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
    }
    
    @FXML
    private void handleNew(){
        mainApp.getCustomersData().clear();
        mainApp.setCustomerFilePath(null);
    }
    @FXML
    private void handleOpen(){
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files(*.xml)", "*.xml");
       
       fileChooser.getExtensionFilters().add(extensionFilter);
       
       File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
       if(file!=null){
           mainApp.loadCustomerDataFromFile(file);
       }
    }
    
    @FXML
    private void handleSave(){
        File customerFile = mainApp.getCustomerFilePath();
        if(customerFile!=null){
            mainApp.saveCustomerDataToFile(customerFile);
        }else{
            handleSaveAs();
        }
    }
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files(*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        
        if(file!=null){
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath()+".xml");
            }
            mainApp.saveCustomerDataToFile(file);
        }
    }
    @FXML
    private void handleAbout(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Customer net app");
        alert.setHeaderText("Informacije o programu");
        alert.setContentText("Aplikacija customer net app je napravljna od strane Vrabac Adi-ja za potrebe assignmenta iz predmeta GUI na ITAcademy! \n "
                + "Kod je open-source i slobodan je za koristenje "
                + "\n Github -https://github.com/illy4bih");
        
        alert.showAndWait();
    }
    @FXML
    private void handleExit(){
        Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Customer net app");
        alert.setHeaderText("Zavrsavate seriju");
        alert.setContentText("Napustate program. Dovidjenja!");
        
        alert.showAndWait();

        System.exit(0);
    }
}
