package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NEWDONOR implements Initializable {
    @FXML
    private TextField NewDonorName;

    @FXML
    private TextField NewDonorCnic;

    @FXML
    private TextField NewDonorAge;

    @FXML
    private TextField NewDonorAddress;

    @FXML
    private Button NewDonateBtn;

    @FXML
    private CheckBox New1Bottle;

    @FXML
    private CheckBox New2Bottle;

    @FXML
    private ComboBox<String> BloodGroup;
    ObservableList<String> BloodGroupData = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void New1Bottle(ActionEvent event) {
        if(New1Bottle.isSelected()){
            New2Bottle.setSelected(false);
        }    }

    @FXML
    void New2Bottle(ActionEvent event) {
        if(New2Bottle.isSelected()){
            New1Bottle.setSelected(false);
        }
    }

public void Submission(){
    try{
            File file=new File("./DonorData.txt");
        FileWriter writer= new FileWriter(file.getName(),true);
        BufferedWriter bufferedWriter= new BufferedWriter(writer);
        bufferedWriter.write(NewDonorName.getText()+",");
        bufferedWriter.write(NewDonorCnic.getText()+",");
        bufferedWriter.write(BloodGroup.getValue()+",");
        bufferedWriter.write(NewDonorAge.getText()+",");
        bufferedWriter.write(NewDonorAddress.getText()+",");
        if(New1Bottle.isSelected()){
            bufferedWriter.write("1");

        }if(New2Bottle.isSelected()){
            bufferedWriter.write("2");
        }


        File thefile = new File("./TempDonor.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(thefile));

        bw.write(NewDonorName.getText()+",");
        bw.write(NewDonorCnic.getText()+",");
       bw.write(NewDonorAge.getText()+",");
        bw.write(NewDonorAddress.getText());
        bw.write(BloodGroup.getValue()+",");
        if(New1Bottle.isSelected()){
            bw.write("1");

        }if(New2Bottle.isSelected()){
            bw.write("2");
        }

        bw.close();
        bufferedWriter.close();

    }catch (Exception e){
        e.printStackTrace();
    }
}
    @FXML
    void NewDonateBtn(ActionEvent event) throws Exception {
if(NewDonorName.getText().isEmpty()||NewDonorName == null ||NewDonorCnic.getText().isEmpty()||NewDonorCnic == null ||NewDonorAge.getText().isEmpty()||NewDonorAge == null || NewDonorAddress.getText().isEmpty()||NewDonorAddress == null){
    Alert FieldEmptyAlert = new Alert(Alert.AlertType.ERROR, "FIELD EMPTY", ButtonType.OK);
    FieldEmptyAlert.setContentText("Field is empty");
    FieldEmptyAlert.initModality(Modality.APPLICATION_MODAL);
    FieldEmptyAlert.showAndWait();
    NewDonorName.clear();
    NewDonorAge.clear();
    NewDonorAddress.clear();
    NewDonorCnic.clear();
    if(FieldEmptyAlert.getResult() == ButtonType.OK) {
        FieldEmptyAlert.close();
    }
}
else {
    Submission();
    Alert SuccessAlert = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
    SuccessAlert.setContentText("Save Succesfull");
    SuccessAlert.showAndWait();
    if(SuccessAlert.getResult() == ButtonType.OK) {
        SuccessAlert.close();
        root= FXMLLoader.load(getClass().getResource("DoctorPanel.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BloodGroupData.add(new String("A+"));
        BloodGroupData.add(new String("A-"));
        BloodGroupData.add(new String("B+"));
        BloodGroupData.add(new String("B-"));
        BloodGroupData.add(new String("AB+"));
        BloodGroupData.add(new String("AB-"));
        BloodGroupData.add(new String("O+"));
        BloodGroupData.add(new String("O-"));
        BloodGroup.setItems(BloodGroupData);
    }
}
