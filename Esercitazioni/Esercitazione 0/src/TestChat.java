public class TestChat {
    /*
    * Utente
    * Messaggio
    *
        * Messaggio Multimediale
        *   Video
        *   Immagine
        *   Audio
        * Messaggio Citato
    *
    * */
    public static void main(String[] args) {
        Utente me=new Utente("Test","code","0000");
        Rubrica miaRubrica=new Rubrica();
        Chat chat=new Chat();
        //Creo rubrica utente
        miaRubrica.add("Mario","Rossi","1234");
        miaRubrica.add("Pippo","Bianchi","2345");
        miaRubrica.add("Antonio","Neri","9886");

        chat.invia(new Messaggio(new Utente("Mario","Rossi","1234"),"Ciao!!"));


        chat.invia(new Video(new Utente("Pippo","Verdi","0909"),"video",Formati.AVI,100,1920,1080,0.5,CanaliAudio.STEREO));
        chat.invia(new MessaggioCitato(me,"Ciao Mario",chat.randomMessage(me)));
        chat.invia(new Immagine(new Utente("Pippo","Bianchi","1234"),"immagine",Formati.JPG,1920,1080,340));
        chat.invia(new Audio(new Utente("Antonia","Gialli","3456"),"audio",Formati.MP3,100242,4,CanaliAudio.MONO));
        System.out.println(chat.mostraChat(me,miaRubrica));
    }
}
