import logic.ChatMessage;
import logic.MessageManager;
import tools.ChatHandler;
import tools.MessageManagerHandler;
import veiw.GuestWaitingFrame;
import veiw.LoginFrame;
import veiw.MainFrame;
import veiw.WaitingForConnectionFrame;

import java.awt.dnd.DropTarget;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        MessageManager messageManager=null;
        boolean b = true;
        b =!b;
        if(b){
            messageManager = new MessageManager(9999);
        }
        if(!b) {
            messageManager = new MessageManager("192.168.43.215",9999);
        }
        new MessageManagerHandler(messageManager);

        MainFrame mainFrame = new MainFrame();
 //       LoginFrame loginFrame = new LoginFrame();
//       WaitingForConnectionFrame waitingForConnectionFrame = new WaitingForConnectionFrame();
  //      GuestWaitingFrame guestWaitingFrame = new GuestWaitingFrame();
    }
}
