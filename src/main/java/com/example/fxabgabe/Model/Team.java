package com.example.fxabgabe.Model;

import javafx.beans.property.*;

public class Team {
    private final StringProperty  name = new SimpleStringProperty(this, "name");
    private final IntegerProperty punkte = new SimpleIntegerProperty(this, "punkte");
    private final IntegerProperty kadergroesse = new SimpleIntegerProperty(this, "kadergroesse");
    private final DoubleProperty  marktwert = new SimpleDoubleProperty(this, "marktwert");
    private final IntegerProperty tore = new SimpleIntegerProperty(this, "tore");
    private final IntegerProperty gegentore = new SimpleIntegerProperty(this, "gegentore");
    private final IntegerProperty siege = new SimpleIntegerProperty(this, "siege");
    private final IntegerProperty unents = new SimpleIntegerProperty(this, "unents");
    private final IntegerProperty niederl = new SimpleIntegerProperty(this, "niederl");
    private final IntegerProperty spieleGespielt = new SimpleIntegerProperty(this, "spieleGespielt");
    private final IntegerProperty torDiff = new SimpleIntegerProperty(this, "torDiff");

    public Team(String name, int kadergroesse, double marktwert, int tore, int gegentore, int siege, int unents, int niederl) {
        this.name.set(name);
        this.kadergroesse.set(kadergroesse);
        this.marktwert.set(marktwert);
        this.tore.set(tore);
        this.gegentore.set(gegentore);
        this.siege.set(siege);
        this.unents.set(unents);
        this.niederl.set(niederl);
        this.punkte.set((siege * 3) + unents);
        this.spieleGespielt.set(siege + unents + niederl);
        this.torDiff.set(tore - gegentore);
    }

    //Für Bindings
    public StringProperty  nameProperty()      { return name; }
    public IntegerProperty punkteProperty()    { return punkte; }
    public DoubleProperty  marktwertProperty() { return marktwert; }
    public IntegerProperty toreProperty()      { return tore; }
    public IntegerProperty gegentoreProperty() { return gegentore; }
    public IntegerProperty siegeProperty()     { return siege; }
    public IntegerProperty unentsProperty()    { return unents; }
    public IntegerProperty niederlProperty()   { return niederl; }
    public IntegerProperty kadergroesseProperty() { return kadergroesse;}
    public IntegerProperty spieleGespieltProperty() {return spieleGespielt;}
    public IntegerProperty torDiffProperty() {return torDiff;}

    //Value Getter
    public String getName() { return name.get(); }
    public int getPunkte() { return punkte.get(); }
    public double getMarktwert() { return marktwert.get(); }
    public int getTore() { return tore.get(); }
    public int getGegentore() { return gegentore.get(); }
    public int getSiege() { return siege.get(); }
    public int getUnents() { return unents.get(); }
    public int getNiederl() { return niederl.get(); }
    public int getKadergroesse() {return kadergroesse.get();}
    public int getSpieleGespielt() {
        return spieleGespielt.get();
    }

    @Override
    public String toString() {
        return getName(); // damit z.B. ListView direkt den Namen anzeigt
    }
}