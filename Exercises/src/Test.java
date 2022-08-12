import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Sorter{
    public static void sortForName(List<Country> list)
    {
        list.sort(Comparator.comparing(Country::getName));
    }
    public static void sortForArea(List<Country> list)
    {
        list.sort(Comparator.comparingInt(Country::getArea));
    }
}
public class Test {
    public static void main(String[] args) {
        List<Country> countries=new ArrayList<>();
        var country1=new Country();
        country1.setArea(10000);
        country1.setName("Switzerland");
        countries.add(country1);
        country1=new Country();
        country1.setArea(5000);
        country1.setName("San Marino");
        countries.add(country1);
        country1=new Country();
        country1.setArea(400000);
        country1.setName("Italy");
        countries.add(country1);

        countries.forEach(e-> System.out.println(e.getName()+" - "+e.getArea()));
        System.out.println("--------area---------");
        Sorter.sortForArea(countries);
        countries.forEach(e-> System.out.println(e.getName()+" - "+e.getArea()));
        System.out.println("--------name---------");

        Sorter.sortForName(countries);

        countries.forEach(e-> System.out.println(e.getName()+" - "+e.getArea()));

    }
}
