package veiw;

import javax.swing.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MainFrame extends JFrame{
    private MainPanel mainPanel;
    public MainFrame()
    {
        setSize(500,500);
        setLocation(400,400);
        setVisible(true);
        mainPanel = new MainPanel();
    }

}
