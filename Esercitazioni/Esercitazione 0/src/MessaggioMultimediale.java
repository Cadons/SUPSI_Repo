public class MessaggioMultimediale extends Messaggio {
    private Formati formato;
    private double dimensione;
    public MessaggioMultimediale(Utente mittente, String testo, Formati formato, double dimensione) {
        super(mittente, testo);
        this.formato=formato;
    }

    public double getDimensione() {
        return dimensione;
    }

    public Formati getFormato() {
        return formato;
    }

    @Override
    public String toString() {
        return "MessaggioMultimediale{" +
                "formato=" + formato +
                ", dimensione=" + dimensione +
                "} " + super.toString();
    }
}
