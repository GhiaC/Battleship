package veiw;

import tools.MessageManagerHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohsen on 7/10/17.
 */
public class NewConnectionPanel extends JPanel {

    private int index;
    JButton acceptButton;
    NewConnectionPanel(String IP,String name,int index) {
        this.index = index;
        setLayout(null);
        setSize(300, 150);
        setLocation(0, 150 * index);
        setBorder(new LineBorder(Color.BLACK, 1));
        addNameIP(IP, name);
        addButtons();
        setAcceptButtonListener();
    }
    private void addNameIP(String IP,String name)
    {
       JLabel nameLabel = new JLabel(name);
       add(nameLabel);
       nameLabel.setLocation(10,0);
       nameLabel.setSize(80,40);
       JLabel IPLabel = new JLabel(IP);
       add(IPLabel);
       IPLabel.setLocation(10,40);
       IPLabel.setSize(100,45);
    }
    private void addButtons()
    {
        acceptButton = new JButton("Accept");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageManagerHandler.Accept(index);
            }
        });
        add(acceptButton);
        acceptButton.setSize(100,50);
        acceptButton.setLocation(200,100);
        JButton rejectButton = new JButton("Reject");
        add(rejectButton);
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageManagerHandler.Reject(index);
            }
        });rejectButton.setSize(100,50);
        rejectButton.setLocation(100,100);
    }
    private void setAcceptButtonListener()
    {
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MessageManagerHandler.Accept(index);
            }
        });
    }
}

