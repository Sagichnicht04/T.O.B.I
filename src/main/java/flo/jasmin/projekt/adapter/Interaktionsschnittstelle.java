package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.application.Persistenz;
import flo.jasmin.projekt.application.Spiel;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Exceptions.FalscheBefehlEingabe;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Status;

import java.util.*;

public class Interaktionsschnittstelle {
    Spiel spiel;
    public static void main(String[] args){
        new Interaktionsschnittstelle();
    }
    public Interaktionsschnittstelle(){

        Scanner scanner = new Scanner(System.in);

        spiel = (new Persistenz("")).laden();
        while(true){

            anzeigen(spiel);

            String eingabe = scanner.nextLine();  // Read user input
            String befehlTeil;
            String paramTeil = "";
            if (eingabe.contains("|")){
                befehlTeil = eingabe.split("\\|")[0].toLowerCase();
                paramTeil = eingabe.split("\\|")[1].toLowerCase();
            } else {
                befehlTeil = eingabe.toLowerCase();
            }

            try {
                Befehl befehl = eingabeÜbersetzenInBefehl(befehlTeil);
                gebAlleInfosAus(spiel.spieleBefehl(befehl, paramTeil));
                
            } catch (FalscheBefehlEingabe e) {
                System.out.println("Hä?");;
            }
        }
    }


    public void gebAlleInfosAus(ArrayList<String> antwort){
        for (String i :antwort){
            System.out.println(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public Befehl eingabeÜbersetzenInBefehl(String eingabe) throws FalscheBefehlEingabe {
        //Kognitive engagement
        if(Objects.equals(eingabe, "norden")){
            return Befehl.HOCH;
        }
        else if(Objects.equals(eingabe, "westen")){
            return Befehl.LINKS;
        }
        else if(Objects.equals(eingabe, "süden")){
            return Befehl.RUNTER;
        }
        else if(Objects.equals(eingabe, "osten")){
            return Befehl.RECHTS;
        }
        else if(Objects.equals(eingabe, "campen")){
            return Befehl.CAMPEN;
        }
        else if(Objects.equals(eingabe, "kochen")){
            return Befehl.KOCHEN;
        }
        else if(Objects.equals(eingabe, "zurueck")){
            return Befehl.ZURÜCK;
        }
        else if(Objects.equals(eingabe, "speichern")){
            return Befehl.SPEICHERN;
        }
        else if(Objects.equals(eingabe, "angriff")){
            return Befehl.ANGRIFF;
        }
        else if(Objects.equals(eingabe, "zutaten")){
            return Befehl.ZUTATEN;
        }
        else if(Objects.equals(eingabe, "ausstatten")){
            return Befehl.KREATURAUSSTATTEN;
        }
        else if(Objects.equals(eingabe, "kaufen")){
            return Befehl.KAUFEN;
        }
        else if(Objects.equals(eingabe, "ja")){
            return Befehl.JA;
        }
        throw new FalscheBefehlEingabe();
    }

    public void anzeigen(Spiel spiel){
        Status status = spiel.getStatus();
        System.out.println("Momentaner Status: " + status);
/*         else if (status == Status.CAMPEN){
            System.out.println("Ihr sitzt am Lagerfeuer und singt das Lagerfeuerlied.");
            System.out.println("Inventar:");
            for(Gegenstand gegenstand : spiel.getTeam().getInventar().getGegenstände()){
                System.out.println(gegenstand.getName());
            }
            System.out.println();
            System.out.println("Team:");
            for(Wesen wesen : spiel.getTeam().getWesenInTeam()){
                System.out.println(wesen.getName());
            }
        } else if (status == Status.KOCHEN) {
            System.out.println("Jetzt wird gekocht");
            System.out.println("Inventar:");
            for(Gegenstand gegenstand : spiel.getTeam().getInventar().getGegenstände()){
                System.out.println(gegenstand.getName());
            }
        } */
    }
}
