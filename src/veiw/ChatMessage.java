package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatMessage extends JLabel {
    private static ChatViewerPanel chatViewerPanel;
    private String name;
    private String message;
    public ChatMessage(String name , String message)
    {
////        super("<html><span style='padding:10px;height:100px;float:right;position:relative;direction:rtl'><p>"+name + "</p>: " + message+"</span></html>");
//        JTextPane text = new JTextPane();
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        setText("<html><span style='color:red;'>"+name+"</span><p style='color:green;'>"+message+ "     "/*"</p></html>"*/ + dateObj.getHours()+":" + dateObj.getMinutes());
        this.name = name;
        this.message = message;
        setBorder(new LineBorder(Color.BLACK,1));
        writeMessage();
    }
    private void writeMessage()
    {
        chatViewerPanel.write(this);
    }
    public static void setChatViewerPanel(ChatViewerPanel chatViewerPanel) {
        ChatMessage.chatViewerPanel = chatViewerPanel;
    }
}
