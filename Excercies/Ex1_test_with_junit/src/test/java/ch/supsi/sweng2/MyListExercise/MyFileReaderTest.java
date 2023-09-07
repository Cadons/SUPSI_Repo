package ch.supsi.sweng2.MyListExercise;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.*;

class MyFileReaderTest {


    @Test
    void toDo() {

        PrintStream output = null;
        try {
            output = new PrintStream(new FileOutputStream("test.txt"));
            System.setOut(output);
            MyFileReader.toDo();
           // assertEquals("oggi\nstai\ncome\nciao", String.join("\n",Files.readAllLines(Path.of("test.txt"))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}