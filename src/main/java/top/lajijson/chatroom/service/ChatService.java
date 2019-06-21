package top.lajijson.chatroom.service;

import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.entity.bo.UserListBo;

import java.util.List;

/**
 * 消息服务层
 *
 * @author liuwei
 */
public interface ChatService {

    /**
     * 发送消息
     */
    void sendMessage(Message message) throws Exception;

    /**
     * 当前的用户list，根据入参，返回指定组的用户或全部用户token
     *
     * @param bo
     * @return
     */
    List<String> userList(UserListBo bo);
}
