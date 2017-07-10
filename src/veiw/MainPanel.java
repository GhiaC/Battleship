package veiw;

import javax.swing.*;

public class MainPanel extends JPanel{
    ChatPanel chatPanel;
    StatusPanel statusPanel;
    MenuBar menuBar;
    MainGamePanel mainGamePanel;
    InGameStatusPanel inGameStatusPanel;
    public MainPanel()
    {
        setLayout(null);
        setSize(1000,700);
        chatPanel = new ChatPanel();
        mainGamePanel = new MainGamePanel();
        statusPanel = new StatusPanel();
        menuBar = new MenuBar();
        statusPanel.setGamePanel(mainGamePanel.getGamePanel());
        statusPanel.setMainPanel(this);
        mainGamePanel.setStatusPanel(statusPanel);
        add(chatPanel);
        add(statusPanel);
        add(mainGamePanel);
        add(menuBar);
        repaint();
    }
    public void openInGameStatusPanel()
    {
       // remove(statusPanel);
        statusPanel.setVisible(false);
        inGameStatusPanel = new InGameStatusPanel();
        add(inGameStatusPanel);
        mainGamePanel.setInGameStatusPanel(inGameStatusPanel);
        revalidate();

    }
    public void printField(PlayerField playerField)
    {
        mainGamePanel.printField(playerField);
    }
    public void setShipFired(int size,int type)
    {
        if(type == 1)
            inGameStatusPanel.removeEnemyShip(size);
        else
            inGameStatusPanel.removeMyShip(size);
    }
}