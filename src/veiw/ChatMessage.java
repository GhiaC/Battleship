package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();

// create a JEditorPane
//        JEditorPane jEditorPane = new JEditorPane();

// make it read-only
        setEditable(false);

// add a HTMLEditorKit to the editor pane
        HTMLEditorKit kit = new HTMLEditorKit();
        setEditorKit(kit);

// now add it to a scroll pane
        //JScrollPane scrollPane = new JScrollPane(jEditorPane);


// add some styles to the html
        StyleSheet
                styleSheet = kit.getStyleSheet();

        styleSheet.addRule(".section {margin-top:10px;padding:10px;" +
                "width:100%;margin-left:30px !important;");
        styleSheet.addRule(".time {position:relative;float:right;width:100%;text-align:right;}");
        styleSheet.addRule(".total {border:2px solid rgb(70,70,70);}");
        styleSheet.addRule("p {width:90%}");

//        styleSheet.addRule(".empty {" +
//                "position:relative;float:right;width:20px;border:2px solid rgb(70,70,70);");
//        styleSheet.addRule(".total {" +
//                "position:relative;float:right;width:300px;border:2px solid rgb(0,170,70);");

//        styleSheet.addRule("* {" +
//                "padding:0;" +
//                "margin:0;}");

// create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        setDocument(doc);
        setText(
                "<html>" +
                        "<div class='section'>" +
                        "<div class='total'>"+
                        name+"<br>"+message+"<br><div class='time'> "+ dateObj.getHours()+":" + dateObj.getMinutes()+" </div>" +
                        "<div></div>" +
                "</html>"
                );

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
