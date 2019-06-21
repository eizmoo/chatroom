package top.lajijson.chatroom.service.impl.strategy;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import top.lajijson.chatroom.endipoint.ChatSocket;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.service.MessageHandleStrategy;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单聊（一对一）聊天消息处理策略
 *
 * @author liuwei
 */
@Service
public class SingleChatMessageHandleStrategy implements MessageHandleStrategy {

    @Override
    public void handle(String token, Session session, Message message) {
        ConcurrentHashMap<String, ChatSocket> map = ChatSocket.getChatSocketConcurrentHashMap();

        // 获取目标的session，发送消息
        ChatSocket targetSocket = map.get(message.getTargetToken());
        if (targetSocket != null) {
            send(targetSocket.getSession(), JSON.toJSONString(message));
        }
    }

}
