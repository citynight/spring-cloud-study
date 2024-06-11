package cn.citynight.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum {

    // 如何定义一个通用的枚举值， 举值-构造-遍历


    // 1. 举值
    RC999(999, "操作失败"),
    RC200(200, "操作成功"),
    RC201(201, "服务开启降级保护, 请稍后再试"),
    RC202(202, "热点参数限流, 请稍后再试"),
    RC203(203, "系统规则不满足要求, 请稍后再试"),
    RC204(204, "授权规则不通过, 请稍后再试"),
    RC375(375, "数学运算异常, 请稍后再试"),
    RC401(401, "匿名用户无法访问该资源"),
    RC403(403, "无访问权限, 请联系管理员授权"),
    RC404(404, "无法找到页面"),
    RC500(500, "系统异常, 请稍后再试"),
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    USER_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),
    ;

    // 2. 构造
    // 自定义状态码
    private final Integer code;

    // 自定义描述
    private final String message;

    ReturnCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 3. 遍历
   // 3.1 传统版
    public static ReturnCodeEnum getReturnCodeEnumV1(Integer code) {
        for (ReturnCodeEnum returnCodeEnum : ReturnCodeEnum.values()) {
            if (returnCodeEnum.getCode().equals(code)) {
                return returnCodeEnum;
            }
        }
        return null;
    }

    // 3.2 lambda版
    public static ReturnCodeEnum getReturnCodeEnumV2(Integer code) {
        return Arrays.stream(ReturnCodeEnum.values())
                .filter(returnCodeEnum -> returnCodeEnum.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

//    public static void main(String[] args) {
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1(200));
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1(200).getCode());
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1(200).getMessage());
//
//        System.out.println();
//
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV2(404));
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV2(404).getCode());
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV2(404).getMessage());
//    }
}
