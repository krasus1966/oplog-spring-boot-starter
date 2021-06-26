package top.krasus1966.oplog.repository;

import org.springframework.data.domain.Page;

/**
 * @author Krasus1966
 * @date 2021/6/26 11:01
 **/
public interface BaseService<T> {

    T save(T obj);

    Page<T> queryPage(T obj,Integer page,Integer pageSize);
}
