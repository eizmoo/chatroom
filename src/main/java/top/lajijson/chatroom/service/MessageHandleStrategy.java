package top.lajijson.chatroom.service;

import top.lajijson.chatroom.entity.base.Message;

import javax.websocket.Session;
import java.io.IOException;

/**
 * 消息处理接口
 *
 * @author liuwei
 */
public interface MessageHandleStrategy {

    /**
     * 消息处理
     *
     * @param token   当前用户的token
     * @param session 当前用户的session
     * @param message 消息
     */
    void handle(String token, Session session, Message message);

    /**
     * 发送消息，默认实现体方法
     *
     * @param session
     * @param message
     */
    default void send(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
