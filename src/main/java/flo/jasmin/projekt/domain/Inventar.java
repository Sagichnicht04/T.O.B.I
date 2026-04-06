package flo.jasmin.projekt.domain;

import java.util.ArrayList;
import java.util.Map;

import flo.jasmin.projekt.domain.Exceptions.NichtGenugZutatenImInventar;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;

public class Inventar {
    private int erspartes;
    private ArrayList<Gegenstand> gegenstände;
    private Map<Zutat, Integer> zutaten;
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
    public Map<Zutat, Integer> getZutaten() {
        return zutaten;
    }
    public void setZutaten(Map<Zutat, Integer> zutaten) {
        this.zutaten = zutaten;
    }    

    public void fügeZutatenHinzu(Map<Zutat, Integer> zutaten){
        for(Zutat z : zutaten.keySet()){
            if(this.zutaten.containsKey(z)){
                this.zutaten.put(z, this.zutaten.get(z) + zutaten.get(z));
            } else {
                this.zutaten.put(z, zutaten.get(z));
            } 
        }
    }

    public void entferneZutaten(Map<Zutat, Integer> zutaten) throws NichtGenugZutatenImInventar{
        checkGenugZutatenImImventar(zutaten);
        for (Zutat z : zutaten.keySet()){
            this.zutaten.put(z, this.zutaten.get(z) - zutaten.get(z));
            if (this.zutaten.get(z) == 0){
                this.zutaten.remove(z);
            }
        }
    }


    //sogenante "guard-Methode" sie ist nur zum absichern da. Deswegen wirft sie einen Fehler oder eben nicht
    public void checkGenugZutatenImImventar(Map<Zutat, Integer> zutaten) throws NichtGenugZutatenImInventar{
        for (Zutat z : zutaten.keySet()){
            if(!this.zutaten.containsKey(z) || zutaten.get(z) > this.zutaten.get(z)){
                throw new NichtGenugZutatenImInventar();
            }
        }
    }

}