package veiw;

import javax.swing.*;
import java.awt.*;

public class ChatViewerPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel jPanel = new JPanel();
    public ChatViewerPanel()
    {
        setOpaque(true);
        setBounds(0,50,300,600);
        ChatMessage.setChatViewerPanel(this);
        jPanel.setBackground(new Color(230,230,230));
        jPanel.setOpaque(true);
        jPanel.setLayout(new GridBagLayout());

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;


        for (int i = 0; i < 2; i++) {
            write(new ChatMessage("HIII","HIIIII"));
        }
        // Temporary panel to fill the rest of the bigPanel

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(jPanel);
        scrollPane.setSize(300,600);
        gbc.weighty = 1.0;
        jPanel.add(empty, gbc);
        add(scrollPane);

        setVisible(true);

    }
    JPanel empty =new JPanel();
    public void write(ChatMessage chatMessage)
    {
        jPanel.remove(empty);
        gbc.weighty = 0;
        JPanel panel_a = new JPanel();
        panel_a.setBounds(0,0,250,150);
        panel_a.add(chatMessage, gbc);
        jPanel.add(panel_a, gbc);

        gbc.weighty = 1.0;
        jPanel.add(empty, gbc);

        repaint();
    }
}
