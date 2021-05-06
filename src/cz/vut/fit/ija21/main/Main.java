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
import java.util.List;
import java.util.Scanner;




public class Main extends Application{

    public static List<Canvas> elem = new ArrayList<>();
    public static List<String> goodsInShelfs = new ArrayList<String>();
    public static List<String> goodsInShelfsCount = new ArrayList<String>();
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

              //  Goods goods = new StoreGoods(nazev);
               //System.out.println(goods.getName());
              //System.out.println(goods.size());

               goodsInShelfs.add(nazev);
               goodsInShelfsCount.add(pocet);

         //       shelfList.add(nazev);
         //       shelfList.add(pocet);
         //       Shelfs regal = new Shelfs(nazev, pocet);
         //       goodsInShelfs.add(regal.getId());
         //       goodsInShelfs.

               //elem.add(regal);
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba pri otevirani souboru 'goods.txt'.");
            e.printStackTrace();
        }

       // controller.setElements(elem);
    }

    public static void main(String[] args) {


        /*
        //Zakladni operace s objekty. Jen ciste textovy vystup.
        Goods goods1 = new StoreGoods("Stul");
        Goods goods2 = new StoreGoods("Zidle");

        GoodsItem itm11 = goods1.newItem(LocalDate.of(2021, 1, 5));

        System.out.println("Pocet kusu v seznamu zbozi: "+ goods1.size());
        System.out.println("Typ zbozi: "+ goods1.getName());
        goods1.remove(itm11);
        System.out.println("Pocet kusu v seznamu zbozi po odtraneni jednoho kusu: "+ goods1.size());

        System.out.println("Zbozi "+ goods1.getName() +" je prazdne: "+ goods1.empty());
        */

        launch(args);
    }

}
