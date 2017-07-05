package veiw;

import javax.swing.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MainFrame extends JFrame{
    private MainPanel mainPanel;
    public MainFrame()
    {
        setSize(1000,700);
        setLocation(400,150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setResizable(false);
        mainPanel = new MainPanel();
        add(mainPanel);
    }

}
