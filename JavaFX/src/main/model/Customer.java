
package main.model;

import java.util.UUID;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Customer {
    
    Integer idBroj = (int)Math.random();
    
    private final IntegerProperty idUgovora = new SimpleIntegerProperty(this, "Id ugovora", idBroj);
    private final StringProperty firstName = new SimpleStringProperty(this, "Ime", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "Prezime", "");
    private final StringProperty adresa = new SimpleStringProperty(this, "Adresa", "");
    private final StringProperty trajanjeUgovora = new SimpleStringProperty(this, "Trajanje ugovora", "");
    private final StringProperty brzinaProtoka = new SimpleStringProperty(this, "Brzina protoka","");
    private final StringProperty limitProtoka = new SimpleStringProperty(this, "Limit protoka", "");
    
    public Customer(){
        
    }
    public Customer(String firstName, String lastName){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }
    
    public Customer(String firstName, String lastName, String adresa, String trajanjeUgovora, String brzinaProtoka, String limitProtoka){
        
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.adresa.set(adresa);
        this.trajanjeUgovora.set(trajanjeUgovora);
        this.brzinaProtoka.set(brzinaProtoka);
        this.limitProtoka.set(limitProtoka);
    }
    //idUgovora bean metode
    public IntegerProperty idUgovoraProperty(){
        return idUgovora;
    }
    public Integer getIdUgovora(){
        return idUgovora.get();
    }
    public void setIdUgovora(Integer id){
        id=idBroj;
        this.idUgovora.set(id);
    }
    //firstName bean metode
    public StringProperty firstNameProperty(){
        return firstName;
    }  
    public String getFirstName(){
        return firstName.get();
    }
    public void setFirstName(String firstName){
        this.firstName.set(firstName);  
    }
        //lastName bean metode
    public StringProperty lastNameProperty(){
        return lastName;
    }  
    public String getLastName(){
        return lastName.get();
    }
    public void setLastName(String lastName){
        this.lastName.set(lastName);  
    }
    //adresa bean metode
    public StringProperty adresaProperty(){
        return adresa;
    }
    public String getAdresa(){
        return adresa.get();
    }
    public void setAdresa(String adresa){
        this.adresa.set(adresa);
    }
    //trajanje ugovora bean metode
    public StringProperty trajanjeUgovoraProperty(){
        return trajanjeUgovora;
    }    
    public String getTrajanjeUgovora(){
        return trajanjeUgovora.get();
    }
    public void setTrajanjeUgovora(String trajanjeUgovora){
        this.trajanjeUgovora.set(trajanjeUgovora);
    }
    //brzina protoka bean metode
    public StringProperty brzinaProtokaProperty(){
        return brzinaProtoka;
    }
    public String getBrzinaProtoka(){
        return brzinaProtoka.get();
    }
    public void setBrzinaProtoka(String brzinaProtoka){
        this.brzinaProtoka.set(brzinaProtoka);
    }
    //limit protoka bean metode
    public StringProperty limitProtokaProperty(){
        return limitProtoka;
    }
    public String getLimitProtoka(){
     return limitProtoka.get();
    }
    public void setLimitProtoka(String limitProtoka){
        this.limitProtoka.set(limitProtoka);
    }
}
