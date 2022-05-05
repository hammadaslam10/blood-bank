package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class Controller {

    @FXML
    private TextField CnicField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button LoginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void Entry(ActionEvent event) throws  Exception{
        String TempName = CnicField.getText();
        String TempPass = PasswordField.getText();
        Boolean Admin = false;
        if (TempName.equals("admin") && TempPass.equals("123")) {
            Admin = true;

            root = FXMLLoader.load(getClass().getResource("DoctorTable.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }
        Boolean Doctor = false;
        try {
            BufferedReader auth = new BufferedReader(new FileReader("./Data.txt"));
            String thisLine = "";

            while ((thisLine = auth.readLine()) != null) {
                String[] split = thisLine.split(",");
                String Name = split[0];
                int Cnic =Integer.parseInt(split[1]);
                int Reg =Integer.parseInt(split[2]);
                int Age =Integer.parseInt(split[3]);
                String Password = split[4];


                if ((TempName.equals(Name)) && (TempPass.equals(Password))) {
                    Doctor = true;

                    root = FXMLLoader.load(getClass().getResource("DoctorPanel.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    File thefile = new File("./Temp.txt");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(thefile));
                    String data = DoctorVoid(Name,Cnic,Reg,Age,Password);
                    bw.write(data);
                    bw.close();


                }

            }
            if ( Doctor==false && Admin==false) {
                Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
                ioAlert.setContentText("Sorry. An error has occurred.");
                ioAlert.showAndWait();
                if (ioAlert.getResult() == ButtonType.OK) {
                    ioAlert.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String DoctorVoid(String name, int cnic,int RegstrationId, int age,String Password){
        return name+","+cnic+","+RegstrationId+","+age+","+Password;
    }


}
