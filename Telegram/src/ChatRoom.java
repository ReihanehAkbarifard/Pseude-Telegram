import java.util.ArrayList;

public class ChatRoom {
    private ArrayList<Message> messageArrayList = new ArrayList<>();

    public ArrayList<Message> getMessageArrayList() {
        return messageArrayList;
    }

    public void setMessageArrayList(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }

    public static ChatRoom getChatRoomFromItsID(String chatRoomID, User currentUser){
        ChatRoom findChatRoom = null;
        for (GroupChat groupChat : currentUser.getGroupChatArrayList()){
            if(chatRoomID.equals(groupChat.getGroupID())){
                findChatRoom = groupChat;
            }
        }
        for (PrivateChat privateChat : currentUser.getPrivateChatArrayList()){
            if(chatRoomID.equals(privateChat.getReceiver().getUserID())){
                findChatRoom = privateChat;
            }
        }
        for (Channel channel : currentUser.getChannelArrayList()){
            if(chatRoomID.equals(channel.getChannelID())){
                findChatRoom = channel;
            }
        }
        return findChatRoom;
    }
}
