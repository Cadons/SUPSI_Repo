package ch.supsi.localchat.backend.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Contact userInfo;
    private final List<Contact> contacts = new ArrayList<>();

    public User(Contact username) {
        this.userInfo = username;
    }

    public void addContact(Contact c) {
        contacts.add(c);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getUserInfo() {
        return userInfo;
    }
}
