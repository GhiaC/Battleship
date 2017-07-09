package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/7/17.
 */
public class ChatViewerPanel extends JPanel {
    private static int counter=0;
    public ChatViewerPanel()
    {
        setLocation(0,50);
        setSize(300,600);
        setOpaque(false);
        ChatMessage.setChatViewerPanel(this);

        JTable t = new JTable(null);

        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        setPreferredSize(getSize());
        setVisible(true);

    }
    GridBagConstraints gbc = new GridBagConstraints();
    public void write(ChatMessage chatMessage)
    {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);  //top padding
        gbc.gridx = 0;
        gbc.gridy = counter;
        gbc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING; //bottom of space
        gbc.weightx = 3;
        this.add(chatMessage, gbc);
        counter++;
        repaint();
    }
}
