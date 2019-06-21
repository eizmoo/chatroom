package top.lajijson.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.enums.MessageTypeEnum;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Service
public class MessageHandleStrategyContext {

    private final Map<String, MessageHandleStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public MessageHandleStrategyContext(Map<String, MessageHandleStrategy> strategyMap) {
        this.strategyMap.clear();
        this.strategyMap.putAll(strategyMap);
    }

    public void execute(String token, Session session, Message message) throws Exception {
        MessageHandleStrategy strategy = strategyMap.get(MessageTypeEnum.codeOf(message.getType()).getStrategyBeanName());

        strategy.handle(token, session, message);
    }
}
