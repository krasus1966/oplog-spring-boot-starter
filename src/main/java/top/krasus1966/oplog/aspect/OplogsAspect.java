package top.krasus1966.oplog.aspect;


import cn.hutool.core.lang.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.krasus1966.oplog.annotation.OplogInfo;
import top.krasus1966.oplog.annotation.OplogModule;
import top.krasus1966.oplog.entity.OplogError;
import top.krasus1966.oplog.entity.OplogOperation;
import top.krasus1966.oplog.repository.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Krasus1966
 * @date 2021/6/23 09:15
 **/
@Aspect
public class OplogsAspect {
    /**
     * 版本号
     */
    @Value("${version}")
    private String version;


    @Autowired
    private BaseService<OplogOperation> operationRepository;

    @Autowired
    private BaseService<OplogError> errorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(top.krasus1966.oplog.annotation.OplogInfo)")
    public void operLogPoinCut() {
    }

    @Before(value = "operLogPoinCut()")
    public void caculateStartTime(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OplogOperation oplogs = new OplogOperation();
        try {
            Class<?> clazz = joinPoint.getSignature().getDeclaringType();
            boolean hasOplogModule = clazz.isAnnotationPresent(OplogModule.class);
            if (hasOplogModule) {
                //类名前注解
                OplogModule oplogModule = clazz.getAnnotation(OplogModule.class);
                // 操作模块
                oplogs.setModule(oplogModule.value());
            }

            // 主键ID
            oplogs.setId(UUID.randomUUID().toString());
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OplogInfo opLog = method.getAnnotation(OplogInfo.class);
            if (opLog != null) {
                String oplogValue = opLog.value();
                // 操作描述
                oplogs.setOperation(oplogValue);
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            // 请求方法
            oplogs.setMethod(methodName);

            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = objectMapper.writeValueAsString(rtnMap);
            // 请求参数
            oplogs.setParams(params);
            // 返回结果
            oplogs.setResults(objectMapper.writeValueAsString(keys));
            // 请求URI
            oplogs.setUrl(request.getRequestURI());
            // 耗时
            oplogs.setTimes(System.currentTimeMillis() - startTime.get());
            // 清除记录的时间
            startTime.remove();
            // 操作版本
            oplogs.setVersion(version);
            operationRepository.save(oplogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OplogError excepLog = new OplogError();
        try {
            Class<?> clazz = joinPoint.getSignature().getDeclaringType();
            boolean hasOplogModule = clazz.isAnnotationPresent(OplogModule.class);
            if (hasOplogModule) {
                //类名前注解
                OplogModule oplogModule = clazz.getAnnotation(OplogModule.class);
                // 操作模块
                excepLog.setModule(oplogModule.value());
            }
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            excepLog.setId(UUID.randomUUID().toString());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = objectMapper.writeValueAsString(rtnMap);
            // 请求参数
            excepLog.setParams(params);
            // 请求方法名
            excepLog.setMethod(methodName);
            // 异常名称
            excepLog.setClazzName(e.getClass().getName());
            // 异常信息
            excepLog.setMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            // 操作URI
            excepLog.setUrl(request.getRequestURI());
            // 操作版本号
            excepLog.setVersion(version);
            errorRepository.save(excepLog);
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stet : elements) {
            stringBuilder.append(stet).append("\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + stringBuilder.toString();
    }
}
