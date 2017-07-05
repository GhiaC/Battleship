package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MainPanel extends JPanel{
    ChatPanel chatPanel;
    StatusPanel statusPanel;
    MenuBar menuBar;
    GameViewerPanel gameVeiwerPanel;
    public MainPanel()
    {
        setLayout(null);
        setSize(1000,700);
        chatPanel = new ChatPanel();
        gameVeiwerPanel = new GameViewerPanel();
        statusPanel = new StatusPanel();
        menuBar = new MenuBar();
        add(chatPanel);
        add(statusPanel);
        add(gameVeiwerPanel);
        add(menuBar);
        repaint();
    }


}
