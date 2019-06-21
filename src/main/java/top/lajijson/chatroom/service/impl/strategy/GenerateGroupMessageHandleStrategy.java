package top.lajijson.chatroom.service.impl.strategy;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import top.lajijson.chatroom.endipoint.ChatSocket;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.enums.ChatSocketTypeEnum;
import top.lajijson.chatroom.service.MessageHandleStrategy;

import javax.websocket.Session;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成群组消息处理策略
 *
 * @author liuwei
 */
@Service
public class GenerateGroupMessageHandleStrategy implements MessageHandleStrategy {
    @Override
    public void handle(String token, Session session, Message message) {
        ConcurrentHashMap<String, ChatSocket> map = ChatSocket.getChatSocketConcurrentHashMap();

        // 群聊的map-key
        String groupToken = message.getTargetToken();

        ChatSocket groupSocket = map.get(groupToken);

        // 检验群组是否已存在
        if (groupSocket == null) {
            // 不存在，生成群组
            ChatSocket chatSocket = new ChatSocket();
            chatSocket.setType(ChatSocketTypeEnum.GROUP.getCode());

            Set<ChatSocket> set = Sets.newConcurrentHashSet();
            set.add(map.get(token));
            chatSocket.setSessions(set);

            // 放入map
            map.put(groupToken, chatSocket);
        }
    }
}
