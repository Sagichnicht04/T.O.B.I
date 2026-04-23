package flo.jasmin.projekt.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugZutatenImInventar;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Ausstattung;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Zutat;
import flo.jasmin.projekt.domain.Values.Geld;

public class Inventar {
    private Geld erspartes;
    private ArrayList<Gegenstand> gegenstaende;
    private Map<Zutat, Integer> zutaten;
    private int zeltkapazitaet;
    private float kochtopfMultiplikator;

    public Inventar(){
        erspartes = Geld.von(5);
        gegenstaende = new ArrayList<>();
        zutaten = new HashMap<>();
        zeltkapazitaet = 1;
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

    public ArrayList<Gegenstand> getGegenstaende() {
        return gegenstaende;
    }

    public void setGegenstaende(ArrayList<Gegenstand> gegenstaende) {
        this.gegenstaende = gegenstaende;
    }

    public int getZeltkapazitaet() {
        return zeltkapazitaet;
    }

    public void setZeltkapazitaet(int zeltkapazitaet) {
        this.zeltkapazitaet = zeltkapazitaet;
    }

    public float getKochtopfMultiplikator() {
        return kochtopfMultiplikator;
    }

    public void entferneGegenstaende(ArrayList<Gegenstand> gegenstaende){
        this.gegenstaende.removeAll(gegenstaende);
    }

    public void setKochtopfMultiplikator(float kochtopfMultiplikator) {
        this.kochtopfMultiplikator = kochtopfMultiplikator;
    }
    public void fuegeGegenstandHinzu(Gegenstand gegenstand){
        gegenstaende.add(gegenstand);
    }
    public void fuegeGegenstaendeHinzu(ArrayList<Gegenstand> gegenstaende){
        this.gegenstaende.addAll(gegenstaende);
    }
    public Map<Zutat, Integer> getZutaten() {
        return zutaten;
    }
    public void setZutaten(Map<Zutat, Integer> zutaten) {
        this.zutaten = zutaten;
    }    

    public void fuegeZutatenHinzu(Map<Zutat, Integer> zutaten){
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

    public void geldHinzufuegen(Geld betrag) {
        erspartes = erspartes.addiere(betrag);
    }


    public void fuegeGemischteGegenstaendeHinzu(Map<Gegenstand, Integer> einkauf){
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
        fuegeZutatenHinzu(zutaten);
        fuegeGegenstaendeHinzu(anderes);
    }


    public void fuegeGemischteGegenstaendeHinzu(ArrayList<Gegenstand> belohnung){
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
        fuegeZutatenHinzu(zutaten);
        fuegeGegenstaendeHinzu(anderes);
    }

    public ArrayList<Gegenstand> getAusruestung() {
        return gegenstaende.stream()
            .filter(g -> g instanceof Ausstattung)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public void entferneAusruestung(Ausstattung ausstattung) {
        gegenstaende.remove(ausstattung);
    }

    public void fuegeAusruestungHinzu(Ausstattung ausstattung) {
        gegenstaende.add(ausstattung);
    }

    public ArrayList<Gegenstand> verliereZufaelligeGegenstaende(int anzahl) {
        ArrayList<Gegenstand> verloreneGegenstaende = new ArrayList<>();
        int zuVerlieren = Math.min(anzahl, gegenstaende.size());
        
        for (int i = 0; i < zuVerlieren; i++) {
            if (!gegenstaende.isEmpty()) {
                int randomIndex = (int) (Math.random() * gegenstaende.size());
                verloreneGegenstaende.add(gegenstaende.remove(randomIndex));
            }
        }
        
        return verloreneGegenstaende;
    }
}
