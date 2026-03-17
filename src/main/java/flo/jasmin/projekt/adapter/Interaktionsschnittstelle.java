package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.application.Persistenz;
import flo.jasmin.projekt.application.Spiel;
import flo.jasmin.projekt.domain.Befehl;
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

            anzeigen(spiel.getStatus());

            String eingabe = scanner.nextLine();  // Read user input

            Befehl befehl = eingabeÜbersetzenInBefehl(eingabe.toLowerCase());

            spiel.spieleBefehl(befehl, new String[]{});

            if(befehl == Befehl.SPEICHERN) {
                break;
            }
        }
    }



    public Befehl eingabeÜbersetzenInBefehl(String eingabe){
        if(Objects.equals(eingabe, "w")){
            return Befehl.HOCH;
        }
        return Befehl.SPEICHERN;
    }

    public void anzeigen(Status status){
        if(status == Status.EXISTIEREN){
            System.out.println("Wir existieren");
        }
    }
}
