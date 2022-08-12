package matteo_cadoni.serie02.esercizio02;

import java.io.ObjectStreamException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Encapsulator {

    private static Class<?> getClass(String className)
    {
        try {
            Class<?> cls=Class.forName(className);
            return cls;
        }catch (ClassNotFoundException e)
        {
            return null;
        }
    }
    public static String encapusulate(String className)
    {
        try {
            Class<?> myclass=getClass(className);
            Object obj=myclass.newInstance();

            if(myclass!=null)
            {
                StringBuilder str=new StringBuilder();
                className=className.replaceAll(".*\\.","");//Remove package signature with regex

                String output="public class "+className+"{";

                ArrayList<Field> fileds=new ArrayList<Field>(Arrays.asList(myclass.getDeclaredFields()));
                //Create fields declaration
                for (Field e: fileds
                ) {
                    if(e.getModifiers()!=1)
                    {
                        e.setAccessible(true);
                    }

                    String typeName=getTypeName(e);
                    String value=e.get(obj)!=null?e.get(obj).toString():"null";
                    if(typeName.equals("String"))
                        value="\""+value+"\"";

                            output+=("\n\tprivate "+typeName+" "+e.getName()+" = "+value+";");


                }
                output+="\n";
                //set get-methods
                for (Field e: fileds
                ) {
                    String newName="get"+(String) (e.getName().charAt(0)+"").toUpperCase()+e.getName().substring(1);
                    output+=("\n\tpublic "+getTypeName(e)+" "+newName+"()"+"{\t\n\t\t return "+e.getName()+";\n\t}");


                }
                output+="\n";
                //set set-methods
                for (Field e: fileds
                ) {
                    String newName="set"+(String) (e.getName().charAt(0)+"").toUpperCase()+e.getName().substring(1);

                    output+=("\n\tpublic void "+newName+"("+getTypeName(e)+" "+e.getName()+")"+"{\t\n\t\t this."+e.getName()+"="+e.getName()+";\n\t}");


                }
                output+="\n" +
                        "}";
                return (output);
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return "Error!";
    }


    private static String getTypeName(Field f)
    {
        return f.getType().getName().replaceAll(".*\\.","");
    }

    public static void main(String[] args) {
        System.out.println(Encapsulator.encapusulate(Target.class.getName()));
    }
}
