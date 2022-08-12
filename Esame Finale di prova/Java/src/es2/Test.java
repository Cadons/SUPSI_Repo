package es2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class Figure {

    public abstract double volume();

    public abstract void scale(double factor);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Sphere extends Figure {

    private double radius;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        return 4. / 3. * Math.PI * Math.pow(this.radius, 3.0);
    }

    @Override
    public void scale(double factor) {
        this.radius *= factor;
    }

    @Override
    public String toString() {
        return String.format("%s [radius=%s]", super.toString(), radius);
    }
}

class Cube extends Figure {

    protected double side;

    public Cube(double base) {
        this.side = base;
    }

    @Override
    public double volume() {
        return Math.pow(this.side, 3.0);
    }

    public void scale(double factor) {
        this.side *= factor;
    }

    @Override
    public String toString() {
        return String.format("%s [side=%s]", super.toString(), side);
    }
}

class Pyramid extends Figure {

    private double base;
    private double height;

    public Pyramid(float base, float height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double volume() {
        return (this.base * this.base * this.height) / 3.0;
    }

    public void scale(double factor) {
        this.base *= factor;
        this.height *= factor;
    }

    @Override
    public String toString() {
        return String.format("%s [base=%s, height=%s]", super.toString(), base, height);
    }
}

class Container<T extends Figure> {
    private List<T> objects = new ArrayList<>();

    public void collect(List<T> e, double threshold) {

        for (T k : e) {
            if (k.volume() > threshold)
                objects.add(k);
        }
    }

    public static <T extends Figure> void scale(List<T> e, double factor) {
        for (T k : e) {

                k.scale(factor);
        }
    }

    public List<T> getObjects() {
        return objects;
    }
}

public class Test {
    public static void main(String[] args) {
        Container c1=new Container();
        Container c2=new Container();
        Container c3=new Container();
        List<Sphere> l1=new ArrayList<>();
        l1.add(new Sphere(1));
        l1.add(new Sphere(2));
        l1.add(new Sphere(3));
        List<Cube> l2=new ArrayList<>();
        l2.add(new Cube(1));
        l2.add(new Cube(2));
        l2.add(new Cube(3));
        List<Pyramid> l3=new ArrayList<>();
        l3.add(new Pyramid(1,5));
        l3.add(new Pyramid(2,6));
        l3.add(new Pyramid(3,4));
        c1.collect(l1,0);
        c2.collect(l2,0);
        c3.collect(l3,0);
        List<? extends Figure> all=new ArrayList<>();
        all.addAll(c1.getObjects());
        all.addAll(c2.getObjects());

        all.addAll(c3.getObjects());
        System.out.println(all);
        System.out.println("-------scale-----");
        Container.scale(all,0.5);
        System.out.println(all);
    }
}
