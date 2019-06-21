package top.lajijson.chatroom.entity.base;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 消息实体
 *
 * @author liuwei
 */
@Data
public class Message {

    /**
     * 消息id
     */
    private String uuid;

    /**
     * 用户token
     */
    @NotBlank
    private String userToken;

    /**
     * 消息内容
     */
    @NotBlank
    private String content;

    /**
     * 目标token（群或个人）
     */
    @NotBlank
    private String targetToken;

    /**
     * 消息类型 0群1个人2创建群组3加入群组4退出群组
     */
    @NotNull
    private Integer type;

}
