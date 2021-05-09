/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Controller.java
 * Trida pro ovladani hlavniho rozhrani skladu
 */
package cz.vut.fit.ija21.controller;


import cz.vut.fit.ija21.main.*;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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
    public List<Integer> allIndexGoodsRequest = new ArrayList<>();
    double zoom = 1.00;

    // V proměnné pozadavekFile je uložen název souboru s požadavky.
    public String pozadavekFile;
    // urcuje rychlost pohybu
    public int SpeedOfCoolVoziku = 3;

    /**
     * Nastaveni potrebnych prepinacu po inicializaci
     * @param location location
     * @param resources resources
     */
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


    /**
     * Vytvori novy vuz, ktery prida na rozhrani a dale pro nej necha vytvorit cestu
     * @param indexGoodsRequest list indexu regalu, ktere se maji obslouzit danym vozem
     */
    public void obsluhaPozadavku(List<Integer> indexGoodsRequest) {
        Circle vuz = new Circle(92.0, 10.0, 13, Color.RED);
        vuz.setId("vozik:" + vuzIndex);
        vuzIndex++;
        root.getChildren().add(vuz);
        vytvorCestu(indexGoodsRequest, vuz);
    }

    /**
     * Posune vozik na danou radu
     * @param polyline trasa animace
     * @param X1 x souradnice aktualni rady
     * @param X2 x souradnice dalsi rady
     * @param Y y souradnice prostredni cesty
     * @return vraci upravenou trasu pro pohyb do dalsi rady
     */
    public Polyline moveToLine(Polyline polyline, double X1, double X2, double Y){
        // nastaveni voziku na stred rady
        polyline.getPoints().addAll(X1, Y);

        // posun na dalsi radu
        polyline.getPoints().addAll(X2, Y);
        return polyline;
    }

    /**
     * Vypne aplikaci
     */
    @FXML protected void handleQuitButtonAction(){
        Platform.exit();
    }

    /**
     * Otevre okno pro zadani uzivatelskeho pozadavku
     * @throws IOException otevirani noveho okna
     */
    @FXML public void handleMakePozadavek() throws IOException {
        Parent koren = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("newpozadavek.fxml")));
        Stage newPozadavek = new Stage();
        newPozadavek.setTitle("Vytvoř nový požadavek");
        newPozadavek.setScene(new Scene(koren,420,260));
        newPozadavek.show();
    }

    /**
     * Otevreni noveho okna po kliknuti na vozik nebo regal
     * @param mouseEvent parametr podle ktereho se zjisti id kliknuteho objektu
     */
    public void onClickEvent(MouseEvent mouseEvent) {
        String id = mouseEvent.getPickResult().getIntersectedNode().getId();
        if(!id.equals("null")){
            // po kliknuti na danou blokaci se prepne prepinac a nastavi se jina barva
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
                return;
            }

            // po kliknuti na symbol voziku
            if(id.contains("vozik")){
                Integer vozikID = Integer.parseInt(id.split(":")[1]);
                kliknutiNaVozik(vozikID);
                return;
            }

            // po kliknuti na regal
            Integer shelfID = Integer.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()); // vraci id regalu
            kliknutiNaRegal(shelfID);
        }
    }

    /**
     * Vraceni skladu na zacatecni stav
     */
    public void handleResetButton() {
        Main.goodsInShelfs.clear();
        Main.goodsInShelfsCount.clear();
        zaplSklad();
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
        isFirstUp = false;
        isSecondUp = false;
        isThirdUp = false;
        isFourthUp = false;
        isFifthUp = false;
        isSixthUp = false;
        isSeventhUp = false;
        isEighthUp = false;
        isFirstDown = false;
        isSecondDown = false;
        isThirdDown = false;
        isFourthDown = false;
        isFifthDown = false;
        isSixthDown = false;
        isSeventhDown = false;
        isEighthDown = false;
    }



    /**
     * Zaplneni skladu ze souboru goods.txt
     */
    public void zaplSklad(){
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

    /**
     * Cteni pozadavku ze souboru
     */
    public void prectiPozadavek(){
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
    }

    /**
     * Zpracovani pozadavku ze souboru, tlacitko 1
     */
    public void handlePozadavek1() {
        pozadavekFile = "pozadavky1.txt";
        zpracujPozadavek();
    }

    /**
     * Zpracovani pozadavku ze souboru, tlacitko 2
     */
    public void handlePozadavek2() {
        pozadavekFile = "pozadavky2.txt";
        zpracujPozadavek();
    }

    /**
     * Zpracovani pozadavku ze souboru, tlacitko 3
     */
    public void handlePozadavek3() {
        pozadavekFile = "pozadavky3.txt";
        zpracujPozadavek();
    }

    /**
     * Zmena rychlosti animace, tlacitko 1
     */
    public void handleSpeed1() {
        SpeedOfCoolVoziku = 7;
    }

    /**
     * Zmena rychlosti animace, tlacitko 2
     */
    public void handleSpeed2() {
        SpeedOfCoolVoziku = 5;
    }

    /**
     * Zmena rychlosti animace, tlacitko 3
     */
    public void handleSpeed3() {
        SpeedOfCoolVoziku = 3;
    }

    /**
     * Zmena rychlosti animace, tlacitko 4
     */
    public void handleSpeed4() {
        SpeedOfCoolVoziku = 1;
    }

    /**
     * Zpracovani pozadavku ze souboru
     */
    public void zpracujPozadavek() {
        Main.nameGoodsRequest.clear();
        Main.countGoodsRequest.clear();
        prectiPozadavek();
        zaplnVoziky();
    }

    /**
     * Otevre okno se zbozim v regalu a jeho mnozstvim
     * @param shelfID id kliknuteho regalu
     */
    public void kliknutiNaRegal(Integer shelfID) {
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

    /**
     * Priblizeni a oddaleni obrazu
     * @param scrollEvent Zjisteni jakym smerem uzivatel toci koleckem
     */
    public void zoom(ScrollEvent scrollEvent) {
        if(scrollEvent.getDeltaY() > 0 && zoom >= 1.00){    // scrolluje nahoru
            zoom += 0.05;
            root.setTranslateX(-(scrollEvent.getX() - 320.00));
            root.setTranslateY(-(scrollEvent.getY() - 210.00));
            root.setScaleX(zoom);
            root.setScaleY(zoom);
        }
        else{                           // scrolluje dolu
            if(zoom != 1.00)
                zoom -= 0.05;
            else {
                root.setTranslateX(0.00);
                root.setTranslateY(0.00);
            }
            root.setScaleX(zoom);
            root.setScaleY(zoom);
        }
    }

    /**
     * Vytvori cestu na zaklade indexu pro dany vuz
     * @param indexGoodsRequest indexy zastavek
     * @param vuz kolecko vozu
     */
    public void vytvorCestu(List<Integer> indexGoodsRequest, Circle vuz) {
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
        pathTransition.setDuration(Duration.seconds(SpeedOfCoolVoziku*(actualLine+1)));
        pathTransition.setNode(vuz);
        pathTransition.setPath(polyline);
        pathTransition.play();
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
        int shelf = 0;
        int line = 0;
        vuzIndex = 0;
        allIndexGoodsRequest.clear();

        int x;
        for (String i : Main.nameGoodsRequest) {
            x = 0;
            for (String j : Main.goodsInShelfs) {
                if (i.equals(j)) {
                    allIndexGoodsRequest.add(x);
                    break;
                }
                x++;
            }
        }
        allIndexGoodsRequest.add(999);


        // rozdeleni po max 10 kusech na vozik
        for (int m=0; m < Main.nameGoodsRequest.size(); m++){
            if(allIndexGoodsRequest.get(m) != 999) {
                shelf = allIndexGoodsRequest.get(m) % 10;
                line = allIndexGoodsRequest.get(m) / 20;
                // omezeni podle zablokovane trasy
                if (isFirstUp) {
                    if(line != 0 || shelf >= 5)
                        continue;
                }
                if (isFirstDown) {
                    if(line == 0 && shelf >= 5){
                        continue;
                    }
                }
                if (isSecondUp) {
                    if (line == 1 && shelf < 5)
                        continue;
                }
                if (isSecondDown) {
                    if (line == 1 && shelf >= 5) {
                        continue;
                    }
                }
                if (isThirdUp) {
                    if (line == 2 && shelf < 5)
                        continue;
                }
                if (isThirdDown) {
                    if (line == 2 && shelf >= 5)
                        continue;
                }
                if (isFourthUp) {
                    if (line == 3 && shelf < 5)
                        continue;
                }
                if (isFourthDown) {
                    if (line == 3 && shelf >= 5)
                        continue;
                }
                if (isFifthUp) {
                    if (line == 4 && shelf < 5)
                        continue;
                }
                if (isFifthDown) {
                    if (line == 4 && shelf >= 5)
                        continue;
                }
                if (isSixthUp) {
                    if (line == 5 && shelf < 5)
                        continue;
                }
                if (isSixthDown) {
                    if (line == 5 && shelf >= 5)
                        continue;
                }
                if (isSeventhUp) {
                    if (line == 6 && shelf < 5)
                        continue;
                }
                if (isSeventhDown) {
                    if (line == 6 && shelf >= 5)
                        continue;
                }
                if (isEighthUp) {
                    if (line == 7 && shelf < 5)
                        continue;
                }
                if (isEighthDown) {
                    if (line == 7 && shelf >= 5)
                        continue;
                }
            }



            if(count + Integer.parseInt(Main.countGoodsRequest.get(m)) > 10 || allIndexGoodsRequest.get(m) == 999){
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

    /**
     * Spusteni uzivatelskeho pozadavku
     */
    public void spustitUzPozadavek() {
        zaplnVoziky();
    }

    /**
     * Otevre okno po kliknuti na symbol voziku se zbozim, mnozstvim a jeho cestou
     * @param vozikID id daneho voziku
     */
    public void kliknutiNaVozik(Integer vozikID) {
        Stage vozikWindow = new Stage();
        ListView<String> goodsNameCountList = new ListView<>();
        ObservableList list = FXCollections.observableArrayList();

        vozikWindow.setTitle("vozik: " + vozikID);

        Integer count = 0;
        Integer iterace = 0;
        Integer shelf;
        Integer line;
        List<String> nameGoodsRequest = new ArrayList<>();

        // vybere jen veci ktere ma vozik podle id dovest
        for (int m=0; m < Main.nameGoodsRequest.size(); m++){
            // omezeni podle zablokovane cesty
            if(allIndexGoodsRequest.get(m) != 999) {
                shelf = allIndexGoodsRequest.get(m) % 10;
                line = allIndexGoodsRequest.get(m) / 20;
                // omezeni podle zablokovane trasy
                if (isFirstUp) {
                    if(line != 0 || shelf >= 5)
                        continue;
                }
                if (isFirstDown) {
                    if(line == 0 && shelf >= 5){
                        continue;
                    }
                }
                if (isSecondUp) {
                    if (line == 1 && shelf < 5)
                        continue;
                }
                if (isSecondDown) {
                    if (line == 1 && shelf >= 5) {
                        continue;
                    }
                }
                if (isThirdUp) {
                    if (line == 2 && shelf < 5)
                        continue;
                }
                if (isThirdDown) {
                    if (line == 2 && shelf >= 5)
                        continue;
                }
                if (isFourthUp) {
                    if (line == 3 && shelf < 5)
                        continue;
                }
                if (isFourthDown) {
                    if (line == 3 && shelf >= 5)
                        continue;
                }
                if (isFifthUp) {
                    if (line == 4 && shelf < 5)
                        continue;
                }
                if (isFifthDown) {
                    if (line == 4 && shelf >= 5)
                        continue;
                }
                if (isSixthUp) {
                    if (line == 5 && shelf < 5)
                        continue;
                }
                if (isSixthDown) {
                    if (line == 5 && shelf >= 5)
                        continue;
                }
                if (isSeventhUp) {
                    if (line == 6 && shelf < 5)
                        continue;
                }
                if (isSeventhDown) {
                    if (line == 6 && shelf >= 5)
                        continue;
                }
                if (isEighthUp) {
                    if (line == 7 && shelf < 5)
                        continue;
                }
                if (isEighthDown) {
                    if (line == 7 && shelf >= 5)
                        continue;
                }
            }

            if(count + Integer.parseInt(Main.countGoodsRequest.get(m)) > 10 || allIndexGoodsRequest.get(m) == 999){
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
                    nameGoodsRequest.add(Main.nameGoodsRequest.get(m) + ", " + Main.countGoodsRequest.get(m) + ", " + allIndexGoodsRequest.get(m) + "\n");
                }
            }
        }

        // naplni list pripravenyma vecma
        list.addAll(nameGoodsRequest);
        goodsNameCountList.getItems().addAll(list);

        // vytvoreni noveho okna s listem veci na dovoz
        VBox contain = new VBox(goodsNameCountList);
        contain.setSpacing(15);
        contain.setPadding(new Insets(15));
        contain.setAlignment(Pos.CENTER_LEFT);

        vozikWindow.setScene(new Scene(contain, 150, 150));
        vozikWindow.show();
        vozikWindow.setResizable(false);
    }


}

