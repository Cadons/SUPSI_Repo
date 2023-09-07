package ch.supsi.sweng2.MyListExercise;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Presentable<T> {

    /**
     * @apiNote Print on stadout
     * */
    void print();
    /**
     * @apiNote Print on file
     * @param filename file where you want to write the output
     * */
    void print(String filename) throws FileNotFoundException;
    /**
     * @apiNote Print on file and on stdout
     * @param filename file where you want to write the output
     * */
    void printWithCopy(String filename) throws FileNotFoundException;
    /**
     * @apiNote Print on file and show the stats
     * @param filename file where you want to write the output
     * */
    void printWithStatistics(String filename) throws FileNotFoundException;
}
