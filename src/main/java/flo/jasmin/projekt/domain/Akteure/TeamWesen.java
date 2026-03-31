package flo.jasmin.projekt.domain.Akteure;

public class TeamWesen extends Wesen{
    private int ausstattungsplatzAngriff;
    private int ausstattungsplatzVerteidigung;

    @Override
    public int getAngriff(){
        return super.getAngriff() + ausstattungsplatzAngriff;
    }

    public TeamWesen(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name) {
        super(gesundheit, verteidigung, angriff, initiative, erfahrung, name);
    }

    public int getAusstattungsplatzAngriff() {
        return ausstattungsplatzAngriff;
    }

    public void setAusstattungsplatzAngriff(int ausstattungsplatzAngriff) {
        this.ausstattungsplatzAngriff = ausstattungsplatzAngriff;
    }


    public void heile(int lebensPunkte){

    }

    public int getAusstattungsplatzVerteidigung() {
        return ausstattungsplatzVerteidigung;
    }

    public void setAusstattungsplatzVerteidigung(int ausstattungsplatzVerteidigung) {
        this.ausstattungsplatzVerteidigung = ausstattungsplatzVerteidigung;
    }
}
