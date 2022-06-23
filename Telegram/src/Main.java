import com.sun.prism.impl.BaseMesh;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        DataBase dataBase = new DataBase();

        User user1 = new User("Reihaneh Akbarifard", LocalDate.of(2002,10,24),
                20,"09305480649", Gender.FEMALE, "rei", "123");
        DataBase.getUsersArrayList().add(user1);

        User user2 = new User("Sogol Bazrafshan", LocalDate.of(2002,02,02),
                20,"09172036281", Gender.FEMALE, "sogoli", "123");
        DataBase.getUsersArrayList().add(user2);

        User user3 = new User("Milad Noraiee", LocalDate.of(2002,01,21),
                20,"09377370945", Gender.Male, "miloo", "123");
        DataBase.getUsersArrayList().add(user3);

        GroupChat groupChat1 = new GroupChat("ComputerEngineering", "CSE" ,0 ,
                PrivacyMode.PUBLIC, user1);
        DataBase.getGroupChatDataBaseArrayList().add(groupChat1);
        user1.getGroupChatArrayList().add(groupChat1);

        Channel channel = new Channel("FactsChannel", "facts", user1);
        DataBase.getChannelDataBaseArrayList().add(channel);
        user1.getChannelArrayList().add(channel);

        PrivateChat privateChat1 = new PrivateChat(user1, user2);
        user1.getPrivateChatArrayList().add(privateChat1);
        user2.getPrivateChatArrayList().add(privateChat1);







        JOptionPane.showMessageDialog(null, "Welcome To Telegram Application",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);

        boolean isAppRunning = true;
        while (isAppRunning){
            int chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "What Do You Want To Do?\n 1. Log-In\n 2. Sign-Up\n 3. Exit", "Log-In/Sign-Up Page"
                    , JOptionPane.QUESTION_MESSAGE));
            switch (chosenOption) {
                case 1:
                    User currentUser = User.logIn();
                    if(!currentUser.equals(null)){
                        boolean isInHomePage = true;
                        while (isInHomePage){
                            chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "What Do You Want To Do?\n1. Chat-Page\n2. Profile-Page\n" +
                                            "3. Search-Page\n4. Create A New Group Or Channel\n" +
                                            "5. Delete-Account\n6. Log-Out ",
                                    "Log-In page", JOptionPane.QUESTION_MESSAGE));
                            switch (chosenOption){
                                case 1:
                                    boolean isChatPage = true;
                                    while (isChatPage){
                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                "What Do You Want To Do?\n1. Show All Privates Chat\n" +
                                                        "2. Show All Groups Chat\n3. Show All Channel Chat\n4. Back",
                                                "Chat-Page" , JOptionPane.QUESTION_MESSAGE))) {
                                            case 1:
                                                boolean isInAllPrivatesChat = true;
                                                while (isInAllPrivatesChat){
                                                    chosenOption = User.showsAllPrivatesChat(currentUser);
                                                    switch (chosenOption){
                                                        case 0:
                                                            isInAllPrivatesChat = false;
                                                            break;
                                                        default: {
                                                            PrivateChat chosenPrivateChat = User.goToAPrivateChat(chosenOption - 1, currentUser);
                                                            boolean isInPrivatesChat = true;
                                                            while (isInPrivatesChat){
                                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                                        "What Do You Want To Do?\n1. See Messages Page\n2 .Send A " +
                                                                                "Message To This Private Chat\n3. Delete " +
                                                                                "Chat\n4. Back" ,
                                                                        chosenPrivateChat.getReceiver().getUserID() + " PrivateChat-Page " ,
                                                                        JOptionPane.QUESTION_MESSAGE))){
                                                                    case 1:
                                                                        User.seeMessagesPageOnPrivateChat(chosenPrivateChat, currentUser);
                                                                        if (!chosenPrivateChat.getMessageArrayList().isEmpty()){
                                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null ,"Do You" +
                                                                                            "Want To Select A Message To Do Some Operation On That?" +
                                                                                            "\n1. Yes\n2. No,Back To Messages Page", "Do Some Operation On Message"
                                                                                    ,JOptionPane.QUESTION_MESSAGE))){
                                                                                case 1:
                                                                                        String operationOnMessage = JOptionPane.showInputDialog(null, "Enter That " +
                                                                                                "Message :\n", "Do Some Operation On Message",JOptionPane.QUESTION_MESSAGE);
                                                                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null, "< " + operationOnMessage + " >" +
                                                                                                "\nWhat Do You Want To Do With This Message?\n1. Delete The Message\n2. Like The Message\n" +
                                                                                                "3. Forward The Message\n4. Reply The Message\n5. Back"))){
                                                                                            case 1:
                                                                                                User.deleteMessageFromAPrivateChat(chosenPrivateChat, operationOnMessage);
                                                                                                break;
                                                                                            case 2:
                                                                                                User.likeTheMessageInPrivateChat(chosenPrivateChat, operationOnMessage, currentUser);
                                                                                                break;
                                                                                            case 3:
                                                                                                User.forwardAMessage(chosenPrivateChat, operationOnMessage, currentUser);
                                                                                                break;
                                                                                            case 4:
                                                                                                User.sendReply(chosenPrivateChat, operationOnMessage, currentUser);
                                                                                                break;
                                                                                            case 5:
                                                                                               break;
                                                                                        }

                                                                                    break;
                                                                                case 2:
                                                                                    break;
                                                                            }
                                                                        }
                                                                        else {
                                                                            isInPrivatesChat = false;
                                                                        }
                                                                        break;
                                                                    case 2:
                                                                        User.sendMessageToAPrivateChat(chosenPrivateChat);
                                                                        break;
                                                                    case 3:
                                                                        User.deleteChat(chosenPrivateChat);
                                                                        isInPrivatesChat = false;
                                                                        break;
                                                                    case 4:
                                                                        isInPrivatesChat = false;
                                                                        break;

                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                                break;
                                            case 2:
                                                boolean isInAllGroupChat = true;
                                                while (isInAllGroupChat) {
                                                    chosenOption = User.showsAllGroupsChat(currentUser);
                                                    switch (chosenOption) {
                                                        case 0:
                                                            isInAllGroupChat = false;
                                                            break;
                                                        default:
                                                            boolean isInGroupChat = true;
                                                            while (isInGroupChat) {
                                                                GroupChat chosenGroup = User.goToAGroupChat(chosenOption - 1, currentUser);
                                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                        "What Do You Want To Do?\n1. See Messages Page\n" +
                                                                                "2. Send Message To The Group\n3. Add New Admin\n" +
                                                                                "4. Add New Member\n5. Kick SomeOne From The Group\n6. See Admins\n7. See Members\n" +
                                                                                "8. See Bio\n9. See GroupID\n" +
                                                                                "10. Accept Or Decline Requests\n11. Exit Group\n12. Delete Entire Group\n" +
                                                                                "13. Back", chosenGroup.getName() +
                                                                                "Group-Page ", JOptionPane.QUESTION_MESSAGE))) {
                                                                    case 1:
                                                                        User.seeMessagesPageOnGroupChat(chosenGroup);
                                                                        if (!chosenGroup.getMessageArrayList().isEmpty()) {
                                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Do You" +
                                                                                            "Want To Select A Message To Do Some Operation On That?" +
                                                                                            "\n1. Yes\n2. No,Back To Messages Page", "Do Some Operation On Message"
                                                                                    , JOptionPane.QUESTION_MESSAGE))) {
                                                                                case 1:
                                                                                    String operationOnThatMessage = JOptionPane.showInputDialog(null, "Enter That " +
                                                                                            "Message :\n", "Do Some Operation On Message", JOptionPane.QUESTION_MESSAGE);
                                                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "< " + operationOnThatMessage + " >" +
                                                                                            "\nWhat Do You Want To Do With This Message?\n1. Delete The Message\n2. Like The Message\n" +
                                                                                            "3. Forward The Message\n4. Reply The Message\n5. Set Caption For The Message" +
                                                                                            "\n6. Back"))) {
                                                                                        case 1:
                                                                                            User.deleteMessageFromAGroupChat(chosenGroup, operationOnThatMessage, currentUser);
                                                                                            break;
                                                                                        case 2:
                                                                                            User.likeTheMessageGroup(chosenGroup, operationOnThatMessage, currentUser);
                                                                                            break;
                                                                                        case 3:
                                                                                            User.forwardAMessage(chosenGroup, operationOnThatMessage, currentUser);
                                                                                            break;
                                                                                        case 4:
                                                                                            User.sendReplyInGroup(chosenGroup, operationOnThatMessage, currentUser);
                                                                                            break;
                                                                                        case 5:
                                                                                            User.setCaptionForAMessage(chosenGroup, operationOnThatMessage, currentUser);
                                                                                        case 6:
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                                case 2:
                                                                                    break;
                                                                            }
                                                                        } else {
                                                                            isInGroupChat = false;
                                                                        }
                                                                        break;
                                                                    case 2:
                                                                        User.sendMessageToAGroup(chosenGroup, currentUser);
                                                                        break;
                                                                    case 3:
                                                                        User.addNewAdminToGroup(chosenGroup, currentUser);
                                                                        break;
                                                                    case 4:
                                                                        User.addNewMembersToGroup(chosenGroup, currentUser);
                                                                        break;
                                                                    case 5:
                                                                        User.kickSomeOneFromTheGroup(chosenGroup, currentUser);
                                                                        break;
                                                                    case 6:
                                                                        User.seeAdmins(chosenGroup);
                                                                        break;
                                                                    case 7:
                                                                        User.seeMembersInGroup(chosenGroup);
                                                                        break;
                                                                    case 8:
                                                                        Boolean isInBioPage = true;
                                                                        while (isInBioPage) {
                                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                                    "What Do You Want To Do\n1. See bio\n" +
                                                                                            "2. Change Bio\n3. Back", "Bio-Page",
                                                                                    JOptionPane.QUESTION_MESSAGE))) {
                                                                                case 1:
                                                                                    User.seeGroupBio(chosenGroup);
                                                                                    break;
                                                                                case 2:
                                                                                    User.changeGroupBio(chosenGroup, currentUser);
                                                                                    break;
                                                                                case 3:
                                                                                    isInBioPage = false;
                                                                                    break;

                                                                            }
                                                                            continue;

                                                                        }
                                                                        break;
                                                                    case 9:
                                                                        User.seeGroupID(chosenGroup);
                                                                        break;
                                                                    case 10:
                                                                        User.acceptOrDeclineRequests(chosenGroup, currentUser);
                                                                        break;
                                                                    case 11:
                                                                        User.exitGroupChat(chosenGroup, currentUser);
                                                                        isInGroupChat = false;
                                                                        break;
                                                                    case 12:
                                                                        User.deleteEntireGroup(chosenGroup, currentUser);
                                                                        isInGroupChat = false;
                                                                        break;
                                                                    case 13:
                                                                        isInGroupChat = false;
                                                                        break;

                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 3:
                                                boolean isInAllChannels = true;
                                                while (isInAllChannels){
                                                    chosenOption = User.showsAllChannels(currentUser);
                                                    switch (chosenOption){
                                                        case 0:
                                                            isInAllChannels = false;
                                                            break;
                                                        default:
                                                            Channel chosenChannel = User.goToAChannel(chosenOption - 1 ,currentUser);
                                                            boolean isInChannel = true;
                                                            while (isInChannel){
                                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                                        "What Do You Want To Do?\n1. See Messages Page" +
                                                                                "\n2. BroadCast A Message\n3. See Bio\n4. See Channel-ID\n5. Add New Admins\n6. Add New Viewers\n" +
                                                                                "7. Exit Channel\n8. Delete Entire Channel\n" +
                                                                                "9. Back" ,chosenChannel.getName() +
                                                                                " Channel-Page " , JOptionPane.QUESTION_MESSAGE))){
                                                                    case 1:
                                                                        User.seeMessagesPageOnChannel(chosenChannel);
                                                                        if (!chosenChannel.getMessageArrayList().isEmpty()){
                                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null ,"Do You" +
                                                                                            "Want To Select A Message To Do Some Operation On That?" +
                                                                                            "\n1. Yes\n2. No, Back To Messages Page", "Do Some Operation On Message"
                                                                                    ,JOptionPane.QUESTION_MESSAGE))){
                                                                                case 1:
                                                                                    String operationOnMessage = JOptionPane.showInputDialog(null, "Enter That " +
                                                                                            "Message :\n", "Do Some Operation On Message",JOptionPane.QUESTION_MESSAGE);
                                                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "< " + operationOnMessage +" >"+
                                                                                            "\nWhat Do You Want To Do With This Message?\n1. Delete The Message\n" +
                                                                                            "2. Like The Message\n3. Forward The Message\n4. Back", "Do" +
                                                                                            " Some Operation On Message", JOptionPane.QUESTION_MESSAGE))){
                                                                                        case 1:
                                                                                            User.deleteMessageFromAChannel(chosenChannel, operationOnMessage, currentUser);
                                                                                            break;
                                                                                        case 2:
                                                                                            User.likeTheMessageChannel(chosenChannel, operationOnMessage, currentUser);
                                                                                            break;
                                                                                        case 3:
                                                                                            User.forwardAMessage(chosenChannel, operationOnMessage, currentUser);
                                                                                            break;
                                                                                        case 4:
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                                case 2:
                                                                                    break;
                                                                            }
                                                                        }
                                                                        else {
                                                                            isInAllChannels = false;
                                                                        }
                                                                        break;
                                                                    case 2:
                                                                        User.broadCastAMessageInAChannel(chosenChannel, currentUser);
                                                                        break;
                                                                    case 3:
                                                                        Boolean isInBioPage = true;
                                                                        while (isInBioPage){
                                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                                                    "What Do You Want To Do\n1. See bio\n" +
                                                                                            "2. Change Bio\n3. Back" ,"Bio-Page",
                                                                                    JOptionPane.QUESTION_MESSAGE))){
                                                                                case 1:
                                                                                    User.seeChannelBio(chosenChannel);
                                                                                    break;
                                                                                case 2:
                                                                                    User.changeChannelBio(chosenChannel, currentUser);
                                                                                    break;
                                                                                case 3:
                                                                                    isInBioPage = false;
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 4:
                                                                        User.seeChannelID(chosenChannel);
                                                                        break;
                                                                    case 5:
                                                                        User.addNewAdminToChannel(chosenChannel, currentUser);
                                                                        break;
                                                                    case 6:
                                                                        User.addNewViewersToChannel(chosenChannel, currentUser);
                                                                        break;
                                                                    case 7:
                                                                        User.exitChannel(chosenChannel, currentUser);
                                                                        isInChannel = false;
                                                                        break;
                                                                    case 8:
                                                                        User.deleteEntireChannel(chosenChannel, currentUser);
                                                                        isInChannel = false;
                                                                        break;
                                                                    case 9:
                                                                        isInChannel = false;
                                                                        break;

                                                                }
                                                            }

                                                    }
                                                }
                                                break;
                                            case 4:
                                                isChatPage = false;
                                                break;
                                        }
                                    }
                                    break;

                                case 2:
                                    boolean isProfilePage = true;
                                    while (isProfilePage){
                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                "What Do You Want To Do?\n1. See Profile-Page\n" +
                                                        "2. Edit Profile-Page\n3. Back","Profile-Page"
                                                ,JOptionPane.QUESTION_MESSAGE))){
                                            case 1:
                                                User.seeProfilePage(currentUser);
                                                break;
                                            case 2:
                                                User.editProfilePage(currentUser);
                                                break;
                                            case 3:
                                                isProfilePage = false;
                                                break;

                                        }
                                    }
                                    break;
                                case 3:
                                    boolean inSearchPage = true;
                                    while (inSearchPage){
                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                "Choose One Of The Option\n1. Search For A User\n2. Search For A Group\n" +
                                                        "3 .Search For A Channel\n4. Back","Search-Page",JOptionPane.QUESTION_MESSAGE))){
                                            case 1:
                                                User searchedUser = User.searchUser();
                                                if(searchedUser != null){
                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                            "Do You Want To Start Private Chat" +
                                                                    "With " + searchedUser.getUserID() + " ?\n1. Yes\n2. No"
                                                            ,"Start Chat",JOptionPane.QUESTION_MESSAGE))){
                                                        case 1:
                                                            User.startNewPrivateChat(currentUser ,searchedUser);
                                                            break;
                                                        case 2:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 2:
                                                GroupChat searchedGroup = User.searchGroup();
                                                if(searchedGroup != null){
                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                            "Do You Want To Join This Group?\n" +
                                                                    "With " + searchedGroup.getGroupID() + " ?\n1. Yes\n2 .No"
                                                            ,"Start Chat",JOptionPane.QUESTION_MESSAGE))){
                                                        case 1:
                                                            User.joinTheGroup(currentUser, searchedGroup);
                                                            break;
                                                        case 2:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 3:
                                                Channel searchedChannel = User.searchChannel();
                                                if(searchedChannel != null){
                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                            "Do You Want To Join "+ searchedChannel.getChannelID() + " Channel ?\n1. Yes\n2 .No"
                                                            ,"Start Chat",JOptionPane.QUESTION_MESSAGE))){
                                                        case 1:
                                                            User.joinTheChannel(currentUser, searchedChannel);
                                                            break;
                                                        case 2:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 4:
                                                inSearchPage = false;
                                                break;
                                        }

                                    }
                                    break;
                                case 4:
                                    boolean isCreateANewGroupOrChannel = true;
                                    while (isCreateANewGroupOrChannel){
                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null ,
                                                "What Do You Want To Do?\n1. Create A New Group\n" +
                                                        "2. Create A New Channel\n3. Back","Create A New Group Or Channel" ,
                                                JOptionPane.QUESTION_MESSAGE))){
                                            case 1:
                                                User.createNewGroup(currentUser);
                                                break;
                                            case 2:
                                                User.createNewChannel(currentUser);
                                                break;
                                            case 3:
                                                isCreateANewGroupOrChannel = false;
                                                break;

                                        }

                                    }
                                    break;
                                case 5:
                                    User.deleteAccount(currentUser);
                                    isInHomePage = false;
                                    break;
                                case 6:
                                    isInHomePage = false;
                                    break;
                                }
                            }
                        break;
                        }
                    break;
                case 2:
                    User.signUp();
                    break;
                case 3:
                    isAppRunning = false;
                    break;

            }
        }


    }
}

