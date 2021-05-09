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
    @FXML public Rectangle firstUp;
    @FXML public Rectangle secondUp;
    @FXML public Rectangle thirdUp;
    @FXML public Rectangle fourthUp;
    @FXML public Rectangle fifthUp;
    @FXML public Rectangle sixthUp;
    @FXML public Rectangle seventhUp;
    @FXML public Rectangle eighthUp;
    @FXML public Rectangle firstDown;
    @FXML public Rectangle secondDown;
    @FXML public Rectangle thirdDown;
    @FXML public Rectangle fourthDown;
    @FXML public Rectangle fifthDown;
    @FXML public Rectangle sixthDown;
    @FXML public Rectangle seventhDown;
    @FXML public Rectangle eighthDown;
    public boolean isFirstUp = false;
    public boolean isSecondUp = false;
    public boolean isThirdUp = false;
    public boolean isFourthUp = false;
    public boolean isFifthUp = false;
    public boolean isSixthUp = false;
    public boolean isSeventhUp = false;
    public boolean isEighthUp = false;
    public boolean isFirstDown = false;
    public boolean isSecondDown = false;
    public boolean isThirdDown = false;
    public boolean isFourthDown = false;
    public boolean isFifthDown = false;
    public boolean isSixthDown = false;
    public boolean isSeventhDown = false;
    public boolean isEighthDown = false;
    public Integer vuzIndex = 0;
    public List<String> allIndexGoodsRequest = new ArrayList<>();


    // V proměnné pozadavekFile je uložen název souboru s požadavky.
    public String pozadavekFile;


    public void initialize(URL location, ResourceBundle resources) {
        firstUp.setFill(Color.BLUE);
        secondUp.setFill(Color.BLUE);
        thirdUp.setFill(Color.BLUE);
        fourthUp.setFill(Color.BLUE);
        fifthUp.setFill(Color.BLUE);
        sixthUp.setFill(Color.BLUE);
        seventhUp.setFill(Color.BLUE);
        eighthUp.setFill(Color.BLUE);
        firstDown.setFill(Color.BLUE);
        secondDown.setFill(Color.BLUE);
        thirdDown.setFill(Color.BLUE);
        fourthDown.setFill(Color.BLUE);
        fifthDown.setFill(Color.BLUE);
        sixthDown.setFill(Color.BLUE);
        seventhDown.setFill(Color.BLUE);
        eighthDown.setFill(Color.BLUE);
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
            // po kliknuti na zabranu firstUp se nastavi prepinac isFirstUp a zmeni barva pro indikaci atd pro ostatni
            if(id.equals("firstUp")){
                isFirstUp = !isFirstUp;
                if(firstUp.getFill().equals(Color.BLUE))
                    firstUp.setFill(Color.RED);
                else
                    firstUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("secondUp")){
                isSecondUp = !isSecondUp;
                if(secondUp.getFill().equals(Color.BLUE))
                    secondUp.setFill(Color.RED);
                else
                    secondUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("thirdUp")){
                isThirdUp = !isThirdUp;
                if(thirdUp.getFill().equals(Color.BLUE))
                    thirdUp.setFill(Color.RED);
                else
                    thirdUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("fourthUp")){
                isFourthUp = !isFourthUp;
                if(fourthUp.getFill().equals(Color.BLUE))
                    fourthUp.setFill(Color.RED);
                else
                    fourthUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("fifthUp")){
                isFifthUp = !isFifthUp;
                if(fifthUp.getFill().equals(Color.BLUE))
                    fifthUp.setFill(Color.RED);
                else
                    fifthUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("sixthUp")){
                isSixthUp = !isSixthUp;
                if(sixthUp.getFill().equals(Color.BLUE))
                    sixthUp.setFill(Color.RED);
                else
                    sixthUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("seventhUp")){
                isSeventhUp = !isSeventhUp;
                if(seventhUp.getFill().equals(Color.BLUE))
                    seventhUp.setFill(Color.RED);
                else
                    seventhUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("eighthUp")){
                isEighthUp = !isEighthUp;
                if(eighthUp.getFill().equals(Color.BLUE))
                    eighthUp.setFill(Color.RED);
                else
                    eighthUp.setFill(Color.BLUE);
                return;
            }
            if(id.equals("firstDown")){
                isFirstDown = !isFirstDown;
                if(firstDown.getFill().equals(Color.BLUE))
                    firstDown.setFill(Color.RED);
                else
                    firstDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("secondDown")){
                isSecondDown = !isSecondDown;
                if(secondDown.getFill().equals(Color.BLUE))
                    secondDown.setFill(Color.RED);
                else
                    secondDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("thirdDown")){
                isThirdDown = !isThirdDown;
                if(thirdDown.getFill().equals(Color.BLUE))
                    thirdDown.setFill(Color.RED);
                else
                    thirdDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("fourthDown")){
                isFourthDown = !isFourthDown;
                if(fourthDown.getFill().equals(Color.BLUE))
                    fourthDown.setFill(Color.RED);
                else
                    fourthDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("fifthDown")){
                isFifthDown = !isFifthDown;
                if(fifthDown.getFill().equals(Color.BLUE))
                    fifthDown.setFill(Color.RED);
                else
                    fifthDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("sixthDown")){
                isSixthDown = !isSixthDown;
                if(sixthDown.getFill().equals(Color.BLUE))
                    sixthDown.setFill(Color.RED);
                else
                    sixthDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("seventhDown")){
                isSeventhDown = !isSeventhDown;
                if(seventhDown.getFill().equals(Color.BLUE))
                    seventhDown.setFill(Color.RED);
                else
                    seventhDown.setFill(Color.BLUE);
                return;
            }
            if(id.equals("eighthDown")){
                isEighthDown = !isEighthDown;
                if(eighthDown.getFill().equals(Color.BLUE))
                    eighthDown.setFill(Color.RED);
                else
                    eighthDown.setFill(Color.BLUE);
                System.out.println("isEighthDown: " + isEighthDown);
                return;
            }




            // po kliknuti na symbol voziku
            if(id.contains("vozik")){
                Stage vozikWindow = new Stage();
                ListView<String> goodsNameCountList = new ListView<>();
                ObservableList list = FXCollections.observableArrayList();
                Integer vozikID = Integer.parseInt(id.split(":")[1]);

                vozikWindow.setTitle("vozik: " + vozikID);

                Integer count = 0;
                Integer iterace = 0;
                List<String> nameGoodsRequest = new ArrayList<>();
                List<String> countGoodsRequest = new ArrayList<>();

                // magic vybere jen veci ktere ma vozik podle id dovest
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
                        if(iterace.equals(vozikID)) {
                            nameGoodsRequest.add(Main.nameGoodsRequest.get(m));
                            countGoodsRequest.add(Main.countGoodsRequest.get(m));
                        }
                    }
                }

                // naplni list pripravenyma vecma
                list.addAll(nameGoodsRequest, countGoodsRequest);
                goodsNameCountList.getItems().addAll(list);

                // vytvoreni noveho okna s listem veci na dovoz
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

