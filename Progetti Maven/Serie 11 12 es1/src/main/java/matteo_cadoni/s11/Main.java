package matteo_cadoni.s11;

import matteo_cadoni.s11.Author;
import matteo_cadoni.s11.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("matteo_cadoni.s11.Main");

    public static void main(String[] args) {
        factory.getMetamodel().getEntities().forEach(System.out::println);
        final Author stephenKing = insertAuthor("Stephen", "King", 1947);
        final Author hermanMelville = insertAuthor("Herman", "Melville", 1819);
        final Author hermannHesse = insertAuthor("Hermann", "Hesse", 1877);

        insertBook("Carrie", 1974, "Doubleday", stephenKing);
        insertBook("Shining", 1977, "Doubleday", stephenKing);
        insertBook("IT", 1986, "Viking", stephenKing);
        insertBook("Moby dick", 1851, "Richard Bentley", hermanMelville);
        insertBook("Siddhartha", 1922, "Suhrkamp Verlag", hermannHesse);

        System.out.println("author(s) by id");
        for (int i = 1; i < 5; i++)
            System.out.println(getAuthorById(Long.valueOf(i)));

        System.out.println();
        System.out.println("book(s) by id");
        for (int i = 1; i < 5; i++)
            System.out.println(getBookById(Long.valueOf(i)));

        System.out.println("author(s)");
        getAllAuthors().forEach(System.out::println);

        System.out.println();
        System.out.println("book(s)");
        getAllBooks().forEach(System.out::println);

        System.out.println();
        System.out.println("book(s) by " + hermanMelville);
        getAllBooksByAuthors(hermanMelville).forEach(System.out::println);

        System.out.println();
        System.out.println("book(s) by " + stephenKing);
        getAllBooksByAuthors(stephenKing).forEach(System.out::println);
        factory.close();
    }

    /**
     * Add a new author to the database
     *
     * @param firstName
     * @param lastName
     * @param birthYear
     */
    private static Author insertAuthor(String firstName, String lastName, int birthYear) {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        var trans = entityMenager.getTransaction();
        trans.begin();

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthYear(birthYear);

        entityMenager.persist(author);
        System.out.println("author id: " + author.getId());
        trans.commit();
        entityMenager.close();

        return author;
    }

    /**
     * Add a new book to the database
     *
     * @param title
     * @param year
     * @param publisher
     */
    private static Book insertBook(String title, int year, String publisher, Author author) {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        var trans = entityMenager.getTransaction();
        trans.begin();
        Book book = new Book();

        book.setTitle(title);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setAuthor(author);
        entityMenager.persist(book);
        trans.commit();
        entityMenager.close();
        return book;
    }

    /**
     * Returns matteo_cadoni.s11.Author for given id
     *
     * @param id
     * @return
     */
    private static Author getAuthorById(final Long id) {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        Author output = entityMenager.find(Author.class, id);
        entityMenager.close();
        return output;

    }

    /**
     * Returns matteo_cadoni.s11.Book for given id
     *
     * @param id
     * @return
     */
    private static Book getBookById(final Long id) {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        Book output = entityMenager.find(Book.class, id);
        entityMenager.close();
        return output;
    }

    private static List<Author> getAllAuthors() {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        List<Author> output = entityMenager.createQuery("SELECT a FROM Author a").getResultList();

        entityMenager.close();
        return output;
    }

    /**
     * Returns a list of all stored boosks
     *
     * @return
     */
    private static List<Book> getAllBooks() {
        // FIXME to implement

        EntityManager entityMenager = factory.createEntityManager();
        List<Book> output = entityMenager.createQuery("SELECT book FROM Book book").getResultList();

        entityMenager.close();
        return output;
    }

    /**
     * Returns a list of all books written by a given author
     *
     * @return
     */
    private static List<Book> getAllBooksByAuthors(Author author) {
        // FIXME to implement
        EntityManager entityMenager = factory.createEntityManager();
        List<Book> output = entityMenager.createQuery("SELECT book FROM Book book where book.author=?1").setParameter(1, author).getResultList();

        entityMenager.close();
        return output;
    }
}
