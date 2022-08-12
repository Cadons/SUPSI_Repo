package ch.supsi.localchat.backend.repositories.util;

import java.io.File;

public class ChatFileUtils {
    /**
     * @return if directory exists with all sub-folders return true else create missing and return false
     */
    public static boolean checkDirectory() {
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

    /**
     * @return return main folder of the chat program
     */
    public static String chatHomeFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator();
    }

    /**
     * @return return chats' folder of the chat program
     */
    public static String chatFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator() + "chats" + separator();
    }

    /**
     * @return return contact's folder of the chat program
     */
    public static String contactFolder() {
        return System.getProperty("user.home") + separator() + ".chat" + separator() + "contacts" + separator();
    }

    /**
     * @return return separator char, for every OS
     */
    public static String separator() {
        return System.getProperty("file.separator");
    }
}
