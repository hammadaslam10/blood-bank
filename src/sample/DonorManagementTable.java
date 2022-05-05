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

import static java.lang.Integer.parseInt;

public class DonorManagementTable implements Initializable {
    @FXML
    private TableColumn<DonorDetails, String> NameRow;

    @FXML
    private TableView<DonorDetails> Table;
    static ObservableList<DonorDetails> ObservableTableList =FXCollections.observableArrayList();
    @FXML
    private TableColumn<DonorDetails, Number> CnicRow;

    @FXML
    private TableColumn< DonorDetails,String> BloodGrouo;

    @FXML
    private TableColumn<DonorDetails,Number > AgeRow;

    @FXML
    private TableColumn<DonorDetails, String> AddressRow;

    @FXML
    private TableColumn<DonorDetails, Number> BottlesToBeDonated;

    @FXML
    private TextField NameField;

    @FXML
    private TextField CnicField;

    @FXML
    private TextField AgeField;

    @FXML
    private TextField AddressField;

    @FXML
    private ComboBox<String> BloodGroup;
    ObservableList<String> BloodGroupData = FXCollections.observableArrayList();
    @FXML
    private CheckBox FirstBottle;

    @FXML
    private CheckBox SecondBottle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File File = new File("./DonorData.txt");
        if(File.length() !=0){
            try(BufferedReader br = new BufferedReader(new FileReader("./DonorData.txt"))){
                String thisLine="";
                while((thisLine= br.readLine())!=null){
                    String[] split = thisLine.split(",");
                    String Name = split[0];
                    int Cnic = Integer.parseInt(split[1]);
                    String BloodGroup = split[2];
                    int Age = Integer.parseInt(split[3]);
                    String Address =split[4];
                    int NumberOfBottles = Integer.parseInt(split[5]);
                    DonorDetails readData = new DonorDetails(Name,Cnic,BloodGroup,Age,Address,NumberOfBottles);
                    ObservableTableList.add(readData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BloodGroupData.add(new String("A+"));
        BloodGroupData.add(new String("A-"));
        BloodGroupData.add(new String("B+"));
        BloodGroupData.add(new String("B-"));
        BloodGroupData.add(new String("AB+"));
        BloodGroupData.add(new String("AB-"));
        BloodGroupData.add(new String("O+"));
        BloodGroupData.add(new String("O-"));
        BloodGroup.setItems(BloodGroupData);
        Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Table.setEditable(true);
        NameRow.setOnEditCommit(e->NameRowEdit(e));
        CnicRow.setOnEditCommit(e->CnicRowEdit(e));
        BloodGrouo.setOnEditCommit(e->BloodGroupEdit(e));
        AgeRow.setOnEditCommit(e->AgeEdit(e));
        AddressRow.setOnEditCommit(e->AddressEdit(e));
        BottlesToBeDonated.setOnEditCommit(e->BottlesEdit(e));

        NameRow.setCellFactory(TextFieldTableCell.forTableColumn());
        CnicRow.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        BloodGrouo.setCellFactory(TextFieldTableCell.forTableColumn());
        AgeRow.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        AddressRow.setCellFactory(TextFieldTableCell.forTableColumn());
        BottlesToBeDonated.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

        NameRow .setCellValueFactory(cellData -> cellData.getValue().donorNameProperty());
        CnicRow.setCellValueFactory(cellData -> cellData.getValue().donorCnicProperty());
        BloodGrouo.setCellValueFactory(cellData -> cellData.getValue().bloodGroupProperty());
        AgeRow.setCellValueFactory(cellData -> cellData.getValue().donorAgeProperty());
        AddressRow.setCellValueFactory(cellData -> cellData.getValue(). donorAddressProperty());
        BottlesToBeDonated.setCellValueFactory(cellData -> cellData.getValue().bottlesProperty());

        Table.setItems(ObservableTableList);

    }
private boolean Valid(ActionEvent event){
    Boolean validInput = true;
    if(NameField.getText().isEmpty()||NameField == null ||CnicField.getText().isEmpty()||CnicField == null ||AgeField.getText().isEmpty()||AgeField == null ||AddressField.getText().isEmpty()||AddressField == null){
        validInput=true;
        Alert FieldEmptyAlert = new Alert(Alert.AlertType.ERROR, "FIELD EMPTY", ButtonType.OK);
        FieldEmptyAlert.setContentText("Field is empty");
        FieldEmptyAlert.initModality(Modality.APPLICATION_MODAL);
        FieldEmptyAlert.showAndWait();
        NameField.clear();
        AgeField.clear();
        AddressField.clear();
        CnicField.clear();
        if(FieldEmptyAlert.getResult() == ButtonType.OK) {
            NameField.requestFocus();
            FieldEmptyAlert.close();
        }
    }
    return validInput;

}
    @FXML
    void AddMethod(ActionEvent event) throws IOException {
if(Valid(event)){
DonorDetails donorDetails= new DonorDetails();
donorDetails.setDonorName(NameField.getText());
donorDetails.setDonorCnic(Long.parseLong(CnicField.getText()));
donorDetails.setDonorAge(Long.parseLong(AgeField.getText()));
donorDetails.setDonorAddress(AddressField.getText());
donorDetails.setBloodGroup(BloodGroup.getValue());
if(FirstBottle.isSelected()){
    donorDetails.setBottles(1);
}
if(SecondBottle.isSelected())
{
    donorDetails.setBottles(2);
}
ObservableTableList.add(donorDetails);
Table.setItems(ObservableTableList);
    System.out.println(donorDetails.toString());
    handleSave(event);
    NameField.clear();
    AgeField.clear();
    AddressField.clear();
    CnicField.clear();
    BloodGroup.setValue("blood group");



}
    }


    public void AddressEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, String>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setDonorAddress(cellEditEvent.getNewValue());
    }


    public void AgeEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, Number>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setDonorAge((Long)cellEditEvent.getNewValue());

    }

    public void BloodGroupEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, String>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setBloodGroup(cellEditEvent.getNewValue());
    }

    public void BottlesEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, Number>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setBottles((Long)cellEditEvent.getNewValue());


    }

    public void CnicRowEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, Number>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setDonorCnic((Long)cellEditEvent.getNewValue());


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


    public void NameRowEdit(Event e) {
        TableColumn.CellEditEvent<DonorDetails, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DonorDetails, String>) e;
        DonorDetails donorDetails = cellEditEvent.getRowValue();
        donorDetails.setDonorName(cellEditEvent.getNewValue());
    }

    public static void loadDataStudent(){
        try(BufferedReader br = new BufferedReader(new FileReader("./DonorData.txt"))){
            String thisLine="";
            while((thisLine= br.readLine())!=null){
                String[] split = thisLine.split(",");
                String Name = split[0];
                int Cnic = Integer.parseInt(split[1]);
                String BloodGroup = split[2];
                int Age = Integer.parseInt(split[3]);
                String Address =split[4];
                int NumberOfBottles = Integer.parseInt(split[5]);

                DonorDetails readData = new DonorDetails(Name,Cnic,BloodGroup,Age,Address,NumberOfBottles);
                ObservableTableList.add(readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            File ss = new File("DonorData.txt");
            if(!ss.exists()){
                ss.createNewFile();

            }
            saveFile(Table.getItems(), ss);
        }
    }
    public void saveFile(ObservableList<DonorDetails> observableDonorList, File ss) {
        try {
            FileWriter append =new FileWriter(ss.getName(),true);
            //   BufferedWriter outWriter = new BufferedWriter(new FileWriter(ss));
//jo comment mara hai upar usse file me data add hoga or neche wala jo chal raha hai abhi us se overwrite
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(ss));


            for(DonorDetails donorDetails : observableDonorList) {
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
    @FXML
    void SecondbottleFunction(ActionEvent event) {
        if(SecondBottle.isSelected()){
            FirstBottle.setSelected(false);
        }
    }
    @FXML
    void FirstbottleFunction(ActionEvent event) {
        if(FirstBottle.isSelected()){
            SecondBottle.setSelected(false);
        }    }

    }

