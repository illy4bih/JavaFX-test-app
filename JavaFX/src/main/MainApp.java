/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import main.model.Customer;
import main.model.CustomerListWrapper;
import main.view.CustomerEditController;
import main.view.CustomerOverviewController;
import main.view.RootLayoutController;


public class MainApp extends Application {

    public Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Customer>customerData =FXCollections.observableArrayList();
    
    public static void main(String[]args){

        Application.launch(args);
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
      this.primaryStage=primaryStage;
      this.primaryStage.setTitle("Glavni prozor");
      
      Customer customer1 = new Customer("Adi", "Vrabac");
      Customer customer2 = new Customer("Omar", "Vrabac");
      Customer customer3= new Customer("Lejla", "Kobiljak-Vrabac");
      customerData.addAll(customer1,customer2,customer3);
      
      this.primaryStage.getIcons().add(new Image("file:view/earth.png"));
      
      initRootLayout();
        showCustomerOverview();
    }
    public void initRootLayout() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        
        Scene scena = new Scene(rootLayout);
        primaryStage.setScene(scena);
        
        
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.show();
        
        File file = getCustomerFilePath();
        if(file!=null){
            loadCustomerDataFromFile(file);
        }
    }
    
    public void showCustomerOverview() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/customerOverview.fxml"));
        AnchorPane customerOverview = (AnchorPane)loader.load();
        
        rootLayout.setCenter(customerOverview);
        
        CustomerOverviewController controller = loader.getController();
        controller.setMainApp(this);  
    }
    public boolean showCustomerEdit(Customer customer) throws IOException{//do unosa ovog je radilo
       
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/CustomerEdit.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        Stage editStage = new Stage();
        editStage.setTitle("Izmijeni korisnika");
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(primaryStage);
        Scene scena = new Scene(page);
        editStage.setScene(scena);
        
        CustomerEditController controller = loader.getController();
        controller.setCustomer(customer);
        controller.setEditStage(editStage);
        
        editStage.showAndWait();
        return controller.isOkClicked();
        
                } catch (IOException e) {
        return false;
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public ObservableList<Customer> getCustomersData() {
        return customerData;
    }
    
    public File getCustomerFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if(filePath!=null){
            return new File(filePath);
        }else{
        return null;
        } 
    }
    public void setCustomerFilePath(File file){
        Preferences pref = Preferences.userNodeForPackage(MainApp.class);
        if(file!=null){
            pref.put("filePath", file.getPath());
            primaryStage.setTitle("Customer net app - "+file.getName());
        }else{
            pref.remove("filePath");
            primaryStage.setTitle("Customer net app");
        }
    }
    public void loadCustomerDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(CustomerListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        
        CustomerListWrapper wrapper = (CustomerListWrapper) um.unmarshal(file);

        customerData.clear();
        customerData.addAll(wrapper.getCustomers());

   
        setCustomerFilePath(file);

    } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Greska");
        alert.setHeaderText("Nemoguce ucitati podatke");
        alert.setContentText("Nemoguce ucitati podatke sa lokacije:\n" + file.getPath());

        alert.showAndWait();
    }
}
    public void saveCustomerDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(CustomerListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        CustomerListWrapper wrapper = new CustomerListWrapper();
        wrapper.setCustomers(customerData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setCustomerFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Greska");
        alert.setHeaderText("Nemoguce spremiti podatke");
        alert.setContentText("Nemoguce spremiti podatke na lokaciji:\n" + file.getPath());

        alert.showAndWait();
    }
}

    
    
}
