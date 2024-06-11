package cn.citynight.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) // 链式编程
public class ResultData<T> {

    private Integer code;
    private String message;
    private T data;
    private Long timestamp;

    public static <T> ResultData<T> success(T data) {
        return new ResultData<T>().setCode(ReturnCodeEnum.RC200.getCode()).setMessage(ReturnCodeEnum.RC200.getMessage()).setData(data).setTimestamp(System.currentTimeMillis());
    }
    public static <T> ResultData<T> fail(ReturnCodeEnum returnCodeEnum) {
        return new ResultData<T>().setCode(returnCodeEnum.getCode()).setMessage(returnCodeEnum.getMessage()).setTimestamp(System.currentTimeMillis());
    }
}
