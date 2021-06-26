package top.krasus1966.oplog.annotation;

import java.lang.annotation.*;

/**
 * 标注在类上，记录模块名称
 *
 * @author Krasus1966
 * @date 2021/6/23 08:51
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OplogModule {
    String value() default "";
}
