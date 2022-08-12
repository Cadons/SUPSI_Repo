package matteo_cadoni.s11;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "increment")
    @Column(nullable = false)
    private Long id;
    @Column
    private int year;
    @Column
    private String title;
    @Column
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return String.format("Book [id=%s, year=%s, title=%s, publisher=%s, author=%s]", id, year, title, publisher, author);
    }
}
