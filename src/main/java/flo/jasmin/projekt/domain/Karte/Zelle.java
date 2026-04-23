package flo.jasmin.projekt.domain.Karte;

import flo.jasmin.projekt.domain.Doerfer.Dorf;
import flo.jasmin.projekt.domain.Karte.ZellenTypen.Zellentyp;
import flo.jasmin.projekt.domain.NPCs.NPC;

public class Zelle {
    Zellentyp zellentyp;
    float gegnerWahrscheinlichkeit;
    NPC npc;
    int stufeDerGegner;
    Dorf dorf;

    public Zelle(Zellentyp zellentyp, float gegnerWahrscheinlichkeit, NPC npc, int stufeDerGegner, Dorf dorf) {
        this.zellentyp = zellentyp;
        this.gegnerWahrscheinlichkeit = gegnerWahrscheinlichkeit;
        this.npc = npc;
        this.stufeDerGegner = stufeDerGegner;
        this.dorf = dorf;
    }

    public String getBeschreibung(){
        if (dorf == null){
            return zellentyp.getBeschreibung();
        } else {
            return dorf.getBeschreibung();
        }
    }

    

    public Dorf getDorf() {
        return dorf;
    }

    public void setDorf(Dorf dorf) {
        this.dorf = dorf;
    }

    public Zellentyp getZellentyp() {
        return zellentyp;
    }

    public void setZellentyp(Zellentyp zellentyp) {
        this.zellentyp = zellentyp;
    }
    public float getGegnerWahrscheinlichkeit() {
        return gegnerWahrscheinlichkeit;
    }
    public void setGegnerWahrscheinlichkeit(float gegnerWahrscheinlichkeit) {
        this.gegnerWahrscheinlichkeit = gegnerWahrscheinlichkeit;
    }
    public NPC getNpc() {
        return npc;
    }
    public void setNpc(NPC npc) {
        this.npc = npc;
    }



    
}
