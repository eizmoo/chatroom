package top.lajijson.chatroom.service.impl.strategy;

import org.springframework.stereotype.Service;
import top.lajijson.chatroom.endipoint.ChatSocket;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.service.MessageHandleStrategy;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 建立群聊关系消息处理策略
 *
 * @author liuwei
 */
@Service
public class BuildGroupRelationshipMessageHandleStrategy implements MessageHandleStrategy {
    @Override
    public void handle(String token, Session session, Message message) {
        ConcurrentHashMap<String, ChatSocket> map = ChatSocket.getChatSocketConcurrentHashMap();

        // 群聊的map-key
        String groupToken = message.getTargetToken();

        ChatSocket groupSocket = map.get(groupToken);

        if (groupSocket != null) {
            // 当前session放入群组关系的sessionSet中
            if (map.containsKey(token)) {
                groupSocket.getSessions().add(map.get(token));
            }
        }
    }
}
