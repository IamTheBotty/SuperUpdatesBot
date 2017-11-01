package utils;

import org.telegram.telegrambots.api.objects.Message;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ensure security
 *
 * Created by pavel on 01.11.17.
 */
public class Confidentional {

    private static Set<Long> chatIdsAll = new HashSet<Long>();

    public static boolean checkChatId(Message message) {
        long chatId = message.getChatId();
        boolean ifAdded = false;
        if (!chatIdsAll.contains(chatId)) {
            chatIdsAll.add(chatId);
            ifAdded = true;
        }
        return ifAdded;
    }

    public static Set<Long> getAllIds() {
        return chatIdsAll;
    }

}
