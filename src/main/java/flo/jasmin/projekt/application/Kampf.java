package flo.jasmin.projekt.application;

import java.util.ArrayList;

import flo.jasmin.projekt.domain.Akteure.Wesen;

public class Kampf {
    private ArrayList<Wesen> alleWesen;
    private int momentanesWesenIdex;

    public ArrayList<Wesen> getAlleWesen() {
        return alleWesen;
    }

    public void setAlleWesen(ArrayList<Wesen> alleWesen) {
        this.alleWesen = alleWesen;
    }

    public int getMomentanesWesenIdex() {
        return momentanesWesenIdex;
    }

    public void setMomentanesWesenIdex(int momentanesWesenIdex) {
        this.momentanesWesenIdex = momentanesWesenIdex;
    }

    //Kapmfschritt wird benötigt weil wir keine While Loop haben können
    public void kampfSchritt(){
        momentanesWesenIdex++;
    }
}
