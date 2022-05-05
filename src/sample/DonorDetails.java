package sample;
import javafx.beans.property.*;


public class DonorDetails {

    private StringProperty DonorName =new SimpleStringProperty(this,"DonorName", "");
    private LongProperty DonorCnic = new SimpleLongProperty(this,"DonorCnic",0);
    private StringProperty BloodGroup =new SimpleStringProperty(this,"BloodGroup", "");
    private LongProperty DonorAge = new SimpleLongProperty(this,"DonorAge",0);
    private StringProperty DonorAddress =new SimpleStringProperty(this,"DonorAddress", "");
    private LongProperty Bottles = new SimpleLongProperty(this,"Bottles",0);

    public DonorDetails(){

}

    @Override
    public String toString() {
        return  getDonorName() +"," + getDonorCnic() +"," + getBloodGroup() +"," + getDonorAge() +"," + getDonorAddress() +"," + getBottles();
    }

    public DonorDetails(String NewDonorName, long NewDonorCnic, String BloodType, long NewDonorAge, String NewDonorAddress, long Bottles) {
        this.DonorName = new SimpleStringProperty(NewDonorName);
        this.DonorCnic = new SimpleLongProperty(NewDonorCnic);
        this.BloodGroup= new SimpleStringProperty(BloodType);
        this.DonorAge = new SimpleLongProperty(NewDonorAge);
        this.DonorAddress = new SimpleStringProperty(NewDonorAddress);
        this.Bottles=new SimpleLongProperty(Bottles);

    }

    public void setDonorName(String donorName) {
        this.DonorName.set(donorName);
    }

    public void setDonorCnic(long donorCnic) {
        this.DonorCnic.set(donorCnic);
    }

    public void setBloodGroup(String bloodGroup) {
        this.BloodGroup.set(bloodGroup);
    }

    public void setBottles(long bottles) {
        this.Bottles.set(bottles);
    }

    public void setDonorAddress(String donorAddress) {
        this.DonorAddress.set(donorAddress);
    }

    public void setDonorAge(long donorAge) {
        this.DonorAge.set(donorAge);
    }

    public String getDonorName() {
        return DonorName.get();
    }

    public long getDonorCnic() {
        return DonorCnic.get();
    }

    public long getBottles() {
        return Bottles.get();
    }

    public String getBloodGroup() {
        return BloodGroup.get();
    }

    public long getDonorAge() {
        return DonorAge.get();
    }

    public String getDonorAddress() {
        return DonorAddress.get();
    }

    public StringProperty donorNameProperty() {
        return DonorName;
    }

    public LongProperty bottlesProperty() {
        return Bottles;
    }

    public LongProperty donorAgeProperty() {
        return DonorAge;
    }

    public LongProperty donorCnicProperty() {
        return DonorCnic;
    }

    public StringProperty bloodGroupProperty() {
        return BloodGroup;
    }

    public StringProperty donorAddressProperty() {
        return DonorAddress;
    }

    public StringProperty DonorNameProperty() {
        return DonorName;
    }



    public LongProperty DonorCnicProperty() {
        return DonorCnic;
    }
    public LongProperty NewDonorAgeProperty() {
        return DonorAge;
    }
    public StringProperty NewDonorAddressProperty() {
        return DonorAddress;
    }

}
