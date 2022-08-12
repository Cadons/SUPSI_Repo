package matteo_cadoni.serie10.es1;

import org.h2.table.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

/*

REFERENCE SQL:

drop table if exist book, author;

create table author (
    first_name varchar2(20) not null,
    last_name varchar2(20) not null,
    birth_year integer,
    primary key (first_name, last_name)
);
create table book (
    title varchar2(50),
    pub_year integer,
    publisher varchar2(20),
    author_first_name varchar2(20),
    author_last_name varchar2(20),
    primary key (title),
    foreign key(author_first_name, author_last_name) references author(first_name, last_name)
);

*/
//Versione 1
/*public class Books {
    private static Connection conn;

    public static void main(String[] args) {

        try {
            conn = DriverManager.getConnection("jdbc:h2:/Users/Matte/OneDrive - SUPSI\\A.A. 2021-2022/Corsi/SA/Programmazione ad oggetti/database/books", "sa", "1");


            // insertAuthor("Stephen", "King", 1947);
            // insertBook("Carrie", 1974, "Doubleday", "Stephen", "King");
            // insertBook("Shining", 1977, "Doubleday", "Stephen", "King");
            // insertBook("IT", 1986, "Viking", "Stephen", "King");

            // insertAuthor("Herman", "Melville", 1819);
            // insertBook("Moby dick", 1851, "Richard Bentley", "Herman", "Melville");

            // insertAuthor("Hermann", "Hesse", 1877);
            // insertBook("Siddhartha", 1922, "Suhrkamp Verlag", "Hermann", "Hesse");

            System.out.println("author(s)");
            printTable("author");

            System.out.println();
            System.out.println("book(s)");
            printTable("book");

            System.out.println();
            System.out.println("complete books list");
            printBooksFull();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private static void insertAuthor(String firstName, String lastName, int birthYear) throws SQLException {
        // FIXME to implement
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Author (first_name,last_name,birth_year) VALUES('" + firstName + "','" + lastName + "','" + birthYear + "')");
        stmt.close();
    }


    private static void insertBook(String title, int pub_year, String publisher, String firstName, String lastName) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO Book (title,pub_year,publisher,author_first_name,author_last_name) VALUES('" + title + "','" + pub_year + "','" + publisher + "','" + firstName + "','" + lastName + "')");
        stmt.close();

    }

    private static void printBooksFull() throws SQLException {
        // FIXME to implement
        // REMARK use inner join to
        printTable("(select * from book inner join author on author_first_name=first_name and author_last_name=last_name)");
    }


    private static void printTable(String table) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData data = rs.getMetaData();
        while (rs.next()) {
            for (int i = 1; i < data.getColumnCount(); i++) {
                System.out.print(data.getColumnName(i) + ":" + rs.getString(i) + " ");

            }
            System.out.println();
        }
        rs.close();
        stmt.close();
    }
}
*/
//Versione 2
/*public class Books {
    private static Connection conn;

    public static void main(String[] args) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:/Users/Matte/OneDrive - SUPSI\\A.A. 2021-2022/Corsi/SA/Programmazione ad oggetti/database/books", "sa", "1");
        ) {
            conn = connection;
          //   insertAuthor("Stephen", "King", 1947);
             insertBook("Carrie", 1974, "Doubleday", "Stephen", "King");
             insertBook("Shining", 1977, "Doubleday", "Stephen", "King");
             insertBook("IT", 1986, "Viking", "Stephen", "King");

             insertAuthor("Herman", "Melville", 1819);
             insertBook("Moby dick", 1851, "Richard Bentley", "Herman", "Melville");

             insertAuthor("Hermann", "Hesse", 1877);
             insertBook("Siddhartha", 1922, "Suhrkamp Verlag", "Hermann", "Hesse");

            System.out.println("author(s)");
            printTable("author");

            System.out.println();
            System.out.println("book(s)");
            printTable("book");

            System.out.println();
            System.out.println("complete books list");
            printBooksFull();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void insertAuthor(String firstName, String lastName, int birthYear) throws SQLException {
        // FIXME to implement
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Author (first_name,last_name,birth_year) VALUES(?,?,?)");
        //  stmt.executeUpdate("INSERT INTO Author (first_name,last_name,birth_year) VALUES('" + firstName + "','" + lastName + "','" + birthYear + "')");
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setInt(3, birthYear);

        stmt.execute();
        stmt.close();
    }


    private static void insertBook(String title, int pub_year, String publisher, String firstName, String lastName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book (title,pub_year,publisher,author_first_name,author_last_name) VALUES(?,?,?,?,?)");
        //  stmt.executeUpdate("INSERT INTO Author (first_name,last_name,birth_year) VALUES('" + firstName + "','" + lastName + "','" + birthYear + "')");
        stmt.setString(1, title);
        stmt.setInt(2, pub_year);
        stmt.setString(3, publisher);
        stmt.setString(4, firstName);
        stmt.setString(5, lastName);
        stmt.execute();
        //stmt.executeUpdate("INSERT INTO Book (title,pub_year,publisher,author_first_name,author_last_name) VALUES('" + title + "','" + pub_year + "','" + publisher + "','" + firstName + "','" + lastName + "')");
        stmt.close();

    }

    private static void printBooksFull() throws SQLException {
        // FIXME to implement
        // REMARK use inner join to
        printTable("(select * from book inner join author on author_first_name=first_name and author_last_name=last_name)");
    }


    private static void printTable(String table) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+table);
     //   stmt.setString(1, table);
       // ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
        ResultSet rs=stmt.executeQuery();
        ResultSetMetaData data = rs.getMetaData();
        while (rs.next()) {
            for (int i = 1; i < data.getColumnCount(); i++) {
                System.out.print(data.getColumnName(i) + ":" + rs.getString(i) + " ");

            }
            System.out.println();
        }
        rs.close();
        stmt.close();
    }
}*/
//Versione 3

public class Books {
    private static Connection conn;

    private static class Author {
        private String firstName, lastName;
        private int birthYear;

        public Author(String firstName, String lastName, int birthYear) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthYear = birthYear;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getBirthYear() {
            return birthYear;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", birthYear=" + birthYear +
                    '}';
        }
    }

    private static class Book {
        private String title, publisher, authorFirstName, authorLastName;
        private int pubYear;

        public Book(String title, int pubYear, String publisher, String authorFirstName, String authorLastName) {
            this.title = title;
            this.publisher = publisher;
            this.authorFirstName = authorFirstName;
            this.authorLastName = authorLastName;
            this.pubYear = pubYear;
        }

        public String getTitle() {
            return title;
        }

        public String getPublisher() {
            return publisher;
        }

        public String getAuthorFirstName() {
            return authorFirstName;
        }

        public String getAuthorLastName() {
            return authorLastName;
        }

        public int getPubYear() {
            return pubYear;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", publisher='" + publisher + '\'' +
                    ", authorFirstName='" + authorFirstName + '\'' +
                    ", authorLastName='" + authorLastName + '\'' +
                    ", pubYear=" + pubYear +
                    '}';
        }
    }

    public static void main(String[] args) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:/Users/Matte/OneDrive - SUPSI\\A.A. 2021-2022/Corsi/SA/Programmazione ad oggetti/database/books", "sa", "1");
        ) {
            conn = connection;
            deleteAll();
            insertAuthor(new Author("Stephen", "King", 1947));
            insertBook(new Book("Carrie", 1974, "Doubleday", "Stephen", "King"));

            insertBook(new Book("Shining", 1977, "Doubleday", "Stephen", "King"));
            insertBook(new Book("IT", 1986, "Viking", "Stephen", "King"));

            insertAuthor(new Author("Herman", "Melville", 1819));
            insertBook(new Book("Moby dick", 1851, "Richard Bentley", "Herman", "Melville"));

            insertAuthor(new Author("Hermann", "Hesse", 1877));
            insertBook(new Book("Siddhartha", 1922, "Suhrkamp Verlag", "Hermann", "Hesse"));
            System.out.println("author(s)");
            printTable("author");

            System.out.println();
            System.out.println("book(s)");
            printTable("book");

            System.out.println();
            System.out.println("complete books list");
            printBooksFull();

            insertBook(new Book("Moef3fby dick", 1851, "Richard Bentley", "Heerman", "Melville"));

            getAllAuthor().forEach(System.out::println);
            getAllBooks().forEach(System.out::println);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAll() throws SQLException {

        //Dangerous, utility for test
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM book where 1=1");

        stmt.execute();
        stmt = conn.prepareStatement("DELETE FROM author where 1=1");

        stmt.execute();
        System.out.println("Database emptied");
    }

    private static void insertAuthor(Author author) throws SQLException {
        // FIXME to implement
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Author (first_name,last_name,birth_year) VALUES(?,?,?)");
        //  stmt.executeUpdate("INSERT INTO Author (first_name,last_name,birth_year) VALUES('" + firstName + "','" + lastName + "','" + birthYear + "')");
        stmt.setString(1, author.getFirstName());
        stmt.setString(2, author.getLastName());
        stmt.setInt(3, author.getBirthYear());

        stmt.execute();
        stmt.close();
    }


    private static void insertBook(Book book) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT first_name, last_name from author where first_name=? and last_name=?");
        stmt.setString(1, book.getAuthorFirstName());
        stmt.setString(2, book.getAuthorLastName());
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            System.out.println("Author not registred!");
            return;
        }
        //  stmt.executeUpdate("INSERT INTO Author (first_name,last_name,birth_year) VALUES('" + firstName + "','" + lastName + "','" + birthYear + "')");
        stmt = conn.prepareStatement("INSERT INTO Book (title,pub_year,publisher,author_first_name,author_last_name) VALUES(?,?,?,?,?)");

        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getPubYear());
        stmt.setString(3, book.getPublisher());
        stmt.setString(4, book.getAuthorFirstName());
        stmt.setString(5, book.getAuthorLastName());
        stmt.execute();
        //stmt.executeUpdate("INSERT INTO Book (title,pub_year,publisher,author_first_name,author_last_name) VALUES('" + title + "','" + pub_year + "','" + publisher + "','" + firstName + "','" + lastName + "')");
        stmt.close();

    }

    private static void printBooksFull() throws SQLException {
        // FIXME to implement
        // REMARK use inner join to
        printTable("(select title, pub_year, publisher from book inner join author on author_first_name=first_name and author_last_name=last_name)");
    }


    private static void printTable(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;
        if (table.equals("Book")) {
            sql = "SELECT title, year, publisher FROM " + table;
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        //   stmt.setString(1, table);
        // ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData data = rs.getMetaData();

        while (rs.next()) {
            for (int i = 1; i < data.getColumnCount(); i++) {
                System.out.print(data.getColumnName(i) + ":" + rs.getString(i) + " ");

            }
            System.out.println();
        }
        rs.close();
        stmt.close();
    }

    private static ArrayList<Author> getAllAuthor() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Author");
        ArrayList<Author> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData data = rs.getMetaData();
        while (rs.next()) {
            Author author = new Author(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("birth_year"));
            list.add(author);
        }
        rs.close();
        stmt.close();
        return list;
    }

    private static ArrayList<Book> getAllBooks() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book");
        ArrayList<Book> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData data = rs.getMetaData();
        while (rs.next()) {
            Book book = new Book(rs.getString("title"), rs.getInt("pub_year"), rs.getString("publisher"), rs.getString("author_first_name"), rs.getString("author_last_name"));
            list.add(book);
        }
        rs.close();
        stmt.close();
        return list;
    }
}