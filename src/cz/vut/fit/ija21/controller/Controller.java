/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Controller.java
 */
package cz.vut.fit.ija21.controller;


import cz.vut.fit.ija21.main.*;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.net.URL;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.util.Duration;


/**
 * Ovladani uzivatelskeho rozhrani
 * @author Rene Szotkowski
 */
public class Controller implements Initializable {

    public static List<Integer> indexGoodsRequest = new ArrayList<>();

    @FXML
    private Circle vuz;

    // V proměnné pozadavekFile je uložen název souboru s požadavky.
    String pozadavekFile;


    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        indexGoodsRequest.add(32);
        indexGoodsRequest.add(8);

        Collections.sort(indexGoodsRequest);
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
                92.0, 10.0,
        });

        for (int i = 0; i < indexGoodsRequest.size(); i++) {
            if (indexGoodsRequest.get(i) < 20) {
                System.out.println(indexGoodsRequest.get(i));
                polyline.getPoints().addAll(new Double[]{
                        92.00,
                });polyline.getPoints().addAll(new Double[]{
                        320.0,
                });
            }
            if (indexGoodsRequest.get(i) > 20) {
                System.out.println(indexGoodsRequest.get(i));
                polyline.getPoints().addAll(new Double[]{
                        92.00,
                });polyline.getPoints().addAll(new Double[]{
                        180.0,
                        150.0, 180.0,
                });
            }
        }



        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5));
       // transition.setDuration(Duration.seconds(4));
        pathTransition.setNode(vuz);
        pathTransition.setPath(polyline);
       // transition.setFromX(92);
      //  transition.setToY(100);
        pathTransition.play();
    }

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

    @FXML public void handleMakePozadavek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("newpozadavek.fxml"));
        Stage newPozadavek = new Stage();
        newPozadavek.setTitle("Vytvoř nový požadavek");
        newPozadavek.setScene(new Scene(root,420,260));
        newPozadavek.show();
    }

    @FXML public void handleObsahVoziku(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("obsahVoziku.fxml"));
        Stage newPozadavek = new Stage();
        newPozadavek.setTitle("Aktuální obsah vozíku");
        newPozadavek.setScene(new Scene(root,420,260));
        newPozadavek.show();
    }

    public void onClickEvent(MouseEvent mouseEvent) {
        Integer shelfID = Integer.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()); //returns JUST the id of the object that was clicked
        if(!shelfID.equals("null")) {
            Stage shelfWindow = new Stage();
            shelfWindow.setTitle("shelf id: " + shelfID);

            Label goodsTypeText = new Label("Zboží: " + Main.goodsInShelfs.get(shelfID));
            Label goodsAmountText = new Label("Počet kusů:: " + Main.goodsInShelfsCount.get(shelfID));

            VBox container = new VBox(goodsTypeText, goodsAmountText);
            container.setSpacing(15);
            container.setPadding(new Insets(15));
            container.setAlignment(Pos.CENTER_LEFT);

            shelfWindow.setScene(new Scene(container, 250, 100));
            shelfWindow.show();
            shelfWindow.setResizable(false);
        }
    }

    public void handleResetButton(MouseEvent mouseEvent) {
        Main.goodsInShelfs.clear();
        Main.goodsInShelfsCount.clear();

        Path path = Paths.get("");
        path = path.toAbsolutePath();
        String cesta = path.toString();

        try {
            File obj = new File(cesta + "/data/goods.txt");
            Scanner myScanner = new Scanner(obj);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                String[] zbozi = data.split(";");

                String nazev = zbozi[0];
                String pocet = zbozi[1];

                Main.goodsInShelfs.add(nazev);
                Main.goodsInShelfsCount.add(pocet);
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba pri otevirani souboru 'goods.txt'.");
            e.printStackTrace();
        }
    }

    public void handlePozadavek1(MouseEvent mouseEvent) {
        pozadavekFile = "pozadavky1.txt";
        zpracujPozadavek();
    }

    public void handlePozadavek2(MouseEvent mouseEvent) {
        pozadavekFile = "pozadavky2.txt";
        zpracujPozadavek();
    }

    public void handlePozadavek3(MouseEvent mouseEvent) {
        pozadavekFile = "pozadavky3.txt";
        zpracujPozadavek();
    }

    public void zpracujPozadavek() {
        Main.nameGoodsRequest.clear();
        Main.countGoodsRequest.clear();
        Path path = Paths.get("");
        path = path.toAbsolutePath();
        String cesta = path.toString();

        int pocet = 0;
        try {

            File obj = new File(cesta + "/data/"+pozadavekFile);
            Scanner scannerPozadavek = new Scanner(obj);
            while (scannerPozadavek.hasNextLine()) {
                pocet++;
                String data = scannerPozadavek.nextLine();
                String str = data.toString();
                String[] zboziP = str.split(";");

                Main.nameGoodsRequest.add(zboziP[0]);
                Main.countGoodsRequest.add(zboziP[1]);

            }
            scannerPozadavek.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba pri otevirani souboru s pozadavky.");
            e.printStackTrace();
        }

        //Uložení id, dle zadaneho zbozi v požadavku.
        int u = 0;
        int c = 0;
        // iterating over an array
        for (String i : Main.goodsInShelfs) {
            c++;
            for (String j : Main.nameGoodsRequest) {
                if (i.equals(j)) {
                    indexGoodsRequest.add(c);       //TODO
                    u++;
                }
            }
        }
        System.out.println(Main.nameGoodsRequest);
        System.out.println(indexGoodsRequest);
    }

    public void start(float time_c)
    {

    }


}

