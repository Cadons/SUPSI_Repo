package ch.supsi.sweng2.MyListExercise;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Printer<T> implements Presentable<T> {

    private final MyList<T> myList;
    private final Formater<? super T> formater;

    public Printer(MyList<T> list, Formater<? super T> f) {
        this.myList = list;
        this.formater = f;
    }


    @Override
    public void print() {
        var it = myList.getForwardIterator();
        T next;
        while ((next = it.nextElement()) != null) {
            System.out.println(formater.format(next));
        }

    }

    @Override
    public void print(String filename) throws FileNotFoundException {
        var previoudOut = System.out;
        var output = new PrintStream(new FileOutputStream(filename));
        System.setOut(output);
        print();
        System.setOut(previoudOut);
    }

    @Override
    public void printWithCopy(String filename) throws FileNotFoundException {
        var previoudOut = System.out;
        print();
        var output = new PrintStream(new FileOutputStream(filename));
        System.setOut(output);
        print();
        System.setOut(previoudOut);
    }


    @Override
    public void printWithStatistics(String filename) throws FileNotFoundException {
        print(filename);
        Scanner input = new Scanner(new File(filename));
        int numberOf = 0;
        while (input.hasNextLine()) {
            var line = input.nextLine();
            numberOf += line.toLowerCase().chars().filter(ch -> ch == 'a').count();
        }
        input.close();

        System.out.println("number of a:" + numberOf);
    }

}
