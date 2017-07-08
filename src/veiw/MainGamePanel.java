package veiw;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/6/17.
 */
public class MainGamePanel extends JPanel {
    private GameViewerPanel gamePanel;
    private MenuBar menuBar;
    public MainGamePanel() {
     //   setBackground(Color.BLUE);
        setLayout(null);
        gamePanel = new GameViewerPanel();
        add(gamePanel);
        menuBar = new MenuBar();
        add(menuBar);
        setBorder(new LineBorder(Color.BLACK,1) {
        });
        setSize(700,550);
        setLocation(0,0);
    }
    public void printField(PlayerField playerField)
    {
        gamePanel.printField(playerField);
    }

}
