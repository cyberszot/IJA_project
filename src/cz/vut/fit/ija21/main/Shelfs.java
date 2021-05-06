package cz.vut.fit.ija21.main;

import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class Shelfs implements Canvas{
    private String id_s;
    //private ArrayList<Stop> stops = new ArrayList<>();
    private List<Shape> gui = new ArrayList<>();



    public Shelfs(java.lang.String id){

        Text nazev = new Text(id);
        gui.add(nazev);

        this.id_s = id;
    }

    public String getId(){
        return this.id_s;
    }

    @Override
    public List<Shape> getGUI(){
        return gui;
    }
}
