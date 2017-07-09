package veiw;

import logic.MessageManager;
import tools.Game;
import tools.MessageManagerHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{

    private JLabel nameLabel;
    private JLabel portLabel;
    private JTextField nameField;
    private JRadioButton hostButton;
    private JRadioButton guestButton;
    private JTextField portField;
    private JLabel IPLabel;
    private JTextField IPField;
    private JLabel guestPortLabel;
    private JTextField guestPortField;
    private JButton startButton;
    private JButton exitButton;
    private MessageManager messageManager;

    public LoginFrame()
    {
        super("select Connection mode");
        setLayout(null);
        setSize(600,350);
        setLocation(600,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameLabel.setLocation(20,20);
        nameLabel.setSize(50,50);


        nameField = new JTextField();
        nameField.setText("name");
        nameField.setLocation(70,30);
        nameField.setSize(350,30);
        add(nameField);


        hostButton= new JRadioButton("host");
        hostButton.setLocation(20,60);
        hostButton.setSize(150,80);
        add(hostButton);


        portLabel = new JLabel("Port:");
        portLabel.setLocation(20,130);
        portLabel.setSize(50,50);
        add(portLabel);


        portField = new JTextField();
        portField.setLocation(70,140);
        portField.setSize(350,30);
        portField.setText("1111");
        add(portField);


        guestButton = new JRadioButton("Guest");
        guestButton.setLocation(20,170);
        guestButton.setSize(100,60);
        add(guestButton);


        IPLabel = new JLabel("IP:");
        IPLabel.setLocation(20,230);
        IPLabel.setSize(50,50);
        add(IPLabel);


        IPField = new JTextField();
        IPField.setText("127.0.0.1");
        IPField.setLocation(50,240);
        IPField.setSize(200,30);
        add(IPField);


        guestPortLabel = new JLabel("Port:");
        guestPortLabel.setLocation(260,230);
        guestPortLabel.setSize(50,50);
        add(guestPortLabel);


        guestPortField = new JTextField();
        guestPortField.setText("1111");
        guestPortField.setLocation(310,240);
        guestPortField.setSize(200,30);
        add(guestPortField);



        startButton = new JButton("Start");
        startButton.setLocation(500,300);
        startButton.setSize(90,40);
        add(startButton);


        exitButton = new JButton("Exit");
        exitButton.setLocation(400,300);
        exitButton.setSize(90,40);
        add(exitButton);


        setHostButtonActionListener();
        setGuestButtonActionListener();
        setExitButtonActionLinstener();
        setStartButtonActionLinstener();
        setUndecorated(true);
        setVisible(true);
    }
    private void setHostButtonActionListener()
    {
        //for initial for first time
        hostButton.setSelected(true);
        guestButton.setSelected(false);
        guestPortField.setEditable(false);
        portField.setEditable(true);
        IPField.setEditable(false);

        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guestButton.setSelected(false);
                guestPortField.setEditable(false);
                portField.setEditable(true);
                IPField.setEditable(false);
            }
        });
    }
    private void setGuestButtonActionListener()
    {
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hostButton.setSelected(false);
                portField.setEditable(false);
                IPField.setEditable(true);
                guestPortField.setEditable(true);
            }
        });
    }
    private void setStartButtonActionLinstener(){
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(guestButton.isSelected()){
                   // GuestWaitingFrame guestWaitingFrame = new GuestWaitingFrame();
                    new MessageManagerHandler(new MessageManager(IPField.getText(),Integer.parseInt(guestPortField.getText())));
                }else{
                   // WaitingForConnectionFrame waitingForConnectionFrame = new WaitingForConnectionFrame();
                    new MessageManagerHandler(new MessageManager(Integer.parseInt(portField.getText())));
                }
                Game.openMainFrame();
                setVisible(false);
            }
        });
    }
    private void setExitButtonActionLinstener(){
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
