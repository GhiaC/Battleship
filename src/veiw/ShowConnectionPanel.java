package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/8/17.
 */
public class ShowConnectionPanel extends JPanel {
    private int numberOfConnection = 0;

    public ShowConnectionPanel()
    {
       setLocation(0,50);
       setBackground(Color.BLACK);
       setPreferredSize(new Dimension(300,2000));
       setLayout(null);
    }
    public void addNewConnection(String IP,String name)
    {
        JPanel newConnection = new JPanel(null);
        newConnection.setLayout(null);
        newConnection.setSize(300,150);
        newConnection.setLocation(0,150*numberOfConnection);
        newConnection.setBorder(new LineBorder(Color.BLACK,1));
        addNameIP(IP,name,newConnection);
        addButtons(newConnection);
        numberOfConnection++;
        add(newConnection);
    }
    private void addNameIP(String IP,String name,JPanel newConnection)
    {
       JLabel nameLabel = new JLabel(name);
       newConnection.add(nameLabel);
       nameLabel.setLocation(10,0);
       nameLabel.setSize(80,40);
       JLabel IPLabel = new JLabel(IP);
       newConnection.add(IPLabel);
       IPLabel.setLocation(10,40);
       IPLabel.setSize(100,45);
    }
    private void addButtons(JPanel newConnection)
    {
        JButton acceptButton = new JButton("Accept");
        newConnection.add(acceptButton);
        acceptButton.setSize(100,50);
        acceptButton.setLocation(200,100);
        JButton rejectButton = new JButton("Reject");
        newConnection.add(rejectButton);
        rejectButton.setSize(100,50);
        rejectButton.setLocation(100,100);
    }
}
