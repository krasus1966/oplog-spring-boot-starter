//package top.krasus1966.oplog.repository.mongo.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import top.krasus1966.oplog.entity.OplogError;
//import top.krasus1966.oplog.repository.BaseService;
//import top.krasus1966.oplog.repository.mongo.OplogErrorRepostory;
//
///**
// * @author Krasus1966
// * @date 2021/6/26 17:34
// **/
////@Service
//@Deprecated
//public class OplogErrorMongoImpl implements BaseService<OplogError> {
//
//    @Autowired
//    private OplogErrorRepostory repostory;
//
//    @Override
//    public OplogError save(OplogError obj) {
//        return repostory.save(obj);
//    }
//
//    @Override
//    public Page<OplogError> queryPage(OplogError obj, Integer page, Integer pageSize) {
//        return null;
//    }
//}
