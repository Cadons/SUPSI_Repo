package es1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    // DON'T CHANGE
    private static List<String> loadWordList(final String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // DON'T CHANGE
    private static boolean isPalindrome(final String word) {
        int i = 0, j = word.length() - 1;
        while (i < j)
            if (word.charAt(i++) != word.charAt(j--))
                return false;
        return true;
    }

    // DON'T CHANGE
    private static String toCaMeLcAsE(final String word) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < word.length(); i++)
            if (i % 2 == 0)
                sb.append(Character.toUpperCase(word.charAt(i)));
            else
                sb.append(Character.toLowerCase(word.charAt(i)));
        return sb.toString();
    }

    public static void main(String[] args) {
        // REMARK: make sure to download the file from iCorsi!
        final String wordlistFilePath = "wordlist.txt";
        final List<String> words = loadWordList(wordlistFilePath);

        // FIXME Code to change starts here!!!
        if (words == null) {
            System.out.println("Could not load file");
        } else {
            System.out.println("-----------------------------------");
            System.out.println("- Palindrome words:");
            System.out.println("-----------------------------------");
            System.out.println("stream version");
            words.stream()
                    .filter((e) -> isPalindrome(e))
                    .sorted(String::compareTo)
                     .collect(Collectors.toList())
                    .forEach(System.out::println);
            System.out.println("original version");
            final List<String> palindromes = new ArrayList<>();
            for (final String word : words) {
                if (isPalindrome(word))
                    palindromes.add(word);
            }
            // Sort words
            Collections.sort(palindromes);
            // Print
            for (final String word : palindromes)
                System.out.println(word);

          System.out.println("-----------------------------------");
            System.out.println("- Transformed words:");
            System.out.println("-----------------------------------");

            System.out.println("stream version");
            //TODO da controllare
            words.stream()
                    .filter(e->e.matches("^[aeiou]r[aeiou].[aeiou]"))
                    .map(Test::toCaMeLcAsE)
                    .sorted((o1, o2) -> {return o2.compareToIgnoreCase(o1);})
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

            System.out.println("original version");
            final List<String> transformedWords = new ArrayList<>();
            final String pattern = "^[aeiou]r[aeiou].[aeiou]";
            for (int i = 0; i < words.size(); i++) {
                final String word = words.get(i);
                // word matches regex
                if (word.matches(pattern)) {
                    // transform to camelcase
                    transformedWords.add(toCaMeLcAsE(word));
                }
            }

            // Sort descending (z to a)
            Collections.sort(transformedWords, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareToIgnoreCase(o1);
                }
            });

            // print
            for (int i = 0; i < transformedWords.size(); i++) {
                System.out.println(transformedWords.get(i));
            }

           System.out.println("-----------------------------------");
            System.out.println("- Word count by length:");
            System.out.println("-----------------------------------");
            // compute word count by staring letter
            System.out.println("stream version");
            words.stream().collect(Collectors.groupingBy(String::length,Collectors.counting()))
                    .entrySet().stream().
                    sorted((o1, o2) ->Integer.compare(o1.getKey(),o2.getKey()))
                    .forEach(e-> System.out.println(String.format("%d: %d words", e.getKey(), e.getValue())));
            System.out.println("original version");
            final Map<Integer, Integer> wordcountByWordLenght = new HashMap<>();
            for (final String word : words) {
                final int length = word.length();
                if (!wordcountByWordLenght.containsKey(length))
                    wordcountByWordLenght.put(length, 0);
                final Integer currentCount = wordcountByWordLenght.get(length);

                // increment counter for lenght
                wordcountByWordLenght.put(length, currentCount.intValue() + 1);
            }

            // Sort ascending by word length
            List<Entry<Integer, Integer>> sorted = new ArrayList<>(wordcountByWordLenght.entrySet());
            Collections.sort(sorted, new Comparator<Entry<Integer, Integer>>() {

                @Override
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                    return Integer.compare(o1.getKey(), o2.getKey());
                }
            });

            // Print char:
            for (Entry<Integer, Integer> entry : sorted) {
                System.out.println(String.format("%d: %d words", entry.getKey(), entry.getValue()));
            }

            System.out.println("-----------------------------------");
            System.out.println("- Letter distribution:");
            System.out.println("-----------------------------------");
            System.out.println("stream version");
          //  words.stream().map(String::chars).collect(Collectors.toList()).forEach(System.out::println);//.collect(Character::,Collectors.counting()));

                  //  .collect(Character::valueOf,Collectors.counting());
            System.out.println("original version");
            final Map<Character, Integer> letterCount = new HashMap<>();
            for (final String word : words) {

                // Get all characters from string
                final List<Character> wordChars = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                for (int idx = 0; idx < wordChars.size(); idx++) {
                    final char charAt = word.charAt(idx);

                    if (letterCount.containsKey(charAt) == false)
                        letterCount.put(charAt, 0);
                    final Integer currentCount = letterCount.get(charAt);
                    letterCount.put(charAt, currentCount.intValue() + 1);
                }
            }

            // sort by count descending
            List<Entry<Character, Integer>> sortedLetterCount = new ArrayList<>(letterCount.entrySet());
            Collections.sort(sortedLetterCount, Collections.reverseOrder(new Comparator<Entry<Character, Integer>>() {
                @Override
                public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                    return Integer.compare(o1.getValue(), o2.getValue());
                }
            }));

            for (int i = 0; i < sortedLetterCount.size(); i++) {
                final Entry<Character, Integer> entry = sortedLetterCount.get(i);
                System.out.println(String.format("%c: %d", entry.getKey(), entry.getValue()));
            }
        }
    }
}