import java.util.ArrayList;

public class Channel extends ChatRoom {
    private String name;
    private String bio;
    private String channelID;

    private ArrayList<User> adminsUserArraylist = new ArrayList<>();
    private ArrayList<User> viewersUserArraylist = new  ArrayList<>();

    public Channel(String name, String channelID ,User admin){
        setName(name);
        setChannelID(channelID);
        getAdminsUserArraylist().add(admin);
    }

    public ArrayList<User> getAdminsUserArraylist() {
        return adminsUserArraylist;
    }

    public void setAdminsUserArraylist(ArrayList<User> adminsUserArraylist) {
        this.adminsUserArraylist = adminsUserArraylist;
    }

    public ArrayList<User> getViewersUserArraylist() {
        return viewersUserArraylist;
    }

    public void setViewersUserArraylist(ArrayList<User> viewersUserArraylist) {
        this.viewersUserArraylist = viewersUserArraylist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public static boolean isInAdminArrayList(Channel chosenChannel ,User currentUser){
        for (User user : chosenChannel.getAdminsUserArraylist()){
            if (currentUser.equals(user)){
                return true;
            }
        }
        return false;
    }
}
