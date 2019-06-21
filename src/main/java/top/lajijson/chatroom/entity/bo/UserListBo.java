package top.lajijson.chatroom.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 获取用户list入参bo
 *
 * @author liuwei
 */
@Data
public class UserListBo {

    @NotNull
    private Integer queryGroup;

    private String groupToken;

}
