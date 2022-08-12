public class Immagine extends MessaggioMultimediale{
    private int altezza,larghezza;


    public Immagine(Utente mittente, String testo, Formati formato, int altezza, int larghezza, double dimensione) {
        super(mittente, testo, formato,dimensione);
        this.altezza = altezza;
        this.larghezza = larghezza;

    }



    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    @Override
    public String toString() {
        return "Immagine{" +
                "altezza=" + altezza +
                ", larghezza=" + larghezza +
                "} " + super.toString();
    }
}
