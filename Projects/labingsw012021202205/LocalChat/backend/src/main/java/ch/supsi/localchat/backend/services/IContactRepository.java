package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.User;
import ch.supsi.localchat.backend.repositories.NoUserLoadedException;

import java.util.List;

public interface IContactRepository {
    public boolean isPresent(String username);

    public User getUser() throws NoUserLoadedException;

    public Contact getUserContact() throws NoUserLoadedException;

    public void save() throws NoUserLoadedException;

    public boolean load(String username);

    public void addUser(String username) throws NoUserLoadedException;

    public List<Contact> getAll();

    public List<Contact> getAnonymousChat();

    void unload();
}
