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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.net.URL;

import javafx.util.Duration;


/**
 * Ovladani uzivatelskeho rozhrani
 * @author Rene Szotkowski
 */
public class Controller implements Initializable {

    @FXML
    private Circle vuz;

    // V proměnné pozadavekFile je uložen název souboru s požadavky.
    String pozadavekFile;


    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub


    }

    public void obsluhaPozadavku(List<Integer> indexGoodsRequest){
        int actualLine = 0;
        int nextLine = 0;
        int shelf;
        double defaultPositionX = 92.00;
        double defaultPositionY;
        double shelfOffset = 30.00;
        double lineOffset = 65.00;
        double center = 180.00;


        Collections.sort(indexGoodsRequest);
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
                92.0, 10.0,
        });

        // obsluha cesty
        for (int i = 0; i < indexGoodsRequest.size(); i++) {
            shelf = indexGoodsRequest.get(i) % 10;
            if(shelf / 5 == 0) defaultPositionY = 35.00;
            else defaultPositionY = 50.00;
            if (indexGoodsRequest.get(i) / 20 == 0) {       // 1. rada
                nextLine = 0;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 0;
            }
            if (indexGoodsRequest.get(i) / 20 == 1) {       // 2. rada
                nextLine = 1;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 1;
            }
            if (indexGoodsRequest.get(i) / 20 == 2) {       // 3. rada
                nextLine = 2;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 2;
            }
            if (indexGoodsRequest.get(i) / 20 == 3) {       // 4. rada
                nextLine = 3;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 3;
            }
            if (indexGoodsRequest.get(i) / 20 == 4) {       // 5. rada
                nextLine = 4;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 4;
            }
            if (indexGoodsRequest.get(i) / 20 == 5) {       // 6. rada
                nextLine = 5;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 5;
            }
            if (indexGoodsRequest.get(i) / 20 == 6) {       // 7. rada
                nextLine = 6;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 6;
            }
            if (indexGoodsRequest.get(i) / 20 == 7) {       // 8. rada
                nextLine = 7;
                // dalsi pozadavek je v jine rade
                if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 7;
            }

            // posun voziku na pozici regalu v rade
            polyline.getPoints().addAll(new Double[]{
                    defaultPositionX + lineOffset * nextLine, defaultPositionY + shelfOffset * shelf,
            });
        }

        // homerun
        nextLine = 0;
        if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
        polyline.getPoints().addAll(new Double[]{
                defaultPositionX + lineOffset * nextLine, 10.00,
        });


        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(20));
        // transition.setDuration(Duration.seconds(4));
        pathTransition.setNode(vuz);
        pathTransition.setPath(polyline);
        // transition.setFromX(92);
        //  transition.setToY(100);
        pathTransition.play();
    }

    public Polyline moveToLine(Polyline polyline, double X1, double X2, double Y){
        // nastaveni voziku na stred rady
        polyline.getPoints().addAll(new Double[]{
                X1, Y
        });

        // posun na dalsi radu
        polyline.getPoints().addAll(new Double[]{
                X2, Y
        });
        return polyline;
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


        try {

            File obj = new File(cesta + "/data/"+pozadavekFile);
            Scanner scannerPozadavek = new Scanner(obj);
            while (scannerPozadavek.hasNextLine()) {
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




        List<Integer> indexGoodsRequest = new ArrayList<>();

        //Uložení id, dle zadaneho zbozi v požadavku.
        int c = 0;
        // iterating over an array
        for (String i : Main.goodsInShelfs) {
            for (String j : Main.nameGoodsRequest) {
                if (i.equals(j)) {
                    indexGoodsRequest.add(c);
                    break;
                }
            }
            c++;
        }

        int indexOfGoods;
        int indexOfRequest = 0;
        int goodsCount;
        int requestedCount;
        for(String i : Main.nameGoodsRequest){
            System.out.println("Hledam: " + Main.nameGoodsRequest.get(indexOfRequest));
            indexOfGoods = 0;
            for(String j: Main.goodsInShelfs){
                if(i.equals(j)){
                    goodsCount = Integer.valueOf(Main.goodsInShelfsCount.get(indexOfGoods));
                    requestedCount = Integer.valueOf(Main.countGoodsRequest.get(indexOfRequest));
                    goodsCount -= requestedCount;
                    Main.goodsInShelfsCount.set(indexOfGoods, Integer.toString(goodsCount));
                    break;
                }
                indexOfGoods++;
            }
            indexOfRequest++;
        }

        System.out.println(Main.nameGoodsRequest);
        System.out.println(Main.countGoodsRequest);
        System.out.println(indexGoodsRequest);
        obsluhaPozadavku(indexGoodsRequest);
    }

    public void start(float time_c)
    {

    }


}

