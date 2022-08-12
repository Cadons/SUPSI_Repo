package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.controls.IUserPreferencesService;
import ch.supsi.localchat.backend.model.Language;
import ch.supsi.localchat.backend.repositories.NoUserLoadedException;
import ch.supsi.localchat.backend.repositories.UserPreferencesRepository;

public class UserPreferencesService implements IUserPreferencesService {
    private static UserPreferencesService preferencesService;
    private final UserPreferencesRepository repo;
    private UserServices userService = UserServices.instance();

    private UserPreferencesService() {
        repo = new UserPreferencesRepository();
        if (UserServices.getActiveUser() != null)
            repo.setUser(UserServices.getActiveUser().getUserInfo().getUsername());
    }

    public static UserPreferencesService instance() {
        if (preferencesService == null)
            preferencesService = new UserPreferencesService();
        return preferencesService;
    }

    private void update() {
        repo.setUser(UserServices.getActiveUser().getUserInfo().getUsername());

    }

    public String translate(String translate) {
        if (UserServices.getActiveUser() != null) {
            update();
        }
        return repo.translate(translate);
    }

    public void changeLanguage(Language l) {
        if (UserServices.getActiveUser() != null) {
            update();
            repo.changeLanguage(l);

        }
    }

    @Override
    public void setDefault() {
        repo.setDefault();
    }

    public Language getLanguage() {

        try {
            update();
            return repo.getLanguage();
        } catch (NoUserLoadedException e) {
            return Language.EN;//Default
        }
    }
}