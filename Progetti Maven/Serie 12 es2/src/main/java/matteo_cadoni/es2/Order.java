package matteo_cadoni.es2;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getAllOrders",
                query = "select a from Order a"
        ),
        @NamedQuery(
                name = "getOrdersById",
                query = "select a from Order a where orderNumber=:id"
        ),
        @NamedQuery(
                name = "getOrdersByCustomer",
                query = "select a from Order a where customer.id=:customer"
        ),
        @NamedQuery(
                name = "getOrdersByShippingAddress",
                query = "select a from Order a where shippingAddress.id=:address"
        )
})
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(generator = "increment")
    @Column
    private long orderNumber;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shippingAddress")
    private Address shippingAddress;
    @ManyToOne
    @JoinColumn(name = "billingAddress")
    private Address billingAddress;
    @Column
    private double totalAmount;

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customer=" + customer +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
