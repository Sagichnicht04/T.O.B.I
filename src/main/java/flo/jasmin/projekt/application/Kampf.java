package flo.jasmin.projekt.application;

import java.util.ArrayList;
import java.util.Comparator;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Values.Geld;
import flo.jasmin.projekt.domain.Values.Schaden;

public class Kampf {
    private ArrayList<Wesen> alleWesen;
    private int momentanesWesenIndex;
    private boolean kampfImGange;
    private ArrayList<Gegenstand> verloreneGegenstaende;

    public boolean isKampfImGange() {
        return kampfImGange;
    }

    public void setKampfImGange(boolean kampfImGange) {
        this.kampfImGange = kampfImGange;
    }

    public Kampf(ArrayList<Wesen> alleWesen){
        this.alleWesen = alleWesen;
        alleWesen.sort(Comparator.comparingInt(Wesen::getInitiative).reversed());
        momentanesWesenIndex = 0;
        kampfImGange = true;
        verloreneGegenstaende = new ArrayList<>();
    }

    public ArrayList<Gegenstand> getVerloreneGegenstaende() {
        return verloreneGegenstaende;
    }

    public void setVerloreneGegenstaende(ArrayList<Gegenstand> verloreneGegenstaende) {
        this.verloreneGegenstaende = verloreneGegenstaende;
    }

    private void fuegeVerlorenenGegenstandHinzu(Gegenstand gegenstand){
        this.verloreneGegenstaende.add(gegenstand);
    }
    private void fuegeVerlorenenGegenstaendeHinzu(ArrayList<Gegenstand> gegenstaende){
        this.verloreneGegenstaende.addAll(gegenstaende);
    }

    public Geld errechneZufaelligVerlorenesGeld(){
        return Geld.von((int) (Math.random()*5) + 1);
    }

    public ArrayList<Wesen> getAlleWesen() {
        return alleWesen;
    }

    public void setAlleWesen(ArrayList<Wesen> alleWesen) {
        this.alleWesen = alleWesen;
    }

    public int getMomentanesWesenIndex() {
        return momentanesWesenIndex;
    }

    public void setMomentanesWesenIndex(int momentanesWesenIndex) {
        this.momentanesWesenIndex = momentanesWesenIndex;
    }

    //Kapmfschritt wird benoetigt weil wir keine While Loop haben koennen
   /*  public void kampfSchritt(){
        if (getMomentanesWesen().getClass() == Gegner.class){
            gegnerGreiftAn();
            kampfSchritt();
        } else{
            //spieler darf angreifen
        }
    } */

    public void erhoeheMomentanesWesenIndex(){
        momentanesWesenIndex = (momentanesWesenIndex + 1) % alleWesen.size();
    }
    
    public Wesen getMomentanesWesen(){
        return alleWesen.get(momentanesWesenIndex);
    }

    public ArrayList<String> teamGreiftAn(Wesen ziel) throws ZielIstSpielerWesen{
        if(ziel.getClass() == TeamWesen.class){
            throw new ZielIstSpielerWesen();
        }
        Schaden schaden = getMomentanesWesen().berechneSchaden();
        ziel.nehmeSchaden(schaden);

        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add(ziel.getName() + " nimmt " + schaden.getWert() + " Schaden. HP uebrig: "+ziel.getGesundheit());

        if(!ziel.kampfFaehig()){
            antwort.add(ziel.getName() + " faellt zu Boden.");
            // Es fuehlt sich so an, als haette ich diesen Cast schon oft gemacht. Refactoren?
            if(ziel instanceof Gegner){
                ArrayList<Gegenstand> gegenstaende = ((Gegner) ziel).getInventar();
                for(Gegenstand gegenstand: gegenstaende){
                    antwort.add(ziel.getName() + " laesst " + gegenstand.getName() + " fallen.");
                    fuegeVerlorenenGegenstandHinzu(gegenstand);
                }
            }
            entferneWesenAusListe(ziel);
        }

        erhoeheMomentanesWesenIndex();
        String kampfImGangeAntwort = rechneKampfImGange();
        if(kampfImGangeAntwort == null){
            antwort.addAll(gegnerGreiftAn());
        }else {
            antwort.add(kampfImGangeAntwort);
        }
        return antwort;
    }


    public void entferneWesenAusListe(Wesen ziel){
        if(alleWesen.indexOf(ziel) < momentanesWesenIndex){
            momentanesWesenIndex -= 1;
        }
        alleWesen.remove(ziel);
    }


    //muesste Fehler schmeissen, wenn es kein passendens Wesen gibt
    public ArrayList<String> ueberMittelZiel(int ziel) throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        return teamGreiftAn(alleWesen.get(ziel));
    }

    public ArrayList<String> gegnerGreiftAn(){
        ArrayList<String> antwort = new ArrayList<String>();
        while(getMomentanesWesen() instanceof Gegner && !alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList().isEmpty()) {
            Gegner angreifer = (Gegner) getMomentanesWesen();
            Wesen ziel = angreifer.ausgewaehltesZiel(new ArrayList<>(alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList()));
            Schaden schaden = angreifer.berechneSchaden();
            ziel.nehmeSchaden(schaden);
            antwort.add(getMomentanesWesen().getName() + " greift an.");
            antwort.add(ziel.getName() + " nimmt " + schaden.reduziereDurch(ziel.getVerteidigung()).getWert() + " Schaden. HP uebrig: "+ziel.getGesundheit());
            if(!ziel.kampfFaehig()){
                entferneWesenAusListe(ziel);
            }
            erhoeheMomentanesWesenIndex();
        }

        String kampfImGangeAntwort = rechneKampfImGange();
        if(kampfImGangeAntwort == null){
            antwort.addAll(gibSpielerInfoUeberKampf());
        }
        else{
            antwort.add(kampfImGangeAntwort);
        }


        return antwort;
    }

    public String rechneKampfImGange(){
        String grund = null;
        if (alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList().isEmpty()){
            grund = "Dein gesamtes Team wurde besiegt!";
            kampfImGange = false;
        } else if (alleWesen.stream().filter(wesen -> wesen instanceof Gegner).toList().isEmpty()){
            grund = "Alle Gegner wurden besiegt!";
            kampfImGange = false;
        }
        return grund;
    }

    public ArrayList<String> gibSpielerInfoUeberKampf(){
        ArrayList<String> antwort = new ArrayList<>();
        for (Wesen w: alleWesen){
            antwort.add(w.getName() +" : " + alleWesen.indexOf(w));
        }
        //ersetzt antwort.add("Dein Wesen: 'Wesen'");
        antwort.add(getMomentanesWesen().getName() + " ist an der Reihe");
        antwort.addAll(holeAlleGegner());
        return antwort;
    }

    private ArrayList<String> holeAlleGegner(){
        ArrayList<String> antwort = new ArrayList<>();
        antwort.add("Gegnerauswahl: ");
        for (Wesen g : alleWesen.stream().filter(w -> w.getClass().getSuperclass() == Gegner.class).toList()){
            antwort.add(g.getName() + " : " + alleWesen.indexOf(g) + "\n");
        }
        return antwort;
    }

}
