package top.krasus1966.oplog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.krasus1966.oplog.constant.R;
import top.krasus1966.oplog.entity.OplogError;
import top.krasus1966.oplog.entity.OplogOperation;
import top.krasus1966.oplog.repository.BaseService;

/**
 * @author Krasus1966
 * @date 2021/6/23 22:52
 **/
@RestController
@RequestMapping("/oplogs")
public class OplogsController {

    @Autowired
    private BaseService<OplogOperation> operationRepository;

    @Autowired
    private BaseService<OplogError> errorRepository;

    @RequestMapping("/queryPageOperations")
    public R<Page<OplogOperation>> queryPageOperations(
            @RequestParam(required = false) OplogOperation obj,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        if (null == obj){
            obj = new OplogOperation();
        }
        return R.parse(200,"操作成功", operationRepository.queryPage(obj,page,pageSize));
    }

    @RequestMapping("/queryPageError")
    public R<Page<OplogError>> queryPageError(
            @RequestParam(required = false) OplogError obj,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        if (null == obj){
            obj = new OplogError();
        }
        return R.parse(200,"操作成功", errorRepository.queryPage(obj,page,pageSize));
    }
}
