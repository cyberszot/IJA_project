/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *          xszotk07    :   Rene Szotkowski
 *
 * file: Main.java
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.time.LocalDate;


public class Main{

    /* GUI - pro tento ukol nepouzivam.
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        //Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("../../data/sample.fxml"));
        //primaryStage.setTitle("IJAProject");
        //primaryStage.setScene(new Scene(root, 640, 420));
        //primaryStage.show();

        /*  limits window resizability  */
        //primaryStage.setResizable(false);
        /*----------------------*/


       /* StoreGoods goods1 = new StoreGoods("okuRka");
        StoreGoods goods2 = new StoreGoods("okuRka");
        StoreGoods goods3 = new StoreGoods("okuRka");
        System.out.println(goods1.size());

        Goods goods1 = new StoreGoods("Stul");
        Goods goods2 = new StoreGoods("Zidle");

        GoodsItem itm11 = goods1.newItem(LocalDate.of(2021, 1, 5));

        System.out.println("Pocet kusu v seznamu zbozi: "+ goods1.size());


    }
    */

    public static void main(String[] args) {
        //launch(args);

        // Zakladni operace s objekty. Jen ciste textovy vystup.
        Goods goods1 = new StoreGoods("Stul");
        Goods goods2 = new StoreGoods("Zidle");

        GoodsItem itm11 = goods1.newItem(LocalDate.of(2021, 1, 5));

        System.out.println("Pocet kusu v seznamu zbozi: "+ goods1.size());
        System.out.println("Typ zbozi: "+ goods1.getName());
        goods1.remove(itm11);
        System.out.println("Pocet kusu v seznamu zbozi po odtraneni jednoho kusu: "+ goods1.size());

        System.out.println("Zbozi "+ goods1.getName() +" je prazdne: "+ goods1.empty());


    }

}
