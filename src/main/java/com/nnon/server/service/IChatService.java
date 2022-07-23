package com.nnon.server.service;

import com.nnon.server.pojo.Chat;

import java.util.List;

public interface IChatService {
    List<Integer> findChatUid(int uid);

    Chat getLatestMessage(int personUid, Integer otherUid);

    Integer unreadMessageCount(int personUid, Integer otherUid);

    List<Chat> getConversationMessageByOtherUid(Integer personUid, Integer otherUid);

    Integer insertChat(Chat chat);

    void updateChat(Chat chat);

    void setIsReadTrueByOtherUid(int personUid, Integer otherUid);
}
