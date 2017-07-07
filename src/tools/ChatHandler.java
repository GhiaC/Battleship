package tools;

import veiw.ChatMessage;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatHandler {

    public void writeMessage(String name , String message)
    {
        ChatMessage chatMessage = new ChatMessage(name,message);
    }
}
