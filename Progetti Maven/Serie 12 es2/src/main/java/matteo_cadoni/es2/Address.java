package matteo_cadoni.es2;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries(
        @NamedQuery(
                name = "getAllAddress",
                query = "select a from Address a"
        ))
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(generator = "increment")
    @Column
    private long id;
    @Column
    private String street;
    @Column
    private int zip;
    @Column
    private String city;
    @Column
    private String country;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry(String country) {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && zip == address.zip && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(country, address.country) && customer.equals(address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, zip, city, country, customer);
    }
}
