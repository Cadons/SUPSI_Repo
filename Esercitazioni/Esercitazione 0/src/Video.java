enum CanaliAudio
{
    MONO,STEREO
}
public class Video extends MessaggioMultimediale {
    private int altezza,larghezza;
    private double durata;
    private CanaliAudio canaleAudio;

    public Video(Utente mittente, String testo, Formati formato, double dimensione, int altezza, int larghezza, double durata, CanaliAudio canaleAudio) {
        super(mittente, testo, formato, dimensione);
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.durata = durata;
        this.canaleAudio = canaleAudio;
    }

    public double getDurata() {
        return durata;
    }

    public CanaliAudio getCanaleAudio() {
        return canaleAudio;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public int getAltezza() {
        return altezza;
    }

    @Override
    public String toString() {
        return "Video{" +
                "altezza=" + altezza +
                ", larghezza=" + larghezza +
                ", durata=" + durata +
                ", canaleAudio=" + canaleAudio +
                "} " + super.toString();
    }
}
