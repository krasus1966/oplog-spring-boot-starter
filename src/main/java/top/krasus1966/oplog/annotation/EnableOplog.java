package top.krasus1966.oplog.annotation;

import org.springframework.context.annotation.Import;
import top.krasus1966.oplog.config.OplogAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author Krasus1966
 * @date 2021/6/26 19:39
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OplogAutoConfiguration.class})
public @interface EnableOplog {
}
