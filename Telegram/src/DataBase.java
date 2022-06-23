import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> usersArrayList = new ArrayList<>();
    private static ArrayList<GroupChat> groupChatDataBaseArrayList = new ArrayList<>();
    private static ArrayList<Channel> channelDataBaseArrayList = new ArrayList<>();


    public static ArrayList<Channel> getChannelDataBaseArrayList() {
        return channelDataBaseArrayList;
    }

    public static void setChannelDataBaseArrayList(ArrayList<Channel> channelDataBaseArrayList) {
        DataBase.channelDataBaseArrayList = channelDataBaseArrayList;
    }

    public static ArrayList<GroupChat> getGroupChatDataBaseArrayList() {
        return groupChatDataBaseArrayList;
    }

    public static void setGroupChatDataBaseArrayList(ArrayList<GroupChat> groupChatDataBaseArrayList) {
        DataBase.groupChatDataBaseArrayList = groupChatDataBaseArrayList;
    }

    public DataBase(){
        setUsersArrayList(new ArrayList<>());
    }

    public static ArrayList<User> getUsersArrayList() {
        return usersArrayList;
    }

    public static void setUsersArrayList(ArrayList<User> usersArrayList) {
        DataBase.usersArrayList = usersArrayList;
    }


    public static boolean isUniqueUserID(String userID){
        if (getUsersArrayList().isEmpty())
            return true;
        for(User user : usersArrayList){
            if (user.getUserID().equals(userID))
                return false;
        }
        return true;
    }

    public static boolean isAvailableUserID(String userID){
        if (getUsersArrayList().isEmpty())
            return false;
        for(User user : usersArrayList ){
            if (user.getUserID().equals(userID))
                return true;
        }
        return false;
    }

    public static boolean isAvailableUserPhoneNumber(String phoneNumber){
        if (getUsersArrayList().isEmpty())
            return false;
        for(User user : usersArrayList ){
            if (user.getPhoneNumber().equals(phoneNumber))
                return true;
        }
        return false;
    }

    public static boolean isAvailableGroup(String groupID){
        if (getGroupChatDataBaseArrayList().isEmpty())
            return false;
        for(GroupChat groupChat : getGroupChatDataBaseArrayList() ){
            if (groupChat.getGroupID().equals(groupID))
                return true;
        }
        return false;
    }

    public static boolean isAvailableChannel(String channelID){
        if (getChannelDataBaseArrayList().isEmpty())
            return false;
        for(Channel channel : getChannelDataBaseArrayList() ){
            if (channel.getChannelID().equals(channelID))
                return true;
        }
        return false;
    }
    public static Channel getChannelFromChannelID(String channelID){
        if (getChannelDataBaseArrayList().isEmpty())
            return null;
        for (Channel channel : getChannelDataBaseArrayList()){
            if(channel.getChannelID().equals(channelID))
                return channel;
        }
        return null;

    }

    public static GroupChat getGroupFromGroupID(String groupID){
        if (getGroupChatDataBaseArrayList().isEmpty())
            return null;
        for (GroupChat groupChat : getGroupChatDataBaseArrayList()){
            if(groupChat.getGroupID().equals(groupID))
                return groupChat;
        }
        return null;

    }
    public static User getUserFromUserID(String userID){
        if (getUsersArrayList().isEmpty())
            return null;
        for (User user : getUsersArrayList()){
            if(user.getUserID().equals(userID))
                return user;
        }
        return null;

    }
    public static User getUserFromPhoneNumber(String phoneNumber){
        if (getUsersArrayList().isEmpty())
            return null;
        for (User user : getUsersArrayList()){
            if(user.getPhoneNumber().equals(phoneNumber))
                return user;
        }
        return null;

    }




}
