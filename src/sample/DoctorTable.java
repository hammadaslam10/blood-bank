package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
public class DoctorTable implements Initializable{
    @FXML
    private TableView<DoctorInfo> Table;
    static ObservableList<DoctorInfo> ObservableTableList =FXCollections.observableArrayList();

    @FXML
    private TableColumn<DoctorInfo,String> Name;

    @FXML
    private TableColumn<DoctorInfo, Number> Cnic;

    @FXML
    private TableColumn<DoctorInfo, Number> Reg;

    @FXML
    private TableColumn<DoctorInfo, Number> Age;

    @FXML
    private TableColumn<DoctorInfo,String> Pass;

    @FXML
    private TextField DocNameField;

    @FXML
    private TextField DocCnicField;

    @FXML
    private TextField DocRegNoField;

    @FXML
    private TextField DocAgeField;

    @FXML
    private TextField DocPassField;

    @FXML
    void AddMethod(ActionEvent event) throws IOException {
        if(Valid(event)){
            DoctorInfo doctorInfo= new DoctorInfo();
            doctorInfo.setName(DocNameField.getText());
            doctorInfo.setCnic(Long.parseLong(DocCnicField.getText()));
            doctorInfo.setReg(Long.parseLong(DocRegNoField.getText()));
            doctorInfo.setPassword(DocPassField.getText());
            doctorInfo.setAge(Long.parseLong(DocAgeField.getText()));
            ObservableTableList.add(doctorInfo);
            System.out.println(doctorInfo.toString());

            Table.setItems(ObservableTableList);
            handleSave(event);
            DocNameField.clear();
            DocCnicField.clear();
            DocRegNoField.clear();
            DocPassField.clear();
            DocAgeField.clear();
        }
    }

    @FXML
    void AgeEdit(Event e) {
        TableColumn.CellEditEvent<DoctorInfo, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DoctorInfo, Number>) e;
        DoctorInfo  doctorInfo= cellEditEvent.getRowValue();
        doctorInfo.setAge((Long)cellEditEvent.getNewValue());
    }

    @FXML
    void CnicnEdit(Event e) {
        TableColumn.CellEditEvent<DoctorInfo, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DoctorInfo, Number>) e;
        DoctorInfo  doctorInfo= cellEditEvent.getRowValue();
        doctorInfo.setCnic((Long)cellEditEvent.getNewValue());
    }

    @FXML
    void DeleteMethod(ActionEvent event) {
        if(!ObservableTableList.isEmpty()){
            System.out.println("Delete button CLicked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK){
                ObservableTableList.removeAll(Table.getSelectionModel().getSelectedItem());
                Table.getSelectionModel().clearSelection();
            }
            else{
                deleteAlert.close();

            }
        }
    }

    @FXML
    void NameEdit(Event e) {
        TableColumn.CellEditEvent<DoctorInfo, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DoctorInfo, String>) e;
       DoctorInfo  doctorInfo= cellEditEvent.getRowValue();
        doctorInfo.setName(cellEditEvent.getNewValue());
    }

    @FXML
    void PassEdit(Event e) {
        TableColumn.CellEditEvent<DoctorInfo, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DoctorInfo, String>) e;
        DoctorInfo  doctorInfo= cellEditEvent.getRowValue();
        doctorInfo.setPassword(cellEditEvent.getNewValue());
    }

    @FXML
    void RegEdit(Event e) {
        TableColumn.CellEditEvent<DoctorInfo, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DoctorInfo, Number>) e;
        DoctorInfo  doctorInfo= cellEditEvent.getRowValue();
        doctorInfo.setReg((Long)cellEditEvent.getNewValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File File = new File("./Data.txt");
        if(File.length() !=0){
            try(BufferedReader br = new BufferedReader(new FileReader("./Data.txt"))){
                String thisLine="";
                while((thisLine= br.readLine())!=null){
                    String[] split = thisLine.split(",");
                    String Name = split[0];
                    int Cnic =Integer.parseInt(split[1]);
                    int Reg =Integer.parseInt(split[2]);
                    int Age =Integer.parseInt(split[3]);
                    String Password = split[4];
                    DoctorInfo readData = new DoctorInfo(Name,  Cnic,Reg,Age,Password);
                    ObservableTableList.add(readData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            Table.setEditable(true);
            Name.setOnEditCommit(e->NameEdit(e));
            Cnic.setOnEditCommit(e->CnicnEdit(e));
            Reg.setOnEditCommit(e->RegEdit(e));
            Age.setOnEditCommit(e->AgeEdit(e));
            Pass.setOnEditCommit(e->CnicnEdit(e));

            Name.setCellFactory(TextFieldTableCell.forTableColumn());
            Cnic.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
            Reg.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
            Age.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
            Pass.setCellFactory(TextFieldTableCell.forTableColumn());

            Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            Cnic.setCellValueFactory(cellData -> cellData.getValue().cnicProperty());
            Reg.setCellValueFactory(cellData -> cellData.getValue().regProperty());
            Age.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
            Pass.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
            Table.setItems(ObservableTableList);

        }
    }
    private boolean Valid(ActionEvent event){
        Boolean validInput = true;
        if(DocNameField.getText().isEmpty()||DocNameField == null ||DocCnicField.getText().isEmpty()||DocCnicField == null ||DocRegNoField.getText().isEmpty()||DocRegNoField== null ||DocPassField.getText().isEmpty()||DocPassField == null){
            validInput=true;
            Alert FieldEmptyAlert = new Alert(Alert.AlertType.ERROR, "FIELD EMPTY", ButtonType.OK);
            FieldEmptyAlert.setContentText("Field is empty");
            FieldEmptyAlert.initModality(Modality.APPLICATION_MODAL);
            FieldEmptyAlert.showAndWait();
            DocNameField.clear();
            DocCnicField.clear();
            DocRegNoField.clear();
            DocPassField.clear();
            DocAgeField.clear();
            if(FieldEmptyAlert.getResult() == ButtonType.OK) {
                DocNameField.requestFocus();
                FieldEmptyAlert.close();
            }
        }
        return validInput;

    }
    public void handleSave(ActionEvent event) throws IOException {
        //   Stage secondaryStage = new Stage();
        // FileChooser fileChooser = new FileChooser();
        /// fileChooser.setTitle("Save-Student-Table");
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(ObservableTableList.isEmpty()) {
            //  secondaryStage.initOwner(this.filemenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            //  emptyTableAlert.initOwner(this.filemenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else {
            //   File file = fileChooser.showSaveDialog(secondaryStage);
            File ss = new File("Data.txt");
            if(!ss.exists()){
                ss.createNewFile();

            }
            saveFile(Table.getItems(), ss);
        }
    }
    public void saveFile(ObservableList<DoctorInfo> observableDonorList, File ss) {
        try {
            FileWriter append =new FileWriter(ss.getName(),true);
            //   BufferedWriter outWriter = new BufferedWriter(new FileWriter(ss));
//jo comment mara hai upar usse file me data add hoga or neche wala jo chal raha hai abhi us se overwrite
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(ss));


            for(DoctorInfo donorDetails : observableDonorList) {
                outWriter.write(donorDetails.toString());
                outWriter.newLine();
            }
            //System.out.println(observableStudentList.toString());

            System.out.println(observableDonorList.toString());
            //System.out.println(observableStudentList.toString());
            Alert SuccessAlert = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
            SuccessAlert.setContentText("Save Succesfull");
            SuccessAlert.showAndWait();
            if(SuccessAlert.getResult() == ButtonType.OK) {
                SuccessAlert.close();
            }
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
}
