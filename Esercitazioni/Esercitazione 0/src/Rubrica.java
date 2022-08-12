import java.util.ArrayList;

public class Rubrica {
    private ArrayList<Utente> rubrica=new ArrayList<>();
    public boolean cercaUtente(String tel)
    {
        for (Utente utente : rubrica) {
            if(utente.getTel()==tel)
                return true;
        }
        return  false;
    }
    public void add(String nome, String cognome, String tel)
    {
        if(!nome.equals("")&&!cognome.equals("")&&!tel.equals(""))
            rubrica.add(new Utente(nome,cognome,tel));
        else
            System.out.println("Campi mancanti!");
    }

}
