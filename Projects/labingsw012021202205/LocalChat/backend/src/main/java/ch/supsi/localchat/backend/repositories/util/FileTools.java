package ch.supsi.localchat.backend.repositories.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileTools {

    /**
     * @return return text content of the given file
     */
    public static String ReadFile(String file) {
        StringBuilder output = new StringBuilder();
        try (FileInputStream input = new FileInputStream(file); Scanner reader = new Scanner(input)) {
            while (reader.hasNextLine()) {
                output.append(reader.nextLine()).append("\n");

            }

            return output.toString();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }

    /**
     * @apiNote write given content on the given file
     */
    public static void WriteFile(String file, String content) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);


        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
