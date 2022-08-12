package matteo_cadoni.es2;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "getAllCustomers",
                query = "select a from Customer a"

        ),
      })
@Entity
@Table(name = "Customer")
public class Customer {
    @javax.persistence.Id
    @GeneratedValue(generator = "increment")
    @Column(nullable = false)
    private long id;
    @Column
    private String name;
    @Column

    private String lastName;
    @Column

    private String email;
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
