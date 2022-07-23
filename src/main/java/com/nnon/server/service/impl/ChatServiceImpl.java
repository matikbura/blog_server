package com.nnon.server.service.impl;

import com.nnon.server.mapper.ChatMapper;
import com.nnon.server.pojo.Chat;
import com.nnon.server.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    ChatMapper mapper;
    @Override
    public List<Integer> findChatUid(int uid) {
        return mapper.findChatUid(uid);
    }

    @Override
    public Chat getLatestMessage(int personUid, Integer otherUid) {
        return mapper.getLatestMessage(personUid,otherUid);
    }

    @Override
    public Integer unreadMessageCount(int personUid, Integer otherUid) {
        return mapper.unreadMessageCount(personUid,otherUid);
    }

    @Override
    public List<Chat> getConversationMessageByOtherUid(Integer personUid, Integer otherUid) {
        return mapper.getConversationMessageByOtherUid(personUid,otherUid);
    }

    @Override
    public Integer insertChat(Chat chat) {
        return mapper.insertChat(chat);
    }

    @Override
    public void updateChat(Chat chat) {
        mapper.updateChat(chat);
    }

    @Override
    public void setIsReadTrueByOtherUid(int personUid, Integer otherUid) {
        List<String> result= mapper.getIReadFalse(personUid,otherUid);
        if (result!=null&&!result.isEmpty()){
            mapper.setIsReadTrueByOtherUid(result);
        }

    }
}
