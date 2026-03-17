package flo.jasmin.projekt.domain.Karte;

import flo.jasmin.projekt.domain.Akteure.NPC;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Values.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Karte {
    private final static int KARTEN_GRÖSSE = 10;
    Position momentanePosition;
    Map<Position, Zelle> positionen;
    //Position im Format [horizontal, vertikal]


    public Karte() {
        this.momentanePosition = new Position(3, 8); //Entspricht Feld 83
        this.positionen = new HashMap<>();
        generiereKarte();
    }


    public void gehe(Befehl befehl) throws LaufGegenBarriereException {
        int horizontale = momentanePosition.getHorizontal();
        int vertikale = momentanePosition.getVertikal();

        if(vertikale <= 0 && befehl == Befehl.HOCH
                || vertikale >= KARTEN_GRÖSSE-1 && befehl == Befehl.RUNTER
                || horizontale <= 0 && befehl == Befehl.LINKS
                || horizontale >= KARTEN_GRÖSSE-1 && befehl == Befehl.RECHTS){
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

    //Generiert eine Karte im Pattern:
    // 0  1  2  ...
    // 10 11 12 ...
    // ...
    public void generiereKarte() {
        for (int i = 0; i<KARTEN_GRÖSSE; i++) {
            for (int j = 0; j<KARTEN_GRÖSSE; j ++){
                positionen.put(new Position(i,j), new Zelle(gibZellenTyp(new Position(i,j)), 0.75f, new NPC(), stufeDerGegnerFestlegung(new Position(i,j))));
            }
        }
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

    private Zellentyp gibZellenTyp(Position position) {
        /*
        if (pos == 22) {
            return Festung
        }
        elif(pos in [16, 63, 88]){
            return DorfZellenTyp
        } else if ( sollWasserZelleSein(pos) ){
            return WasserZelle
        } else {
            return GrasZelle
        }
         */
        if(sollWasserZelleSein(position)){
            return new WasserZelle();
        }
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
