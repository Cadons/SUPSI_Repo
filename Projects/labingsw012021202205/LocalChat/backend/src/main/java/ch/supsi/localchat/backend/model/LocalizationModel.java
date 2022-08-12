package ch.supsi.localchat.backend.model;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationModel {

    private static String budleName = "i18n/translations";

    private static LocalizationModel model;

    private ResourceBundle translations;

    protected LocalizationModel() {
    }

    // singleton instance creation
    public static LocalizationModel instance() {
        if (model == null) {
            model = new LocalizationModel();
        }

        return model;
    }

    public void init(Locale locale) {
        this.translations = ResourceBundle.getBundle(budleName, locale);
    }

    public String translate(String key) {
        if (model == null) {
            return key;
        }

        try {
            String translation = this.translations.getString(key);
            if (translation.isEmpty()) {
                return key;
            }
            return translation;
        } catch (MissingResourceException e) {
            return key;
        }
    }

}
