package veiw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestWaitingFrame extends JFrame {
    private JLabel waitForHost;
    private JButton cancelButton;

    public GuestWaitingFrame() {
        super("Please wait...");
        setLayout(null);
        setSize(500, 200);
        setLocation(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        waitForHost = new JLabel("Waiting for the host to join...");
        add(waitForHost);
        waitForHost.setLocation(50, 50);
        waitForHost.setSize(300, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(100, 50);
        cancelButton.setLocation(380, 130);
        add(cancelButton);
        setCancelButtonActionListener();
        setVisible(true);
    }

    private void setCancelButtonActionListener() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GuestWaitingFrame.this.dispose();
            }
        });
    }
}
