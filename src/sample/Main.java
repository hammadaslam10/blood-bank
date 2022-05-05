package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
            primaryStage.setTitle("Bank MANAGEMENT");

            primaryStage.setResizable(false);
            Scene se=new Scene(root);


            primaryStage.setScene(se);
            // String css=this.getClass().getResource("Styling.css").toExternalForm();
            //  se.getStylesheets().add(css);
            primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
