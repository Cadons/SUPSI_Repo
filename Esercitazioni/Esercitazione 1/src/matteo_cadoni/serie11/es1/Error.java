package matteo_cadoni.serie11.es1;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ErrorsContainer.class)
public @interface Error {
    String severity();
    String description();
    String reporter() default "Error";
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ErrorsContainer{
    Error[] value();
}
