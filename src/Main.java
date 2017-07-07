import logic.ChatMessage;
import logic.MessageManager;
import tools.ChatHandler;
import veiw.MainFrame;

import java.awt.dnd.DropTarget;

public class Main {
    public static void main(String[] args) {
        MessageManager messageManager=null;
        MainFrame mainFrame = new MainFrame();
        if(false){
            messageManager = new MessageManager(9999);
        }
        if(false) {
            messageManager = new MessageManager("172.24.66.15",9999);
        }
        while (true){
            messageManager.sendData(new ChatMessage("hi"));
        }

    }
}
