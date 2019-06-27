package top.lajijson.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.lajijson.chatroom.entity.base.Message;
import top.lajijson.chatroom.entity.base.Result;
import top.lajijson.chatroom.entity.bo.UserListBo;
import top.lajijson.chatroom.service.ChatService;

import javax.validation.Valid;

/**
 * 发消息控制层
 *
 * @author liuwei
 */
@RequestMapping("/")
@RestController
public class ChatController {

    private ChatService chatService;

    /**
     * 获取当前用户list
     *
     * @param bo
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public Result userList(@Valid UserListBo bo) {
        return Result.successResult(chatService.userList(bo));
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/message")
    public Result sendMessage(@RequestBody @Valid Message message) {
        try {
            chatService.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failResult();
        }
        return Result.successResult();
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }
}
