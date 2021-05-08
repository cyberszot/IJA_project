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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.net.URL;




/**
 * Ovladani uzivatelskeho rozhrani
 * @author vsichni
 */
public class Controller implements Initializable {
    @FXML
    public AnchorPane root;
    public Integer vuzIndex = 0;


    // V proměnné pozadavekFile je uložen název souboru s požadavky.
    public String pozadavekFile;


    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub


    }

    public void obsluhaPozadavku(List<Integer> indexGoodsRequest) {
        Circle vuz = new Circle(92.0, 10.0, 13, Color.RED);
        vuz.setId("vozik:" + vuzIndex);
        vuzIndex++;
        root.getChildren().add(vuz);
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
        polyline.getPoints().addAll(92.0, 10.0);

        // obsluha cesty
        for (Integer integer : indexGoodsRequest) {
            shelf = integer % 10;
            if (shelf / 5 == 0) defaultPositionY = 35.00;
            else defaultPositionY = 50.00;
            if (integer / 20 == 0) {       // 1. rada
                nextLine = 0;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 0;
            }
            if (integer / 20 == 1) {       // 2. rada
                nextLine = 1;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 1;
            }
            if (integer / 20 == 2) {       // 3. rada
                nextLine = 2;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 2;
            }
            if (integer / 20 == 3) {       // 4. rada
                nextLine = 3;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 3;
            }
            if (integer / 20 == 4) {       // 5. rada
                nextLine = 4;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 4;
            }
            if (integer / 20 == 5) {       // 6. rada
                nextLine = 5;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 5;
            }
            if (integer / 20 == 6) {       // 7. rada
                nextLine = 6;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 6;
            }
            if (integer / 20 == 7) {       // 8. rada
                nextLine = 7;
                // dalsi pozadavek je v jine rade
                if (nextLine != actualLine)
                    polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
                actualLine = 7;
            }

            // posun voziku na pozici regalu v rade
            polyline.getPoints().addAll(defaultPositionX + lineOffset * nextLine, defaultPositionY + shelfOffset * shelf);
        }

        // homerun
        nextLine = 0;
        if(nextLine != actualLine) polyline = moveToLine(polyline, defaultPositionX + lineOffset * actualLine, defaultPositionX + lineOffset * nextLine, center);
        polyline.getPoints().addAll(defaultPositionX + lineOffset * nextLine, 10.00);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(50));
        // transition.setDuration(Duration.seconds(4));
        pathTransition.setNode(vuz);
        pathTransition.setPath(polyline);
        pathTransition.play();
    }

    public Polyline moveToLine(Polyline polyline, double X1, double X2, double Y){
        // nastaveni voziku na stred rady
        polyline.getPoints().addAll(X1, Y);

        // posun na dalsi radu
        polyline.getPoints().addAll(X2, Y);
        return polyline;
    }

    @FXML protected void handleQuitButtonAction(){
        Platform.exit();
    }

    @FXML protected void handleHelpButtonAction() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("help.fxml")));
        Stage help = new Stage();
        help.setTitle("About ija-app");
        help.setScene(new Scene(root, 420, 260));
        help.show();
        help.setResizable(false);
    }

    @FXML public void handleMakePozadavek() throws IOException {
        Parent koren = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("newpozadavek.fxml")));
        Stage newPozadavek = new Stage();
        newPozadavek.setTitle("Vytvoř nový požadavek");
        newPozadavek.setScene(new Scene(koren,420,260));
        newPozadavek.show();
    }

    /**
     * Otevreni noveho okna po kliknuti na vozik nebo regal
     * @param mouseEvent
     */
    public void onClickEvent(MouseEvent mouseEvent) {
        String id = mouseEvent.getPickResult().getIntersectedNode().getId();
        if(!id.equals("null")){
            if(id.contains("vozik")){
                Stage vozikWindow = new Stage();
                ListView<String> goodsNameCountList = new ListView<String>();
                ObservableList list = FXCollections.observableArrayList();
                Integer vozikID = Integer.parseInt(id.split(":")[1]);

                vozikWindow.setTitle("vozik: " + vozikID);

                Integer count = 0;
                Integer iterace = 0;
                List<String> nameGoodsRequest = new ArrayList<>();
                List<String> countGoodsRequest = new ArrayList<>();

                // magic
                for (int m=0; m < Main.nameGoodsRequest.size(); m++){
                    if(count + Integer.parseInt(Main.countGoodsRequest.get(m)) > 10 || m == Main.nameGoodsRequest.size() - 1){
                        if(m == Main.nameGoodsRequest.size() - 1){
                            nameGoodsRequest.add(Main.nameGoodsRequest.get(m));
                            countGoodsRequest.add(Main.countGoodsRequest.get(m));
                        }
                        iterace++;
                        if(iterace > vozikID)
                            break;
                        count=0;
                        if(m != Main.nameGoodsRequest.size() - 1)
                            m--;
                    }
                    else{
                        count += Integer.parseInt(Main.countGoodsRequest.get(m));
                        if(iterace == vozikID) {
                            nameGoodsRequest.add(Main.nameGoodsRequest.get(m));
                            countGoodsRequest.add(Main.countGoodsRequest.get(m));
                        }
                    }
                }

                list.addAll(nameGoodsRequest, countGoodsRequest);
                goodsNameCountList.getItems().addAll(list);

                VBox contain = new VBox(goodsNameCountList);
                contain.setSpacing(15);
                contain.setPadding(new Insets(15));
                contain.setAlignment(Pos.CENTER_LEFT);

                vozikWindow.setScene(new Scene(contain, 250, 100));
                vozikWindow.show();
                vozikWindow.setResizable(false);
                return;
            }
            Integer shelfID = Integer.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()); //returns JUST the id of the object that was clicked
            Stage shelfWindow = new Stage();
            shelfWindow.setTitle("shelf id: " + shelfID);

            Label goodsTypeText = new Label("Zboží: " + Main.goodsInShelfs.get(shelfID));
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

    /**
     * Vraceni zaplneni skladu na vychozi hodnotu
     */
    public void handleResetButton() {
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

    public void handlePozadavek1() throws IOException {
        pozadavekFile = "pozadavky1.txt";
        zpracujPozadavek();
    }

    public void handlePozadavek2() throws IOException {
        pozadavekFile = "pozadavky2.txt";
        zpracujPozadavek();
    }

    public void handlePozadavek3() throws IOException {
        pozadavekFile = "pozadavky3.txt";
        zpracujPozadavek();
    }

    /**
     * Zpracovani pozadavku ze souboru
     */
    public void zpracujPozadavek() throws IOException {
        Main.nameGoodsRequest.clear();
        Main.countGoodsRequest.clear();
        Path path = Paths.get("");
        path = path.toAbsolutePath();
        String cesta = path.toString();


        try {

            File obj = new File(cesta + "/data/" + pozadavekFile);
            Scanner scannerPozadavek = new Scanner(obj);
            while (scannerPozadavek.hasNextLine()) {
                String data = scannerPozadavek.nextLine();
                String[] zboziP = data.split(";");

                Main.nameGoodsRequest.add(zboziP[0]);
                Main.countGoodsRequest.add(zboziP[1]);

            }
            scannerPozadavek.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba pri otevirani souboru s pozadavky.");
            e.printStackTrace();
        }
        zaplnVoziky();
    }

    /**
     * Rozdeleni pozadavku na voziky
     */
    public void zaplnVoziky() {
        List<String> nameGoodsRequest = new ArrayList<>();
        List<String> countGoodsRequest = new ArrayList<>();
        List<Integer> indexGoodsRequest = new ArrayList<>();
        int count = 0;
        int c;
        int indexOfGoods;
        int indexOfRequest;
        int goodsCount;
        int requestedCount;
        vuzIndex = 0;

        //
        for (int m=0; m < Main.nameGoodsRequest.size(); m++){
            if(count + Integer.parseInt(Main.countGoodsRequest.get(m)) > 10 || m == Main.nameGoodsRequest.size() - 1){
                if(m == Main.nameGoodsRequest.size() - 1){
                    nameGoodsRequest.add(Main.nameGoodsRequest.get(m));
                    countGoodsRequest.add(Main.countGoodsRequest.get(m));
                }
                System.out.println("vozik: " + nameGoodsRequest);
                System.out.println("mnozstvi: " + countGoodsRequest);
                count = 0;
                c = 0;
                indexOfRequest = 0;

                //Uložení id, dle zadaneho zbozi v požadavku.
                for (String i : Main.goodsInShelfs) {
                    for (String j : nameGoodsRequest) {
                        if (i.equals(j)) {
                            indexGoodsRequest.add(c);
                            break;
                        }
                    }
                    c++;
                }

                for(String i : nameGoodsRequest){
                    indexOfGoods = 0;
                    for(String j: Main.goodsInShelfs){
                        if(i.equals(j)){
                            goodsCount = Integer.parseInt(Main.goodsInShelfsCount.get(indexOfGoods));
                            requestedCount = Integer.parseInt(countGoodsRequest.get(indexOfRequest));
                            goodsCount -= requestedCount;
                            Main.goodsInShelfsCount.set(indexOfGoods, Integer.toString(goodsCount));
                            break;
                        }
                        indexOfGoods++;
                    }
                    indexOfRequest++;
                }
                if(m != Main.nameGoodsRequest.size() - 1)
                    m--;
                obsluhaPozadavku(indexGoodsRequest);
                nameGoodsRequest.clear();
                countGoodsRequest.clear();
                indexGoodsRequest.clear();
            }
            else{
                count += Integer.parseInt(Main.countGoodsRequest.get(m));
                nameGoodsRequest.add(Main.nameGoodsRequest.get(m));
                countGoodsRequest.add(Main.countGoodsRequest.get(m));
            }
        }
    }

    public void start(float time_c)
    {

    }


}

