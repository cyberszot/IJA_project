package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        //Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("../../data/sample.fxml"));
        primaryStage.setTitle("IJAProject");
        primaryStage.setScene(new Scene(root, 640, 420));
        primaryStage.show();

        /*  limits window resizability  */
        primaryStage.setResizable(false);
        /*----------------------*/
    }

    public static void main(String[] args) { launch(args); }

}
