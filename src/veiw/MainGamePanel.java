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
    JLabel myTurnLabel;
    JLabel enemyTurnLabel;
    public MainGamePanel() {
        //setBackground(Color.BLUE);
        setLayout(null);
        gamePanel = new GameViewerPanel();
        add(gamePanel);
        menuBar = new MenuBar();
        add(menuBar);
        setBorder(new LineBorder(Color.BLACK,1));
        setSize(700,550);
        setLocation(0,0);
        myTurnLabel = new JLabel("Your turn");
        enemyTurnLabel = new JLabel("Enemy turn");

        myTurnLabel.setSize(200,50);
        myTurnLabel.setLocation(15,0);
        add(myTurnLabel);
        myTurnLabel.setVisible(false);
        enemyTurnLabel.setSize(200,50);
        enemyTurnLabel.setLocation(15,0);
        add(enemyTurnLabel);
        enemyTurnLabel.setVisible(false);

    }
    public void printField(PlayerField playerField)
    {
        revalidate();
        repaint();
        if(playerField.getPlayerType()) {

            myTurnLabel.setVisible(true);
            enemyTurnLabel.setVisible(false);
        }
        else {
            myTurnLabel.setVisible(false);
            enemyTurnLabel.setVisible(true);

        }

        revalidate();
        repaint();
        gamePanel.printField(playerField);
    }
    public GamePanel getGamePanel()
    {
        return gamePanel.getGamePanel();
    }
    public void setStatusPanel(StatusPanel statusPanel)
    {
        gamePanel.setStatusPanel(statusPanel);
    }
    public void setInGameStatusPanel(InGameStatusPanel inGameStatusPanel)
    {
        gamePanel.setInGameStatusPanel(inGameStatusPanel);
    }

}
