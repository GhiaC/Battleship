package tools;

import logic.BaseMessage;
import logic.MessageManager;

public class MessageManagerHandler {
    private static MessageManager messageManager;

    public MessageManagerHandler(MessageManager messageManager) {
        MessageManagerHandler.messageManager = messageManager;
    }

    public static void sendData(BaseMessage baseMessage){
        MessageManagerHandler.messageManager.sendData(baseMessage);
    }
    public static void sendLoginRequest(){

    }
    public static boolean isAccept(){
        return true;
    }
}
