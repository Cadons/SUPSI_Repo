import java.util.ArrayList;
import java.util.Random;

public class Chat {
    private ArrayList<Messaggio> chat=new ArrayList<>();

    //carica rubrica
      /*



       */
    public void invia(Messaggio m)
    {
        if(m!=null)
            chat.add(m);

    }

    public Messaggio getMessage(int index) {
        return chat.get(index);
    }

    public String mostraChat(Utente me, Rubrica rub)
    {
        String out="";
        for (Messaggio m: chat) {
            if(me.equals(m.getMittente()))
            {
               out+=("(ME):"+m);
            }else if(rub.cercaUtente(m.getMittente().getTel()))
            {
                out+=("("+m.getMittente().getNome()+" "+m.getMittente().getCognome()+"):"+m);
            }else
            {
                out+=(("("+m.getMittente().getTel()+"):"+m));
            }
               out+="\n";
        }
        return out;
    }
    public Messaggio randomMessage(Utente me)
    {
        Messaggio tmp;
        int index=0;
        Random rnd=new Random();
        do {
            index=rnd.nextInt(chat.size());
        }while (chat.get(index).getMittente().equals(me));
        return  chat.get(index);
    }
    public String mostraChatPerUtente()
    {
        String out="";
        for (int i=0;i<chat.size();i++) {
           for(int j=0;j<chat.size();j++)
                out+=(("("+m.getMittente().getTel()+"):"+m));

            out+="\n";
        }
        return out;
    }


}
