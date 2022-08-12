package matteo_cadoni.serie01.esercizio01;
import java.util.ArrayList;
import java.util.List;

/*enum FigureType {
    SQUARE, RECTANGLE, EQULATERIAL_TRIANGLE, RIGHT_TRIANGLE, SCALENE_TRIANGLE, CIRCLE, PENTAGON,
}*/
interface RegularSide
{
    public default double regularSide(int sidesNumber,double length)
    {
        return sidesNumber*length;
    }
}

class Square extends GeometricFigure implements RegularSide{
    public Square(double sidesLength) {
        super(4, new double[]{sidesLength});
    }

    @Override
    public double getPerimeter() {
        return regularSide(4,dimensions[0]);
    }

    @Override
    public double getArea() {
        return Math.pow(dimensions[0], 2.0);
    }

    @Override
    public String toString() {
        return "(Square:, sides:"+getSides()+", side length: "+dimensions[0]+")\n";
    }
}
class Rectangle extends GeometricFigure{
    public Rectangle(double base,double height) {
        super(4, new double[]{base,height});
    }

    @Override
    public double getPerimeter() {
        return (dimensions[0]+dimensions[1])*2;
    }

    @Override
    public double getArea() {
        return dimensions[0]*dimensions[1];
    }
    public String toString() {
        return "(Rectangle: sides:"+getSides()+", width: "+dimensions[0]+", height: "+dimensions[1]+")\n";
    }
}

class Circle extends GeometricFigure
{
    public Circle(double radius) {
        super(1, new double[]{radius});
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*dimensions[0];
    }

    @Override
    public double getArea() {
        return Math.PI*Math.pow(dimensions[0],2);
    }
    public String toString() {
        return "(Circle: radius: "+dimensions[0]+")\n";
    }
}
class Pentagon extends GeometricFigure implements RegularSide
{
    public Pentagon( double sideLength) {
        super(5, new double[]{sideLength});
    }

    @Override
    public double getPerimeter() {
        return regularSide(5,dimensions[0]);
    }

    @Override
    public double getArea() {
        return 1.72 * Math.pow(dimensions[0], 2.0);
    }
    public String toString() {
        return "(Pentagon: sides:"+getSides()+", side length: "+dimensions[0]+")";
    }

}
class RightTriangle extends Triangle
{
    public RightTriangle( double base,double height) {
        super(base,height);
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(dimensions[0], 2.0) + Math.pow(dimensions[1], 2.0)) + dimensions[0] + dimensions[1];
    }


    public String toString() {
        return "(RightTriangle: "+super.toString()+")\n";
    }
}
class EquilateralTriangle extends Triangle implements RegularSide{
    public EquilateralTriangle(double sideLength) {
        super(sideLength);
    }

    @Override
    public double getPerimeter() {
        return regularSide(3,dimensions[0]);
    }
    public String toString() {
        return "(EquilateralTriangle: "+super.toString()+")\n";
    }
}
class ScaleneTriangle extends Triangle
{

    public ScaleneTriangle(double l1, double l2, double l3) {
        super(l1, l2,l3);
    }

    @Override
    public double getPerimeter() {
        return  dimensions[0]+dimensions[1]+dimensions[2];
    }


    public String toString() {
        return "(ScaleneTrangle"+super.toString()+")\n";
    }
}
abstract class Triangle extends GeometricFigure
{


    public Triangle(double base,double height) {
        super(3, new double[]{base, height});
    }
    public Triangle(double l1,double l2,double l3) {
        super(3, new double[]{l1, l2,l3});
    }
    public Triangle(double length) {
        super(3, new double[]{length});
    }
    @Override
    public final double getArea() {
        if(dimensions.length==2)
            return (dimensions[0]*dimensions[1])/2;
        else if(dimensions.length==3) {
            double p = (dimensions[0] + dimensions[0] + dimensions[0]) / 2.0;
            return Math.sqrt(p * (p - dimensions[0]) * (p - dimensions[0]) * (p - dimensions[0]));
        }else
        {
            return (Math.sqrt(3.0) / 4.0) * Math.pow(dimensions[0], 2.0);
        }
    }
    public String toString() {
        if(dimensions.length==2)
        return "base: "+dimensions[0]+", height: "+dimensions[1]+"";
        else
            return "side: "+dimensions[0];
    }
}

abstract class GeometricFigure {
    private int sides;
    protected double dimensions[] = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

    public GeometricFigure(int sides, double[] dimensions) {
        this.sides = sides;
        this.dimensions = dimensions;
    }
    public abstract double getPerimeter();
    public abstract double getArea();
    public final double getRatio(){

            return getArea()/getPerimeter();

    }

    public int getSides() {
        return sides;
    }

    @Override
    public String toString() {
        return "(unknown)";
    }


}

public class S1E1 {
    public static void main(String[] args) {
        List<GeometricFigure> allFigures = new ArrayList<>();
        allFigures.add(new Square(2.0));
        allFigures.add(new Square(4.0));
        allFigures.add(new Square(8.0));
        allFigures.add(new Rectangle(2.0, 3.0));
        allFigures.add(new Rectangle(4.0, 6.0));
        allFigures.add(new Rectangle(8.0, 12.0));
        allFigures.add(new Pentagon(2.0));
        allFigures.add(new Pentagon(4.0));
        allFigures.add(new Pentagon(8.0));
        allFigures.add(new Circle(2.0));
        allFigures.add(new Circle(4.0));
        allFigures.add(new Circle(8.0));
        allFigures.add(new EquilateralTriangle(2.0));
        allFigures.add(new EquilateralTriangle(4.0));
        allFigures.add(new EquilateralTriangle(8.0));
        allFigures.add(new RightTriangle(2.0, 3.0));
        allFigures.add(new RightTriangle(4.0, 6.0));
        allFigures.add(new RightTriangle(8.0, 12.0));
        allFigures.add(new ScaleneTriangle(3.0, 4.0, 5.0));
        allFigures.add(new ScaleneTriangle(6.0, 8.0, 10.0));
        allFigures.add(new ScaleneTriangle(12.0, 16.0, 15.0));

        for (final GeometricFigure figure : allFigures) {
            System.out.println(figure);
        }

        List<Double> perimeters = new ArrayList<>();
        for (final GeometricFigure figure : allFigures) {
                perimeters.add(figure.getPerimeter());

        }

        List<Double> allAreas = new ArrayList<>();
        for(final GeometricFigure figure : allFigures) {
                allAreas.add(figure.getArea());
        }

        List<Double> allRatios = new ArrayList<>();
        for (final GeometricFigure figure : allFigures) {
           allRatios.add(figure.getRatio());

        }

        double totalArea = 0.0;
        double totalPerimeter = 0.0;
        for (int i = 0; i < allFigures.size(); i++) {
            GeometricFigure figure = allFigures.get(i);
            Double area = allAreas.get(i);
            Double perimeter = perimeters.get(i);
            Double ratio = allRatios.get(i);
            totalArea += area;
            totalPerimeter += perimeter;
            System.out.println(String.format("%s: area=%f perimeter=%f ratio=%f", figure, area, perimeter, ratio));
        }

        System.out.println(String.format("Total area=%f perimeter=%f ratio=%f", totalArea, totalPerimeter,
                (totalArea / totalPerimeter)));
    }
}
