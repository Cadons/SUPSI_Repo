package ch.supsi.localchat.backend.controls;

import ch.supsi.localchat.backend.model.Language;

public interface IUserPreferencesService {
    String translate(String key);

    void changeLanguage(Language l);

    Language getLanguage();

    void setDefault();
}
