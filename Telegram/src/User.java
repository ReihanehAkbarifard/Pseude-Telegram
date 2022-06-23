import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {

    private String fullName;
    LocalDate birthDay;
    private int age;
    private String phoneNumber;
    private String userID;
    private String passWord;
    Gender gender;

    private ArrayList<PrivateChat> privateChatArrayList = new ArrayList<>();
    private ArrayList<GroupChat> groupChatArrayList = new ArrayList<>();
    private ArrayList<Channel> channelArrayList = new ArrayList<>();


    public User(String fullName , LocalDate birthDay , int age , String phoneNumber , Gender gender , String userID
            , String passWord){

        setFullName(fullName);
        setBirthDay(birthDay);
        setAge(age);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setUserID(userID);
        setPassWord(passWord);
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getPassWord() {
        return passWord;
    }

    public ArrayList<PrivateChat> getPrivateChatArrayList() {
        return privateChatArrayList;
    }

    public void setPrivateChatArrayList(ArrayList<PrivateChat> privateChatArrayList) {
        this.privateChatArrayList = privateChatArrayList;
    }

    public ArrayList<GroupChat> getGroupChatArrayList() {
        return groupChatArrayList;
    }

    public void setGroupChatArrayList(ArrayList<GroupChat> groupChatArrayList) {
        this.groupChatArrayList = groupChatArrayList;
    }

    public ArrayList<Channel> getChannelArrayList() {
        return channelArrayList;
    }

    public void setChannelArrayList(ArrayList<Channel> channelArrayList) {
        this.channelArrayList = channelArrayList;
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();

    }

    public static User signUp(){
        String fullName = JOptionPane.showInputDialog(null ,"Please Enter Your FullName :",
                "Sign-Up page" , JOptionPane.QUESTION_MESSAGE);

        String phoneNumber = JOptionPane.showInputDialog(null ,"Please Enter Your PhoneNumber :",
                "Sign-Up page" , JOptionPane.QUESTION_MESSAGE);

        LocalDate birthDay = LocalDate.parse(JOptionPane.showInputDialog(null ,"Please Enter Your" +
                        "Date Of Birth In YYYY-MM-DD Format: :",
                "Sign-Up Page" , JOptionPane.QUESTION_MESSAGE));
        int age = calculateAge(birthDay);

        String userID = "";
        do {
            userID = JOptionPane.showInputDialog(null ,"Please Enter Your UserID :",
                    "Sign-Up Page" , JOptionPane.QUESTION_MESSAGE);
        }while (!DataBase.isUniqueUserID(userID));

        String passWord = JOptionPane.showInputDialog(null ,"Please Enter your passWord :",
                "Sign-Up Page" , JOptionPane.QUESTION_MESSAGE);

        int genderNumber = Integer.parseInt(JOptionPane.showInputDialog(null ,
                "Please Enter Your Gender :\n 1. Male\n 2. Female " ,"Sign-Up page" ,
                JOptionPane.QUESTION_MESSAGE));

        Gender chosenGender = Gender.NOT_DEFIED;
        switch (genderNumber){
            case 1:
                chosenGender = Gender.Male;
                break;
            case 2:
                chosenGender = Gender.FEMALE;
                break;
            default:
                break;

        }

        User newUser = new User(fullName ,birthDay ,age ,phoneNumber ,chosenGender ,userID ,passWord);
        DataBase.getUsersArrayList().add(newUser);
        return newUser;

    }
    public static User logIn(){
        String userID = JOptionPane.showInputDialog(null ,"Please Enter Your " +
                "UserID :" ,"Log-In page" ,JOptionPane.QUESTION_MESSAGE).toLowerCase();
        boolean isTruePassWord = true;
        if(DataBase.isAvailableUserID(userID)){
            String passWord;
            User currentUser = DataBase.getUserFromUserID(userID);
            while (isTruePassWord){
                passWord = JOptionPane.showInputDialog(null ,"Please Enter" +
                        " Your PassWord : ","Log-In page" ,JOptionPane.QUESTION_MESSAGE);
                if (passWord.equals(currentUser.passWord)){
                    JOptionPane.showMessageDialog(null ,"Welcome Dear " +
                                    currentUser.getFullName() ,"Welcome"
                            ,JOptionPane.INFORMATION_MESSAGE);
                    return currentUser;
                }
                else {
                    JOptionPane.showMessageDialog(null ,"Your PassWord Is" +
                                    " Incorrect, Please Try Again" ,"Log-In page"
                            ,JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null ,"No Such Account Found!"
                    ,"Log-In page" ,JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return null;

    }

    public static void seeProfilePage(User currentUser){

        JOptionPane.showMessageDialog(null ,"Full Name : " + currentUser.getFullName()
        + "\nPhone Number : " + currentUser.getPhoneNumber() + "\nUser ID : @ " + currentUser.getUserID() +
                "\nAge : " + currentUser.getAge() , "Profile-Page" ,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void editProfilePage(User currentUser){
        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,"Which Option Do Want To Change?\n" +
                        "1. Full Name\n2. Phone Number\n3. User ID\n4. PassWord\n" ,
                "Edit Profile-Page" ,JOptionPane.QUESTION_MESSAGE))){
            case 1:
                String newFullName = JOptionPane.showInputDialog(null ,"Enter Your New fullName :",
                        "Change FullName" ,JOptionPane.QUESTION_MESSAGE);
                currentUser.setFullName(newFullName);
                JOptionPane.showMessageDialog(null ,"Your FullName Has Been" +
                        "changed successfully!\nYour new fullName is : " + currentUser.getFullName() ,
                        "Change fullName" ,JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                String newPhoneNumber = JOptionPane.showInputDialog(null ,"Enter Your New PhoneNumber :",
                        "Change PhoneNumber" ,JOptionPane.QUESTION_MESSAGE);
                currentUser.setPhoneNumber(newPhoneNumber);
                JOptionPane.showMessageDialog(null ,"Your NewPhoneNumber Has Been" +
                                "Changed Successfully!\nYour New PhoneNumber Is : " + currentUser.getPhoneNumber() ,
                        "Change PhoneNumber" ,JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                String newUserID = JOptionPane.showInputDialog(null ,"Enter Your New UserID :",
                        "Change UserID" ,JOptionPane.QUESTION_MESSAGE);
                while (DataBase.isAvailableUserID(newUserID)){
                    newUserID = JOptionPane.showInputDialog(null ,"Try Another UserID :",
                            "Change UserID" ,JOptionPane.QUESTION_MESSAGE);
                }
                currentUser.setUserID(newUserID);
                JOptionPane.showMessageDialog(null ,"Your New UserID Has been " +
                                "Changed Successfully!\nYour New UserID Is : " + currentUser.getUserID() ,
                            "Change UserID" ,JOptionPane.INFORMATION_MESSAGE);

                break;

            case 4:
                String currentPassWord = JOptionPane.showInputDialog(null ,"Please" +
                        "Enter Your Current passWord" , "Change PassWord",JOptionPane.QUESTION_MESSAGE);
                while (!currentUser.getPassWord().equals(currentPassWord)){
                    currentPassWord = JOptionPane.showInputDialog(null ,"Your" +
                            "PassWord Is Incorrect ! Please Try Again.");
                }
                String newPassword = JOptionPane.showInputDialog(null ,"Enter Your New Password :",
                        "Change PassWord" ,JOptionPane.QUESTION_MESSAGE);
                currentUser.setPassWord(newPassword);
                JOptionPane.showMessageDialog(null ,"Your PassWord has been" +
                                "Changed Successfully!" ,"Change PassWord" ,JOptionPane.INFORMATION_MESSAGE);
                break;

        }


    }
    public static User searchUser(){
        String userSearched = JOptionPane.showInputDialog(null ,
                "Who Are You Searching For?\nEnter His/Her PhoneNumber/ID :"
                , "Search User",
                JOptionPane.QUESTION_MESSAGE);

        if (DataBase.isAvailableUserID(userSearched)) {
            return DataBase.getUserFromUserID(userSearched);
        }
        else if(DataBase.isAvailableUserPhoneNumber(userSearched)){
            return DataBase.getUserFromPhoneNumber(userSearched);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Account " +
                    "Found!" , "Search User" ,JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }
    public static GroupChat searchGroup(){
        String groupSearched = JOptionPane.showInputDialog(null ,
                "Which Group Are You Searching For?\nEnter Its ID :"
                , "Search Group",
                JOptionPane.QUESTION_MESSAGE);

        if (DataBase.isAvailableGroup(groupSearched)) {
            return DataBase.getGroupFromGroupID(groupSearched);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Group " +
                    "Found!" , "Search Group" ,JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }
    public static Channel searchChannel(){
        String channelSearched = JOptionPane.showInputDialog(null ,
                "Which Channel Are You Searching For?\nEnter Its ID :"
                , "Search Channel",
                JOptionPane.QUESTION_MESSAGE);

        if (DataBase.isAvailableChannel(channelSearched)) {
            return DataBase.getChannelFromChannelID(channelSearched);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Channel " +
                    "Found!" , "Search Channel" ,JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }

    public static void deleteAccount(User currentUser){
        DataBase.getUsersArrayList().remove(currentUser);
        JOptionPane.showMessageDialog(null, "Your Account " +
                "Was Successfully Deleted");
    }

    public static PrivateChat startNewPrivateChat(User currentUser ,User toSendMessageTOThisUser){

            if (!alreadyHasPrivateChat(toSendMessageTOThisUser ,currentUser)){

                PrivateChat newPrivateChat = new PrivateChat(currentUser , toSendMessageTOThisUser);
                currentUser.getPrivateChatArrayList().add(newPrivateChat);
                toSendMessageTOThisUser.getPrivateChatArrayList().add(newPrivateChat);

                JOptionPane.showMessageDialog(null ,"New Private " +
                        "Chat With " + toSendMessageTOThisUser.getUserID()
                                +" Just Added!"
                        , "Start New Private Chat" ,JOptionPane.INFORMATION_MESSAGE);
                return newPrivateChat;
            }
            else {
                JOptionPane.showMessageDialog(null ,"You Have Already has" +
                        "Private Chat With This User! " , "Start New Private Chat" ,JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

    }
    public static boolean alreadyHasPrivateChat(User receiver ,User currentUser){
        for (PrivateChat privateChat : currentUser.getPrivateChatArrayList()){
            if (privateChat.getReceiver().equals(receiver))
                return true;
        }
        return false;
    }
    public static void createNewGroup(User currentUser){
        String newGroupName = JOptionPane.showInputDialog(null ,"Please Enter New Group's Name: " ,
                "Create New Group" ,JOptionPane.QUESTION_MESSAGE);
        String newGroupID = JOptionPane.showInputDialog(null ,"Please Enter" +
                "New Group's ID:\n" , "Create New Group" ,JOptionPane.QUESTION_MESSAGE);
        int ageRestriction = Integer.parseInt(JOptionPane.showInputDialog(null ,"Please Enter" +
                "An Age Restriction For The Group :\n" , "Create New Channel" ,JOptionPane.QUESTION_MESSAGE));
        PrivacyMode privacyMode = PrivacyMode.PUBLIC;
        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,"Please Choose The PrivacyMode :\n" +
                        "1. Public\n2. Private\n" ,
                "Create New Group" ,JOptionPane.QUESTION_MESSAGE))){
            case 1:
                privacyMode = PrivacyMode.PUBLIC;
                break;
            case 2:
                privacyMode = PrivacyMode.PRIVATE;
                break;

        }
        GroupChat newGroupChat = new GroupChat(newGroupName ,newGroupID ,ageRestriction ,privacyMode,
                currentUser);
        currentUser.getGroupChatArrayList().add(newGroupChat);
        newGroupChat.getAdminsUserArraylist().add(currentUser);
        DataBase.getGroupChatDataBaseArrayList().add(newGroupChat);


        JOptionPane.showMessageDialog(null ,"Group Was successfully" +
                        " Created!","Create New Group" ,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void createNewChannel(User currentUser){
        String newChannelName = JOptionPane.showInputDialog(null ,"Please Enter New Channel's Name: " ,
                "Create New Channel" ,JOptionPane.QUESTION_MESSAGE);
        String newChannelID = JOptionPane.showInputDialog(null ,"Please Enter" +
                "New Channel's ID:\n" , "Create New Channel" ,JOptionPane.QUESTION_MESSAGE);
        Channel newChannel = new Channel(newChannelName ,newChannelID ,currentUser);
        currentUser.getChannelArrayList().add(newChannel);
        DataBase.getChannelDataBaseArrayList().add(newChannel);

        JOptionPane.showMessageDialog(null ,"Channel Was successfully" +
                "Created!","Create New Channel" ,JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showsAllPrivatesChat(User currentUser){

        StringBuilder allPrivatesChat = new StringBuilder();
        int counter = 1;
        for (PrivateChat privateChat : currentUser.getPrivateChatArrayList()){
            allPrivatesChat.append(counter).append(" . ").append(privateChat.getReceiver().getUserID())
                    .append("\n");
            counter++;
        }
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null ,"This Are All" +
                        " Of Your Privates Chat.\n" + allPrivatesChat +
                        "Select The Number To Show More Or Enter 0 To Return To " +
                        "Previous Page.",
                "showsAllPrivatesChat\n"  ,JOptionPane.QUESTION_MESSAGE));
        return chosenOpt;

    }
    public static int showsAllGroupsChat(User currentUser){


        StringBuilder allGroupsChat = new StringBuilder();
        int counter = 1;
        for (GroupChat groupChat : currentUser.getGroupChatArrayList()){
            allGroupsChat.append(counter).append(" . ").append(groupChat.getGroupID())
                    .append("\n");
            counter++;
        }
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null ,"These Are All" +
                        " Of Your Groups Chat.\n" + allGroupsChat + "Select The Number To Show More Or Enter 0" +
                        " To Go Back To The Previous Page" ,
                "Show All GroupsChat" ,JOptionPane.QUESTION_MESSAGE));
        return chosenOpt;

    }

    public static int showsAllChannels(User currentUser){


        StringBuilder allChannels = new StringBuilder();
        int counter = 1;
        for (Channel channel : currentUser.getChannelArrayList()){
            allChannels.append(counter).append(" . ").append(channel.getChannelID())
                    .append("\n");
            counter++;
        }
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null ,"These Are All" +
                        "Of Your Groups Chat :\n" + allChannels +"Select The Number To Show More .Or Enter 0 " +
                        " To Back To The Previous Page",
                "Show All Channels" ,JOptionPane.QUESTION_MESSAGE));
        return chosenOpt;

    }


    public static PrivateChat goToAPrivateChat(int index, User currentUser) {

        for (PrivateChat privateChat : currentUser.getPrivateChatArrayList()){
            if(privateChat.equals(currentUser.getPrivateChatArrayList().get(index)))
                return privateChat;
        }

        return null;
    }

    public static GroupChat goToAGroupChat(int index, User currentUser) {

        for (GroupChat groupChat : currentUser.getGroupChatArrayList()){
            if(groupChat.equals(currentUser.getGroupChatArrayList().get(index)))
                return groupChat;
        }
        return null;

    }
    public static Channel goToAChannel(int index, User currentUser) {

        for (Channel channel : currentUser.getChannelArrayList()){
            if(channel.equals(currentUser.getChannelArrayList().get(index)))
                return channel;
        }
        return null;

    }

    public static void seeMessagesPageOnGroupChat(GroupChat chosenGroup){
        if(!chosenGroup.getMessageArrayList().isEmpty()){
            StringBuilder allChats = new StringBuilder();
            StringBuilder allReply = new StringBuilder();
            StringBuilder allCaption = new StringBuilder();

            for (Message message : chosenGroup.getMessageArrayList()){

                if(!message.getReplyArrayList().isEmpty()){
                    for (Message reply : message.getReplyArrayList()){
                        allReply.append(reply.getMessage()+ "\n");
                    }

                }
                else {
                    allReply.append(" ");
                }
                if(!message.getCaptionArrayList().isEmpty()){
                    for (Message caption : message.getCaptionArrayList()){
                        allReply.append(caption.getMessage()+ "\n");
                    }

                }
                else {
                    allCaption.append(" ");
                }
                allChats.append(message.getSender().getUserID()).append(" : ").append(message.getMessage()).
                        append("\n").append("Like : " + message.getLikerUserArrayList().size() + "\n")
                        .append("Reply On This Message : " + allReply + "\n").append("Caption On This Message :" +
                                allCaption + "\n");
            }
            JOptionPane.showMessageDialog(null , allChats,
                    "All Chats", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null , "No Messages Yet",
                    "All Chats", JOptionPane.INFORMATION_MESSAGE);
        }



    }
    public static void seeMessagesPageOnChannel(Channel channel){
        StringBuilder allChats = new StringBuilder();
        for (Message message : channel.getMessageArrayList()){
            allChats.append(message.getSender().getUserID()).append(" : ").append(message.getMessage()).
                    append("\n").append("Like : " + message.getLikerUserArrayList().size() + "\n");

        }
        JOptionPane.showMessageDialog(null , allChats,
                "All Chats", JOptionPane.INFORMATION_MESSAGE);


    }
    public static void seeAdmins(GroupChat chosenGroup){
        StringBuilder allAdmins = new StringBuilder();
        int counter = 1;
        for (User user : chosenGroup.getAdminsUserArraylist()){
            allAdmins.append("Admin").append(counter).append(" : ").append(user.getFullName())
                    .append("\n");
            counter++;
        }
        JOptionPane.showMessageDialog(null ,allAdmins ,"showsAllAdmins" ,
                JOptionPane.INFORMATION_MESSAGE);
    }
    public static void seeGroupBio(GroupChat chosenGroup){
        JOptionPane.showMessageDialog(null ,chosenGroup.getBio() ,
                "See Group Bio", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void seeChannelBio(Channel chosenChannel){
        JOptionPane.showMessageDialog(null ,chosenChannel.getBio() ,
                "See Channel Bio", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void changeGroupBio(GroupChat chosenGroup, User currentUser){

            if(GroupChat.isInAdminArrayList(chosenGroup ,currentUser)){
                String newGroupBio = JOptionPane.showInputDialog(null , "Please Enter The " +
                        "New Bio :","Change Bio Page" ,JOptionPane.QUESTION_MESSAGE);
                chosenGroup.setBio(newGroupBio);
                JOptionPane.showMessageDialog(null , "Bio" +
                        "Successfully Changed","Change Bio Page" ,JOptionPane.INFORMATION_MESSAGE);

            }
            else {
                JOptionPane.showMessageDialog(null ,"You Can Not" +
                        "Change The Bio, Because You Are Not One Of The Admins","Change Bio Page",
                        JOptionPane.INFORMATION_MESSAGE);
            }

    }
    public static void changeChannelBio(Channel chosenChannel, User currentUser){

        if(Channel.isInAdminArrayList(chosenChannel ,currentUser)){
            String newChannelBio = JOptionPane.showInputDialog(null , "Please Enter The " +
                    "New Bio :","Change Bio Page" ,JOptionPane.QUESTION_MESSAGE);
            chosenChannel.setBio(newChannelBio);
            JOptionPane.showMessageDialog(null , "Bio" +
                    " Successfully Changed","Change Bio Page" ,JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null ,"You Can Not" +
                            "Change The Bio, Because You Are Not One Of The Admins","Change Bio Page",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void seeGroupID(GroupChat chosenGroup){
        JOptionPane.showMessageDialog(null ,chosenGroup.getGroupID() ,
                "See Group-ID" ,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void seeChannelID(Channel chosenChannel){
        JOptionPane.showMessageDialog(null ,chosenChannel.getChannelID() ,
                "See Channel-ID" ,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void exitGroupChat(GroupChat chosenGroup ,User currentUser){
        chosenGroup.getMembersUserArraylist().remove(currentUser);
        currentUser.getGroupChatArrayList().remove(chosenGroup);
        JOptionPane.showMessageDialog(null, "You Exit The Group Successfully");

    }

    public static void exitChannel(Channel chosenChannel ,User currentUser){
        chosenChannel.getViewersUserArraylist().remove(currentUser);
        currentUser.getChannelArrayList().remove(chosenChannel);

    }


    public static void deleteEntireGroup(GroupChat chosenGroup ,User currentUser ){

        if(GroupChat.isInAdminArrayList(chosenGroup ,currentUser)){
            String groupID = chosenGroup.getGroupID();
            DataBase.getGroupChatDataBaseArrayList().remove(chosenGroup);
            currentUser.getGroupChatArrayList().remove(chosenGroup);
            JOptionPane.showMessageDialog(null ,groupID +
                    " Group was deleted Successfully" ,"Delete Group" ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null , "You" +
                    "Cant Not Delete The Group!\nBecause You are Not Admin"
                    ,"Delete Group" ,JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void deleteEntireChannel(Channel chosenChannel,User currentUser ){

        if(Channel.isInAdminArrayList(chosenChannel,currentUser)){
            String channelID = chosenChannel.getChannelID();
            DataBase.getChannelDataBaseArrayList().remove(chosenChannel);
            currentUser.getChannelArrayList().remove(chosenChannel);
            JOptionPane.showMessageDialog(null ,channelID +
                    " Group was deleted Successfully" ,"Delete Channel" ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null , "You" +
                            "Cant Not Delete The Channel!\nBecause You are Not Admin"
                    ,"Delete Channel" ,JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void sendMessageToAGroup(GroupChat groupChat,User currentUser){
        String text = JOptionPane.showInputDialog(null ,"Enter Your " +
                "Message", "Message Page", JOptionPane.QUESTION_MESSAGE);
        Message newMessage = new Message(text, currentUser);
        groupChat.getMessageArrayList().add(newMessage);
        JOptionPane.showMessageDialog(null, "Your Message Was Successfully " +
                "Sent");
    }

    public static void deleteMessageFromAPrivateChat(PrivateChat privateChat, String messageToRemove){
        boolean isRemove = false;
        for(Message message : privateChat.getMessageArrayList()){
            if (message.getMessage().equals(messageToRemove)){
                privateChat.getMessageArrayList().remove(message);
                JOptionPane.showMessageDialog(null ,"Your Message Was" +
                                "Successfully Deleted" , "Delete Message",
                        JOptionPane.INFORMATION_MESSAGE);
                isRemove = true;
                break;

            }
        }
        if(!isRemove) {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Delete Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void deleteMessageFromAGroupChat(GroupChat groupChat, String messageToRemove,User currentUser){
        if(isMessageInChatRoom(groupChat, messageToRemove) != null){
            Message messageToSearch = isMessageInChatRoom(groupChat, messageToRemove);
            if(groupChat.getAdminsUserArraylist().contains(currentUser) ||
                    groupChat.getMembersUserArraylist().contains(currentUser)){
                groupChat.getMessageArrayList().remove(messageToSearch);
                JOptionPane.showMessageDialog(null ,"Your Message Was" +
                                "Successfully Deleted" , "Delete Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }
            else {
                JOptionPane.showMessageDialog(null ,"You Can Not" +
                                "Delete The Message ,Because You Are Not The Sender Or" +
                                "Admin!", "Delete Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }

        }

       else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Delete Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void deleteMessageFromAChannel(Channel channel, String messageToRemove, User currentUser){
        if(isInMessageChannel(channel, messageToRemove) != null){
            Message messageToSearch = isInMessageChannel(channel, messageToRemove);
            if(channel.getAdminsUserArraylist().contains(currentUser)){
                channel.getMessageArrayList().remove(messageToSearch);
                JOptionPane.showMessageDialog(null ,"Your Message Was" +
                                "Successfully Deleted" , "Delete Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }
            else {
                JOptionPane.showMessageDialog(null ,"You Can Not" +
                                "Delete The Message ,Because You Are Not One Of The Admins!",
                        "Delete Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }

        else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Delete Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void joinTheGroup(User user, GroupChat groupChat){
        if(user.getGroupChatArrayList().contains(groupChat)){

                if(user.getAge() > groupChat.getAgeRestriction()) {
                    if (groupChat.getPrivacyMode().equals(PrivacyMode.PUBLIC)) {
                        groupChat.getMembersUserArraylist().add(user);
                        user.getGroupChatArrayList().add(groupChat);
                        JOptionPane.showMessageDialog(null, "You Joined To" +
                                " Group Successfully", "Join The Group", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        groupChat.getRequestedUsersArraylist().add(user);
                        JOptionPane.showMessageDialog(null, "Your Request Will" +
                                "Be Checked By Admins", "Join The Group", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "You Are Not Able To Join The Group, " +
                                    "Because You Don't Have Legal Age!" ,"Join The Group",
                            JOptionPane.INFORMATION_MESSAGE);
                }

        }
        else {
            JOptionPane.showMessageDialog(null, "You Joined This" +
                    " Group Before", "Join The Group" , JOptionPane.INFORMATION_MESSAGE);
        }



    }
    public static void joinTheChannel(User user, Channel channel){

        if(user.getChannelArrayList().contains(channel)){
            JOptionPane.showMessageDialog(null, "You Joined" +
                    " This Channel Before", "Join The Group" , JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            channel.getViewersUserArraylist().add(user);
            user.getChannelArrayList().add(channel);
            JOptionPane.showMessageDialog(null, "You Joined To " +
                    "Channel Successfully", "Join The Group" , JOptionPane.INFORMATION_MESSAGE);
        }


    }
    public static void seeMessagesPageOnPrivateChat(PrivateChat privateChat, User currentUser){
        StringBuilder allChats = new StringBuilder();
        int like = 0;
        if(privateChat.getMessageArrayList().isEmpty()){
            JOptionPane.showMessageDialog(null , "No Messages Yet",
                    "All Chats", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            for (Message message : privateChat.getMessageArrayList()) {

                StringBuilder allReply = new StringBuilder();
                if (!message.getLikerUserArrayList().isEmpty()) {
                    like += 1;
                }
                if(!message.getReplyArrayList().isEmpty()){
                    for (Message reply : message.getReplyArrayList())
                    allReply.append(reply.getMessage()+ "\n");
                }
                else {
                    allReply.append(" ");
                }
                allChats.append(message.getSender().getUserID()).append(" : ").append(message.getMessage()).append("\n")
                        .append("Like : " + like + "\n" + "Reply On This Message : " + allReply
                                + "\n");

            }

            JOptionPane.showMessageDialog(null, allChats,
                    "All Chats", JOptionPane.INFORMATION_MESSAGE);


        }


    }
    public static void deleteChat(PrivateChat privateChat){
        privateChat.getReceiver().getPrivateChatArrayList().clear();
        privateChat.getSender().getPrivateChatArrayList().clear();
        JOptionPane.showMessageDialog(null, "Your Chat Was Successfully" +
                "Deleted");

    }
    public static void sendMessageToAPrivateChat(PrivateChat privateChat){
        Message newMessage = new Message(JOptionPane.showInputDialog(null, "Enter Your " +
                "Message","Send A Message", JOptionPane.QUESTION_MESSAGE ),privateChat.getSender());
        privateChat.getMessageArrayList().add(newMessage);
        JOptionPane.showMessageDialog(null, "Your Message Was Successfully " +
                "Sent");

    }
    public static Message isInMessage(PrivateChat privateChat, String message){
        for (Message messageOrationOnThat : privateChat.getMessageArrayList()){
            if(messageOrationOnThat.getMessage().equals(message)){
                return messageOrationOnThat;
            }
        }
        return null;
    }
    public static Boolean likeTheMessageInPrivateChat(PrivateChat privateChat, String messageToLike, User currentUser){

            if(isInMessage(privateChat, messageToLike) != null){
                Message message = isInMessage(privateChat, messageToLike);

                if(!message.getLikerUserArrayList().contains(privateChat.getReceiver()) &&
                        !message.getLikerUserArrayList().contains(privateChat.getSender())){
                    message.getLikerUserArrayList().add(currentUser);
                    JOptionPane.showMessageDialog(null, "You Liked This Massage!",
                            "Like Message", JOptionPane.INFORMATION_MESSAGE);

                    return true;

                } else {
                    JOptionPane.showMessageDialog(null, "You Liked This " +
                            "Message Before", "Like A Message", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        else {
                JOptionPane.showMessageDialog(null, "No Such Message Found!",
                        "Like Message", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

    }
    public static Message isMessageInChatRoom(ChatRoom chatRoom, String messageToDoOperation){
        for (Message message : chatRoom.getMessageArrayList()){
            if(message.getMessage().equals(messageToDoOperation)){
                return message;
            }
        }
        return null;
    }
    public static Message isInMessageChannel(Channel channel, String messageToDoOperation){
        for (Message message : channel.getMessageArrayList()){
            if(message.getMessage().equals(messageToDoOperation)){
                return message;
            }
        }
        return null;
    }
    public static void likeTheMessageGroup(GroupChat groupChat, String messageToLike, User currentUser){
        if(isMessageInChatRoom(groupChat, messageToLike) != null){
            Message message = isMessageInChatRoom(groupChat, messageToLike);
                if(!message.getLikerUserArrayList().contains(currentUser)){
                    message.getLikerUserArrayList().add(currentUser);
                    JOptionPane.showMessageDialog(null, "You Liked This Massage!",
                            "Like Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "You Liked This " +
                            "Message Before", "Like A Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Message Found!",
                        "Like Message", JOptionPane.INFORMATION_MESSAGE);
            }


    }
    public static void likeTheMessageChannel(Channel channel, String messageToLike, User currentUser){
        if(isInMessageChannel(channel, messageToLike) != null){
            Message message = isInMessageChannel(channel, messageToLike);
            if(!message.getLikerUserArrayList().contains(currentUser)){
                message.getLikerUserArrayList().add(currentUser);
                JOptionPane.showMessageDialog(null, "You Liked This Massage!",
                        "Like Message", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "You Liked This " +
                        "Message Before", "Like A Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Like Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void sendReply(PrivateChat privateChat, String message, User currentUser){
        if(isInMessage(privateChat, message) != null){
            Message chosenMessage = isInMessage(privateChat, message);
                Message reply = new Message(JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Reply :\n", "Send Reply", JOptionPane.QUESTION_MESSAGE), currentUser);

                chosenMessage.getReplyArrayList().add(reply);
                JOptionPane.showMessageDialog(null, "Your Reply Was Successfully " +
                        " Added","Send Reply",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Message Found!",
                        "Send Reply", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    public static void sendReplyInGroup(GroupChat groupChat, String message, User currentUser){
        if(isMessageInChatRoom(groupChat, message) != null){
            Message chosenMessage = isMessageInChatRoom(groupChat, message);
            Message reply = new Message(JOptionPane.showInputDialog(null, "Please Enter" +
                    " Your Reply :\n", "Send Reply", JOptionPane.QUESTION_MESSAGE), currentUser);

            chosenMessage.getReplyArrayList().add(reply);
            JOptionPane.showMessageDialog(null, "Your Reply Was Successfully" +
                    " Added","Send Reply",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Send Reply", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void broadCastAMessageInAChannel(Channel channel, User currentUser){
        if (Channel.isInAdminArrayList(channel, currentUser)){
            String text = JOptionPane.showInputDialog(null ,"Enter Your " +
                    "Message", "Message Page", JOptionPane.QUESTION_MESSAGE);
            Message newMessage = new Message(text, currentUser);
            channel.getMessageArrayList().add(newMessage);
            JOptionPane.showMessageDialog(null, "Your Message Was " +
                    "BroadCasted", "Message Page", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "You Can Not Send BroadCast" +
                    "A Message In This Channel ,Because You Are Not One Of Tha Admin!","Message Page",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void addNewAdminToChannel(Channel channel, User currentUser){
        if(Channel.isInAdminArrayList(channel, currentUser)){
            String phoneNumberOrUserID = JOptionPane.showInputDialog(null, "Who Do You Want To Add To " +
                    "Admins?\nEnter His/Her PhoneNumber/UserID\n", "Add New Admin",
                    JOptionPane.QUESTION_MESSAGE);
            if (DataBase.isAvailableUserID(phoneNumberOrUserID)) {
                if(Channel.isInAdminArrayList(channel, DataBase.getUserFromUserID(phoneNumberOrUserID))){
                    channel.getAdminsUserArraylist().remove(DataBase.getUserFromUserID(phoneNumberOrUserID));
                }
                channel.getAdminsUserArraylist().add(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getChannelArrayList().add(channel);
                JOptionPane.showMessageDialog(null ,   phoneNumberOrUserID
                        + " Was Successfully Added");

            }
            else if(DataBase.isAvailableUserPhoneNumber(phoneNumberOrUserID)){
                if(Channel.isInAdminArrayList(channel, DataBase.getUserFromPhoneNumber(phoneNumberOrUserID))){
                    channel.getAdminsUserArraylist().remove(DataBase.getUserFromPhoneNumber(phoneNumberOrUserID));
                }
                channel.getAdminsUserArraylist().add(DataBase.getUserFromPhoneNumber(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getChannelArrayList().add(channel);
                JOptionPane.showMessageDialog(null ,
                        DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getUserID()
                                + " Was Successfully Added");
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Account " +
                        "Found!" , "Add New Admin To Channel" ,JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You Can Not Add Admins To This Channel" +
                            ",Because You Are Not One Of Tha Admins!","Add New Admin To Channel",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public static void addNewViewersToChannel(Channel channel, User currentUser){
        if(Channel.isInAdminArrayList(channel, currentUser)){
            String phoneNumberOrUserID = JOptionPane.showInputDialog(null, "Who Do You Want To Add To " +
                            "Viewers?\nEnter His/Her PhoneNumber/UserID\n", "Add New Admin",
                    JOptionPane.QUESTION_MESSAGE);
            if (DataBase.isAvailableUserID(phoneNumberOrUserID)) {
                channel.getViewersUserArraylist().add(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getChannelArrayList().add(channel);
                JOptionPane.showMessageDialog(null ,   phoneNumberOrUserID
                        + " Was Successfully Added");
            }
            else if(DataBase.isAvailableUserPhoneNumber(phoneNumberOrUserID)){
                channel.getViewersUserArraylist().add(DataBase.getUserFromPhoneNumber(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getChannelArrayList().add(channel);
                JOptionPane.showMessageDialog(null ,
                        DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getUserID()
                                + " Was Successfully Added");
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Account " +
                        "Found!" , "Add New Viewers To Channel" ,JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You Can Not Add Viewers To This Channel" +
                            ",Because You Are Not One Of Tha Admins!","Add New Viewers To Channel",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void addNewMembersToGroup(GroupChat groupChat, User currentUser){
        if(groupChat.isInAdminArrayList(groupChat, currentUser)){
            String phoneNumberOrUserID = JOptionPane.showInputDialog(null, "Who Do You Want To Add To " +
                            "Members?\nEnter His/Her PhoneNumber/UserID\n", "Add New Member",
                    JOptionPane.QUESTION_MESSAGE);
            if (DataBase.isAvailableUserID(phoneNumberOrUserID)) {
                groupChat.getMembersUserArraylist().add(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getGroupChatArrayList().add(groupChat);
                JOptionPane.showMessageDialog(null ,   phoneNumberOrUserID
                        + " Was Successfully Added");
            }
            else if(DataBase.isAvailableUserPhoneNumber(phoneNumberOrUserID)){
                groupChat.getMembersUserArraylist().add(DataBase.getUserFromPhoneNumber(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getGroupChatArrayList().add(groupChat);
                JOptionPane.showMessageDialog(null ,
                        DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getUserID()
                        + " Was Successfully Added");
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Account " +
                        "Found!" , "Add New Member To Group" ,JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You Can Not Add Member To This Group" +
                            ",Because You Are Not One Of Tha Admins!","Add New Member To Channel",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void addNewAdminToGroup(GroupChat groupChat, User currentUser){
        if(groupChat.isInAdminArrayList(groupChat, currentUser)){
            String phoneNumberOrUserID = JOptionPane.showInputDialog(null, "Who Do You Want To Add To " +
                            "Admins?\nEnter His/Her PhoneNumber/UserID\n", "Add New Admin",
                    JOptionPane.QUESTION_MESSAGE);
            if (groupChat.isInMembersArrayList(groupChat, DataBase.getUserFromUserID(phoneNumberOrUserID))) {

                groupChat.getMembersUserArraylist().remove(DataBase.getUserFromUserID(phoneNumberOrUserID));
                groupChat.getAdminsUserArraylist().add(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getGroupChatArrayList().add(groupChat);
                JOptionPane.showMessageDialog(null ,   phoneNumberOrUserID
                        + " Was Successfully Added");

            }
            else if(groupChat.isInMembersArrayList(groupChat, DataBase.getUserFromPhoneNumber(phoneNumberOrUserID))){

                groupChat.getMembersUserArraylist().remove(DataBase.getUserFromUserID(phoneNumberOrUserID));
                groupChat.getAdminsUserArraylist().add(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getGroupChatArrayList().add(groupChat);
                JOptionPane.showMessageDialog(null ,
                        DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getUserID()
                                + " Was Successfully Added");
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Account Found In" +
                        " This Group", "Add New Admin To Channel" ,JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You Can Not Add Admins To This Channel" +
                            ",Because You Are Not One Of Tha Admins!","Add New Admin To Channel",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public static void kickSomeOneFromTheGroup(GroupChat groupChat, User currentUser){

        if(groupChat.isInAdminArrayList(groupChat, currentUser)){
            String phoneNumberOrUserID = JOptionPane.showInputDialog(null, "Who Do You Want To Kick From " +
                            "The Group?\nEnter His/Her PhoneNumber/UserID\n", "Kick SomeOne From The Group",
                    JOptionPane.QUESTION_MESSAGE);
            if (groupChat.isInMembersArrayList(groupChat, DataBase.getUserFromUserID(phoneNumberOrUserID))
            || groupChat.isInAdminArrayList(groupChat, DataBase.getUserFromUserID(phoneNumberOrUserID)) ) {
                groupChat.getAdminsUserArraylist().remove(DataBase.getUserFromUserID(phoneNumberOrUserID));
                DataBase.getUserFromUserID(phoneNumberOrUserID).getGroupChatArrayList().remove(groupChat);
                JOptionPane.showMessageDialog(null, phoneNumberOrUserID + " Was Kicked From" +
                        " The Group");

            }
            else if(groupChat.isInMembersArrayList(groupChat, DataBase.getUserFromPhoneNumber(phoneNumberOrUserID))
                    || groupChat.isInAdminArrayList(groupChat, DataBase.getUserFromUserID(phoneNumberOrUserID))){
                groupChat.getAdminsUserArraylist().remove(DataBase.getUserFromPhoneNumber(phoneNumberOrUserID));
                DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getGroupChatArrayList().remove(groupChat);
                JOptionPane.showMessageDialog(null,
                        DataBase.getUserFromPhoneNumber(phoneNumberOrUserID).getUserID() + " Was Kicked From" +
                        " The Group");
            }
            else {
                JOptionPane.showMessageDialog(null, "No Such Account Found In" +
                        "This Group", "Kick SomeOne From The Group" ,JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Kick SomeOne From The Group" +
                            ",Because You Are Not One Of Tha Admins!","Kick SomeOne From The Group",
                    JOptionPane.INFORMATION_MESSAGE);

        }

    }
    public static void seeMembersInGroup(GroupChat chosenGroup){
        StringBuilder allMembers = new StringBuilder();
        int counter = 1;
        for (User user : chosenGroup.getMembersUserArraylist()){
            allMembers.append("Member").append(counter).append(" : ").append(user.getFullName())
                    .append("\n");
            counter++;
        }
        JOptionPane.showMessageDialog(null ,allMembers ,"show All Members" ,
                JOptionPane.INFORMATION_MESSAGE);

    }
    public static void setCaptionForAMessage(GroupChat groupChat, String message, User currentUser){
        if(isMessageInChatRoom(groupChat, message) != null){
            Message chosenMessage = isMessageInChatRoom(groupChat, message);
            Message caption = new Message(JOptionPane.showInputDialog(null, "Please Enter" +
                    " Your Caption :\n", "Set Caption", JOptionPane.QUESTION_MESSAGE), currentUser);

            chosenMessage.getCaptionArrayList().add(caption);
            JOptionPane.showMessageDialog(null, "Your Caption Was Successfully" +
                    " Added","Set Caption",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Set Caption", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void forwardAMessage(ChatRoom  chatRoom, String message, User currentUser){
        if(isMessageInChatRoom(chatRoom, message) != null){
            Message chosenMessage = isMessageInChatRoom(chatRoom, message);
            String messageSendToThisIDChatRoom = JOptionPane.showInputDialog(null, "Where Do" +
                    "You Want To Forward This ChatRoom To? Enter Its ID :\n","Forward A Message", JOptionPane.QUESTION_MESSAGE);
            ChatRoom messageSendToThisChatRoom = ChatRoom.getChatRoomFromItsID(messageSendToThisIDChatRoom, currentUser);
            if(currentUser.getPrivateChatArrayList().contains(messageSendToThisChatRoom)){
                messageSendToThisChatRoom.getMessageArrayList().add(chosenMessage);
            }
            else if (currentUser.getGroupChatArrayList().contains(messageSendToThisChatRoom)){
                messageSendToThisChatRoom.getMessageArrayList().add(chosenMessage);
            }
            JOptionPane.showMessageDialog(null, "Message Was Forwarded " +
                    "Successfully", "Forward A Message", JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Message Found!",
                    "Forward A Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void acceptOrDeclineRequests(GroupChat groupChat, User currentUser){
        if(groupChat.getAdminsUserArraylist().contains(currentUser)){
            for (User requestedUsers : groupChat.getRequestedUsersArraylist()){
                switch (Integer.parseInt(JOptionPane.showInputDialog(null, "You Want " +
                        "To Accept " + requestedUsers.getUserID() +" ?\n1. Yes\n2. No"))){
                    case 1:
                        groupChat.getRequestedUsersArraylist().remove(requestedUsers);
                        groupChat.getMembersUserArraylist().add(requestedUsers);
                        requestedUsers.getGroupChatArrayList().add(groupChat);
                        JOptionPane.showMessageDialog(null, "This User Was Successfully" +
                                " Added", "Accept Or Decline Requests",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        groupChat.getRequestedUsersArraylist().remove(requestedUsers);
                        JOptionPane.showMessageDialog(null, "You Decline This User!",
                                "Accept Or Decline Requests",JOptionPane.INFORMATION_MESSAGE);
                        break;

                }
            }

        }
        else {
            JOptionPane.showMessageDialog(null, "You Cant Accept Or Decline Requests," +
                    " Because You Are Not Amin", "Accept Or Decline Requests",JOptionPane.INFORMATION_MESSAGE);
        }
    }



}
