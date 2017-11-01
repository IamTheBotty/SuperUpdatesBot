package utils;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;

import java.util.*;

/**
 * Ensure security
 *
 * Created by pavel on 01.11.17.
 */
public class Confidentional {

    private static Map<String, List<String>> chatsMap = new HashMap<String, List<String>>();

    public static boolean checkChatId(Message message) {
        String chatId = message.getChatId().toString();
        Chat chat = message.getChat();
        boolean ifAdded = false;
        if (!chatsMap.keySet().contains(chatId)) {
            List<String> chatInfo = new ArrayList<String>();
            if (chat.getFirstName() != null) { chatInfo.add("FirstName = " + chat.getFirstName()); }
            if (chat.getLastName() != null)  { chatInfo.add("LastName = " + chat.getLastName()); }
            if (chat.getTitle() != null)     { chatInfo.add("Title = " + chat.getTitle()); }
            if (chat.getUserName() != null)  { chatInfo.add("UserName = @" + chat.getUserName()); }
            chatsMap.put(chatId, chatInfo);
            ifAdded = true;
        }
        return ifAdded;
    }

    public static String getAllRegisteredChatsInfo() {
        if (chatsMap.size() == 0) {
            return "";
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("```").append("\n");
        for (String id : chatsMap.keySet()) {
            sb.append("--- --- --- ---\n");
            sb.append("ID = ").append(id).append("\n");
            for (String field : chatsMap.get(id)) {
                sb.append(field).append("\n");
            }
            i++;
        }
        sb.append("--- --- --- ---\n");
        sb.append("```");
        sb.append("Total count: ").append(i);
        return sb.toString();
    }

    public static void addWhiteList() {

    }

}
