package matteo_cadoni.es2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("matteo_cadoni.es2.Main");

    public static void main(String[] args) {
        var tmp = addCustomer("Mario", "Rossi", "mario@rom.com");
        var addresstmp = addAddress("via roma", 123, "Milano", "Italia", tmp);
        addAddress("via verdi", 123, "Milano", "Italia", tmp);

        tmp = addCustomer("Simone", "Fini", "Sim@rom.com");
        addAddress("via berna", 404, "Lugano", "Svizzera", tmp);

        tmp = addCustomer("Gianmarco", "Fasu", "Gmf@rom.com");
        addAddress("via rossi", 32, "Roma", "Italia", tmp);
        addAddress("via arco", 423, "Lugano", "Svizzera", tmp);
        addAddress("1st road ", 404, "New York", "USA", tmp);
        //showData("getAllCustomers");

        if (addOrder(1, 2, 2, 1200) != null) {
            System.out.println("Order added");
        } else
            System.err.println("Order not added, invalid inputs 1 2 2");

        if (addOrder(3, 3, 2, 412) != null) {
            System.out.println("Order added");
        } else
            System.err.println("Order not added, invalid inputs 3 3 2");

        if (addOrder(2, 3, 3, 12) != null) {
            System.out.println("Order added");
        } else
            System.err.println("Order not added, invalid inputs 2 1 1");

        if (addOrder(1, 1, 1, 500) != null) {
            System.out.println("Order added");
        } else
            System.err.println("Order not added, invalid inputs 1 1 1");
        System.out.println("ORDERS:");
        showData("getAllOrders");
        System.out.println("Search by order number");
        searchOrderByOrderNumber(1);
        System.out.println("Search by address");
        searchOrderByAddress(2);
        System.out.println("Search by order Customer");
        searchOrderByCustomer(2);
    }

    private static Customer addCustomer(String name, String lastName, String email) {
        EntityManager manager = emf.createEntityManager();
        var trans = manager.getTransaction();
        var newCustomer = new Customer();
        trans.begin();
        newCustomer.setName(name);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        manager.persist(newCustomer);
        trans.commit();
        manager.close();

        return newCustomer;

    }

    private static Address addAddress(String street, int zip, String city, String country, Customer customer) {
        EntityManager manager = emf.createEntityManager();
        var trans = manager.getTransaction();
        var newAddress = new Address();
        trans.begin();
        newAddress.setStreet(street);
        newAddress.setZip(zip);
        newAddress.setCity(city);
        newAddress.setCountry(country);
        newAddress.setCustomer(customer);
        manager.persist(newAddress);

        trans.commit();
        manager.close();

        return newAddress;
    }

    private static Order addOrder(long customerId, long shippingAddress, long billingAddress, double amount) {
        EntityManager manager = emf.createEntityManager();
        var trans = manager.getTransaction();
        var customer = manager.find(Customer.class, customerId);

        if (customer != null) {

            var newOrder = new Order();
            var shippingAddress_ = manager.find(Address.class, shippingAddress);
            var billingAddress_ = manager.find(Address.class, billingAddress);
            if (customer.getAddresses().contains(shippingAddress_) && customer.getAddresses().contains(billingAddress_)) {
                trans.begin();
                newOrder.setCustomer(customer);
                newOrder.setBillingAddress(billingAddress_);
                newOrder.setShippingAddress(shippingAddress_);
                newOrder.setTotalAmount(amount);
                manager.persist(newOrder);

                trans.commit();
                manager.close();
                return newOrder;
            }

        }
        return null;
    }

    private static void showData(String queryName) {
        EntityManager menager = emf.createEntityManager();
        var query = menager.createNamedQuery(queryName);
        var ris = query.getResultList();
        ris.forEach(System.out::println);
        menager.close();

    }

    private static void searchOrderByOrderNumber(long orderNumber) {
        EntityManager menager = emf.createEntityManager();
        var query = menager.createNamedQuery("getOrdersById").setParameter("id", orderNumber);
        var ris = query.getResultList();
        ris.forEach(System.out::println);
        menager.close();
    }

    private static void searchOrderByCustomer(long customer) {
        EntityManager menager = emf.createEntityManager();
        var query = menager.createNamedQuery("getOrdersByCustomer").setParameter("customer", customer);
        var ris = query.getResultList();
        ris.forEach(System.out::println);
        menager.close();
    }

    private static void searchOrderByAddress(long address) {
        EntityManager menager = emf.createEntityManager();
        var query = menager.createNamedQuery("getOrdersByShippingAddress").setParameter("address", address);
        var ris = query.getResultList();
        ris.forEach(System.out::println);
        menager.close();
    }
}
