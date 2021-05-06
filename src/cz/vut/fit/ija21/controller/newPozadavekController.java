package cz.vut.fit.ija21.controller;

import cz.vut.fit.ija21.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class newPozadavekController implements Initializable {

    @FXML private ListView<String> goodsList;
    ObservableList list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load_goods();
    }

    public void load_goods(){
        list.removeAll(list);

        for (String s: Main.goodsInShelfs) {
            if(!s.equals("0"))
                list.add(s);
        }

        goodsList.getItems().addAll(list);
    }
}
