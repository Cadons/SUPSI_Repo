package ch.supsi.localchat.backend.controls;

import ch.supsi.localchat.backend.model.Language;
import ch.supsi.localchat.backend.services.UserPreferencesService;


public class UserPreferencesController {

    private UserPreferencesService preferencesService = UserPreferencesService.instance();

    public void changeLanguage(String choice) {
        preferencesService.changeLanguage(Language.valueOf(choice));
    }

    public String translate(String key) {
        return preferencesService.translate(key);
    }

    public String getLanguage() {
        return preferencesService.getLanguage().toString();
    }

    public void setDefault() {
        preferencesService.setDefault();
    }
}
