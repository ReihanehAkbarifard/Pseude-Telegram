import java.util.ArrayList;

public class Message {
    private String message ;
    private User sender;
    private ArrayList<User> likerUserArrayList = new ArrayList<>();
    private ArrayList<Message> replyArrayList = new ArrayList<>();
    private ArrayList<Message> captionArrayList = new ArrayList<>();


    public Message(String message, User sender){
        setMessage(message);
        setSender(sender);

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public ArrayList<User> getLikerUserArrayList() {
        return likerUserArrayList;
    }

    public void setLikerUserArrayList(ArrayList<User> likerUserArrayList) {
        this.likerUserArrayList = likerUserArrayList;
    }

    public ArrayList<Message> getReplyArrayList() {
        return replyArrayList;
    }

    public void setReplyArrayList(ArrayList<Message> replyArrayList) {
        this.replyArrayList = replyArrayList;
    }

    public ArrayList<Message> getCaptionArrayList() {
        return captionArrayList;
    }

    public void setCaptionArrayList(ArrayList<Message> captionArrayList) {
        this.captionArrayList = captionArrayList;
    }
}
