/**
 * project IJA21
 * date: 2021/05/08
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: newPozadavekController.java
 */
package cz.vut.fit.ija21.controller;

import cz.vut.fit.ija21.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ovladac pro zadavani uzivatelskych pozadavku
 */
public class newPozadavekController implements Initializable {

    @FXML
    private ListView<String> goodsList;
    @FXML
    private Text selectedGoods;
    @FXML
    private TextField goodsCount;
    ObservableList list = FXCollections.observableArrayList();

    /**
     * inicializace noveho pozadavku
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.nameGoodsRequest.clear();
        Main.countGoodsRequest.clear();
        load_goods();
    }

    /**
     * Vypsani dostupneho zbozi do listu
     */
    public void load_goods() {
        list.removeAll(list);

        for (String s : Main.goodsInShelfs) {
            if (!s.equals("0"))
                list.add(s);
        }

        goodsList.getItems().addAll(list);
    }

    /**
     * zobrazeni zbozi, ktere je oznaceno
     *
     * @param mouseEvent
     */
    public void gooodsSelected(MouseEvent mouseEvent) {
        selectedGoods.setText(goodsList.getSelectionModel().getSelectedItem());
    }

    /**
     * Pridani pozadavku po kliknuti tlacitka "pridat"
     *
     * @param mouseEvent
     */
    public void addPozadavek(MouseEvent mouseEvent) {
        Main.nameGoodsRequest.add(goodsList.getSelectionModel().getSelectedItem());
        Main.countGoodsRequest.add(goodsCount.getText());
    }

    /**
     * Zpracovani pozadavku po kliknuti tlacitka "Dokoncit"
     *
     * @param mouseEvent
     */
    public void zpracujPozadavek(MouseEvent mouseEvent) {
        // Zavre okno
        Main.nameGoodsRequest.add("x");
        Main.countGoodsRequest.add("0");
        Stage stage = (Stage) ((Button) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
