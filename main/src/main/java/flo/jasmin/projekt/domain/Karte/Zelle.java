package main.java.flo.jasmin.projekt.domain.Karte;

import main.java.flo.jasmin.projekt.domain.Akteure.NPC;

public class Zelle {
    Zellentyp zellentyp;
    float gegnerWahrscheinlichkeit;
    NPC npc;
    int stufeDerGegner;
    //Ablauf

    public Zelle(Zellentyp zellentyp, float gegnerWahrscheinlichkeit, NPC npc, int stufeDerGegner) {
        this.zellentyp = zellentyp;
        this.gegnerWahrscheinlichkeit = gegnerWahrscheinlichkeit;
        this.npc = npc;
        this.stufeDerGegner = stufeDerGegner;
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
