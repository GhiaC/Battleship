package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatViewerPanel extends JPanel {

    private JScrollPane scrollPane;
    private JScrollBar jScrollBar;
    public ChatViewerPanel()
    {
      //  BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        scrollPane = new JScrollPane();
        jScrollBar = new JScrollBar();
        setLocation(0,50);
        setSize(300,600);
        setOpaque(false);
        //setBackground(Color.BLACK);
        ChatMessage.setChatViewerPanel(this);
        scrollPane.add(this);
        //add(jScrollBar);
    }
    public void write(ChatMessage chatMessage)
    {
        add(chatMessage);
        repaint();
    }
}
