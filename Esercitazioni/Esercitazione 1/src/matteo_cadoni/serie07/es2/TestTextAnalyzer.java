package matteo_cadoni.serie07.es2;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TestTextAnalyzer {
    private static String find(List<String> words, BiPredicate<String, String> operation) {
        String max = words.get(0);
        for (String e : words) {
            if (operation.test(max, e)) {
                max = e;
            }
        }
        return max;
    }

    private static List<String> findAll(List<String> words, Predicate<String> operation) {
        ArrayList<String> words_ = new ArrayList<>();
        for (String e : words) {
            if(operation.test(e))
            {
                words_.add(e);
            }
        }
        return words_;
    }

    public static void main(String[] args) {
        ArrayList<String> text = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("input.txt"); Scanner input = new Scanner(file)) {
            //Read
            while (input.hasNext())
                text.add(input.next());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Text words:"+text.size());
        System.out.println("The longest word:"+find(text, (a, b) -> a.length() < b.length()));//Longest txt
        System.out.println("The shortest word with at least 5 characters:"+find(findAll(text,(a)->a.length()>=5),(a,b)->a.length()>b.length()));// the shortest word with at least 5 characters
        System.out.println("The word that contains the highest number of vowels:"+find(text, (a, b) ->{
            int va=0,vb=0;
            for(int i=0;i<a.length();i++)
            {
                if(a.charAt(i)=='a'||a.charAt(i)=='e'||a.charAt(i)=='i'||a.charAt(i)=='o'||a.charAt(i)=='u'||a.charAt(i)=='A'||a.charAt(i)=='E'||a.charAt(i)=='I'||a.charAt(i)=='O'||a.charAt(i)=='U')
                    va++;
            }
            for(int i=0;i<b.length();i++)
            {
                if(b.charAt(i)=='a'||b.charAt(i)=='e'||b.charAt(i)=='i'||b.charAt(i)=='o'||b.charAt(i)=='u'||b.charAt(i)=='A'||b.charAt(i)=='E'||b.charAt(i)=='I'||b.charAt(i)=='O'||b.charAt(i)=='U')
                    vb++;
            }
            return va<vb;
        } ));// the word that contains the highest number of vowels
        System.out.println("List of words that start with a vowel:[ "+ String.join(", ",findAll(text,(a)->a.charAt(0)=='a'||a.charAt(0)=='e'||a.charAt(0)=='i'||a.charAt(0)=='o'||a.charAt(0)=='u'||a.charAt(0)=='A'||a.charAt(0)=='E'||a.charAt(0)=='I'||a.charAt(0)=='O'||a.charAt(0)=='U'))+"]");//the list of words that start with a vowel
        System.out.println("List of words that start with a capital T:["+ String.join(", ", findAll(text,(a)->a.charAt(0)=='T'))+"]");

    }
}
