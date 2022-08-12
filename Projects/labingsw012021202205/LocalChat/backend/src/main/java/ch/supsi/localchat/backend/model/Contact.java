package ch.supsi.localchat.backend.model;

public class Contact {
    final private String username;


    public Contact(final String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return username != null ? username.equals(contact.username) : contact.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
