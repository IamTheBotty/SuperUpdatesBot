package main.java;

import main.java.generator.UpdateGenerator;
import main.java.generator.ReplyGenerator;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import utils.WhiteList;

import static utils.Confidentional.checkChatId;
import static utils.Confidentional.getAllRegisteredChatsInfo;

public class TelegramUpdatesBot extends TelegramLongPollingBot {

    private WhiteList whiteList = new WhiteList();
    private ReplyGenerator rg = new ReplyGenerator();
    private UpdateGenerator ug = new UpdateGenerator();

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

        Message receivedMsg = update.getMessage();
        if (checkChatId(receivedMsg)) {
            logChatInfo(receivedMsg.getChat());
            logMessage(getAllRegisteredChatsInfo());
        }

        //test msg
        if (receivedMsg != null && receivedMsg.hasText() && receivedMsg.getText().equals("ping ping, kd")) {
            logMessage("Hello! This is a secret message from me.");
        }

        if (checkMessage(receivedMsg) < 0) {
            String receivedTxt = receivedMsg.getText();

            if (receivedMsg.isCommand()) {
                if (receivedTxt.contains("/start")) {
                    sendMsg(receivedMsg, "Коллеги, добрый день!", false);
                }
                else if (receivedTxt.contains("/over")) {
                    sendSticker(receivedMsg, "CAADAgADWwADIRiFCrJ1z12dq5NVAg");
                }
                else if (receivedTxt.contains("/update") || receivedTxt.contains("@FinancialUpdatesBot")) {
                    sendMsg(receivedMsg, ug.getMessage(), false);
                }
            }

            else if (receivedTxt.contains("@FinancialUpdatesBot")) {
                sendMsg(receivedMsg, ug.getMessage(), false);
            }
//            else if (rg.contains(receivedTxt)) {
//                sendMsg(receivedMsg, rg.getReply(), false);
//            }
            else {
                double d = Math.random();
                if (d < 0.005) {
                    sendMsg(receivedMsg, ug.getMessage(), false);
                }
            }
        } else if (checkMessage(receivedMsg) > 0) {
            sendMsg(receivedMsg, rg.getReply(), false);
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

    private void sendSticker(Message message, String stickerId) {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setSticker(stickerId);
        sendSticker.setChatId(message.getChatId());
        try {
            sendSticker(sendSticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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

    public void logChatInfo(Chat chat) {
        String sb = "```\n" +
                "New chat found. ID = " + chat.getId() + "\n" +
                "isGroupChat      = " + chat.isGroupChat().toString() + "\n" +
                "isUserChat       = " + chat.isUserChat().toString() + "\n" +
                "isSuperGroupChat = " + chat.isSuperGroupChat().toString() + "\n" +
                "isChannelChat    = " + chat.isChannelChat().toString() + "\n" +
                chat.toString() + "\n```";
        logMessage(sb);
    }

    private long checkMessage(Message message) {
        if (message != null && message.hasText()) {
            long id = message.getChatId();
            String sid = message.getChatId().toString();
            if (id < 0 && whiteList.getList().contains(sid)) {
                return message.getChatId();
            } else if (id > 0) {
                return message.getChatId();
            }
            else return 0;
        }
        else return 0;
    }

}