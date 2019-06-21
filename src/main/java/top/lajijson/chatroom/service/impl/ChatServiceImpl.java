package top.lajijson.chatroom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lajijson.chatroom.endipoint.ChatSocket;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.entity.bo.UserListBo;
import top.lajijson.chatroom.enums.ChatSocketTypeEnum;
import top.lajijson.chatroom.service.ChatService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 消息服务层实现
 *
 * @author liuwei
 */
@Service
public class ChatServiceImpl implements ChatService {

    private ChatSocket chatSocket;

    /**
     * 发送消息
     */
    @Override
    public void sendMessage(Message message) throws Exception {
        message.setUuid(UUID.randomUUID().toString().replace("-", ""));
        chatSocket.sendMessage(message);
    }


    /**
     * 当前的用户list，根据入参，返回指定组的用户或全部用户token
     *
     * @param bo
     * @return
     */
    @Override
    public List<String> userList(UserListBo bo) {
        ConcurrentHashMap<String, ChatSocket> map = ChatSocket.getChatSocketConcurrentHashMap();

        // 是否查询群组内用户
        if (bo.getQueryGroup() == 1) {
            // 查询群组用户token
            String groupToken = bo.getGroupToken();
            ChatSocket chatSocket = map.get(groupToken);

            // 群组token不存在或token不为群组，返回空用户名数组
            if (chatSocket == null || !chatSocket.getType().equals(ChatSocketTypeEnum.GROUP.getCode())) {
                return new ArrayList<>(0);
            }

            return chatSocket.getSessions().stream().map(ChatSocket::getToken).collect(Collectors.toList());
        } else {
            // 查询全部用户token
            List<String> userTokens = new ArrayList<>(map.size());

            map.forEach((k, v) -> {
                if (v.getType().equals(ChatSocketTypeEnum.PERSONAL.getCode())) {
                    userTokens.add(k);
                }
            });

            return userTokens;
        }
    }

    @Autowired
    public void setChatSocket(ChatSocket chatSocket) {
        this.chatSocket = chatSocket;
    }
}
