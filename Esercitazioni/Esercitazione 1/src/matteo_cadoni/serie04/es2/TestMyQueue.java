package matteo_cadoni.serie04.es2;

import java.util.Comparator;

public class TestMyQueue {
    public static void main(String[] args) {
        MyQueue<Integer> queue=new MyQueue<>();
        queue.print();
        System.out.println("Add");
        queue.add(3);
        queue.add(5);
        queue.add(6);
        queue.add(-6);
        queue.add(8);
        queue.print();
        System.out.println("Sort");
        queue.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        queue.print();
        System.out.println("Remove");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println("End");
        queue.print();

        MyQueue<Double> queue2=new MyQueue<>();
        queue2.print();
        System.out.println("Add");
        queue2.add(3.);
        queue2.add(5.);
        queue2.add(6.);
        queue2.add(-6.);
        queue2.add(8.);
        queue2.print();
        System.out.println("Sort");
        queue2.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (int)(o1-o2);
            }
        });
        queue2.print();
        System.out.println("Remove");
        System.out.println(queue2.remove());
        System.out.println(queue2.remove());
        System.out.println("End");
        queue2.print();

    }
}
