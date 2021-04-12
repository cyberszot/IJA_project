package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("../../data/sample.fxml"));
        primaryStage.setTitle("hoj");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args); }

}
