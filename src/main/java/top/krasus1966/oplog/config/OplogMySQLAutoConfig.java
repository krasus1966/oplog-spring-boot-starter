package top.krasus1966.oplog.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Krasus1966
 * @date 2021/6/25 23:09
 **/
@EntityScan(basePackages = "top.krasus1966.oplog.entity")
@EnableJpaRepositories(basePackages = "top.krasus1966.oplog.repository.jpa")
public class OplogMySQLAutoConfig {

}
