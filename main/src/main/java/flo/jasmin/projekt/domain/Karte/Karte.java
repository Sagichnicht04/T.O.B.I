package main.java.flo.jasmin.projekt.domain.Karte;

import java.util.Map;

public class Karte {
    Map<Integer, Zelle> positionen;
    int momentanePosition;


    public Karte(Map<Integer, Zelle> positionen, int momentanePosition) {
        this.positionen = positionen;
        this.momentanePosition = momentanePosition;
    }

    
    public Map<Integer, Zelle> getPositionen() {
        return positionen;
    }
    public void setPositionen(Map<Integer, Zelle> positionen) {
        this.positionen = positionen;
    }
    public int getMomentanePosition() {
        return momentanePosition;
    }
    public void setMomentanePosition(int momentanePosition) {
        this.momentanePosition = momentanePosition;
    }


    public generiereKarte() {
        for (int i = 0, i<90, i += 10) {
            for (int j = 0, j<9, j ++){
                int zellenNummer = i + j
                System.print.out(zellenNummer, ": ", )
            }
        }
    }


    private int stufeDerGegnerFestlegung(int position) {
        int hoizontale = position % 10;
        int vertikale = position / 10;
        if (vertikale <= 4){
            if(horizontale <=3){
                return 4;
            }
            else {
                return 3;
            }
        } else {
            if(horizontale <= 4){
                return 1;
            } else {
                return 2;
            }
        }
    }

    private Zellentyp gibZellenTyp(int pos) {
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

    }

    private boolean sollWasserZelleSein(int pos){
        int hoizontale = position % 10;
        int vertikale = position / 10;
        if ((vertikale == 5 && horizontale <= 3)
            || (vertikale == 5 && horizontale >= 3)
            || (horizontale == 7 && vertiakle >= 4)
            || pos == 43
        ) {
            return true;
        } else {
            return false;
        }
    }
    
}
