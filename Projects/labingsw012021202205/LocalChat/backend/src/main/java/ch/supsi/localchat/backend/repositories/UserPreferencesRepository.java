package ch.supsi.localchat.backend.repositories;

import ch.supsi.localchat.backend.model.Language;
import ch.supsi.localchat.backend.model.LocalizationModel;
import ch.supsi.localchat.backend.services.IUserPreferencesRepositories;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

public class UserPreferencesRepository implements IUserPreferencesRepositories {

    private final LocalizationModel localizationModel;
    private String username;

    public UserPreferencesRepository() {
        localizationModel = LocalizationModel.instance();
        //default language
        try {
            Locale.setDefault(new Locale(this.getLanguage().toString(), this.getLanguage().getCountry()));
        } catch (NoUserLoadedException e) {
            Locale.setDefault(new Locale("en", Language.EN.getCountry()));
        }
        localizationModel.init(Locale.getDefault());

    }

    static void setDefaultSettings(String username) {
        //Setup default settings
        Properties settings = new Properties();
        settings.put("language", String.valueOf(Language.EN));
        settings.put("country", Language.EN.getCountry());
        try {
            settings.store(new FileOutputStream(PreferencesRepositories.contactFolder() + username + PreferencesRepositories.separator() + "settings.properties"), "");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUser(String username) {
        this.username = username;

    }

    @Override
    public String translate(String key) {
        return localizationModel.translate(key);
    }

    @Override
    public void changeLanguage(Language language) {
        var file = new File(PreferencesRepositories.contactFolder() + username + File.separator + "settings.properties");
        Properties settings = new Properties();
        settings.put("language", language.toString());
        settings.put("country", language.getCountry());
        try {
            settings.store(new FileOutputStream(file), "");
            Locale.setDefault(new Locale(settings.getProperty("language"), settings.getProperty("country")));
            localizationModel.init(Locale.getDefault());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDefault() {
        Locale.setDefault(new Locale(Language.EN.toString(), Language.EN.getCountry()));
        localizationModel.init(Locale.getDefault());

    }

    @Override
    public Language getLanguage() throws NoUserLoadedException {
        return Language.valueOf(getSettings().getProperty("language"));
    }

    private Properties getSettings() throws NoUserLoadedException {
        if (username != null && !username.equals("")) {
            var file = new File(PreferencesRepositories.contactFolder() + username + File.separator + "settings.properties");
            Properties settings = new Properties();

            try {
                settings.load(new FileInputStream(file));

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return settings;
        } else {
            throw new NoUserLoadedException();
        }
    }


}
