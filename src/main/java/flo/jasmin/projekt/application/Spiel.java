package flo.jasmin.projekt.application;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.Team;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Dorf;
import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Values.Einkauf;
import flo.jasmin.projekt.domain.Inventar;
import flo.jasmin.projekt.domain.Kochsystem;
import flo.jasmin.projekt.domain.Status;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spiel {
    private Team team;
    private Karte karte;
    private Kochsystem kochsystem;
    private Status status;
    private Kampf kampf;
    private Einkauf einkauf;



    public Spiel(){
        karte = new Karte();
        team = new Team();
        status = Status.EXISTIEREN;
        kochsystem = new Kochsystem();
    }

    //Holen wir vermutlich einfach nur aus dem Zellentypen
    public Set<Befehl> gibErlaubteBefehle(){
        if(status == Status.EXISTIEREN || status == Status.DORF) {
            return karte.gibMomentaneZelle().getZellentyp().getErlaubteBefehle();
        } else if (status == Status.CAMPEN) {
            return Set.of(Befehl.ZURÜCK, Befehl.KOCHEN);
        } else if (status == Status.KOCHEN) {
            return Set.of(Befehl.ZURÜCK, Befehl.ZUTATEN);
        } else if (status == Status.KAMPF) {
            return Set.of(Befehl.ANGRIFF);
        } else if (status == Status.EINKAUF){
            return Set.of(Befehl.JA, Befehl.ZURÜCK);
        }
        return new HashSet<>();
    }


    //here sollte die Antwort zurück gegeben werden
    public ArrayList<String> spieleBefehl(Befehl befehl, String parameter){
        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add("Du willst " + befehl.name());
        if(gibErlaubteBefehle().contains(befehl)){
            if(status == Status.EXISTIEREN) {
                if (befehl == Befehl.RUNTER || befehl == Befehl.HOCH || befehl == Befehl.LINKS || befehl == Befehl.RECHTS) {
                    try {
                        karte.gehe(befehl);
                        ArrayList<String> antwortAusPotenziellerKampf = potentiellerKampf();
                        if(antwortAusPotenziellerKampf.isEmpty()){
                            antwort.add(karte.gibMomentaneZelle().getBeschreibung());
                        } else{
                            antwort.addAll(antwortAusPotenziellerKampf);
                        }
                    } catch (LaufGegenBarriereException e) {
                        antwort.add("How about we explore the area ahead of us later?");
                    }
                } else if (befehl == Befehl.CAMPEN) {
                    status = Status.CAMPEN;
                    antwort.add("Gemeinsam schlagt ihr euer Zelt auf. Klein aber fein\nWährend dem Campen könnt ihr KOCHEN um euch zu heilen");
                } else if(befehl == Befehl.KAUFEN) {
                    status = Status.DORF;
                    antwort.add("\nDeine Ersparrnisse: " + team.getInventar().getErspartes());
                    antwort.add("\n"+karte.gibMomentaneZelle().getDorf().sortimentAnzeigen());
                }
            }
            else if(status == Status.CAMPEN){
                if(befehl == Befehl.KOCHEN){
                    status = Status.KOCHEN;
                    antwort.add(kochsystem.stringRepräsentationVonZutaten(team.getInventar()));
                }
                else if(befehl == Befehl.KREATURAUSSTATTEN){

                }
                else if (befehl == Befehl.ZURÜCK) {
                    status = Status.EXISTIEREN;
                }
            }
            else if(status == Status.KOCHEN){
                antwort.add(zutatenZeug(befehl, parameterAufteilen(parameter)));
            }
            else if(status == Status.KAMPF){
                //wird an den kampf dann weiterdeligiert:
                try{
                    antwort.addAll(kampf.überMittelZiel(Integer.valueOf(parameter)));
                    if (!kampf.isKampfImGange()) {
                        team.getInventar().fügeGegenständeHinzu(kampf.getVerloreneGegenstände());
                        status = Status.EXISTIEREN;
                        antwort.add("Endlich kannst du dich umschauen.\n"+karte.gibMomentaneZelle().getZellentyp().getBeschreibung());
                    }
                } catch (IndexOutOfBoundsException e){
                    antwort.add("DIESER GEGNER EXISTIERT NICHT! \nBitte gib den Index eines Gegners an!");
                } catch (ZielIstSpielerWesen f) {
                    antwort.add(f.getMessage());
                }
            } 
            else if (status == Status.DORF){
                if(befehl == Befehl.KAUFEN){
                    Map<Gegenstand, Integer> auswahl;
                    try {
                        auswahl = karte.gibMomentaneZelle().getDorf().übersetzeNameZuGegenstand(parameterAufteilen(parameter));
                        int preis = Dorf.gesamtpreisBerechnen(auswahl);
                        einkauf = new Einkauf(auswahl, preis);
                        antwort.add(Dorf.preisVisualisierung(preis));
                        status = Status.EINKAUF;
                    } catch (FalscheZutatenEingabe e) {
                        antwort.add(e.getMessage());
                    }
                    
                }
            }
            else if (status == Status.EINKAUF){
                if(befehl == Befehl.JA){
                    try {
                        team.getInventar().geldEntfernen(einkauf.getGesamtpreis());
                        team.getInventar().fügeGemischteGegenständeHinzu(einkauf.getAuswahl());
                        antwort.add("Vielen Dank für deinen Einkauf! Die Gegenstände wurden in deinem Inventar hinzugefügt.");
                    } catch (NichtGenugErsparrtes e) {
                        antwort.add(e.getMessage() +  "\nDein Einkauf wurde zurückgelegt. Mit dem Befehl KAUFEN kannst du erneut einkaufen");
                    }
                }
                einkauf = null;
                status = Status.EXISTIEREN;
            }
        }
        return antwort;
    }

    private Map<String, Integer> parameterAufteilen(String parameter){
        Pattern pattern = Pattern.compile("(\\w+)\\s+(\\d+)");
        Matcher matcher = pattern.matcher(parameter);
        Map<String, Integer> eingabe = new HashMap<>();
        while (matcher.find()) {
            eingabe.put(matcher.group(1),Integer.parseInt(matcher.group(2)));

        }
        return eingabe;
    }


    //das wird wahrscheinlcih entkoppelt: Das selbe prinzip wird auch zum Kaufen verwendet. Dann wird das nur gegen andere Sachen gematched. 
    private String zutatenZeug(Befehl befehl, Map<String, Integer> aufgeteilteParameter) {
        if(befehl == Befehl.ZUTATEN){
           
            if (!aufgeteilteParameter.isEmpty()){
                try { 
                    Map<Zutat, Integer> übersetzteEingabe = kochsystem.übersetzteZutatenNameZuZutatObjekt(aufgeteilteParameter, team.getInventar());
                    int heilung = 0;
                    heilung = kochsystem.errechneGesundheit(übersetzteEingabe, team.getInventar());
                    return team.heile(heilung);
                    //da kein Fehler aufgetretten ist, wurden auch nicht mehr Items verwendet als 
                } catch (FalscheZutatenEingabe e) {
                    return("Bitte gib Valide Zutaten ein!");
                }
               
            }

        } else if (befehl == Befehl.ZURÜCK) {
            status = Status.CAMPEN;
        } 
        return "nothing to state here";
    }

    public ArrayList<String> potentiellerKampf(){
        Random random = new Random();
        ArrayList<String> antwort = new ArrayList<String>();
        if(karte.gibMomentaneZelle().getGegnerWahrscheinlichkeit() > random.nextFloat()){
            ArrayList<Wesen> alleWesen = new ArrayList<>();
            ArrayList<Gegner> alleGegner = karte.gibMomentaneZelle().getZellentyp().getGegnerAuswahl();

            antwort.add("Herrje!");
            for (Gegner gegner: alleGegner){
                antwort.add(gegner.getName() + " erscheint");
            }


            alleWesen.addAll(team.getWesenInTeam());
            alleWesen.addAll(alleGegner);

            kampf = new Kampf(alleWesen);
            status = Status.KAMPF;
            antwort.addAll(kampf.gegnerGreiftAn());
        }
        return antwort;
    }

    public Kampf getKampf() {
        return kampf;
    }

    public void setKampf(Kampf kampf) {
        this.kampf = kampf;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Karte getKarte() {
        return karte;
    }

    public void setKarte(Karte karte) {
        this.karte = karte;
    }

    public Kochsystem getKochsystem() {
        return kochsystem;
    }

    public void setKochsystem(Kochsystem kochsystem) {
        this.kochsystem = kochsystem;
    }
    
    public Einkauf getEinkauf() {
        return einkauf;
    }

    public void setEinkauf(Einkauf einkauf) {
        this.einkauf = einkauf;
    }
}
