package com.nnon.server.mapper;

import com.nnon.server.pojo.Chat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ChatMapper {
    List<Integer> findChatUid(int uid);

    Chat getLatestMessage(@Param("personUid") int personUid, @Param("otherUid") Integer otherUid);

    Integer unreadMessageCount(@Param("personUid") int personUid, @Param("otherUid")  Integer otherUid);

    List<Chat> getConversationMessageByOtherUid(@Param("personUid") Integer personUid, @Param("otherUid")  Integer otherUid);

    Integer insertChat(Chat chat);

    void updateChat(Chat chat);

    void setIsReadTrueByOtherUid(List<String> chatIds);

    List<String> getIReadFalse(@Param("personUid") int personUid,@Param("otherUid") Integer otherUid);
}
