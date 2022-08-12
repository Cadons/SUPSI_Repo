import java.util.Objects;

public class Utente {
    private String nome, cognome, tel;

    public Utente(String nome, String cognome, String tel) {
        this.nome = nome;
        this.cognome = cognome;
        this.tel = tel;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome) && Objects.equals(tel, utente.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, tel);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
