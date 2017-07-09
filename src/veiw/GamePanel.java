package veiw;

import tools.Game;
import tools.MessageManagerHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JLabel[][] gameFieldDisplay;
    private PlayerField myField;
    private StatusPanel statusPanel;
    private ArrayList<Ship> myShips;
    private InGameStatusPanel inGameStatusPanel;
    ImageIcon seaIcon = new ImageIcon("pic\\sea.png");
    ImageIcon grayIcon = new ImageIcon("pic\\gray.jpg");
    ImageIcon blackIcon = new ImageIcon("pic\\black.jpg");
    ImageIcon redIcon = new ImageIcon("pic\\red.png");
    ImageIcon fire = new ImageIcon("pic\\fire.gif");
    ImageIcon greenIcon = new ImageIcon("pic\\green.png");
    ImageIcon[] tempIcon = new ImageIcon[41];
    private boolean imageInitial = false;

    public GamePanel() {
        myShips = new ArrayList<Ship>();
        gameFieldDisplay = new JLabel[10][10];
        setLayout(new GridLayout(10, 10));
        setSize(300, 300);
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                gameFieldDisplay[i][j] = new JLabel(seaIcon);
                gameFieldDisplay[i][j].setBackground(Color.blue);
                gameFieldDisplay[i][j].setOpaque(true);
                gameFieldDisplay[i][j].setBorder(new LineBorder(new Color(55, 140, 220), 1));
                add(gameFieldDisplay[i][j]);
            }
        myField = new PlayerField();
        setActionListenerGameField();
    }

    public void removeOneShip() {
        if (myShips.size() > 0) {
            Ship ship = myShips.get(myShips.size() - 1);
            myField.removeShip(ship);
            myShips.remove(ship);
            statusPanel.undoShip(ship.getSize());
            paintAgain();
        }
    }

    private void initialImage(int width, int height) {
        for (int i = 0; i < 40; i++) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("pic\\" + (i + 1) + ".png"));
                Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                tempIcon[i] = new ImageIcon(dimg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedImage img = null;
            img = ImageIO.read(new File("pic\\green.jpg"));
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            tempIcon[40] = new ImageIcon(dimg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageInitial = true;
    }

    private int getMode(int mode) {
        if (mode == 100) {
            return 40;
        }
        int num = mode % 100;
        int dir = mode / 100;
        int result = num + dir * 10;
        return result;
    }

    private void paintAgain() {
        if (!imageInitial) {
            initialImage(gameFieldDisplay[0][0].getWidth(), gameFieldDisplay[0][0].getHeight());
        }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (!statusPanel.getGameMod()) {
                    if (myField.getConflictShipAt(i, j)) {
                        gameFieldDisplay[i][j].setIcon(redIcon);
                        gameFieldDisplay[i][j].setBackground(Color.RED);
                    } else if (myField.getTempShip(i, j) >= 0) {
                        gameFieldDisplay[i][j].setIcon(tempIcon[getMode(myField.getTempShip(i, j))]);
                        gameFieldDisplay[i][j].setBackground(Color.gray);
                    } else if (myField.getShipAt(i, j) != -1) {
                        gameFieldDisplay[i][j].setIcon(tempIcon[getMode(myField.getShipAt(i, j))]);
                        gameFieldDisplay[i][j].setBackground(Color.GREEN);
                    } else {
                        gameFieldDisplay[i][j].setIcon(seaIcon);
                    }
                } else {
                    if (myField.getFiredAt(i, j) && myField.getShipAt(i, j) != -1) {
                        gameFieldDisplay[i][j].setIcon(fire);
                        gameFieldDisplay[i][j].setBackground(Color.RED);
                    } else if (myField.getFiredAt(i, j)) {
                        gameFieldDisplay[i][j].setIcon(blackIcon);
                        gameFieldDisplay[i][j].setBackground(Color.BLACK);
                    } else if (myField.getShipAt(i, j) != -1 && !myField.getPlayerType()) {
                        gameFieldDisplay[i][j].setIcon(tempIcon[getMode(myField.getShipAt(i, j))]);
                        gameFieldDisplay[i][j].setBackground(Color.GREEN);
                    } else {
                        gameFieldDisplay[i][j].setIcon(seaIcon);
                        gameFieldDisplay[i][j].setBackground(Color.BLUE);
                    }

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

    private void setActionListenerGameField() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                setActionListener(i, j);
            }
        //    paintAgain();
    }

    private void putTempShip(Ship ship, int i, int j) {
        if (myField.inBoundShip(ship, i, j)) {
            ship.setX(j);
            ship.setY(i);
            if (myField.notConflict(ship, i, j)) {
                myField.removeAllTempShip();
                myField.removeAllConflictShip();
                myField.putTempShip(ship);

            } else {
                myField.removeAllTempShip();
                myField.removeAllConflictShip();
                myField.putConflictShip(ship);
            }
            paintAgain();
        }

    }

    private void setActionListener(int i, int j) {
        gameFieldDisplay[i][j].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    if (!statusPanel.getGameMod()) {
                        if (statusPanel.getChosenShip() != null)
                            statusPanel.getChosenShip().setDir();
                        Ship ship = statusPanel.getChosenShip();
                        putTempShip(ship, i, j);
                    }

                } else if (!statusPanel.getGameMod()) {
                    if (statusPanel.getChosenShip() != null) {
                        Ship ship = statusPanel.getChosenShip();
                        if (myField.inBoundShip(ship, i, j) && myField.notConflict(ship, i, j)) {
                            ship.setX(j);
                            ship.setY(i);
                            myField.putShip(ship);
                            myField.removeAllTempShip();
                            myShips.add(ship);
                            statusPanel.setChosenShip(null);
                            paintAgain();
                        }

                    }
                } else {
                    Game.pointClicked(i, j);
                    /*myField.setFiredAt(i,j);
                    if(myField.getShipFired() >0) {
                        inGameStatusPanel.removeEnemyShip(myField.getShipFired()/2 + 1);
                        myField.setShipFired();
                    }*/
                    paintAgain();
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (!statusPanel.getGameMod()) {
                    if (statusPanel.getChosenShip() != null) {
                        Ship ship = statusPanel.getChosenShip();
                        putTempShip(ship, i, j);
                    }
                }

            }

        });
    }

    public void setInGameStatusPanel(InGameStatusPanel inGameStatusPanel) {
        this.inGameStatusPanel = inGameStatusPanel;
    }

    public void resetPanel() {
        myField.resetField();
        paintAgain();
    }

    public void printField(PlayerField playerField) {
        myField = playerField;
        paintAgain();
    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
    }

    public PlayerField getMyField() {
        return myField;
    }
}
