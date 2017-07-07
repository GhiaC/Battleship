package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatViewerPanel extends JPanel {

    private ScrollPane scrollPane;
    public ChatViewerPanel()
    {
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        scrollPane = new ScrollPane();
        setLocation(0,50);
        setSize(300,600);
        setOpaque(false);
        //setBackground(Color.BLACK);
        ChatMessage.setChatViewerPanel(this);
        scrollPane.add(this);

    }
    public void write(ChatMessage chatMessage)
    {
        System.out.println("SD");
        add(chatMessage);
        repaint();
    }
}
