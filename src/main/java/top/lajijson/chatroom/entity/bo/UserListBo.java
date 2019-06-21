package top.lajijson.chatroom.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 获取用户list入参bo
 *
 * @author liuwei
 */
@Data
public class UserListBo {

    /**
     * 是否查询群组0否1是
     */
    @NotNull
    private Integer queryGroup;



    /**
     * 要查询的群组token
     */
    private String groupToken;

}
