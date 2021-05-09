/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Main.java
 * Hlavni trida aplikace, ktera spusti okno s rozhranim a naplni sklad ze souboru
 */
package cz.vut.fit.ija21.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;




public class Main extends Application{

    /**
     * list vsech zbozi ve skladu
     */
    public static List<String> goodsInShelfs = new ArrayList<>();

    /**
     * list mnozstvi vseho zbozi ve skladu
     */
    public static List<String> goodsInShelfsCount = new ArrayList<>();

    /**
     * list pozadovaneho zbozi
     */
    public static List<String> nameGoodsRequest = new ArrayList<>();

    /**
     * list mnozstvi pozadovaneho zbozi
     */
    public static List<String> countGoodsRequest = new ArrayList<>();


    /**
     *
     * @param primaryStage hlavni okno aplikace
     * @throws Exception vyjimka pri otevirani souboru s rozhranim skladu sample.fxml
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("IJAProject");
        primaryStage.setScene(new Scene(root, 640, 420));
        primaryStage.show();

        /*  limits window resizability  */
        primaryStage.setResizable(false);
        /*------------------------------*/

        Path path = Paths.get("");
        path = path.toAbsolutePath();
        String cesta = path.toString();


        //Naplnění regálu zbožím
        try {
            File obj = new File(cesta + "/data/goods.txt");
            Scanner myScanner = new Scanner(obj);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                String[] zbozi = data.split(";");

                String nazev = zbozi[0];
                String pocet = zbozi[1];

                goodsInShelfs.add(nazev);
                goodsInShelfsCount.add(pocet);

            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba pri otevirani souboru 'goods.txt'.");
            e.printStackTrace();
        }
    }
}
