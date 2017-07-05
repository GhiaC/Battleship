package veiw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class GameViewerPanel extends JPanel {
    GamePanel gamePanel;
    MenuBar menuBar;
    public GameViewerPanel()
    {
        setLocation(125,50);
        setSize(450,450);

        gamePanel = new GamePanel();
        menuBar = new MenuBar();
        //setLayout(null);
        setLayout(new BorderLayout());
        add(gamePanel,BorderLayout.CENTER);
    }

}
