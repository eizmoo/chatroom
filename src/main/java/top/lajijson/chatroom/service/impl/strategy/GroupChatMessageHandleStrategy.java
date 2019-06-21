package top.lajijson.chatroom.service.impl.strategy;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import top.lajijson.chatroom.endipoint.ChatSocket;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.service.MessageHandleStrategy;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 群聊（一对多）聊天消息处理策略
 *
 * @author Administrator
 */
@Service
public class GroupChatMessageHandleStrategy implements MessageHandleStrategy {

    @Override
    public void handle(String token, Session session, Message message) {
        ConcurrentHashMap<String, ChatSocket> map = ChatSocket.getChatSocketConcurrentHashMap();

        // 获取目标的session，发送消息
        ChatSocket targetSocket = map.get(message.getTargetToken());
        if (targetSocket != null) {
            // 群聊，每个在群内的对象都在list<CharSocket>中
            for (ChatSocket chatSocket : targetSocket.getSessions()) {
                send(chatSocket.getSession(), JSON.toJSONString(message));
            }
        }
    }
}
