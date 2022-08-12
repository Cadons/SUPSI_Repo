package Esercitazione1.matteo_cadoni.testProva;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Autorun {
    public Autorun() {
        System.out.println("Constructor");
    }

    public String autorunString() {
        System.out.println("Should be executed returning a string ");
        return "defaultString";
    }

    public String autorunString(final String input) {
        System.out.println("Should be executed receiving a string '" + input + "'");
        return input;
    }

    public Integer autorunInteger(final Integer i) {
        System.out.println("Should be executed receiving and integer: " + i);
        return Integer.sum(i, i);
    }

    public void noRun() {
        System.out.println("Should not be executed");
    }

    public void autorun(final Autorun theClass, String data) {
        System.out.println("Should be executed, passing an instance of Autorun: " + theClass + " and string data: " + data);
    }
}

class RunAutorun {
    public static void SearchAndRun(String class_) {
        try {
            Class<?> cls = Class.forName(class_);
            Object obj = cls.newInstance();
            cls.cast(obj);

            for (Method m : cls.getMethods()) {
                if (m.getName().startsWith("autorun")) {
                    m.setAccessible(true);
                    ArrayList<Object> param = new ArrayList<>();
                    if (m.getParameterCount() > 0) {

                        for (Parameter p : m.getParameters()
                        ) {
                            if (p.getType().getName().equals(Integer.class.getName())) {
                                Random r = new Random();
                                int low = 10;
                                int high = 20;
                                int result = r.nextInt(high - low) + low;
                                param.add(result);
                            } else if (p.getType().getName().equals(String.class.getName())) {
                                param.add(m.getName());
                            }else {
                                param.add(obj);
                            }
                        }
                        m.invoke(obj, param.toArray());
                        param.clear();
                    } else {
                        m.invoke(obj);
                    }

                }


            }
        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

public class Test {
    public static void main(String[] args) {

        RunAutorun.SearchAndRun(Autorun.class.getName());

    }
}