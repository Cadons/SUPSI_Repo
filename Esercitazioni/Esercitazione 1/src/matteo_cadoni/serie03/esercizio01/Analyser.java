package matteo_cadoni.serie03.esercizio01;

import java.lang.reflect.Method;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Analyser {
    public static void main(String[] args) {
        analyse(Target.class.getName());
    }

    private static void analyse(String className) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Class clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            ArrayList<Todo> todos=new ArrayList<>();
            for (Method method : methods) {
            	// FIXME find and collect annotation + todo item
                if(method.isAnnotationPresent(ToDoContainer.class))
                    todos.addAll(Arrays.asList(method.getAnnotationsByType(Todo.class)));
                else
                    todos.add(method.getAnnotation(Todo.class));
                }
            // FIXME Sort collected items
            todos.sort(new Comparator<Todo>() {
                @Override
                public int compare(Todo o1, Todo o2) {
                    try {
                        return sdf.parse(o1.dueDate()).compareTo(sdf.parse(o2.dueDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
            // FIXME print sorted items by dueDate
            todos.forEach((e)->{
                System.out.println(e);
            });
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
