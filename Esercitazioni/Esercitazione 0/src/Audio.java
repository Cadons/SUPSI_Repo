public class Audio extends  MessaggioMultimediale{
    private double durata;
    private CanaliAudio canaleAudio;

    public Audio(Utente mittente, String testo, Formati formato, double dimensione, double durata, CanaliAudio canaleAudio) {
        super(mittente, testo, formato, dimensione);
        this.durata = durata;
        this.canaleAudio = canaleAudio;
    }

    public double getDurata() {
        return durata;
    }

    public CanaliAudio getCanaleAudio() {
        return canaleAudio;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "durata=" + durata +
                ", canaleAudio=" + canaleAudio +
                "} " + super.toString();
    }
}
