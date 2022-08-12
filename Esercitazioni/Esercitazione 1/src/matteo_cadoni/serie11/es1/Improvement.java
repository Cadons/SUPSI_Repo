package matteo_cadoni.serie11.es1;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ImprovementContainer.class)
public @interface Improvement {
    String severity();
    String description();
    String reporter() default "Improvement";}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ImprovementContainer{
    Improvement [] value();
}


