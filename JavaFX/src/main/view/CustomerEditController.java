
package main.view;
//do sad je radilo

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Customer;


public class CustomerEditController {
    
    ObservableList<String>trajanjeUgovoraLista = FXCollections.observableArrayList("1 godina","2 godine");
    Integer idBroj = (int)Math.random();
    
    @FXML
    private Label idUgovoraLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField adresaField;
    @FXML
    private TextField trajanjeUgovoraField;
    @FXML
    private TextField brzinaProtokaField;
    @FXML
    private TextField limitPotrosnjeField;
    
    //Dodan comboBox
    @FXML 
    public ComboBox comboBoxTrajanje;
     
   //Kraj comboboxa
    
    private Stage editStage;
    private Customer customer;
    private boolean okClicked = false;
    
    @FXML
    private void initialize(){
    
    }
    public void setEditStage(Stage editStage){
        this.editStage = editStage;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
            
        
        idUgovoraLabel.setText(Integer.toString(customer.getIdUgovora()));
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        adresaField.setText(customer.getAdresa());
        trajanjeUgovoraField.setText(customer.getTrajanjeUgovora());
        limitPotrosnjeField.setText(customer.getLimitProtoka());
        brzinaProtokaField.setText(customer.getBrzinaProtoka());
    }
    public boolean isOkClicked(){
        return okClicked;
    }
    @FXML   
    private void handleOk(){
        if(isInputValid()){
            
            customer.setIdUgovora(idBroj);
            customer.setFirstName(firstNameField.getText());
            customer.setLastName(lastNameField.getText());
            customer.setAdresa(adresaField.getText());
            customer.setTrajanjeUgovora(trajanjeUgovoraField.getText());
            customer.setBrzinaProtoka(brzinaProtokaField.getText());
            customer.setLimitProtoka(limitPotrosnjeField.getText());
            
            okClicked = true;
            editStage.close();
            
        
        }
    }
    @FXML
    private void handleCancel(){
        editStage.close();
    }
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(firstNameField.getText()==null||firstNameField.getText().length()==0){
            errorMessage +="Neispravan unos korisnickog imena!\n";
        }
        if(lastNameField.getText()==null||lastNameField.getText().length()==0){
            errorMessage +="Neispravan unos korisnickog prezimena!\n";
        }
        if(adresaField.getText()==null||adresaField.getText().length()==0){
            errorMessage +="Neispravan unos korisnicke adrese!\n";
        }
        if(trajanjeUgovoraField.getText()==null||trajanjeUgovoraField.getText().length()==0){
            errorMessage +="Neispravan unos trajanja ugovora!\n";
        }
        if(brzinaProtokaField.getText()==null||brzinaProtokaField.getText().length()==0){
            errorMessage+="Neispravan unos brzine protoka!\n";
        }
        if(limitPotrosnjeField.getText()==null||limitPotrosnjeField.getText().length()==0){
            errorMessage+="Neispravan unos limita potrosnje!\n";
    }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(editStage);
            alert.setContentText(errorMessage);
            alert.setHeaderText("Molim vas da popunite sva polja");
            alert.setTitle("Greska-Prazna polja!");
            alert.showAndWait();
            return false;
        }
    }
}
