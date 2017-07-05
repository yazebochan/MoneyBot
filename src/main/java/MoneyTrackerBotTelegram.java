import org.telegram.telegrambots.*;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

    public class MoneyTrackerBotTelegram extends TelegramLongPollingBot {

        public static void main(String[] args) throws TelegramApiException {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new MoneyTrackerBotTelegram());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getBotUsername() {
            return "";
        }

        @Override
        public String getBotToken() {
            return "";
        }

        @Override
        public void onUpdateReceived(Update update) {



            TableDAO tableDAO = new TableDAO();
            StateDAO stateDAO = new StateDAO();
            Message message = update.getMessage();
            String msg = message.getText();
            String userName = update.getMessage().getFrom().getUserName();

                switch (stateDAO.stateCheck(userName)){
                    case "false":
                    stateDAO.inputUserName(userName);
                    break;
                    case "GetDate":

                        tableDAO.updateGetLastComment(msg, userName);
                        sendMsg(message, "Запись внесена!");
                        stateDAO.updateGetBegin(userName);
                        break;
                    case "GetValue":
                        sendMsg(message, "Введи комментарий");
                        tableDAO.updateGetValue(Integer.valueOf(msg), userName);
                        stateDAO.updateGetComment(userName);
                        break;
                    case "GetBegin": //because of reverse telegram input order
                        tableDAO.inputId();
                        tableDAO.inputUserName(userName);
                        tableDAO.inputDate(msg, userName);
                        sendMsg(message, "Введи сумму");
                        stateDAO.updateStateGetValue(userName);
                        break;
                        }


                }
            private void sendMsg(Message message, String text) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId().toString());
                sendMessage.setReplyToMessageId(message.getMessageId());
                sendMessage.setText(text);
                try {
                    sendMessage(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
    }
