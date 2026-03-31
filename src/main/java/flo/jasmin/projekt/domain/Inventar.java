package flo.jasmin.projekt.domain;

import java.util.ArrayList;

import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;

public class Inventar {
    private int erspartes;
    private ArrayList<Gegenstand> gegenstände;
    private int zeltkapazität;
    private float kochtopfMultiplikator;

    public Inventar(){
        erspartes = 0;
        gegenstände = new ArrayList<>();
        zeltkapazität = 1;
        kochtopfMultiplikator = 1;
    }

    public int getErspartes() {
        return erspartes;
    }

    public void setErspartes(int erspartes) {
        this.erspartes = erspartes;
    }

    public ArrayList<Gegenstand> getGegenstände() {
        return gegenstände;
    }

    public void setGegenstände(ArrayList<Gegenstand> gegenstände) {
        this.gegenstände = gegenstände;
    }

    public int getZeltkapazität() {
        return zeltkapazität;
    }

    public void setZeltkapazität(int zeltkapazität) {
        this.zeltkapazität = zeltkapazität;
    }

    public float getKochtopfMultiplikator() {
        return kochtopfMultiplikator;
    }

    public void entferneGegenstände(ArrayList<Gegenstand> gegenstände){
        this.gegenstände.removeAll(gegenstände);
    }

    public void setKochtopfMultiplikator(float kochtopfMultiplikator) {
        this.kochtopfMultiplikator = kochtopfMultiplikator;
    }
    public void fügeGegenstandHinzu(Gegenstand gegenstand){
        gegenstände.add(gegenstand);
    }
    public void fügeGegenständeHinzu(ArrayList<Gegenstand> gegenstände){
        this.gegenstände.addAll(gegenstände);
    }
}