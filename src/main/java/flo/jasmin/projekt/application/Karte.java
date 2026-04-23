package flo.jasmin.projekt.application;

import flo.jasmin.projekt.domain.NPCs.NPC;
import flo.jasmin.projekt.domain.Doerfer.Farore;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Dorf;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Karte.Zelle;
import flo.jasmin.projekt.domain.Karte.Zellentyp;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.DorfZelle;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.GrasZelle;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.WasserZelle;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.WaldZelle;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.BergZelle;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.FriedhofZelle;
import flo.jasmin.projekt.domain.NPCs.Tobi;
import flo.jasmin.projekt.domain.NPCs.Haendler;
import flo.jasmin.projekt.domain.NPCs.Einsiedler;
import flo.jasmin.projekt.domain.Values.Position;

import java.util.HashMap;
import java.util.Map;

public class Karte {
    private final static int KARTEN_GROeSSE = 10;
    Position momentanePosition;
    Map<Position, Zelle> positionen;


    public Karte() {
        this.momentanePosition = new Position(3, 8); 
        this.positionen = new HashMap<>();
        generiereKarte();
    }


    public void gehe(Befehl befehl) throws LaufGegenBarriereException {
        int horizontale = momentanePosition.getHorizontal();
        int vertikale = momentanePosition.getVertikal();

        if(vertikale <= 0 && befehl == Befehl.HOCH
                || vertikale >= KARTEN_GROeSSE-1 && befehl == Befehl.RUNTER
                || horizontale <= 0 && befehl == Befehl.LINKS
                || horizontale >= KARTEN_GROeSSE-1 && befehl == Befehl.RECHTS){
            throw new LaufGegenBarriereException();
        }
        else {
            if(befehl == Befehl.HOCH){
                momentanePosition = momentanePosition.geheHoch();
            }
            else if(befehl == Befehl.RUNTER){
                momentanePosition = momentanePosition.geheRunter();
            }
            else if(befehl == Befehl.RECHTS){
                momentanePosition = momentanePosition.geheRechts();
            }
            else if(befehl == Befehl.LINKS){
                momentanePosition = momentanePosition.geheLinks();
            }
        }
    }

    public void generiereKarte() {
        for (int i = 0; i<KARTEN_GROeSSE; i++) {
            for (int j = 0; j<KARTEN_GROeSSE; j ++){
                positionen.put(new Position(i,j), erstelleNeueZelle(new Position(i,j)));
            }
        }
    }

    private Zelle erstelleNeueZelle(Position position) {
        Dorf dorf = holeDorf(position);
        Zellentyp zellentyp = gibZellenTyp(position);
        NPC npc = gibNPC(position);
        float gegenerWahrscheinlichkeit = 0.75f;
        int gegnerStufe = stufeDerGegnerFestlegung(position);
        if (dorf != null){
            zellentyp = new DorfZelle();
            gegenerWahrscheinlichkeit = 0;
        }
        Zelle zelle = new Zelle(zellentyp, gegenerWahrscheinlichkeit, npc, gegnerStufe, dorf);
        if(npc != null){
            zelle.getZellentyp().getErlaubteBefehle().add(Befehl.REDEN);
        }
        return zelle;
    }

    private Dorf holeDorf(Position position){
        if (position.getHorizontal() == 3 && position.getVertikal() == 7){
            return new Farore();
        }
        return null;

    }

    private int stufeDerGegnerFestlegung(Position position) {
        if (position.getVertikal() <= 4){
            if(position.getHorizontal() <=3){
                return 4;
            }
            else {
                return 3;
            }
        } else {
            if(position.getHorizontal() <= 4){
                return 1;
            } else {
                return 2;
            }
        }
    }

    private NPC gibNPC(Position position) {
        // Tobi im Sueden
        if (position.getVertikal() == 9 && position.getHorizontal() == 3){
            return new Tobi();
        }
        // Haendler im Westen
        if (position.getVertikal() == 5 && position.getHorizontal() == 1){
            return new Haendler();
        }
        // Einsiedler in den Bergen im Norden
        if (position.getVertikal() == 1 && position.getHorizontal() == 2){
            return new Einsiedler();
        }
        return null;
    }

    private Zellentyp gibZellenTyp(Position position) {
        // Wasser-Zellen
        if(sollWasserZelleSein(position)){
            return new WasserZelle();
        }
        // Berg-Zellen (Norden, obere Haelfte)
        if(position.getVertikal() <= 2){
            return new BergZelle();
        }
        // Friedhof-Zellen (Osten)
        if(position.getHorizontal() >= 8 && position.getVertikal() >= 3 && position.getVertikal() <= 6){
            return new FriedhofZelle();
        }
        // Wald-Zellen (Westen und mittlerer Bereich)
        if((position.getHorizontal() <= 1 && position.getVertikal() >= 3 && position.getVertikal() <= 7)
            || (position.getHorizontal() >= 5 && position.getHorizontal() <= 6 && position.getVertikal() >= 6 && position.getVertikal() <= 8)){
            return new WaldZelle();
        }
        // Standard: Gras-Zellen
        return new GrasZelle();

    }

    private boolean sollWasserZelleSein(Position position){
        if ((position.getVertikal() == 5 && position.getHorizontal() <= 3)
            || (position.getVertikal() == 5 && position.getHorizontal() >= 3)
            || (position.getHorizontal() == 7 && position.getVertikal() >= 4)
            || position.equals(new Position(4,3))
        ) {
            return true;
        } else {
            return false;
        }
    }

    public Zelle gibMomentaneZelle(){
        return positionen.get(momentanePosition);
    }

    public Position getMomentanePosition() {
        return momentanePosition;
    }

    public void setMomentanePosition(Position momentanePosition) {
        this.momentanePosition = momentanePosition;
    }

    public Map<Position, Zelle> getPositionen() {
        return positionen;
    }

    public void setPositionen(Map<Position, Zelle> positionen) {
        this.positionen = positionen;
    }
}
