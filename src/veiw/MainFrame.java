package veiw;

import javax.swing.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MainFrame extends JFrame{
    private MainPanel mainPanel;
    private MenuBar menuBar;
    public MainFrame()
    {
        setSize(1000,720);
        setLocation(400,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        mainPanel = new MainPanel();
        add(mainPanel);

//        setUndecorated(true);
        menuBar = new MenuBar();
        //add(menuBar);
        setJMenuBar(menuBar);
        setVisible(true);
    }
    public void printField(PlayerField playerField)
    {
        mainPanel.printField(playerField);
    }
    public void setShipFired(int size,int type)
    {
        mainPanel.setShipFired(size,type);

    }

}
