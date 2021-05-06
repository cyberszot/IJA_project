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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Ovladani uzivatelskeho rozhrani
 * @author Rene Szotkowski
 */
public class Controller {
    @FXML
    private ChoiceBox types;
    @FXML
    private Pane content;

    @FXML private Label time;

    private int minute;
    private int hour;
    private int second;
    // V púroměnné pozadavekFile je ulozen nazev souboru s pozadavkem.
    String pozadavekFile;


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


    public void onClickEvent(MouseEvent mouseEvent) {
        Integer shelfID = Integer.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()); //returns JUST the id of the object that was clicked
        if(!shelfID.equals("null")) {
            Stage shelfWindow = new Stage();
            shelfWindow.setTitle("shelf id: " + shelfID);
            // ilustracni data

            Goods goods1 = new StoreGoods(Main.goodsInShelfs.get(shelfID));
            Label goodsTypeText = new Label("Typ zboží: " + goods1.getName());
            Label goodsAmountText = new Label("Počet kusů: " + Main.goodsInShelfsCount.get(shelfID));
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
    }




}

