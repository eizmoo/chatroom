package top.lajijson.chatroom.enums;

import lombok.Getter;

/**
 * 消息类型枚举类
 *
 * @author liuwei
 */
@Getter
public enum MessageTypeEnum {
    /**
     * 群聊
     */
    GRUOP_CHAT(0, "群组", "groupChatMessageHandleStrategy"),
    /**
     * 私聊
     */
    SINGLE_CHAT(1, "个人", "singleChatMessageHandleStrategy"),
    /**
     * 创建群组
     */
    GENERATE_GROUP(2, "创建群组", "generateGroupMessageHandleStrategy"),
    /**
     * 加入群组
     */
    JOIN_GROUP(3, "加入群组", "buildGroupRelationshipMessageHandleStrategy"),
    /**
     * 退出群组
     */
    EXIT_GROUP(4, "退出群组", "TODO"),

    ;

    private int code;
    private String desc;
    private String strategyBeanName;

    MessageTypeEnum(int code, String desc, String strategyBeanName) {
        this.code = code;
        this.desc = desc;
        this.strategyBeanName = strategyBeanName;
    }

    /**
     * 根据code返回枚举实体
     *
     * @param code
     * @return
     */
    public static MessageTypeEnum codeOf(Integer code) throws Exception {
        for (MessageTypeEnum typeEnum : MessageTypeEnum.values()) {
            if (typeEnum.code == code) {
                return typeEnum;
            }
        }
        throw new Exception("未知消息type");
    }
}
