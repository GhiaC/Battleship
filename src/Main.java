import logic.ChatMessage;
import logic.MessageManager;
import tools.ChatHandler;
import tools.MessageManagerHandler;
import veiw.LoginFrame;
import veiw.MainFrame;

import java.awt.dnd.DropTarget;

public class Main {
    public static void main(String[] args) {
        MessageManager messageManager=null;
        if(false){
            messageManager = new MessageManager(9999);
        }
        if(true) {
            messageManager = new MessageManager("127.0.0.1",9999);
        }
        new MessageManagerHandler(messageManager);

   //     MainFrame mainFrame = new MainFrame();
        LoginFrame loginFrame = new LoginFrame();
    }
}
