package Matteo_Cadoni_Esame01.es2;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface IGeometryElement {

}

interface IFlatGeometry extends IGeometryElement {
    double getArea();
}
class Point implements IGeometryElement {
    final int x = (int) (Math.random() * 10. - 5.);
    final int y = (int) (Math.random() * 10. - 5.);

    @Override
    public String toString() {
        return String.format("Point(x=%d, y=%d)", x, y);
    }
}

class Line implements IGeometryElement {
    final Point p0 = new Point();
    final Point p1 = new Point();

    @Override
    public String toString() {
        return String.format("Line(p0=%s, p1=%s)", p0, p1);
    }
}

class Circle implements IFlatGeometry {
    final Point center = new Point();
    final int radius = (int) (Math.random() * 10. + 1);

    @Override
    public double getArea() {
        return Math.pow(radius, 2.0) * Math.PI;
    }

    @Override
    public String toString() {
        return String.format("Circle(center=%s, radius=%d, area=%f)", center, radius, getArea());
    }
}

class Square implements IFlatGeometry {
    final Point center = new Point();
    final int side = (int) (Math.random() * 10. + 1);

    @Override
    public double getArea() {
        return Math.pow(side, 2.0);
    }

    @Override
    public String toString() {
        return String.format("Square(center=%s, side=%d, area=%f)", center, side, getArea());
    }
}

class Rectangle implements IFlatGeometry {
    final Point center = new Point();
    final int width = (int) (Math.random() * 10. + 1);
    final int heigth = (int) (Math.random() * 10. + 1);

    @Override
    public double getArea() {
        return width * heigth;
    }

    @Override
    public String toString() {
        return String.format("Rectangle(center=%s, width=%d, heigth=%d, area=%f)", center, width, heigth, getArea());
    }
}

public class JSONExporter {
    public static void main(String[] args) {
        final Point point = new Point();
        System.out.println("Point     : " + point);
        System.out.println("          : " + JSONExporter.toJSON(point));

        final Line line = new Line();
        System.out.println("Line      : " + line);
        System.out.println("          : " + JSONExporter.toJSON(line));

        final Circle circle = new Circle();
        System.out.println("Circle    : " + circle);
        System.out.println("          : " + JSONExporter.toJSON(circle));

        final Square square = new Square();
        System.out.println("Square    : " + square);
        System.out.println("          : " + JSONExporter.toJSON(square));

        final Rectangle rectangle = new Rectangle();
        System.out.println("Rectangle : " + rectangle);
        System.out.println("          : " + JSONExporter.toJSON(rectangle));
    }

    public static String toJSON(final IGeometryElement instance) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");

        // FIXME to implement
        try {
            Class<?> myclass=Class.forName(instance.getClass().getName());

            List<Class<?>> interfaces= Arrays.stream(myclass.getInterfaces())//bisognerebbe cercare il percorso delle intefacce
                    .collect(Collectors.toList())
                    .stream()
                    .filter(e->{
                        if(e.getName().equals(IGeometryElement.class.getName()))
                        return true;
                    else
                    {
                        if(e.getSuperclass()!=null) {
                            if (e.getSuperclass().getName().equals(IGeometryElement.class.getName()))
                                return true;
                            if (getPathToObject(e.getName()).contains(IGeometryElement.class.getName()))
                                return true;
                        }
                    }
                    return false;
                    })
                    .collect(Collectors.toList());



            if(interfaces.size()>0) {
                sb.append("\"type\":"+myclass.getTypeName()+", ");
                Object obj=myclass.newInstance();
                for (Field f : myclass.getDeclaredFields()) {

                    sb.append("\""+f.getName()+"\": " + f.get(obj)+", ");
                }
                sb.replace(sb.length()-2,sb.length()-1,"");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append("}");
        return sb.toString();
    }

    private static List<String> getPathToClass(String startClassName, String endClassName)  {
        ArrayList<String> path=new ArrayList<>();


        path.add(startClassName);
        while (!startClassName.equals(endClassName))
        {
            try
            {
                Class<?> cls=Class.forName(startClassName);
                path.add(cls.getSuperclass().getName());
                for(Class<?> x: cls.getInterfaces())
                    path.add(x.getName());

                startClassName=cls.getSuperclass().getName();


            }catch (ClassNotFoundException e)
            {
                return new ArrayList<String>();
            }

        }
        return path;
    }

    private static List<String> getPathToObject(String startClassName) {

        return getPathToClass(startClassName,Object.class.getName());
    }


}
