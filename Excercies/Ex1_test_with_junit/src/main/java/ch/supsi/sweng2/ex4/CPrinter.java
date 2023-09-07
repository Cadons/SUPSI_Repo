package ch.supsi.sweng2.ex4;

public class CPrinter implements Printer {
    @Override
    public void print(String v) {
        if (v != null)
            System.out.println(v);
    }
}
