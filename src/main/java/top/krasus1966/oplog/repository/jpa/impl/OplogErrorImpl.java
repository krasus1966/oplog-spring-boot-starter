package top.krasus1966.oplog.repository.jpa.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.krasus1966.oplog.entity.OplogError;
import top.krasus1966.oplog.repository.BaseService;
import top.krasus1966.oplog.repository.jpa.OplogErrorRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasus1966
 * @date 2021/6/26 11:04
 **/
@Service
public class OplogErrorImpl implements BaseService<OplogError> {

    @Autowired
    private OplogErrorRepository repostory;

    @Override
    public OplogError save(OplogError obj) {
        return repostory.save(obj);
    }

    @Override
    public Page<OplogError> queryPage(OplogError obj, Integer page, Integer pageSize) {
        page = page - 1;
        return repostory.findAll(getSpecification(obj),PageRequest.of(page,pageSize));
    }

    private Specification<OplogError> getSpecification(OplogError obj){
        //规格定义
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StrUtil.isNotBlank(obj.getClazzName())){
                Predicate clazzName = cb.like(root.get("clazzName").as(String.class),"%"+obj.getClazzName()+"%");
                predicates.add(clazzName);
            }
            if(StrUtil.isNotBlank(obj.getModule())){
                Predicate module = cb.like(root.get("module").as(String.class),"%"+obj.getModule()+"%");
                predicates.add(module);
            }
            if(StrUtil.isNotBlank(obj.getMethod())){
                Predicate method = cb.like(root.get("method").as(String.class),"%"+obj.getMethod()+"%");
                predicates.add(method);
            }
            if(StrUtil.isNotBlank(obj.getUrl())){
                Predicate url = cb.like(root.get("url").as(String.class),"%"+obj.getUrl()+"%");
                predicates.add(url);
            }
            if(StrUtil.isNotBlank(obj.getVersion())){
                Predicate version = cb.equal(root.get("version").as(String.class),obj.getVersion());
                predicates.add(version);
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
