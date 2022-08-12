public class Messaggio {
    private Utente mittente;
    private String testo;

    public Messaggio(Utente mittente, String testo) {
        this.mittente = mittente;
        this.testo = testo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Utente getMittente() {
        return mittente;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "mittente=" + mittente +
                ", testo='" + testo + '\'' +
                '}';
    }
}
