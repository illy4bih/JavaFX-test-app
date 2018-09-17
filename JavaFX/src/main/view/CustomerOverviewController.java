
package main.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.MainApp;
import main.model.Customer;


public class CustomerOverviewController {
    @FXML
    private TableView<Customer>customerTable;
    @FXML
    private TableColumn<Customer,String>firstNameColumn;
    @FXML
    private TableColumn<Customer, String>lastNameColumn;
    
    @FXML
    private Label idUgovoraLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label adresaLabel;
    @FXML
    private Label trajanjeUgovoraLabel;
    @FXML 
    private Label brzinaProtokaLabel;
    @FXML
    private Label limitInternetaLabel;
    
    private MainApp mainApp;
    
    public CustomerOverviewController(){
        
    }
    @FXML
    public void initialize(){
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        //**ovo dodano kad se crashovalo
        showCustomerDetails(null);
        
        customerTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->showCustomerDetails(newValue));
    }
    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
        
        customerTable.setItems(mainApp.getCustomersData());
    }
    //ovo dodano kad se crashovalo
    public void showCustomerDetails(Customer customer){
        if(customer!=null){
            
            idUgovoraLabel.setText(Integer.toString(customer.getIdUgovora()));//-sa ovim nije radilo,sad radi
            firstNameLabel.setText(customer.getFirstName());
            lastNameLabel.setText(customer.getLastName());
            adresaLabel.setText(customer.getAdresa());
            trajanjeUgovoraLabel.setText(customer.getTrajanjeUgovora());
            brzinaProtokaLabel.setText(customer.getBrzinaProtoka());
            limitInternetaLabel.setText(customer.getLimitProtoka());  
        }
        else{
            idUgovoraLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            adresaLabel.setText("");
            trajanjeUgovoraLabel.setText("");
            brzinaProtokaLabel.setText("");
            limitInternetaLabel.setText("");
        }
    }
    @FXML
    public void handleDeleteCustomer(){
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
           customerTable.getItems().remove(selectedIndex); 
        }
        else{
            Alert upozorenje = new Alert(AlertType.WARNING);
            upozorenje.initOwner(mainApp.getPrimaryStage());
            upozorenje.setTitle("Greska u operaciji!");
            upozorenje.setHeaderText("Niste odabrali korisnika");
            upozorenje.setContentText("Molim odaberite korisnika iz table");
            upozorenje.showAndWait();
        }
        
    }
    
    @FXML
    public void handleNewCustomer() throws IOException{
        Customer novicustomer = new Customer();
        boolean okClicked = mainApp.showCustomerEdit(novicustomer);
        if(okClicked){
            mainApp.getCustomersData().add(novicustomer);
            mainApp.primaryStage.setTitle("Dodaj novog korisnika!");
        }
    }
    @FXML
    public void handleEditCustomer() throws IOException{
        Customer odabraniCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(odabraniCustomer!=null){
            boolean okClicked = mainApp.showCustomerEdit(odabraniCustomer);
        if(okClicked){
            showCustomerDetails(odabraniCustomer);
        }
    }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Nije izvrsen odabir!");
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Korisnik nije odabran");
            alert.setContentText("Molim da odaberete korisnika iz liste.");
            
            alert.showAndWait();
        }
    }
}
