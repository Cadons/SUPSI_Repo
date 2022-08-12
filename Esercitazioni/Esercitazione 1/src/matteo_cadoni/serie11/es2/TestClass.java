package matteo_cadoni.serie11.es2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestClass {
    public static <o2> void main(String[] args) throws IOException {
        final List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\Matte\\OneDrive - SUPSI\\A.A. 2021-2022\\Corsi\\SA\\Programmazione ad oggetti\\Esercitazioni\\Esercitazione 1\\albums.csv"));
        System.out.println("request 1");
       allLines.stream().skip(1).map(e-> {//consente il cambio di tipo :D
            String [] data=e.split(";");
            return new Album(Integer.parseInt(data[0]), Year.parse(data[1]),data[2],data[3],data[4],data[5]);
        }).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("request 2");
        System.out.println( allLines.stream().skip(1).map(e-> {//consente il cambio di tipo :D
            String [] data=e.split(";");
            return new Album(Integer.parseInt(data[0]), Year.parse(data[1]),data[2],data[3],data[4],data[5]);
        }).
                collect(Collectors.groupingBy(Album::getYear,Collectors.counting())).
                entrySet().
                stream().max((o1, o2) -> (int) (o1.getValue()-o2.getValue())).
                get()
        );
        System.out.println("request 3");
        System.out.println( allLines.stream().skip(1).map(e-> {//consente il cambio di tipo :D
                    String [] data=e.split(";");
                    return new Album(Integer.parseInt(data[0]), Year.parse(data[1]),data[2],data[3],data[4],data[5]);
                }).
                        collect(Collectors.groupingBy(Album::getGenre,Collectors.counting())).
                        entrySet().
                        stream().max((o1, o2) -> (int) (o1.getValue()-o2.getValue())).
                        get()
        );
        System.out.println("request 4");
       allLines.stream().skip(1).map(e-> {//consente il cambio di tipo :D
                    String [] data=e.split(";");
                    return new Album(Integer.parseInt(data[0]), Year.parse(data[1]),data[2],data[3],data[4],data[5]);
                }).collect(Collectors.groupingBy(Album::getArtist,Collectors.counting())).entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry.comparingByKey()))
                               
                .forEach(System.out::println);
              // .thenComparing(Map.Entry.comparingByKey()).forEach(System.out::println);

    }

}
