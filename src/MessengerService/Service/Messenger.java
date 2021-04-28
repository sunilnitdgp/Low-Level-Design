package MessengerService.Service;

import MessengerService.Model.Group;
import MessengerService.Model.GroupMessage;
import MessengerService.Model.PersonalMessage;
import MessengerService.Model.User;

import java.util.*;

public class Messenger {
    private String chatIdFormat = "%d_%d";
    private Map<String, Stack<PersonalMessage>> oneToOneMessages;
    private Map<Group, Stack<GroupMessage>> oneToManyMessages;

    public Messenger() {
        this.oneToManyMessages = new HashMap<>();
        this.oneToOneMessages = new HashMap<>();
    }

    private void sendMessage(String message, Group group, User sender, User receiver) {
        if(group != null) {
            List<User> users = group.getUsers();
            if(!users.contains(sender)) {
                throw new IllegalArgumentException();
            }
            GroupMessage groupMessage = new GroupMessage(message, new Date(), sender.getId(), group.getGroupId());
            Stack<GroupMessage> groupMessages = oneToManyMessages.get(group);
            if(groupMessages == null)
                groupMessages = new Stack<>();
            groupMessages.push(groupMessage);
            oneToManyMessages.put(group, groupMessages);
        } else {
            String senderChatId = String.format(chatIdFormat, sender.getId(), receiver.getId());
            String receiverChatId = String.format(chatIdFormat, receiver.getId(), sender.getId());
            Stack<PersonalMessage> personalMessages;
            PersonalMessage personalMessage = new PersonalMessage(message, new Date(), sender.getId(), receiver.getId());
            if(oneToOneMessages.containsKey(senderChatId)) {
                personalMessages = oneToOneMessages.get(senderChatId);
                personalMessages.push(personalMessage);
            } else if(oneToOneMessages.containsKey(receiverChatId)) {
                personalMessages = oneToOneMessages.get(receiverChatId);
                personalMessages.push(personalMessage);
            } else {
                 personalMessages = new Stack<>();
                personalMessages.push(personalMessage);
                oneToOneMessages.put(senderChatId, personalMessages);
            }
        }
    }

    public Stack<PersonalMessage> getOneToOneMessages(User currentUser, User targetUser) {
        return oneToOneMessages.get(String.format(chatIdFormat, currentUser.getId(), targetUser.getId()));
    }

    public Stack<GroupMessage> getOneToManyMessages(Group group) {
        return oneToManyMessages.get(group);
    }

    public static void main(String[] args) {

        User user1 = new User(1L, "Sunil");
        User user2 = new User(2L, "Anil");
        User user3 = new User(3L, "Uday");
        User user4 = new User(4L, "Sonu");
        Group g1 = new Group(1L, List.of(user1, user3, user2));
        Group g2 = new Group(2L, List.of(user4, user3));
        Messenger messenger = new Messenger();
        messenger.sendMessage("Hello Anil", null, user1, user2);
        messenger.sendMessage("Hello Sunil", null, user2, user1);
        messenger.sendMessage("Kaise ho", null, user1, user2);
        messenger.sendMessage("Badiya Hu", null, user2, user1);
        messenger.sendMessage("Tu Bta", null, user2, user1);
        messenger.sendMessage("Chal rha he", null, user1, user2);
        messenger.sendMessage("Hi Uday", null, user1, user3);
        messenger.sendMessage("Hi Sonu", null, user2, user4);
        System.out.println(messenger.getOneToOneMessages(user1, user2));
        messenger.sendMessage("Hello Group sunil1", g1, user1, null);
        messenger.sendMessage("Hello Group suni2", g1, user2, null);
        messenger.sendMessage("Hello Group sun3", g1, user1, null);
        messenger.sendMessage("Hello Group sun4", g1, user3, null);
        messenger.sendMessage("Hello Group su5", g1, user1, null);
        messenger.sendMessage("Hello Group s6", g1, user2, null);
        System.out.println(messenger.getOneToManyMessages(g1));
    }
}
