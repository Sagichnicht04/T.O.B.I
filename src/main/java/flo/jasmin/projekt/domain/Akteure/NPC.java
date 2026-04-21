package flo.jasmin.projekt.domain.Akteure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

public class NPC {
    private String name;
    private ArrayList<String> dialog;
    private AsciiVisualisierung visualisierung;
    private int dialogIndex;

    public NPC(String name, ArrayList<String> dialog, AsciiVisualisierung visualisierung) {
        this.name = name;
        this.dialog = dialog;
        this.visualisierung = visualisierung;
        this.dialogIndex = 0;
    }

    public record DialogWithEnd(String string, boolean endOfDialog) { }


    public DialogWithEnd popDialogString(){
        boolean endOfDialog = false;
        String dialogString = dialog.get(dialogIndex);
        dialogIndex++;
        if(dialogIndex >= dialog.size()){
            dialogIndex = 0;
            endOfDialog = true;
        }
        return new DialogWithEnd(dialogString, endOfDialog);
    }

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