package matteo_cadoni.serie11.es1;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CodeReviewer {
    private  static String output="";
    public static String analyze(Class<?> className)
    {
        output="";
        List<Method> methods=new ArrayList<>();
        List<Class<?>> types=new ArrayList<>();

        methods.addAll(Arrays.stream(className.getDeclaredMethods()).collect(Collectors.toList()));
        types.addAll(Collections.singleton(className));
        types.addAll(Arrays.stream(className.getClasses()).collect(Collectors.toList()));
        //Check methods
        methods.forEach(e->{
            if(e.isAnnotationPresent(Error.class))
            {
                List<Error> errors=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Error.class)).collect(Collectors.toList()));
                errors.forEach(error->output+=error.reporter()+" - "+error.severity()+" [Description]"+error.description()+"\n");
            }
            if(e.isAnnotationPresent(Bug.class))
            {
                List<Bug> bugs=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Bug.class)).collect(Collectors.toList()));
                bugs.forEach(bug->output+=bug.reporter()+" - "+bug.severity()+" [Description]"+bug.description()+"\n");
            }
            if(e.isAnnotationPresent(Improvement.class))
            {
                List<Improvement> improvements=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Improvement.class)).collect(Collectors.toList()));
                improvements.forEach(improvement->output+=improvement.reporter()+" - "+improvement.severity()+" [Description]"+improvement.description()+"\n");
            }
        });
        types.forEach(e->{
            if(e.isAnnotationPresent(Error.class))
            {
                List<Error> errors=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Error.class)).collect(Collectors.toList()));
                errors.forEach(error->output+=error.reporter()+" - "+error.severity()+" [Description]"+error.description()+"\n");
            }
            if(e.isAnnotationPresent(Bug.class))
            {
                List<Bug> bugs=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Bug.class)).collect(Collectors.toList()));
                bugs.forEach(bug->output+=bug.reporter()+" - "+bug.severity()+" [Description]"+bug.description()+"\n");
            }
            if(e.isAnnotationPresent(Improvement.class))
            {
                List<Improvement> improvements=new ArrayList<>(Arrays.stream(e.getAnnotationsByType(Improvement.class)).collect(Collectors.toList()));
                improvements.forEach(improvement->output+=improvement.reporter()+" - "+improvement.severity()+" [Description]"+improvement.description()+"\n");
            }
        });
        return output;
    }
}
