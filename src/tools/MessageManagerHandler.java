package tools;

import logic.MessageManager;
import veiw.PlayerField;

public class MessageManagerHandler {
    private static MessageManager messageManager;
    private static String username ="enemy";

    public MessageManagerHandler(MessageManager messageManager) {
        MessageManagerHandler.messageManager = messageManager;
    }

    public static void sendMessage(String message) {
        MessageManagerHandler.messageManager.sendMessage(username,message);
    }

    public static void sendField(PlayerField playerField) {
        MessageManagerHandler.messageManager.sendField(playerField.getField());
    }
    public static void setUsername(String name){
        username = name;
    }

    public static void sendPointAttack(int x, int y) {
        MessageManagerHandler.messageManager.sendPointAttack(x, y);
    }

    public static void isTyping(boolean isTyping){
        MessageManagerHandler.messageManager.isTyping(isTyping);

    }
    public static void Accept(int enemyNum) {
        MessageManagerHandler.messageManager.Accept(enemyNum);
    }
    public static void Reject(int enemyNum) {
        MessageManagerHandler.messageManager.Reject(enemyNum);
    }


}
