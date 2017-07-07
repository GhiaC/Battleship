package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class ChatPanel extends JPanel{

    JTextField textField;
    JButton sendButton;
    JLabel nameLabel;
    public ChatPanel()
    {
    //    setBackground(Color.BLACK);
        setLocation(700,0);
        setSize(300,700);
        setLayout(null);
        textField = new JTextField("Type here...");
        textField.setSize(230,50);
        textField.setLocation(0,650);
        add(textField);
        sendButton = new JButton("send");
        sendButton.setLocation(230,650);
        sendButton.setSize(70,50);
        createNameLabel("ali");
        add(sendButton);
    }
    public void createNameLabel(String s) {
        nameLabel = new JLabel("chat to " + s);
        nameLabel.setSize(300,50);
        nameLabel.setLocation(0,0);
        nameLabel.setOpaque(true);
        nameLabel.setBorder(new LineBorder(Color.BLACK,1));
        add(nameLabel);
    }

}
