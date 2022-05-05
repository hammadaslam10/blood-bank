package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DoctorPanel {

    @FXML
    private Button NewExist;

    @FXML
    private Button BloodTable;

    @FXML
    private Button SearchBloodTable;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void BloodTable(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("DonorTable.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void NewExist(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("NEWDONOR.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SearchBloodTable(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("ExistingDonor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
