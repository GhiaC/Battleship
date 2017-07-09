import logic.MessageManager;
import tools.MessageManagerHandler;
import veiw.LoginFrame;
import veiw.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
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

        //MainFrame mainFrame = new MainFrame();
 //       LoginFrame loginFrame = new LoginFrame();
//       WaitingForConnectionFrame waitingForConnectionFrame = new WaitingForConnectionFrame();
  //      GuestWaitingFrame guestWaitingFrame = new GuestWaitingFrame();
        //LoginFrame loginFrame = new LoginFrame();
    }
}
