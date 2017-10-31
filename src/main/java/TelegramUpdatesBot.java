package main.java;

import main.java.generator.ReplyGenarator;
import main.java.generator.UpdateGenerator;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramUpdatesBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramUpdatesBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "FinancialUpdatesBot";
    }

    @Override
    public String getBotToken() {
        return "492766528:AAHxmOH1ep_30gKK0ovxLjxnqcai6SRzW28";
    }

    public void onUpdateReceived(Update update) {
        ReplyGenarator rg = new ReplyGenarator();
        UpdateGenerator ug = new UpdateGenerator();

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String messageTxt = message.getText();
            if (rg.contains(messageTxt)) {
                sendMsg(message, rg.getReply(), true);
            }
            else {
                double d = Math.random();
                if (d < 0.1) {
                    sendMsg(message, ug.getMessage(), false);
                }
            }
        }
    }

    private void sendMsg(Message message, String text, boolean ifReply) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        if (ifReply) {
            sendMessage.setReplyToMessageId(message.getMessageId());
        }
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}