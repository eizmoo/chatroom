package top.lajijson.chatroom.endipoint;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.service.MessageHandleStrategyContext;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * socket链接接口
 *
 * @author liuwei
 */
@Data
@Slf4j
@Component
@ServerEndpoint("/chatSocket/{token}")
public class ChatSocket {
    /**
     * socket 类型 0群1个人
     */
    private Integer type;

    /**
     * 群组下的session链接
     */
    private Set<ChatSocket> sessions;

    /**
     * 个人session链接
     */
    private Session session;

    /**
     * 此session的token
     */
    private String token;

    private static final ConcurrentHashMap<String, ChatSocket> CHAT_SOCKET_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    private MessageHandleStrategyContext strategyContext;

    /**
     * 创建连接，保存token对应session
     *
     * @param token
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "token") String token, Session session) {
        this.session = session;
        this.token = token;
        CHAT_SOCKET_CONCURRENT_HASH_MAP.put(token, this);
        log.info("new connect,token:{}.  current total:{}", token, CHAT_SOCKET_CONCURRENT_HASH_MAP.size());
    }

    /**
     * 关闭连接，删除token对应session
     *
     * @param token
     */
    @OnClose
    public void onClose(@PathParam(value = "token") String token) {
        CHAT_SOCKET_CONCURRENT_HASH_MAP.remove(token);
        log.info("disconnect,token:{}.  current total:{}", token, CHAT_SOCKET_CONCURRENT_HASH_MAP.size());
    }

    /**
     * 取得消息通知
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Message messageEntity = JSON.parseObject(message, Message.class);
        try {
            strategyContext.execute(messageEntity.getUserToken(), session, messageEntity);
        } catch (Exception e) {
            log.error("执行消息通知异常，消息：{}", message);
            e.printStackTrace();
        }
    }

    /**
     * 发送消息，用于用户主动输入：单聊、群聊
     *
     * @param message
     * @throws Exception
     */
    public void sendMessage(Message message) throws Exception {
        strategyContext.execute(null, null, message);
    }


    public static ConcurrentHashMap<String, ChatSocket> getChatSocketConcurrentHashMap() {
        return CHAT_SOCKET_CONCURRENT_HASH_MAP;
    }

    @Autowired
    public void setStrategyContext(MessageHandleStrategyContext strategyContext) {
        this.strategyContext = strategyContext;
    }
}
