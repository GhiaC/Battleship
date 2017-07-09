package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatMessage extends JEditorPane {
    private static ChatViewerPanel chatViewerPanel;
    private String name;
    private String message;
    public ChatMessage(String name , String message)
    {
        setContentType( "text/html" );
        setText( "<html><body>Hello, world</body></html>" );
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        setText("<html><body style='width:125px;padding:5px;border:0px solid rgb(60,60,60);background:rgb(86,130,163);'>" +
                "<span style='color:red;'>"+name+"</span>"+
                "<br><span style='color:white'>"+name+"</span><br>" +
                "<p style='color:blue;text-align:right;'>"+ dateObj.getHours()+":" + dateObj.getMinutes()+"</p></body><html>");

        this.name = name;
        setBackground(new Color(230,230,230));
        this.message = message;
        setOpaque(true);
        int right = 90,left=0;
        if(name.equals("Me")){
            right = 0;
            left =90;
        }
        setBorder(new MatteBorder(5, left, 5, right, new Color(0,0,0,0)));
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
