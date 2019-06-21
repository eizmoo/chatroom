package top.lajijson.chatroom.enums;

/**
 * 返回结果枚举类
 *
 * @author liuwei
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(200, "访问成功"),

    /**
     * 参数校验异常
     */
    BAD_REQUEST(400, "参数校验异常"),

    /**
     * 未知异常
     */
    FAIL(500, "未知异常"),

    /**
     * 账号已注册
     */
    ACCOUNT_ALREADY_IN_USE(100001, "账号已注册"),

    /**
     * 账号或密码错误
     */
    ACC_OR_PWD_ERROR(100002, "账号或密码错误"),

    /**
     * 未登录或登录失效
     */
    LOGIN_STATUS_INVALID(100003, "未登录或登录失效"),

    /**
     * 同名标签
     */
    SAME_NAME_TYPE(300001, "同名标签"),
    ;

    int code;
    String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
