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
    /*
        private List<Canvas> elements = new ArrayList<>();

        public void setElements(List<Canvas> elements)
        {
            this.elements = elements;

            set_shelfs();
        }

        @FXML
        private void set_shelfs()
        {
            ObservableList<String> items = FXCollections.observableArrayList();
            for(Shelfs s : Main.get_goodsInShelfs())
            {
                items.add(s.getId());
            }

            types.setItems(items);
        }

       // public void naplnSklad() {
       //     String Shelfid =
        //    System.out.println(Shelfid);
       // }

    */
    public void onClickEvent(MouseEvent mouseEvent) {
        Integer shelfID = Integer.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()); //returns JUST the id of the object that was clicked
        if(!shelfID.equals("null")) {
            Stage shelfWindow = new Stage();
            shelfWindow.setTitle("shelf id: " + shelfID);
            // ilustracni data

            Goods goods2 = new StoreGoods("Zidlicka");
            //GoodsItem itm11 = goods1.newItem(LocalDate.of(2021, 1, 5));


            Goods goods1 = new StoreGoods(Main.goodsInShelfs.get(shelfID));
            Label goodsTypeText = new Label("typ zbozi: " + goods1.getName());
            Label goodsAmountText = new Label("mnozstvi: " + Main.goodsInShelfsCount.get(shelfID));
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
    }

    public void handlePozadavek2(MouseEvent mouseEvent) {
    }

    public void handlePozadavek3(MouseEvent mouseEvent) {
    }

/*
    @FXML
    public void set_label_time()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.GERMANY);
        LocalTime time = this.time;
        String f = formatter.format(time);

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                time_label.setText(f);
            }
        });
    }

    @FXML
    public void set_time()
    {
        String str_time = time_set_hh_text.getText() + ":" + time_set_mm_text.getText() + ":00";
        try
        {
            LocalTime s_time = LocalTime.parse(str_time);
            this.time = s_time;
        }
        catch (DateTimeParseException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Zadany cas neodpovida formatu HH:MM");
            alert.showAndWait();
        }
    }

    public void start(float time_c)
    {

    }
    */

}

