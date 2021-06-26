package top.krasus1966.oplog.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.krasus1966.oplog.entity.OplogError;

/**
 * @author Krasus1966
 * @date 2021/6/26 18:54
 **/
@Repository
public interface OplogErrorRepository extends JpaRepository<OplogError,String>, JpaSpecificationExecutor<OplogError> {

}
