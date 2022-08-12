package matteo_cadoni.serie08.es2;

import java.io.*;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestTextAnalyzer {


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

        final ArrayList<String> vowels=new ArrayList<>(Arrays.asList(new String[]{"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"}));
        System.out.println("Text words:"+text.size());
        //streams version
        System.out.println("[Streams] The longest word:"+text.stream().max(Comparator.comparingInt(String::length)).get());//Longest txt
        System.out.println("[Streams]The shortest word with at least 5 characters:"+text.stream().filter((a)->a.length()>=5).min(Comparator.comparingInt(String::length)).get());//the shortest word with at least 5 characters
        System.out.println("[Streams] The word that contains the highest number of vowels:"+text.stream().max( (a, b) ->{
            int va=0,vb=0;
            for(int i=0;i<a.length();i++)
            {
                if(vowels.contains(""+a.charAt(i)))
                    va++;
            }
            for(int i=0;i<b.length();i++)
            {
                if(vowels.contains(""+b.charAt(i)))
                    vb++;
            }
            return va-vb;
        } ).get());
        System.out.println("[Streams]List of words that start with a vowel:[ "+text.stream().filter((a)->vowels.contains(""+a.charAt(0))).collect(Collectors.joining(", "))+"]");//the list of words that start with a vowel
        System.out.println("[Streams]List of words that start with a capital T:["+ String.join(", ",text.stream().filter((a)->a.charAt(0)=='T').collect(Collectors.joining(", ")))+"]");
        System.out.println("[New operation] The number of words that start with a vowel, and the number of remaining words:"+text.stream().filter((a)->vowels.contains(""+a.charAt(0))).count()+(" - ")+text.stream().filter((a)->!(vowels.contains(""+a.charAt(0)))).count());
        System.out.println("[New operation] General statistics about the word lengths such as: sum of lengths, min, average and max length: "+text.stream().collect(Collectors.summarizingDouble(String::length)));

    }
}

