package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.model.Language;
import ch.supsi.localchat.backend.repositories.NoUserLoadedException;

public interface IUserPreferencesRepositories {
    public String translate(String key);

    public void changeLanguage(Language language);

    public Language getLanguage() throws NoUserLoadedException;

    void setDefault();
}
