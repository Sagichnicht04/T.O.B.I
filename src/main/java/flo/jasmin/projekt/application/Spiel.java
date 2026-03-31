package flo.jasmin.projekt.application;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.Team;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
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

    public Spiel(){
        karte = new Karte();
        team = new Team();
        status = Status.EXISTIEREN;
        kochsystem = new Kochsystem();
    }

    //Holen wir vermutlich einfach nur aus dem Zellentypen
    public Set<Befehl> gibErlaubteBefehle(){
        if(status == Status.EXISTIEREN) {
            return karte.gibMomentaneZelle().getZellentyp().getErlaubteBefehle();
        } else if (status == Status.CAMPEN) {
            return Set.of(Befehl.ZURÜCK, Befehl.KOCHEN);
        } else if (status == Status.KOCHEN) {
            return Set.of(Befehl.ZURÜCK, Befehl.ZUTATEN);
        } else if (status == Status.KAMPF) {
            return Set.of(Befehl.ANGRIFF);
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
                            antwort.add(karte.gibMomentaneZelle().getZellentyp().getBeschreibung());
                        } else{
                            antwort.addAll(antwortAusPotenziellerKampf);
                        }
                    } catch (LaufGegenBarriereException e) {
                        antwort.add("How about we explore the area ahead of us later?");
                    }
                } else if (befehl == Befehl.CAMPEN) {
                    status = Status.CAMPEN;
                }
            }
            else if(status == Status.CAMPEN){
                if(befehl == Befehl.KOCHEN){
                    status = Status.KOCHEN;
                }
                else if(befehl == Befehl.KREATURAUSSTATTEN){

                }
                else if (befehl == Befehl.ZURÜCK) {
                    status = Status.EXISTIEREN;
                }
            }
            else if(status == Status.KOCHEN){
                zutatenZeug(befehl, parameter);
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
        }
        return antwort;
    }

    private void zutatenZeug(Befehl befehl, String parameter) {
        if(befehl == Befehl.ZUTATEN){
            Pattern pattern = Pattern.compile("(\\w+)\\s+(\\d+)");
            Matcher matcher = pattern.matcher(parameter);

            ArrayList<String> zutaten = new ArrayList<>();
            ArrayList<Integer> anzahl = new ArrayList<>();

            //TODO: Zwischenschritt über ArrayList unnötig. Sofort Hashmap machen
            while (matcher.find()) {
                zutaten.add(matcher.group(1));
                anzahl.add(Integer.parseInt(matcher.group(2)));
            }
            Map<String, Integer> eingabe = new HashMap<>();

            if(zutaten.size() == anzahl.size() && !zutaten.isEmpty()){
                for (int i=0; i<zutaten.size(); i++){
                    eingabe.put(zutaten.get(i), anzahl.get(i));
                }
                try {
                    team.heile(kochsystem.errechneGesundheit(eingabe, team.getInventar()));
                    for(Gegenstand gegenstand: (ArrayList<Gegenstand>) team.getInventar().getGegenstände().clone()){
                        for(String zutat: eingabe.keySet()){
                            if(Objects.equals(gegenstand.getName(), zutat) && eingabe.get(zutat) > 0){
                                eingabe.put(zutat, eingabe.get(zutat)-1);
                                team.getInventar().entferneGegenstände(new ArrayList<>(List.of(gegenstand)));
                            }
                        }
                    }
                } catch (FalscheZutatenEingabe e) {
                    System.out.println("Bitte richtige Eingabe, danke");
                }
            }
            else{
                System.out.println("Bitte richtige Eingabe, danke");
            }
        } else if (befehl == Befehl.ZURÜCK) {
            status = Status.CAMPEN;
        }
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
}
