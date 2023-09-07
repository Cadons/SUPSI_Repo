package ch.supsi.sweng2.MyListExercise;

import ch.supsi.sweng2.MyListExercise.*;

import java.io.*;

public class MyFileReader {


    public static void main(String[] args) {
    toDo();
    }
    public static void toDo(){
        MyList<String> list=new MyList<>();
        try(BufferedReader input=new BufferedReader(new FileReader("input.txt"))){
            String line = input.readLine();
            while(line!=null) {

                list.addElement(line);
                line = input.readLine();
            }
            MyIterator<String> bkIterator=list.getBackwardIterator();
            Presentable<String> myPrinter=new Printer<>(list,(e)->e.toString().toUpperCase());

            Presentable<String> myPrinter2=new Printer<>(list,(e)->e.toString().toLowerCase().repeat(2));
            Presentable<String> myPrinter3=new Printer<>(list,(e)->e.toString().toUpperCase().repeat(5));
            while ((line=bkIterator.nextElement())!=null){

            }

            myPrinter.printWithStatistics("support.txt");
            myPrinter2.print();
            myPrinter3.print();
            myPrinter3.printWithStatistics("support2.txt");
        }catch (IOException e){

        }
    }
}
