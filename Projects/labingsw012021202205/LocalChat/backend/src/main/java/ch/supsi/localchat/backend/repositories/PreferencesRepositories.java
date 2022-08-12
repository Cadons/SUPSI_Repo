package ch.supsi.localchat.backend.repositories;

import java.io.File;

public class PreferencesRepositories {
    /**
     * @return if directory exists with all sub-folders return true else create missing and return false
     */
    protected static boolean checkDirectory() {
        String homePath = chatHomeFolder();
        var dir = new File(homePath);
        if (!dir.exists()) {
            dir.mkdir();
            new File(chatFolder()).mkdir();
            new File(contactFolder()).mkdir();

            return false;
        } else {
            if (!new File(chatFolder()).exists()) {
                new File(chatFolder()).mkdir();
                return false;
            }
            if (!new File(contactFolder()).exists()) {
                new File(contactFolder()).mkdir();
                return false;
            }
            return true;

        }
    }

    protected static boolean checkUserFolders(String username) {
        File contact = new File(contactFolder() + username);
        if (contact.exists())
            return true;
        else
            return false;
    }

    /**
     * @return return main folder of the chat program
     */
    protected static String chatHomeFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator();
    }

    /**
     * @return return chats' folder of the chat program
     */
    protected static String chatFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator() + "chats" + separator();
    }

    /**
     * @return return contact's folder of the chat program
     */
    protected static String contactFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator() + "users" + separator();
    }

    /**
     * @return return separator char, for every OS
     */
    protected static String separator() {
        return System.getProperty("file.separator");
    }


}
