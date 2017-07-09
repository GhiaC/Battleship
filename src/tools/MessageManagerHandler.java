package tools;

import logic.MessageManager;

public class MessageManagerHandler {
    private static MessageManager messageManager;

    public MessageManagerHandler(MessageManager messageManager) {
        MessageManagerHandler.messageManager = messageManager;
    }

    public static void sendMessage(String message){
        MessageManagerHandler.messageManager.sendMessage(message);
    }
    public static void sendRequestLogin(String name){
        MessageManagerHandler.messageManager.sendRequestLogin(name);
    }
    public static void sendPointAttack(int x ,int y){
        MessageManagerHandler.messageManager.sendPointAttack(x,y);
    }
    public static void Ready(){
        MessageManagerHandler.messageManager.Ready();
    }
    public static void Accept(int enemyNum){
        MessageManagerHandler.messageManager.Accept(enemyNum);
    }

}
