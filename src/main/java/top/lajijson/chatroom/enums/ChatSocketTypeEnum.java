package top.lajijson.chatroom.enums;

import lombok.Getter;

/**
 * socket对象的类型枚举
 *
 * @author liuwei
 */
@Getter
public enum ChatSocketTypeEnum {

    /**
     * 群组
     */
    GROUP(0, "群组"),
    /**
     * 个人
     */
    PERSONAL(1, "个人");

    private int code;
    private String desc;

    ChatSocketTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
