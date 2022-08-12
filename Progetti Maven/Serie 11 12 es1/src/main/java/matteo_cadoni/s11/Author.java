package matteo_cadoni.s11;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "increment")
    @Column(nullable = false)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int birthYear;
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return String.format("Author [id=%s, firstName=%s, lastName=%s, birthYear=%s]", id, firstName, lastName, birthYear);
    }
}
