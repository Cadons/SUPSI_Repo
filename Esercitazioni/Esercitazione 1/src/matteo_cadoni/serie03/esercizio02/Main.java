package matteo_cadoni.serie03.esercizio02;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    private final static HashSet<String> fieldNames = new HashSet<String>();

    // class name, implementation
    private static final String classTemplate = "public class %s {\n%s}";

    // type, field name, field value
    private static final String fieldTemplate = "\tprivate %s %s = %s;\n";

    // type, field name, field value
    private static final String fieldStringTemplate = "\tprivate %s %s = \"%s\";\n";

    // return type, method name, field name
    private static final String getterTemplate = "\tpublic %s get%s(){\n\t\treturn %s;\n\t}\n";

    // method name, parameter type, parameter, field name, parameter
    private static final String setterTemplate = "\tpublic void set%s(%s %s){\n\t\tthis.%s = %s;\n\t}\n";

    public static String generate(String className) throws Exception {
        Class<?> targetClass = null;
        String generatedCode = "";
        try {
            targetClass = Class.forName(className);
            generatedCode = String.format(classTemplate, targetClass.getSimpleName(),
                    generateImplementation(targetClass));
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return generatedCode;
    }

    private static String generateImplementation(Class<?> clazz) throws Exception {
        String output = "";
        Object obj = clazz.newInstance();
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(extractFields(clazz)));
        //Check fields
        for (Field e : fields) {
            if (e.isAnnotationPresent(Extract.class)) {
                if (fieldNames.contains(e.getName()) || fieldNames.contains(e.getAnnotation(Extract.class).value()))
                    throw new MultipleNameFiled(fieldNames.contains(e.getName()) ? e.getName() : e.getAnnotation(Extract.class).value());
                else {
                    if (!e.getAnnotation(Extract.class).value().equals(""))
                        fieldNames.add(e.getAnnotation(Extract.class).value());
                    else
                        fieldNames.add(e.getName());
                }

            }


        }
        for (Field e : fields) {
            if (e.isAnnotationPresent(Extract.class)) {

                if (e.getModifiers() > 1)
                    e.setAccessible(true);
                if (!e.getAnnotation(Extract.class).value().equals(""))
                    output += String.format(fieldStringTemplate, e.getType().getName().replaceAll(".*\\.", ""), e.getAnnotation(Extract.class).value(), e.get(obj));
                else
                    output += String.format(fieldStringTemplate, e.getType().getName().replaceAll(".*\\.", ""), e.getName(), e.get(obj));

            }

        }
        for (Field e : fields) {
            if (e.isAnnotationPresent(Extract.class)) {
                if (e.getModifiers() > 1)
                    e.setAccessible(true);
                if (!e.getAnnotation(Extract.class).value().equals(""))
                    output += String.format(getterTemplate, e.getType().getName().replaceAll(".*\\.", ""), (e.getAnnotation(Extract.class).value().charAt(0) + "").toUpperCase() + e.getAnnotation(Extract.class).value().substring(1), e.get(obj));
                else
                    output += String.format(getterTemplate, e.getType().getName().replaceAll(".*\\.", ""), (e.getName().charAt(0) + "").toUpperCase() + e.getName().substring(1), e.get(obj));

            }
        }
        for (Field e : fields) {
            if (e.isAnnotationPresent(Extract.class)) {
                if (e.getModifiers() > 1)
                    e.setAccessible(true);
                if (!e.getAnnotation(Extract.class).value().equals(""))
                    output += String.format(setterTemplate, (e.getAnnotation(Extract.class).value().charAt(0) + "").toUpperCase() + e.getAnnotation(Extract.class).value().substring(1), e.getType().getName().replaceAll(".*\\.", ""), e.getAnnotation(Extract.class).value(), e.getAnnotation(Extract.class).value(), e.getAnnotation(Extract.class).value());
                else
                    output += String.format(setterTemplate, (e.getName().charAt(0) + "").toUpperCase() + e.getName().substring(1), e.getType().getName().replaceAll(".*\\.", ""), e.getName(), e.getName(), e.getName());

                //     output+=String.format(setterTemplate,e.getType().getName(),(e.getType().getName().charAt(0)+"").toUpperCase()+e.getName().substring(1),e.get(obj));

            }
        }
        fieldNames.clear();
        return output;
    }

    static private Field[] extractFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    public static void main(String[] args) {
        try {
            System.out.println(generate(Target2.class.getName()));
            System.out.println(generate(Target.class.getName()));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}

class MultipleNameFiled extends Exception {
    public MultipleNameFiled(String fieldName) {
        super(fieldName + " is already used!");
    }

}