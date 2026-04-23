package flo.jasmin.projekt.application;

import flo.jasmin.projekt.domain.Akteure.*;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Dorf;
import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugZutatenImInventar;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.NPCs.NPC;
import flo.jasmin.projekt.domain.Values.Einkauf;
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
            return Set.of(Befehl.ZURUeCK, Befehl.KOCHEN, Befehl.KREATURAUSSTATTEN);
        } else if (status == Status.KOCHEN) {
            return Set.of(Befehl.ZURUeCK, Befehl.ZUTATEN);
        } else if (status == Status.KAMPF) {
            return Set.of(Befehl.ANGRIFF);
        } else if (status == Status.EINKAUF){
            return Set.of(Befehl.JA, Befehl.NEIN);
        } else if (status == Status.AUSRUeSTEN){
            return Set.of(Befehl.AUSRUeSTEN, Befehl.ZURUeCK);
        } else if (status == Status.DIALOG){
            return Set.of(Befehl.REDEN, Befehl.ZURUeCK, Befehl.REKRUTIEREN);
        }
        return new HashSet<>();
    }


    //here sollte die Antwort zurueck gegeben werden
    public ArrayList<String> spieleBefehl(Befehl befehl, String parameter){
        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add("Du willst " + befehl.name());

        if(team.istBesiegt()){
            antwort.addAll(respawnImDorf());
            return antwort;
        }
        
        if(gibErlaubteBefehle().contains(befehl)){
            if(status == Status.EXISTIEREN) {
                if (befehl == Befehl.RUNTER || befehl == Befehl.HOCH || befehl == Befehl.LINKS || befehl == Befehl.RECHTS) {
                    try {
                        karte.gehe(befehl);
                        ArrayList<String> antwortAusPotenziellerKampf = potentiellerKampf(new Random());
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
                    antwort.add("Gemeinsam schlagt ihr euer Zelt auf. Klein aber fein\nWaehrend dem Campen koennt ihr KOCHEN um euch zu heilen");
                } else if(befehl == Befehl.KAUFEN) {
                    status = Status.DORF;
                    antwort.add("\nDeine Ersparrnisse: " + team.getInventar().getErspartes());
                    antwort.add("\n"+karte.gibMomentaneZelle().getDorf().sortimentAnzeigen());
                } else if(befehl == Befehl.REDEN) {
                    status = Status.DIALOG;
                    antwort.add("\nDa steht eine Person in der Gegend rum.");
                    NPC.DialogWithEnd dialogWithEnd = karte.gibMomentaneZelle().getNpc().popDialogString();
                    antwort.add(dialogWithEnd.string());
                    if(dialogWithEnd.endOfDialog()){
                        status = Status.EXISTIEREN;
                    }
                }
            }
            else if(status == Status.CAMPEN){
                if(befehl == Befehl.KOCHEN){
                    status = Status.KOCHEN;
                    antwort.add(kochsystem.stringRepraesentationVonZutaten(team.getInventar().getZutaten()));
                }
                else if(befehl == Befehl.KREATURAUSSTATTEN){
                    status = Status.AUSRUeSTEN;
                    antwort.addAll(zeigeAusruestungsmenue());
                }
                else if (befehl == Befehl.ZURUeCK) {
                    status = Status.EXISTIEREN;
                }
            }
            else if(status == Status.AUSRUeSTEN){
                if(befehl == Befehl.AUSRUeSTEN){
                    antwort.addAll(ausruestungsbefehl(parameter));
                }
                else if(befehl == Befehl.ZURUeCK){
                    status = Status.CAMPEN;
                }
            }
            else if(status == Status.KOCHEN){
                antwort.add(zutatenZeug(befehl, parameterAufteilen(parameter)));
            }
            else if(status == Status.KAMPF){
                //wird an den kampf dann weiterdeligiert:
                try{
                    antwort.addAll(kampf.ueberMittelZiel(Integer.valueOf(parameter)));
                    if (!kampf.isKampfImGange()) {
                        team.getInventar().fuegeGemischteGegenstaendeHinzu(((kampf.getVerloreneGegenstaende())));
                        team.getInventar().geldHinzufuegen(kampf.errechneZufaelligVerlorenesGeld());
                        status = Status.EXISTIEREN;
                        antwort.add("Endlich kannst du dich umschauen.\n"+karte.gibMomentaneZelle().getZellentyp().getBeschreibung());
                    }
                } catch (IndexOutOfBoundsException | NumberFormatException e){
                    antwort.add("DIESER GEGNER EXISTIERT NICHT! \nBitte gib den Index eines Gegners an!");
                } catch (ZielIstSpielerWesen f) {
                    antwort.add(f.getMessage());
                }
            } 
            else if (status == Status.DORF){
                if(befehl == Befehl.KAUFEN){
                    Map<Gegenstand, Integer> auswahl;
                    try {
                        auswahl = karte.gibMomentaneZelle().getDorf().uebersetzeNameZuGegenstand(parameterAufteilen(parameter));
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
                        team.getInventar().fuegeGemischteGegenstaendeHinzu(einkauf.getAuswahl());
                        antwort.add("Vielen Dank fuer deinen Einkauf! Die Gegenstaende wurden in deinem Inventar hinzugefuegt.");
                    } catch (NichtGenugErsparrtes e) {
                        antwort.add(e.getMessage() +  "\nDein Einkauf wurde zurueckgelegt. Mit dem Befehl KAUFEN kannst du erneut einkaufen");
                    }
                }
                einkauf = null;
                status = Status.EXISTIEREN;
            }
            else if (status == Status.DIALOG){
                if(befehl == Befehl.REDEN){
                    NPC.DialogWithEnd dialogWithEnd = karte.gibMomentaneZelle().getNpc().popDialogString();
                    antwort.add(dialogWithEnd.string());
                    if(dialogWithEnd.endOfDialog()){
                        status = Status.EXISTIEREN;
                    }
                }
                else if(befehl == Befehl.ZURUeCK){
                    status = Status.EXISTIEREN;
                }
                else if (befehl == Befehl.REKRUTIEREN){
                    NPC derNPC = karte.gibMomentaneZelle().getNpc();
                    antwort.add(derNPC.getName() + " tritt deinem Team bei!");
                    team.addWesenInTeam(derNPC.getWesenWennRekrutiert());
                    karte.gibMomentaneZelle().setNpc(null);
                    status = Status.EXISTIEREN;
                }
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
                status = Status.CAMPEN;
                try { 
                    Map<Zutat, Integer> uebersetzteEingabe = kochsystem.uebersetzteZutatenNameZuZutatObjekt(aufgeteilteParameter, team.getInventar());
                    int heilung = 0;
                    team.getInventar().checkGenugZutatenImImventar(uebersetzteEingabe);
                    team.getInventar().entferneZutaten(uebersetzteEingabe);
                    heilung = kochsystem.errechneGesundheit(uebersetzteEingabe);
                    return team.heile(heilung);
                } catch (FalscheZutatenEingabe e) {
                    return("Bitte gib Valide Zutaten ein!");
                } catch (NichtGenugZutatenImInventar e){
                    return e.getMessage();
                }
                
            }

        } else if (befehl == Befehl.ZURUeCK) {
            status = Status.CAMPEN;
        } 
        return "nothing to state here";
    }

    //heir muessen wir evtl mit dependency injection arbeiten. Wieso? Weil wir diesen Code auch testen sollten und das Random dann gezielt im test ueberschreiben
    public ArrayList<String> potentiellerKampf(Random random){
        ArrayList<String> antwort = new ArrayList<String>();
        if(karte.gibMomentaneZelle().getGegnerWahrscheinlichkeit() > random.nextFloat()){
            ArrayList<Wesen> alleWesen = new ArrayList<>();
            ArrayList<Gegner> alleGegner = karte.gibMomentaneZelle().getZellentyp().getGegnerAuswahl();


            alleWesen.addAll(team.holeKampffaehigeWesen());
            alleWesen.addAll(alleGegner.stream().filter(Wesen::kampfFaehig).toList());

            kampf = new Kampf(alleWesen);
            String grund = kampf.rechneKampfImGange();
            if(!kampf.isKampfImGange()){
                antwort.add(grund);
            }
            else {
                antwort.add("Herrje!");
                for (Gegner gegner: alleGegner){
                    antwort.add(gegner.getName() + " erscheint");
                }

                status = Status.KAMPF;
                antwort.addAll(kampf.gegnerGreiftAn());
            }
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

    private ArrayList<String> zeigeAusruestungsmenue() {
        ArrayList<String> antwort = new ArrayList<>();
        antwort.add("\n=== AUSRUeSTUNGSMENUe ===");
        antwort.add("\nDein Team:");
        for (int i = 0; i < team.getWesenInTeam().size(); i++) {
            TeamWesen wesen = team.getWesenInTeam().get(i);
            antwort.add(i + ": " + wesen.getAusruestungsStatus());
        }
        
        antwort.add("\nVerfuegbare Ausruestung im Inventar:");
        List<Gegenstand> ausruestung = team.getInventar().getAusruestung();
        if (ausruestung.isEmpty()) {
            antwort.add("  Keine Ausruestung verfuegbar");
        } else {
            for (int i = 0; i < ausruestung.size(); i++) {
                antwort.add(i + ": " + ausruestung.get(i).getName() + " - " + ausruestung.get(i).getBeschreibung());
            }
        }
        
        antwort.add("\nFormat: AUSRUeSTEN <Wesen-Index> <Ausruestungs-Index>");
        antwort.add("Beispiel: AUSRUeSTEN 0 0");
        return antwort;
    }

    private ArrayList<String> respawnImDorf() {
        ArrayList<String> antwort = new ArrayList<>();
        
        antwort.add("\n=== NIEDERLAGE ===");
        antwort.add("Dein Team wurde besiegt...");
        
        ArrayList<Gegenstand> verloreneGegenstaende = team.getInventar().verliereZufaelligeGegenstaende(3);
        
        if (!verloreneGegenstaende.isEmpty()) {
            antwort.add("\nIn der Panik habt ihr folgende Gegenstaende verloren:");
            for (Gegenstand g : verloreneGegenstaende) {
                antwort.add("  - " + g.getName());
            }
        }
        
        team.heileKomplett();
        karte.setMomentanePosition(new flo.jasmin.projekt.domain.Values.Position(3, 7));
        status = Status.EXISTIEREN;
        
        antwort.add("\nIhr wacht im Dorf Farore auf, vollstaendig geheilt.");
        antwort.add("Die freundlichen Dorfbewohner haben sich um euch gekuemmert.");
        antwort.add("\n" + karte.gibMomentaneZelle().getBeschreibung());
        
        return antwort;
    }

    private ArrayList<String> ausruestungsbefehl(String parameter) {
        ArrayList<String> antwort = new ArrayList<>();
        
        try {
            String[] parts = parameter.trim().split("\\s+");
            if (parts.length != 2) {
                antwort.add("Bitte gib genau zwei Zahlen an: <Wesen-Index> <Ausruestungs-Index>");
                antwort.addAll(zeigeAusruestungsmenue());
                return antwort;
            }
            
            int wesenIndex = Integer.parseInt(parts[0]);
            int ausruestungsIndex = Integer.parseInt(parts[1]);
            
            if (wesenIndex < 0 || wesenIndex >= team.getWesenInTeam().size()) {
                antwort.add("Ungueltiger Wesen-Index!");
                antwort.addAll(zeigeAusruestungsmenue());
                return antwort;
            }
            
            List<Gegenstand> verfuegbareAusruestung = team.getInventar().getAusruestung();
            if (ausruestungsIndex < 0 || ausruestungsIndex >= verfuegbareAusruestung.size()) {
                antwort.add("Ungueltiger Ausruestungs-Index!");
                antwort.addAll(zeigeAusruestungsmenue());
                return antwort;
            }
            
            TeamWesen wesen = team.getWesenInTeam().get(wesenIndex);
            flo.jasmin.projekt.domain.Gegenstaende.Ausstattung ausruestung = 
                (flo.jasmin.projekt.domain.Gegenstaende.Ausstattung) verfuegbareAusruestung.get(ausruestungsIndex);
            
            flo.jasmin.projekt.domain.Gegenstaende.Ausstattung alteAusruestung = null;
            if (ausruestung.getBeinflussterWert() == flo.jasmin.projekt.domain.Gegenstaende.Ausstattung.BeinflussterWert.ANGRIFF) {
                alteAusruestung = wesen.entferneWaffe();
            } else {
                alteAusruestung = wesen.entferneRuestung();
            }
            
            wesen.ruesteAus(ausruestung);
            team.getInventar().entferneAusruestung(ausruestung);
            
            if (alteAusruestung != null) {
                team.getInventar().fuegeAusruestungHinzu(alteAusruestung);
                antwort.add(wesen.getName() + " hat " + alteAusruestung.getName() + " abgelegt und " + 
                           ausruestung.getName() + " ausgeruestet!");
            } else {
                antwort.add(wesen.getName() + " hat " + ausruestung.getName() + " ausgeruestet!");
            }
            
            antwort.add("\nNeuer Status:");
            antwort.add(wesen.getAusruestungsStatus());
            antwort.add("\nAngriff: " + wesen.getAngriff() + " | Verteidigung: " + wesen.getVerteidigung());
            
        } catch (NumberFormatException e) {
            antwort.add("Bitte gib gueltige Zahlen ein!");
            antwort.addAll(zeigeAusruestungsmenue());
        }
        
        return antwort;
    }
}
