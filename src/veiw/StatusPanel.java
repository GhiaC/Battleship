package veiw;

import logic.MessageManager;
import tools.Game;
import tools.MessageManagerHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by mohsen on 7/5/17.
 */
public class StatusPanel extends JPanel {
    private JLabel shipLabel4;
    private JLabel shipLabel3;
    private JLabel shipLabel2;
    private JLabel shipLabel1;

    private JLabel shipNumberLabel4;
    private JLabel shipNumberLabel3;
    private JLabel shipNumberLabel2;
    private JLabel shipNumberLabel1;
    private JButton readyButton;
    private JButton resetButton;
    private JButton undoButton;

    private GamePanel gamePanel;
    private boolean gameMod;
    private Ship chosenShip;


    private int[] numberOfShips;


    private MainPanel mainPanel;

    public StatusPanel()
    {
      //  setBackground(Color.gray);
        setLayout(null);
        setBorder(new LineBorder(Color.BLACK,1));
        setLocation(0,550);
        setSize(700,150);
        numberOfShips = new int[5];
        makeUndoButton();
        makeShipLabel4();
        makeShipLabel3();
        makeShipLabel2();
        makeShipLabel1();
        makeReadyButton();
        makeResetButton();
        makeShipNumberLabel4();
        makeShipNumberLabel3();
        makeShipNumberLabel2();
        makeShipNumberLabel1();
        chosenShip = null;

        actionListenerShipLabel4();
        actionListenerShipLabel3();
        actionListenerShipLabel2();
        actionListenerShipLabel1();
        setActionListenerResetButton();
        setActionListenerReadyButton();
        setUndoButtonActionListener();
        gameMod = false;
    }
    private void makeUndoButton()
    {
        try {
            undoButton = new JButton("");
            BufferedImage img = null;
            img = ImageIO.read(new File( "undo.png"));
            Image dimg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            undoButton.setIcon(imageIcon);
            undoButton.setSize(50, 50);
            undoButton.setLocation(580, 0);
            add(undoButton);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void setUndoButtonActionListener()
    {
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamePanel.removeOneShip();
            }
        });
    }
    public void setMainPanel(MainPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }
    public boolean getGameMod()
    {
        return gameMod;
    }
    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    public Ship getChosenShip()
    {
        return chosenShip;
    }
    private void countNumberOfShips()
    {
        numberOfShips[chosenShip.getSize()]--;

        showNumberOfShips();
    }
    private void showNumberOfShips()
    {
        shipNumberLabel1.setText("*" + numberOfShips[1]);
        shipNumberLabel2.setText("*" + numberOfShips[2]);
        shipNumberLabel3.setText("*" + numberOfShips[3]);
        shipNumberLabel4.setText("*" + numberOfShips[4]);
        revalidate();

    }
    public void setChosenShip(Ship ship)
    {
        countNumberOfShips();
        chosenShip = ship;
        removeAllBackground();
    }
    private void makeShipNumberLabel4() {
        shipNumberLabel4 = new JLabel("*1");
        shipNumberLabel4.setLocation(185,16);
        shipNumberLabel4.setSize(30,20);

        numberOfShips[4] = 1;
        add(shipNumberLabel4);
    }
    private void makeShipNumberLabel3()
    {
        shipNumberLabel3 = new JLabel("*2");
        shipNumberLabel3.setLocation(185,50);
        shipNumberLabel3.setSize(30,20);


        numberOfShips[3] = 2;

        add(shipNumberLabel3);
    }
    private void makeShipNumberLabel2()
    {
        shipNumberLabel2 = new JLabel("*3");
        shipNumberLabel2.setLocation(185,85);
        shipNumberLabel2.setSize(30,20);

        numberOfShips[2] = 3;
        add(shipNumberLabel2);
    }
    private void makeShipNumberLabel1()
    {
        shipNumberLabel1 = new JLabel("*4");
        shipNumberLabel1.setLocation(185,120);
        shipNumberLabel1.setSize(30,20);

        numberOfShips[1] = 4;
        add(shipNumberLabel1);

    }
    private void makeReadyButton() {
        readyButton = new JButton("Ready");
        readyButton.setLocation(580,100);
        readyButton.setSize(100,40);
        add(readyButton);
    }
    private void makeResetButton()
    {
        resetButton = new JButton("Reset");
        resetButton.setLocation(460,100);
        resetButton.setSize(100,40);
        add(resetButton);
    }
    private void makeShipLabel3() {
        shipLabel3 = new JLabel();
        shipLabel3.setSize(120,20);
        shipLabel3.setLocation(20,50);
        shipLabel3.setOpaque(true);
        shipLabel3.setBorder(new LineBorder(Color.BLACK,1));
        add(shipLabel3);

    }
    private void makeShipLabel2()
    {
        shipLabel2 = new JLabel();
        shipLabel2.setSize(90,20);
        shipLabel2.setLocation(20,85);
        shipLabel2.setOpaque(true);
        shipLabel2.setBorder(new LineBorder(Color.BLACK,1));
        add(shipLabel2);
    }
    private void makeShipLabel1()
    {
        shipLabel1 = new JLabel();
        shipLabel1.setSize(55,20);
        shipLabel1.setLocation(20,120);
        shipLabel1.setOpaque(true);
        shipLabel1.setBorder(new LineBorder(Color.BLACK,1));
        add(shipLabel1);
    }
    private void makeShipLabel4() {
        shipLabel4 = new JLabel();
        shipLabel4.setSize(160,20);
        shipLabel4.setLocation(20,15);

        shipLabel4.setOpaque(true);
        JTextField field = new JTextField();
        field.setDragEnabled(true);
        JLabel label = new JLabel();
        shipLabel4.setBorder(new LineBorder(Color.BLACK,1));
        add(shipLabel4);
    }
    private void removeAllBackground()
    {

        shipLabel4.setBackground(Color.white);
        shipLabel3.setBackground(Color.white);
        shipLabel2.setBackground(Color.white);
        shipLabel1.setBackground(Color.white);

    }
    private void actionListenerShipLabel4()
    {
        shipLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(numberOfShips[4] > 0) {
                    removeAllBackground();
                    shipLabel4.setBackground(Color.RED);
                    chosenShip = new Ship(4, 0);
                }
            }
        });
    }
    private void actionListenerShipLabel3()
    {
        shipLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(numberOfShips[3] > 0) {
                    removeAllBackground();
                    shipLabel3.setBackground(Color.RED);
                    chosenShip = new Ship(3, 0);
                }
            }
        });
    }private void actionListenerShipLabel2()
    {
        shipLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(numberOfShips[2] > 0) {
                    removeAllBackground();
                    shipLabel2.setBackground(Color.RED);
                    chosenShip = new Ship(2, 0);
                }
            }
        });
    }private void actionListenerShipLabel1()
    {
        shipLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(numberOfShips[1] > 0) {
                    removeAllBackground();
                    shipLabel1.setBackground(Color.RED);
                    chosenShip = new Ship(1, 0);
                }
            }
        });
    }
    private void setActionListenerResetButton()
    {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamePanel.resetPanel();
                gamePanel.removeAllShip();
                for(int i=1;i<=4;i++)
                   numberOfShips[i] = 4 - i + 1;
                showNumberOfShips();
            }
        });
    }
    private boolean allShipPutted()
    {
        for(int i=1;i<=4;i++)
            if(numberOfShips[i] >0)
                return false;
        return true;
    }
    private void setActionListenerReadyButton()
    {
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(allShipPutted()) {
                    gameMod = !gameMod;
                    mainPanel.openInGameStatusPanel();
                    Game.setOneField(gamePanel.getMyField(),0);
                    MessageManagerHandler.sendField(gamePanel.getMyField());
                    //TODO maybe he can't start the game we should handle that
                }
            }
        });
    }
    public void undoShip(int size)
    {
        numberOfShips[size]++;
        showNumberOfShips();
    }
}
