package top.krasus1966.oplog.constant;


import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用返回封装
 *
 * @author Krasus1966
 * @date 2020/10/8 21:42
 **/
public class R<T> implements Serializable {
    private static final long serialVersionUID = 9140215144638597826L;

    private Integer code;
    private String msg;
    private T data;
    private String error;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp = LocalDateTime.now();


    public R() {
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(Integer code, String msg, String error) {
        this.code = code;
        this.msg = msg;
        this.error = error;
    }

    public R(Integer code, String msg, String error, T data) {
        this.code = code;
        this.msg = msg;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccessful(){
        return this.code == HttpStatus.OK.value();
    }

    /**
     * 无数据返回格式
     *
     * @param code 状态码
     * @param msg  信息
     * @return @{code,msg}
     */
    public static <T> R<T> parse(Integer code, String msg) {
        return new R<>(code, msg);
    }

    /**
     * 有数据返回格式
     *
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     * @return @{code,msg,data}
     */
    public static <T> R<T> parse(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    /**
     * 有数据返回格式，带错误信息
     *
     * @param resultEnum 封装返回信息
     * @return @{code,msg,data}
     */
    public static <T> R<T> parse(Integer code, String msg, String error, T data) {
        return new R<T>(code, msg, error, data);
    }

    /**
     * 无数据返回格式，带错误信息
     *
     * @param code
	 * @param msg
	 * @param error
     * @return top.krasus1966.iotcenter.base.result.R<T>
     * @author krasus1966
     * @date 2021/6/10 23:20
     */
    public static <T> R<T> parse(Integer code, String msg, String error) {
        return new R<T>(code, msg, error);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
