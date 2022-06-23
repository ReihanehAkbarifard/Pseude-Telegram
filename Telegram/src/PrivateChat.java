public class PrivateChat extends ChatRoom{
    private static User sender;
    private static User receiver;

    public PrivateChat(User sender, User receiver){
        setReceiver(receiver);
        setSender(sender);

    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        PrivateChat.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        PrivateChat.receiver = receiver;
    }


}
