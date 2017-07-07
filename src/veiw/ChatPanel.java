package veiw;

import sun.awt.ExtendedKeyCodes;
import tools.ChatHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by mohsen on 7/5/17.
 */
public class ChatPanel extends JPanel{

    JTextField textField;
    JButton sendButton;
    JLabel nameLabel;
    ChatViewerPanel chatViewerPanel;
    ChatHandler chatHandler;
    public ChatPanel()
    {
    //    setBackground(Color.BLACK);
        setLocation(700,0);
        setSize(300,700);
        setLayout(null);
        setOpaque(false);
        chatHandler = new ChatHandler();
        textField = new JTextField("Type here...");
        textField.setSize(230,50);
        textField.setLocation(0,650);
        add(textField);
        sendButton = new JButton("send");
        sendButton.setLocation(230,650);
        sendButton.setSize(70,50);
        createNameLabel("ali");
        chatViewerPanel = new ChatViewerPanel();
        add(chatViewerPanel);
        add(sendButton);
        setSendButtonActionListener();
        setTextFieldActionListener();
    }
    public void createNameLabel(String s) {
        nameLabel = new JLabel("chat to " + s);
        nameLabel.setSize(300,50);
        nameLabel.setLocation(0,0);
        nameLabel.setOpaque(true);
        nameLabel.setBorder(new LineBorder(Color.BLACK,1));
        add(nameLabel);
    }
    private void setTextFieldActionListener()
    {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyChar() == '\n')
                    writeMassage();

            }
        });
    }
    private void writeMassage()
    {
        chatHandler.writeMessage("ali",textField.getText());
        textField.setText("");
        repaint();
        revalidate();

    }
    private void setSendButtonActionListener()
    {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                writeMassage();
            }
        });
    }
}

