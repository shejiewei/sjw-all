package 注解.V2;

import java.lang.annotation.*;

/**
 * Created by shejiewei on 2019/1/23.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Table {

    String value();
}
