import java.util.ArrayList;

public class GroupChat extends ChatRoom{
    private String name;
    private String bio;
    private String groupID;
    private int ageRestriction;
    PrivacyMode privacyMode;

    private ArrayList<User> adminsUserArraylist = new  ArrayList<>();
    private ArrayList<User> membersUserArraylist = new  ArrayList<>();
    private ArrayList<User> requestedUsersArraylist = new  ArrayList<>();

    public GroupChat(String name, String groupID, int ageRestriction, PrivacyMode privacyMode ,
                     User admin) {
        setName(name);
        setGroupID(groupID);
        setAgeRestriction(ageRestriction);
        setPrivacyMode(privacyMode);
        getAdminsUserArraylist().add(admin);
    }

    public ArrayList<User> getAdminsUserArraylist() {
        return adminsUserArraylist;
    }

    public void setAdminsUserArraylist(ArrayList<User> adminsUserArraylist) {
        this.adminsUserArraylist = adminsUserArraylist;
    }

    public ArrayList<User> getMembersUserArraylist() {
        return membersUserArraylist;
    }

    public void setMembersUserArraylist(ArrayList<User> membersUserArraylist) {
        this.membersUserArraylist = membersUserArraylist;
    }

    public GroupChat(String groupName , String groupID){
        setName(groupName);
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

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public PrivacyMode getPrivacyMode() {
        return privacyMode;
    }

    public void setPrivacyMode(PrivacyMode privacyMode) {
        this.privacyMode = privacyMode;
    }

    public ArrayList<User> getRequestedUsersArraylist() {
        return requestedUsersArraylist;
    }

    public void setRequestedUsersArraylist(ArrayList<User> requestedUsersArraylist) {
        this.requestedUsersArraylist = requestedUsersArraylist;
    }

    public static boolean isInAdminArrayList(GroupChat chosenGroup , User currentUser){
        for (User user : chosenGroup.getAdminsUserArraylist()){
            if (currentUser.equals(user)){
                return true;
            }
        }
        return false;
    }
    public static boolean isInMembersArrayList(GroupChat chosenGroup ,User currentUser){
        for (User user : chosenGroup.getMembersUserArraylist()){
            if (currentUser.equals(user)){
                return true;
            }
        }
        return false;
    }


}
