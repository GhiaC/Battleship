package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class WaitingForConnectionFrame extends JFrame {
    private JLabel receivedConnectionLabel;
    private ShowConnectionPanel showConnectionPanel;
    private JScrollPane scrollPane;

    public WaitingForConnectionFrame() {
        super("Waiting For connections");
        setLayout(null);
        setLocation(700, 300);
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);

        receivedConnectionLabel = new JLabel("Received Connection");
        receivedConnectionLabel.setLocation(0, 0);
        receivedConnectionLabel.setSize(300, 50);
        receivedConnectionLabel.setBorder(new LineBorder(Color.BLACK, 1));
        add(receivedConnectionLabel);


        showConnectionPanel = new ShowConnectionPanel();
        add(showConnectionPanel);


        scrollPane = new JScrollPane(showConnectionPanel);
        scrollPane.setLocation(0, 50);
        scrollPane.setSize(300, 550);

        add(scrollPane);
        revalidate();


        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        showConnectionPanel.addNewConnection("hossein", "ali");
        revalidate();

        setVisible(true);
    }

    public static void main(String[] args) {
        WaitingForConnectionFrame waitingForConnectionFrame = new WaitingForConnectionFrame();
    }
}
