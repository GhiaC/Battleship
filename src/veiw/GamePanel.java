package veiw;

import tools.Game;
import tools.MessageManagerHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JLabel[][] gameFieldDisplay;
    private PlayerField myField;
    private StatusPanel statusPanel;
    private ArrayList<Ship> myShips;
    private InGameStatusPanel inGameStatusPanel;
    public GamePanel()
    {
        myShips = new ArrayList<Ship>();
        gameFieldDisplay = new JLabel[10][10];
        setLayout(new GridLayout(10,10));
        setSize(300,300);
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
            {
                gameFieldDisplay[i][j] = new JLabel();
                //if(i == 5 && j == 5)
                gameFieldDisplay[i][j].setBackground(Color.blue);
                gameFieldDisplay[i][j].setOpaque(true);
                //gameFieldDisplay[i][j].setBackground(Color.gray);
                gameFieldDisplay[i][j].setBorder(new LineBorder(Color.BLACK,1));
                add(gameFieldDisplay[i][j]);
            }
            myField = new PlayerField();
      //      setDefaultField();
            setActionListenerGameField();
    }
    public void removeOneShip()
    {
        if(myShips.size() > 0) {
            Ship ship = myShips.get(myShips.size()-1);
            myField.removeShip(ship);
            myShips.remove(ship);
            statusPanel.undoShip(ship.getSize());
            paintAgain();
        }
    }

    private void paintAgain()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
            if(!statusPanel.getGameMod()) {
                if (myField.getConflictShipAt(i, j))
                    gameFieldDisplay[i][j].setBackground(Color.RED);
                else if (myField.getTempShip(i, j))
                    gameFieldDisplay[i][j].setBackground(Color.gray);
                else if (myField.getShipAt(i, j))
                    gameFieldDisplay[i][j].setBackground(Color.GREEN);
                else
                    gameFieldDisplay[i][j].setBackground(Color.BLUE);
            }
            else
            {
                System.out.println(myField.getPlayerType());
                if(myField.getFiredAt(i,j) && myField.getShipAt(i,j))
                    gameFieldDisplay[i][j].setBackground(Color.RED);
                else if(myField.getFiredAt(i,j))
                    gameFieldDisplay[i][j].setBackground(Color.BLACK);
                else if(myField.getShipAt(i,j) && !myField.getPlayerType())
                    gameFieldDisplay[i][j].setBackground(Color.GREEN);
                else
                    gameFieldDisplay[i][j].setBackground(Color.BLUE);

            }
                /*if(myField.getShipAt(i,j) && !myField.getFiredAt(i,j))
                {
                    gameFieldDisplay[i][j].setBackground(Color.GREEN);
                }
                else if(myField.getShipAt(i,j))
                {
                    gameFieldDisplay[i][j].setBackground(Color.RED);
                }
                else if(myField.getFiredAt(i,j))
                {
                    System.out.println("D");
                    gameFieldDisplay[i][j].setBackground(Color.BLACK);
                }
                else
                    gameFieldDisplay[i][j].setBackground(Color.BLUE);
                 */
            }
        repaint();
        revalidate();
    }
    private void setActionListenerGameField()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
            {
                setActionListener(i,j);
            }
    //    paintAgain();
    }
    private void putTempShip(Ship ship,int i ,int j)
    {
        if(myField.inBoundShip(ship,i,j))
        {
            ship.setX(j);
            ship.setY(i);
            if(myField.notConflict(ship,i,j)) {
                myField.removeAllTempShip();

                myField.removeAllConflictShip();
                myField.putTempShip(ship);

            }
            else
            {
                myField.removeAllTempShip();
                myField.removeAllConflictShip();
                myField.putConflictShip(ship);
            }
            paintAgain();
        }

    }
    private void setActionListener(int i,int j)
    {
        gameFieldDisplay[i][j].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON3)
                {
                    if(!statusPanel.getGameMod())
                    {
                        if(statusPanel.getChosenShip()!=null)
                            statusPanel.getChosenShip().setDir();
                        Ship ship = statusPanel.getChosenShip();
                        putTempShip(ship,i,j);
                    }

                }
                else if(!statusPanel.getGameMod())
                {
                    if(statusPanel.getChosenShip()!=null)
                    {
                         Ship ship = statusPanel.getChosenShip();
                        if (myField.inBoundShip(ship,i,j) && myField.notConflict(ship, i, j)) {
                            ship.setX(j);
                            ship.setY(i);
                            myField.removeAllTempShip();
                            myField.putShip(ship);
                            myShips.add(ship);
                            statusPanel.setChosenShip(null);
                            paintAgain();
                        }

                    }
                }
                else
                {
                    Game.pointClicked(i,j);
                    /*myField.setFiredAt(i,j);
                    if(myField.getShipFired() >0) {
                        inGameStatusPanel.removeEnemyShip(myField.getShipFired()/2 + 1);
                        myField.setShipFired();
                    }*/
                    System.out.println("fuck you");
                    paintAgain();
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if(!statusPanel.getGameMod())
                {
                    if(statusPanel.getChosenShip()!=null) {
                        Ship ship = statusPanel.getChosenShip();
                        putTempShip(ship,i,j);
                    }
                }

            }

        });
    }
    public void setInGameStatusPanel(InGameStatusPanel inGameStatusPanel)
    {
        this.inGameStatusPanel = inGameStatusPanel;
    }
    public void resetPanel()
    {
        myField.resetField();
        paintAgain();
    }
    public void printField(PlayerField playerField)
    {
        myField = playerField;
        paintAgain();
    }
    public void setStatusPanel(StatusPanel statusPanel)
    {
        this.statusPanel = statusPanel;
    }
    public PlayerField getMyField()
    {
        return myField;
    }
}
