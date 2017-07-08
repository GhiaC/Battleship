package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatMessage extends JLabel {
    private static ChatViewerPanel chatViewerPanel;
    private String name;
    private String message;
    public ChatMessage(String name , String message)
    {
        setText("<html><span style='color:red;'>"+name+"</span><p style='color:green;'>"+message+"</p></html>");
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
