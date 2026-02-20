package domain.Akteure;

import domain.Visualisierungen.AsciiVisualisierung;

import java.util.ArrayList;

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
