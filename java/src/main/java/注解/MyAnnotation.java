package 注解;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String name();
    public String value();

}
