package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ExistingDonor implements Initializable {
    @FXML
    private TextField ExistDonorName;

    @FXML
    private TextField ExistDonorCnic;

    @FXML
    private TextField ExistDonorAge;

    @FXML
    private TextField ExistDonorAddress;

    @FXML
    private Button ExistDonateBtn;

    //@FXML
    //private ComboBox<String> BloodGroup;
    //ObservableList<String> BloodGroupData = FXCollections.observableArrayList();
    @FXML
    private CheckBox Exist1Bottle;

    @FXML
    private CheckBox Exist2Bottle;

    @FXML
    private TextField SearchCnic;

    @FXML
    private Button SearchCnicBtn;
    @FXML
    private TextField BloodGroupField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void New1Bottle(ActionEvent event) {
if(Exist1Bottle.isSelected()){
Exist2Bottle.setSelected(false);
}    }

    @FXML
    void New2Bottle(ActionEvent event) {
        if(Exist2Bottle.isSelected()){
            Exist1Bottle.setSelected(false);
        }
    }

    @FXML
    void NewDonateBtn(ActionEvent event) {

        try {

            BufferedReader auth = new BufferedReader(new FileReader("./DonorData.txt"));
            String thisLine = "";
boolean a= false;
            while ((thisLine = auth.readLine()) != null) {

                String[] split = thisLine.split(",");
                String Name = split[0];
                int Cnic = Integer.parseInt(split[1]);
                String BloodGroup = split[2];
                int Age = Integer.parseInt(split[3]);
                String Address =split[4];
                int NumberOfBottles = Integer.parseInt(split[5]);
                int num ;
                if(Exist1Bottle.isSelected()==true){
                    if(NumberOfBottles>=1){
                        a= true;
                        num=NumberOfBottles-1;
                        modifyFile("./DonorData.txt", String.valueOf(NumberOfBottles),String.valueOf(num));



                    }

                }
                if(Exist2Bottle.isSelected()==true){
                    if(NumberOfBottles>=2){
                        a= true;
                        num=NumberOfBottles-2;
                        modifyFile("./DonorData.txt", String.valueOf(NumberOfBottles),String.valueOf(num));

                    }
                         }




            }
            if(a==false){
                Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK,ButtonType.CANCEL);
                ioAlert.setContentText("insufficent amount of bottles");
                ioAlert.showAndWait();

            }else{

                Alert ioAlert = new Alert(Alert.AlertType.INFORMATION, "OOPS!", ButtonType.OK,ButtonType.CANCEL);
                ioAlert.setContentText("Donation Done");
                ioAlert.showAndWait();
                root = FXMLLoader.load(getClass().getResource("DoctorPanel.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }        }catch(Exception e){
        }
    }

    @FXML
    void NewDonorGroup(ActionEvent event) {

    }

    @FXML
    void SearchCnicBtn(ActionEvent event) {
        String TempId=SearchCnic.getText();


        try {
Boolean b= true;
            BufferedReader auth = new BufferedReader(new FileReader("./DonorData.txt"));
            String thisLine = "";

            while ((thisLine = auth.readLine()) != null) {
                String[] split = thisLine.split(",");
                String Name = split[0];
                String Cnic = split[1];
                String BloodGroup = split[2];
                int Age = Integer.parseInt(split[3]);
                String Address =split[4];
                int NumberOfBottles = Integer.parseInt(split[5]);
                if (TempId.equals(Cnic)) {
b=false;
                   // Doctor = true;

                  /*  ExistDonateBtn.setDisable(false);
                    ExistDonorName.setDisable(false);
                    ExistDonorCnic.setDisable(false);
                    ExistDonorAge.setDisable(false);
                    ExistDonorAddress.setDisable(false);
                    BloodGroupField.setDisable(false);*/
ExistDonorName.setText(Name);
ExistDonorCnic.setText(String.valueOf(Cnic));
ExistDonorAge.setText(String.valueOf(Age));
ExistDonorAddress.setText(Address);
BloodGroupField.setText(String.valueOf(BloodGroup));
ExistDonateBtn.setDisable(false);

                    File thefile = new File("./TempDonor.txt");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(thefile));
                   String data = DonorVoid(Name,Cnic,BloodGroup,Age,Address,NumberOfBottles );
                    bw.write(data);
                    bw.close();



                }

            }if(b==true){
                Alert ioAlert = new Alert(Alert.AlertType.WARNING, "OOPS!", ButtonType.OK,ButtonType.CANCEL);
                ioAlert.setContentText("Incorrect");
                ioAlert.showAndWait();
                if (ioAlert.getResult() == ButtonType.OK) {
                    root = FXMLLoader.load(getClass().getResource("NewDonor.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                if(ioAlert.getResult()==ButtonType.CANCEL){
                    ioAlert.close();
                }
            }
        }catch(Exception e){
        }
            }
            public String DonorVoid(String Name,String Cnic, String  BloodGroup,int Age,String Address, int NumberOfBottles){
return Name+","+Cnic+","+BloodGroup+","+Age+","+Address+","+NumberOfBottles;

            }    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     /*   ExistDonateBtn.setDisable(true);
        ExistDonorName.setDisable(true);
        ExistDonorCnic.setDisable(true);
        ExistDonorAge.setDisable(true);
        ExistDonorAddress.setDisable(true);
        BloodGroupField.setDisable(true);*/



    }
    static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources

                reader.close();

                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
