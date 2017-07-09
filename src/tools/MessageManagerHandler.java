package tools;

import logic.BaseMessage;
import logic.Message.RequestLoginMessage;
import logic.MessageManager;

public class MessageManagerHandler {
    private static MessageManager messageManager;

    public MessageManagerHandler(MessageManager messageManager) {
        MessageManagerHandler.messageManager = messageManager;
    }

    public static void sendData(BaseMessage baseMessage){
        MessageManagerHandler.messageManager.sendData(baseMessage);
    }
    public static void sendRequestLogin(String name){
//        MessageManagerHandler.messageManager.sendRequestLogin(new RequestLoginMessage(name));
    }
    public static void sendPointAttack(int x ,int y){

    }
//    public static boolean isAccept(){
//        return true;
//    }
}
