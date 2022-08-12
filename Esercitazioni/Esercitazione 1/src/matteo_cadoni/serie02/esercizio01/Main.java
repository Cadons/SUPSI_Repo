package matteo_cadoni.serie02.esercizio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.*;
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

            System.out.println("Hierarchy between classes");
            getPathToClass(F1Car.class.getName(), Vehicle.class.getName()).forEach((e) -> System.out.println(e));
            System.out.println();
            System.out.println("Hierarchy from start to root ");
            getPathToObject(RallyCar.class.getName()).forEach((e)-> System.out.println(e));
            System.out.println();
            System.out.println("Common class: "+getCommonAncestor(F1Car.class.getName(), RallyCar.class.getName()));
            System.out.println("Common class: "+getCommonAncestor(F1Car.class.getName(), Bycicle.class.getName()));
            System.out.println("Common class: "+getCommonAncestor(F1Car.class.getName(), String.class.getName()));

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

    private static String getCommonAncestor(String className0, String className1) {
        ArrayList<String> cls1=new ArrayList<>(getPathToObject(className0));
        ArrayList<String> cls2=new ArrayList<>(getPathToObject(className1));
             for(int i=0;i<cls1.size();i++)
                 if(cls2.contains(cls1.get(i)))
                 {
                    return (cls1.get(i));
                 }
        return "";
    }
}
