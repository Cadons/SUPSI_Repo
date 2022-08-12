package matteo_cadoni.serie03.esercizio01;

import java.lang.annotation.*;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ToDoContainer.class)
public @interface Todo {
    String dueDate();
    String task() default "";
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ToDoContainer

{
    Todo[] value();
}