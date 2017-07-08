package veiw;

import javax.swing.*;

public class MainPanel extends JPanel{
    ChatPanel chatPanel;
    StatusPanel statusPanel;
    MenuBar menuBar;
    MainGamePanel mainGamePanel;
    public MainPanel()
    {
        setLayout(null);
        setSize(1000,700);
        chatPanel = new ChatPanel();
        mainGamePanel = new MainGamePanel();
        statusPanel = new StatusPanel();
        menuBar = new MenuBar();
        add(chatPanel);
        add(statusPanel);
        add(mainGamePanel);
        add(menuBar);
        repaint();
    }
    public void printField(PlayerField playerField)
    {
        mainGamePanel.printField(playerField);
    }


}