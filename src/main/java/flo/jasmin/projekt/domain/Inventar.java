package flo.jasmin.projekt.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugZutatenImInventar;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Values.Geld;

public class Inventar {
    private Geld erspartes;
    private ArrayList<Gegenstand> gegenstände;
    private Map<Zutat, Integer> zutaten;
    private int zeltkapazität;
    private float kochtopfMultiplikator;

    public Inventar(){
        erspartes = Geld.von(5);
        gegenstände = new ArrayList<>();
        zutaten = new HashMap<>();
        zeltkapazität = 1;
        kochtopfMultiplikator = 1;
    }

    public int getErspartes() {
        return erspartes.getBetrag();
    }

    public Geld getGeld() {
        return erspartes;
    }

    public void setErspartes(int betrag) {
        this.erspartes = Geld.von(betrag);
    }

    public void setGeld(Geld geld) {
        this.erspartes = geld;
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

    public void genugErsparrtes(int betrag) throws NichtGenugErsparrtes{
        if (!erspartes.kannBezahlen(Geld.von(betrag))){
            throw new NichtGenugErsparrtes();
        }
    }

    public void geldEntfernen(int betrag) throws NichtGenugErsparrtes{
        genugErsparrtes(betrag);
        erspartes = erspartes.subtrahiere(Geld.von(betrag));
    }

    public void geldHinzufügen(Geld betrag) {
        erspartes = erspartes.addiere(betrag);
    }

    public void geldHinzufügen(int betrag) {
        erspartes = erspartes.addiere(Geld.von(betrag));
    }

    public void fügeGemischteGegenständeHinzu(Map<Gegenstand, Integer> einkauf){
        Map<Zutat, Integer> zutaten = new HashMap<>();
        ArrayList<Gegenstand> anderes = new ArrayList<>();

        for (Gegenstand g : einkauf.keySet()){
            if (g instanceof Zutat){
                zutaten.put((Zutat) g, einkauf.get(g));
            }else {
                for (int i = 0; i< einkauf.get(g); i++){
                    anderes.add(g);
                }
            }
        }
        fügeZutatenHinzu(zutaten);
        fügeGegenständeHinzu(anderes);
    }


    public void fügeGemischteGegenständeHinzu(ArrayList<Gegenstand> belohnung){
        Map<Zutat, Integer> zutaten = new HashMap<>();
        ArrayList<Gegenstand> anderes = new ArrayList<>();

        for (Gegenstand g : belohnung){
            if (g instanceof Zutat){
                if(zutaten.containsKey(g)){
                    zutaten.put((Zutat) g, zutaten.get(g)+1);
                }else {
                    zutaten.put((Zutat)g, 1);
                }
            }else {
                anderes.add((Gegenstand)g);
            }
        }
        fügeZutatenHinzu(zutaten);
        fügeGegenständeHinzu(anderes);
    }

    public ArrayList<Gegenstand> getAusrüstung() {
        return gegenstände.stream()
            .filter(g -> g instanceof Ausstattung)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public void entferneAusrüstung(Ausstattung ausstattung) {
        gegenstände.remove(ausstattung);
    }

    public void fügeAusrüstungHinzu(Ausstattung ausstattung) {
        gegenstände.add(ausstattung);
    }

    public ArrayList<Gegenstand> verliereZufälligeGegenstände(int anzahl) {
        ArrayList<Gegenstand> verloreneGegenstände = new ArrayList<>();
        int zuVerlieren = Math.min(anzahl, gegenstände.size());
        
        for (int i = 0; i < zuVerlieren; i++) {
            if (!gegenstände.isEmpty()) {
                int randomIndex = (int) (Math.random() * gegenstände.size());
                verloreneGegenstände.add(gegenstände.remove(randomIndex));
            }
        }
        
        return verloreneGegenstände;
    }
}
