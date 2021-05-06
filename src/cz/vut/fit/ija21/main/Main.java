/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Main.java
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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;




public class Main extends Application{

    public static List<Canvas> elem = new ArrayList<>();
    public static List<String> goodsInShelfs = new ArrayList<String>();
    public static List<String> goodsInShelfsCount = new ArrayList<String>();

    public static List<String> nameGoodsRequest = new ArrayList<String>();
    public static List<String> countGoodsRequest = new ArrayList<String>();



    //  public static List<Shelfs> get_goodsInShelfs(){
  //      return goodsInShelfs;
  //  }
    public static List<Canvas> get_elements(){
        return elem;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        List<String> shelfList = new ArrayList<>();

       // FXMLLoader ld =  FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        // BorderPane root = ld.load();
          // Scene scene = new Scene(root, 640, 420);
        primaryStage.setTitle("IJAProject");
       primaryStage.setScene(new Scene(root, 640, 420));

        primaryStage.show();


     //   Controller controller = ld.getController();

        /*  limits window resizability  */
        primaryStage.setResizable(false);
        /*----------------------*/

        Path path = Paths.get("");
        path = path.toAbsolutePath();
        String cesta = path.toString();


        //Naplnění regálu zbožím
        try {
            File obj = new File(cesta + "/data/goods.txt");
            Scanner myScanner = new Scanner(obj);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                String str = data.toString();
                String[] zbozi = str.split(";");

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
