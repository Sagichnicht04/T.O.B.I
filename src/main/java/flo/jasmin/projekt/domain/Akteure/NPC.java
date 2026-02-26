package flo.jasmin.projekt.domain.Akteure;

import java.util.ArrayList;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

public class NPC {
    private String name;
    private ArrayList<String> dialog;
    private AsciiVisualisierung visualisierung;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDialog() {
        return dialog;
    }

    public void setDialog(ArrayList<String> dialog) {
        this.dialog = dialog;
    }

    public AsciiVisualisierung getVisualisierung() {
        return visualisierung;
    }

    public void setVisualisierung(AsciiVisualisierung visualisierung) {
        this.visualisierung = visualisierung;
    }
}