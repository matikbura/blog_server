package com.nnon.server.controller;

import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Chat;
import com.nnon.server.pojo.SocketKey;
import com.nnon.server.pojo.User;
import com.nnon.server.service.IChatService;
import com.nnon.server.service.IUserService;
import com.nnon.server.service.impl.ChatServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import com.nnon.server.utils.SystemConfig;
import com.nnon.server.utils.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("chat")
public class ChatController {
    @Autowired
    IChatService chatService;
    @Autowired
    IUserService userService;
    @Autowired
    WebSocketServer webSocketServer;
    @RequestMapping("getConversationItemList")
    @ResponseBody
    public CommonResult getConversationItemList(HttpServletRequest request) throws ParseException {
       try {
           //获取UID
           Object verifyUid = request.getAttribute("verifyUid");
           int uid = Integer.parseInt(verifyUid.toString());
           //通过UID查找发过去的用户ID  与接收到的用户ID
           List<Integer> chatUid = chatService.findChatUid(uid);
           List<Map<String,String>> resultList= new ArrayList<>();
           Map<String,String> map=null;
           for(Integer cuid:chatUid){
               map = new HashMap<>();
               User userByUid = userService.findUserByUid(cuid);
               map.put("imgUrl", SystemConfig.getProperty("IMGURLBASE")+userByUid.getImgUrl());
               map.put("name",userByUid.getName());
               Chat chat= chatService.getLatestMessage(uid,userByUid.getUid());
               //最新消息
               map.put("latestMessage",chat.getContext());

               //最新消息的时间
               map.put("latestTime", DateTimeUtils.dateConvAppointStr(chat.getCreateTime()));
               //未读消息总数
               map.put("unreadMessageCount",chatService.unreadMessageCount(uid,userByUid.getUid()).toString());
               map.put("otherUid",userByUid.getUid().toString());
               resultList.add(map);
           }
           return CommonResult.success(resultList);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    @RequestMapping("getConversationMessageByOtherUid")
    @ResponseBody
    public CommonResult getConversationMessageByOtherUid(Integer otherUid,HttpServletRequest request) throws ParseException {
        //获取UID
        Object verifyUid = request.getAttribute("verifyUid");
        int uid = Integer.parseInt(verifyUid.toString());
        List<Chat> chatList= chatService.getConversationMessageByOtherUid(uid,otherUid);
        //所有消息标记为已读
        chatService.setIsReadTrueByOtherUid(uid,otherUid);
        for(Chat chat:chatList){
            chat.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(chat.getCreateTime()));
        }
        System.out.println(chatList);
        return CommonResult.success(chatList);
    }



    @RequestMapping("sendMessage")
    @ResponseBody
    public CommonResult sendMessage(Chat chat,HttpServletRequest request){
        try {
            //获取UID
            Object verifyUid = request.getAttribute("verifyUid");
            int uid = Integer.parseInt(verifyUid.toString());
            System.out.println(chat);
            chat.setCreateTime(new Date());
            chat.setIsRead("0");
            chat.setFromUid(uid);
            //加入数据库
            Integer chatId =  chatService.insertChat(chat);
            //查看发送的账号是否登录
            HashMap<String,Object> map = null;
            HashMap<SocketKey, Session> socketMap = WebSocketServer.map;
            for(SocketKey key:socketMap.keySet()){
                if(Objects.equals(key.getUid(), chat.getToUid())){
                    chat.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(chat.getCreateTime()));
                    map = new HashMap<>();
                    User userByUid = userService.findUserByUid(chat.getFromUid());
                    map.put("imgUrl", SystemConfig.getProperty("IMGURLBASE")+userByUid.getImgUrl());
                    map.put("name",userByUid.getName());
                    map.put("context",chat.getContext());
                    map.put("chatId",chat.getChatId());
                    map.put("createTimeStr",chat.getCreateTimeStr());
                    map.put("toUid",chat.getToUid());
                    map.put("fromUid",chat.getFromUid());
                    webSocketServer.sendInfo(chat.getToUid(),map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.success(chat);
    }
    @RequestMapping("updateChat")
    @ResponseBody
    public CommonResult updateChat(Chat chat){
        System.out.println("开始更改"+chat);
        chatService.updateChat(chat);
        return null;
    }
}
