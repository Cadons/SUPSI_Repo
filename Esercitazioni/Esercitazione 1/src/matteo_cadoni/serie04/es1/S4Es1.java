package matteo_cadoni.serie04.es1;
class Tuple2<T1,T2>
{
    final private T1 first;
    final private T2 second;


    public Tuple2(T1 t1,T2 t2) {
        this.first=t1;
        this.second=t2;
    }
    public Tuple2<T2,T1> swap()
    {
        return new Tuple2<>(second,first);
    }
    @Override
    public String toString() {
        return String.format("Tuple2(%s, %s)", first.toString(), second.toString());
    }
}
class  Tuple1<T>
{
    final private T first,second;


    public Tuple1(T t1,T t2) {
      this.first=t1;
      this.second=t2;
    }



    public Tuple1<T> swap()
    {
        return new Tuple1<>(second,first);
    }
    @Override
    public String toString() {
        return String.format("Tuple1(%s, %s)", first.toString(), second.toString());
    }
}
final class Tuple {
    final private Object first;
    final private Object second;

    public Tuple(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    public Tuple swap() {
        return new Tuple(second, first);
    }

    @Override
    public String toString() {
        return String.format("Tuple(%s, %s)", first.toString(), second.toString());
    }
}

public class S4Es1 {

    private static void testTupleClass() {
        System.out.println("---------------------------");
        System.out.println("Testing Tuple class");
        System.out.println("---------------------------");
        Tuple test1 = new Tuple("First Value", "Second Value");
        System.out.println(test1);
        System.out.println(test1.swap());

        Tuple test2 = new Tuple(new Tuple(1,2), new Tuple(3,4));
        System.out.println(test2);
        System.out.println(new Tuple(
                ((Tuple) test2.getFirst()).swap(),
                ((Tuple) test2.getSecond()).swap()
        ).swap());

        Tuple test3 = new Tuple("Sample Value", 3);
        System.out.println(test3);
        System.out.println(test3.swap());
    }

    private static void testTuple1Class() {
        System.out.println("---------------------------");
        System.out.println("Testing Tuple1 class");
        System.out.println("---------------------------");

        // DONE
        Tuple1<Integer> t1=new Tuple1<>(1,2);
        System.out.println(t1);
        System.out.println(t1.swap());

        Tuple1<Tuple1<String>> t2=new Tuple1<>(new Tuple1<>("a","b"),new Tuple1<>("c","d"));
        System.out.println(t2);
        System.out.println(t2.swap());
    }

    private static void testTuple2Class() {
        System.out.println("---------------------------");
        System.out.println("Testing Tuple2 class");
        System.out.println("---------------------------");

        // DONE
        var t2=new Tuple2<>(1,"numero");
        System.out.println(t2);
        System.out.println(t2.swap());
        var t3=new Tuple2<>(new Tuple2<>(3,3.),new Tuple2<>(23,5.5));
        System.out.println(t3);
        System.out.println(t3.swap());
    }

    public static void main(String[] args) {
        testTupleClass();

        testTuple1Class();

        testTuple2Class();
    }
}
