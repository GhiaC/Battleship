package veiw;

import tools.ChatHandler;
import tools.MessageManagerHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatPanel extends JPanel{
    private JTextField textField;
    private JButton sendButton;
    public static JLabel nameLabel,isTypingLanel;
    private ChatViewerPanel chatViewerPanel;
    private ChatHandler chatHandler;
    public ChatPanel()
    {
        setLocation(700,0);
        setSize(300,700);
        setLayout(null);
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK,1));
        chatHandler = new ChatHandler();
        textField = new JTextField("Type here...");
        textField.setSize(230,50);
        textField.setLocation(0,650);
        add(textField);
        sendButton = new JButton("send");
        sendButton.setLocation(230,650);
        sendButton.setSize(70,50);
        createNameLabel("ali");
        createIsTypingLabel();
        chatViewerPanel = new ChatViewerPanel();
        add(chatViewerPanel);
        add(sendButton);
        setSendButtonActionListener();
        setTextFieldActionListener();
        new rePaint(this);
    }
    public void createNameLabel(String s) {
        nameLabel = new JLabel("chat to " + s);
        nameLabel.setSize(130,45);
        nameLabel.setLocation(20,5);
        nameLabel.setOpaque(true);
        add(nameLabel);
    }
    public void createIsTypingLabel(){
        isTypingLanel = new JLabel("");
        isTypingLanel.setSize(130,45);
        isTypingLanel.setLocation(150,5);
        isTypingLanel.setOpaque(true);
        add(isTypingLanel);
    }
    private void setTextFieldActionListener()
    {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyChar() == '\n') {
                    writeMassage();
                }
                if (!textField.getText().equals("") && textField.getText().length() > 1) {
                    MessageManagerHandler.isTyping(true);
                } else {
                    MessageManagerHandler.isTyping(false);
                }
            }
        });
    }
    private void writeMassage()
    {
        chatHandler.writeMessage("Me",textField.getText());
        MessageManagerHandler.sendMessage(textField.getText());
        textField.setText("");
        repaint();
        revalidate();
    }
    private class rePaint extends Thread{
        JPanel jPanel;
        public rePaint(JPanel jPanel) {
            this.jPanel=jPanel;
            start();
        }

        @Override
        public void run() {
            try{
                while (true){
                    this.jPanel.revalidate();
                    Thread.sleep(10);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
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

