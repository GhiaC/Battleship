package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MainPanel extends JPanel{
    ChatPanel chatPanel;
    StatusPanel statusPanel;
    GameVeiwerPanel gameVeiwerPanel;
    public MainPanel()
    {
        setLayout(new BorderLayout());
        chatPanel = new ChatPanel();
        gameVeiwerPanel = new GameVeiwerPanel();
        statusPanel = new StatusPanel();
        setBackground(Color.BLUE);
        add(chatPanel,BorderLayout.EAST);
        add(statusPanel,BorderLayout.SOUTH);
    }


}
