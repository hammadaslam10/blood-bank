package sample;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorInfo {
    private StringProperty Name =new SimpleStringProperty(this,"Name", "");
    private LongProperty Cnic = new SimpleLongProperty(this,"Cnic",0);
    private LongProperty reg =new SimpleLongProperty(this,"reg", 0);
    private LongProperty Age = new SimpleLongProperty(this,"Age",0);
    private StringProperty Password =new SimpleStringProperty(this,"password", "");

    @Override
    public String toString() {
        return   getName() +"," + getCnic()+"," + getReg() +"," + getAge() +"," + getPassword();
    }
    DoctorInfo(){


    }
    DoctorInfo(String Name,long Cnic,long Reg ,long Age , String Password){
        this.Name= new SimpleStringProperty(Name);
        this.Cnic= new SimpleLongProperty(Cnic);
        this.reg= new SimpleLongProperty(Reg);
        this.Age=  new SimpleLongProperty(Age);
        this.Password = new SimpleStringProperty(Password);
    }
    public void setName(String name) {
        this.Name.set(name);
    }

    public void setCnic(long cnic) {
        this.Cnic.set(cnic);
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public void setReg(long reg) {
        this.reg.set(reg);
    }

    public void setAge(long age) {
        this.Age.set(age);
    }

    public long getCnic() {
        return Cnic.get();
    }

    public String getName() {
        return Name.get();
    }

    public String getPassword() {
        return Password.get();
    }

    public long getAge() {
        return Age.get();
    }

    public long getReg() {
        return reg.get();
    }

    public LongProperty cnicProperty() {
        return Cnic;
    }

    public LongProperty regProperty() {
        return reg;
    }

    public LongProperty ageProperty() {
        return Age;
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public StringProperty passwordProperty() {
        return Password;
    }
}
