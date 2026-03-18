package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.application.Persistenz;
import flo.jasmin.projekt.application.Spiel;
import flo.jasmin.projekt.domain.Befehl;
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

            Befehl befehl = eingabeÜbersetzenInBefehl(eingabe.toLowerCase());

            if(spiel.getStatus() == Status.KOCHEN && befehl != Befehl.ZURÜCK){
                spiel.spieleBefehl(Befehl.ZUTATEN, eingabe);
            }
            else{
                spiel.spieleBefehl(befehl, "");
                if(befehl == Befehl.SPEICHERN) {
                    break;
                }
            }
        }
    }



    public Befehl eingabeÜbersetzenInBefehl(String eingabe){
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
        else if(Objects.equals(eingabe, "zurück")){
            return Befehl.ZURÜCK;
        }
        else if(Objects.equals(eingabe, "speichern")){
            return Befehl.SPEICHERN;
        }
        return Befehl.SPEICHERN;
    }

    public void anzeigen(Spiel spiel){
        Status status = spiel.getStatus();
        if(status == Status.EXISTIEREN){
            System.out.println("Wir existieren");
        }
        else if (status == Status.CAMPEN){
            System.out.println("Ihr sitzt am Lagerfeuer und singt das Lagerfeuerlied.");
        } else if (status == Status.KOCHEN) {
            System.out.println("Jetzt wird gekocht");
            System.out.println("Inventar:");
            for(Gegenstand gegenstand : spiel.getInventar().getGegenstände()){
                System.out.println(gegenstand.getName());
            }
        }
    }
}
