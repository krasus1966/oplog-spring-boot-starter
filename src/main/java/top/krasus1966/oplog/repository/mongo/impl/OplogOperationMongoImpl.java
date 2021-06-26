//package top.krasus1966.oplog.repository.mongo.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import top.krasus1966.oplog.entity.OplogOperation;
//import top.krasus1966.oplog.repository.BaseService;
//import top.krasus1966.oplog.repository.mongo.OplogOperationRepostory;
//
///**
// * @author Krasus1966
// * @date 2021/6/26 17:34
// **/
////@Service
//@Deprecated
//public class OplogOperationMongoImpl implements BaseService<OplogOperation> {
//
//    @Autowired
//    private OplogOperationRepostory repostory;
//
//    @Override
//    public OplogOperation save(OplogOperation obj) {
//        return repostory.save(obj);
//    }
//
//    @Override
//    public Page<OplogOperation> queryPage(OplogOperation obj, Integer page, Integer pageSize) {
//        return null;
//    }
//}
