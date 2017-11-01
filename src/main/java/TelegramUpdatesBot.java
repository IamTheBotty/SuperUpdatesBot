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

import static utils.Confidentional.checkChatId;
import static utils.Confidentional.getAllIds;

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

        Message receivedMsg = update.getMessage();
        if (checkChatId(receivedMsg)) {
            logChatIds();
        }

        if (receivedMsg != null && receivedMsg.hasText() && receivedMsg.getText().equals("getChatId")) {
            String secretMessage = receivedMsg.getChatId().toString();
            sendMsg(receivedMsg, "This chat ID: " + secretMessage, true);
        }

        //test
        if (receivedMsg != null && receivedMsg.hasText() && receivedMsg.getText().equals("ping ping, kd")) {
            String secretMessage = receivedMsg.getChatId().toString();
            sendMsg(receivedMsg, "Hello! This is a secret message from me.", true);
        }


//        if (receivedMsg != null && receivedMsg.hasText()) {
        if (receivedMsg == null) {
            String receivedTxt = receivedMsg.getText();
            if (receivedTxt.contains("@FinancialUpdatesBot")) {
                sendMsg(receivedMsg, ug.getMessage(), false);
            } else if (rg.contains(receivedTxt)) {
                sendMsg(receivedMsg, rg.getReply(), true);
            } else {
                double d = Math.random();
                if (d < 0.1) {
                    sendMsg(receivedMsg, ug.getMessage(), false);
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

    public void logChatIds() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Long l : getAllIds()) {
            sb.append(i).append(". ");
            sb.append(l.toString()).append("\n");
            i++;
        }
        logMessage(sb.toString());
    }

    public void logMessage(String text) {
        String logChatId = "-312418133";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(logChatId);
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}