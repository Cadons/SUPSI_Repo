package matteo_cadoni.serie11.es1;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BugsContainer.class)
public @interface Bug {
    String severity();
    String description();
    String reporter() default "Bug";
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface BugsContainer{
    Bug[] value();
}

