package p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class p1tedt {
    public static void main(String[] args) {
        List<String> words=new ArrayList<String>(List.of(new String[]{"pino", "gino", "pasqualino", "nino", "johnny", "albino"}));
        System.out.println("Start input");
        words.forEach(System.out::println);
        System.out.println("-------------");
        //stream
        words.stream().flatMap(word->word.chars().mapToObj(c->(char)c))
                .collect(Collectors.groupingBy((p)->p.charValue(),Collectors.counting()))
                .forEach((e,k)-> System.out.println(e+":"+k));
        Optional<String> t=Optional.of(words.get(0));
        t.ifPresentOrElse(System.out::println,()-> System.out.println("Non presente!"));
        t=Optional.ofNullable(null);
        t.ifPresentOrElse(System.out::println,()-> System.out.println("Non presente!"));

    }
}
