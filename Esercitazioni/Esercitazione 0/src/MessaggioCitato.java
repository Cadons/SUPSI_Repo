public class MessaggioCitato extends Messaggio{
    private Messaggio messaggioTarget;

    public MessaggioCitato(Utente mittente, String testo, Messaggio messaggioTarget) {
        super(mittente, testo);
        this.messaggioTarget = messaggioTarget;
    }

    public Messaggio getMessaggioTarget() {
        return messaggioTarget;
    }

    @Override
    public String toString() {
        return "" +
                "<" + messaggioTarget.getTesto() +">"+
                " " + getTesto();
    }
}
