package veiw;

import javax.swing.*;

public class MainPanel extends JPanel{
    ChatPanel chatPanel;
    StatusPanel statusPanel;
    MenuBar menuBar;
    //GameViewerPanel gameVeiwerPanel;
    MainGamePanel mainGamePanel;
    public MainPanel()
    {
        setLayout(null);
        setSize(1000,700);
        chatPanel = new ChatPanel();
//gameVeiwerPanel = new GameViewerPanel();
        mainGamePanel = new MainGamePanel();
        statusPanel = new StatusPanel();
        menuBar = new MenuBar();
        add(chatPanel);
        add(statusPanel);
// add(gameVeiwerPanel);
        add(mainGamePanel);
        add(menuBar);
        repaint();
    }


}