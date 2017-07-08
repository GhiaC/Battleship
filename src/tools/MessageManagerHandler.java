package tools;

import logic.BaseMessage;
import logic.MessageManager;

/**
 * Created by M on 7/7/2017.
 */
public class MessageManagerHandler {
    private static MessageManager messageManager;

    public MessageManagerHandler(MessageManager messageManager) {
        MessageManagerHandler.messageManager = messageManager;
    }

    public MessageManagerHandler() {}
    public void sendData(BaseMessage baseMessage){
        MessageManagerHandler.messageManager.sendData(baseMessage);
    }
}
