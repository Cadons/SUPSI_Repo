package matteo_cadoni.serie10.es2;

import java.sql.*;

/*
* Database SQL
*
create table address(
    name varchar(20) not null,
    surname varchar(20) not null,
    phone_number int not null,
    mobile_phone_number int not null,
    email varchar(20) not null,
    primary key (name, surname)
);
* */
class Contact {
    private String name;
    private String surname;
    private int phoneNumber;
    private int mobilePhoneNumber;
    private String email;

    public Contact(String name, String surname, int phoneNumber, int mobilePhoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMobilePhoneNumber(int mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", mobilePhoneNumber=" + mobilePhoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}


public class AddressBookTest {
    private static Connection connection;

    public static void main(String[] args) {
        try (Connection myconn = DriverManager.getConnection("jdbc:h2:~/Users/Matte/OneDrive - SUPSI\\A.A. 2021-2022/Corsi/SA/Programmazione ad oggetti/database/addressbook", "sa", "1");) {
            connection = myconn;
            deleteAllContacts();
            insertContact("giuseppe", "verdi", 33334333, 45398432, "g.v@me.com");
            insertContact("mario", "rossi", 23441232, 33244232, "mario.rossi@me.com");
            System.out.println(find("mario","rossi"));

            var tmp=find("mario","rossi");
            tmp.setMobilePhoneNumber(33345643);
            updateContact("mario","rossi",tmp);
            System.out.println(find("mario","rossi"));

            tmp.setName("giuseppe");
            tmp.setSurname("rossi");

            updateContact("mario","rossi",tmp);
            System.out.println(find("giuseppe","rossi"));
            deleteContact("giuseppe","verdi");
            deleteContact("bob","bianchi");

        } catch (SQLException sqlException) {
            System.err.println("SQL ERROR");
            sqlException.printStackTrace();
        }
    }

    private static void insertContact(String name, String surname, int phone, int mobilePhone, String email) throws SQLException {

        if (find(name, surname) != null)
            System.err.println("User is already registred");
        else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Contact (name,surname,phone_number,mobile_phone_number,email) values(?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setInt(3, phone);
            stmt.setInt(4, mobilePhone);
            stmt.setString(5, email);
            stmt.execute();
            System.out.println("User added");

        }

    }
    private static void deleteContact(String name, String surname) throws SQLException {

        if(find(name,surname)==null)
        {
            System.err.println("User not founded");
        }else {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Contact where name=? and surname=?");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.execute();
            System.out.println("User removed");
        }
    }


    private static void updateContact(String name, String surname,Contact editedContact) throws SQLException {
        Contact myContact=find(name,surname);
        if(myContact!=null)
        {
            if((!editedContact.getName().equals(name)||!editedContact.getSurname().equals(surname))&&find(editedContact.getName(),editedContact.getSurname())!=null)
            {
                System.err.println("User already registred!");
                return;
            }
            else if((!editedContact.getName().equals(name)||!editedContact.getSurname().equals(surname))&&find(editedContact.getName(),editedContact.getSurname())==null)
            {
                insertContact(editedContact.getName(),editedContact.getSurname(),editedContact.getPhoneNumber(),editedContact.getMobilePhoneNumber(),editedContact.getEmail());
            }else {
                PreparedStatement stmt = connection.prepareStatement("UPDATE Contact set name=?, surname=?, phone_number=?, mobile_phone_number=?, email=? where name=? and surname=?");
                stmt.setString(1, editedContact.getName());
                stmt.setString(2, editedContact.getSurname());
                stmt.setInt(3, editedContact.getPhoneNumber());
                stmt.setInt(4, editedContact.getMobilePhoneNumber());
                stmt.setString(5, editedContact.getEmail());
                stmt.setString(6, editedContact.getName());
                stmt.setString(7, editedContact.getSurname());
                stmt.execute();
                System.out.println("User updated");
            }
        }
    }
    private static void deleteAllContacts() throws SQLException {

        //Dangerous, utility for test
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Contact where 1=1");

        stmt.execute();
        System.out.println("Database emptied");
    }

    private static Contact find(String name, String surname) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * from Contact where name=? and surname=?");
        stmt.setString(1, name);
        stmt.setString(2, surname);
        ResultSet rs = stmt.executeQuery();
        Contact output = null;
        while (rs.next())
            output = new Contact(rs.getString("name"), rs.getString("surname"), rs.getInt("phone_number"), rs.getInt("mobile_phone_number"), rs.getString("email"));
        return output;
    }
}
