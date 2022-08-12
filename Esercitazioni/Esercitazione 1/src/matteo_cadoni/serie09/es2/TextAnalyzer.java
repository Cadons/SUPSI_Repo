package matteo_cadoni.serie09.es2;

import javax.swing.text.html.Option;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {


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
        System.out.print("[Streams] The longest word:");//Longest txt
        text.stream().max(Comparator.comparingInt(String::length)).ifPresent((e)->{
            System.out.println(e);
        });
        System.out.println();
        System.out.println("[Streams]The shortest word with at least 5 characters:"+  text.stream()
                .filter((a)->a.length()>=5)
                .min(Comparator.comparingInt(String::length))
                .orElse("<not found>"));//the shortest word with at least 5 characters

        System.out.print("[Streams] The word that contains the highest number of vowels:");
        text.stream().max( (a, b) ->{
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
        } ).ifPresentOrElse((e)->{
            System.out.println(e); },()-> System.out.println("Not founded"));

        System.out.print("[Streams] word with at least 10 characters: ");
        try {
            System.out.println(text
                    .stream()
                    .filter(e -> e.length() >= 10)
                    .collect(Collectors.toList())
                        .stream()
                        .limit(1)
                        .findAny()
                        .orElseThrow(NotFoundedException::new
                        ));
        } catch (NotFoundedException e) {
            e.printStackTrace();
        }

    }
}
class NotFoundedException extends Exception
{
    public NotFoundedException() {

    }

    @Override
    public String getMessage() {
        return super.getMessage()+"\n No word with at least 10 characters founed!";
    }
}
