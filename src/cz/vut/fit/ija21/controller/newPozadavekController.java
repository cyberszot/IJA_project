package cz.vut.fit.ija21.controller;

import cz.vut.fit.ija21.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class newPozadavekController implements Initializable {

    @FXML private ListView<String> goodsList;
    @FXML private Text selectedGoods;
    @FXML private TextField goodsCount;
    @FXML private Button finalise;
    ObservableList list = FXCollections.observableArrayList();
    private int pocet = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.nameGoodsRequest.clear();
        Main.countGoodsRequest.clear();
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

    public void gooodsSelected(MouseEvent mouseEvent) {
        selectedGoods.setText(goodsList.getSelectionModel().getSelectedItem());
    }

    public void addPozadavek(MouseEvent mouseEvent) {
        pocet++;
        Main.nameGoodsRequest.add(goodsList.getSelectionModel().getSelectedItem());
        Main.countGoodsRequest.add(goodsCount.getText());
    }

    public void zpracujPozadavek(MouseEvent mouseEvent) {
        int[] indexGoodsRequest = new int[pocet];
        int u = 0;
        int c = 0;
        // iterating over an array
        for (String i : Main.goodsInShelfs) {
            c++;
            for (String j : Main.nameGoodsRequest) {
                if (i.equals(j)) {
                    indexGoodsRequest[u] = c;
                    u++;
                }
            }
        }
        System.out.println(Main.nameGoodsRequest);
        System.out.println(Arrays.toString(indexGoodsRequest));
        Stage stage = (Stage) ((Button)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
