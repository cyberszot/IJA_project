/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Controller.java
 */
package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * Ovladani uzivatelskeho rozhrani
 * @author Rene Szotkowski
 */
public class Controller {
    @FXML protected void handleQuitButtonAction(ActionEvent event){
        Platform.exit();
    }
    @FXML protected void handleHelpButtonAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("help.fxml"));
        Stage help = new Stage();
        help.setTitle("About ija-app");
        help.setScene(new Scene(root, 420, 260));
        help.show();
        help.setResizable(false);
    }
}

