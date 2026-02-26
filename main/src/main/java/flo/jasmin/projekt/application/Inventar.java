package main.java.flo.jasmin.projekt.application;

import java.util.ArrayList;

import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

public class Inventar {
    private int erspartes;
    private ArrayList<Gegenstand> gegenstände;
    private int zeltkapazität;
    private float kochtopfMultiplikator;

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

    public void setKochtopfMultiplikator(float kochtopfMultiplikator) {
        this.kochtopfMultiplikator = kochtopfMultiplikator;
    }
}