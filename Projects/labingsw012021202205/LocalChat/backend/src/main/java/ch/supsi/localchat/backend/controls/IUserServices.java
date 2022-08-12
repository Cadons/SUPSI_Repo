package ch.supsi.localchat.backend.controls;

import ch.supsi.localchat.backend.model.Contact;

import java.util.List;

public interface IUserServices {
    public boolean doesUserExist(String username);

    public List<Contact> getContactList();

    public boolean login(String username);

    public boolean addContact(Contact contact);

    public boolean newUser(String username);

    public List<Contact> searchContacts(String str);

    public Contact getContact(String username); //utente attivo

    public void logout();
}

