package veiw;

import tools.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohsen on 7/9/17.
 */
public class InGameStatusPanel extends JPanel {
    private JLabel myNameLabel;
    private JLabel enemyNameLabel;
    private JLabel[] myShip4;
    private JLabel[] myShip3;
    private JLabel[] myShip2;
    private JLabel[] myShip1;
    private JLabel[] enemyShip4;
    private JLabel[] enemyShip3;
    private JLabel[] enemyShip2;
    private JLabel[] enemyShip1;

    private int[] myShipCounter;
    private int[] enemyShipCounter;
    private int numberOfMyship;
    private int numberOfEnemyShip;
    public InGameStatusPanel()
    {
        setLocation(0,550);
        setSize(700,150);
        setLayout(null);
        //setBackground(Color.BLACK);
        myNameLabel = new JLabel("You");
        myNameLabel.setLocation(10,0);
        myNameLabel.setSize(50,30);
        numberOfMyship = 10;
        numberOfEnemyShip = 10;
        add(myNameLabel);



        enemyNameLabel = new JLabel("Enemy");
        enemyNameLabel.setLocation(350,0);
        enemyNameLabel.setSize(50,30);
        add(enemyNameLabel);
        myShipCounter = new int[5];
        enemyShipCounter = new int[5];
        createMyShip4();
        createMyShip3();
        createMyShip2();
        createMyShip1();


        createEnemyShip4();
        createEnemyShip3();
        createEnemyShip2();
        createEnemyShip1();
    }

    private void createMyShip4()
    {
        myShip4 = new JLabel[1];
        for(int i=0;i<1;i++) {
            myShip4[i] = new JLabel();
            myShip4[i].setSize(160,25);
            myShip4[i].setLocation(10 + 160*i * i,30 );
            myShip4[i].setOpaque(true);
            myShip4[i].setBackground(Color.GREEN);
            add(myShip4[i]);
        }
    }
    private void createMyShip3()
    {
        myShip3 = new JLabel[2];
        for(int i=0;i<2;i++) {
            myShip3[i] = new JLabel();
            myShip3[i].setSize(110,25);
            myShip3[i].setLocation(10 + 120*i ,60);
            myShip3[i].setOpaque(true);
            myShip3[i].setBackground(Color.GREEN);
            add(myShip3[i]);
        }
    }
    private void createMyShip2()
    {
        myShip2 = new JLabel[3];
        for(int i=0;i<3;i++) {
            myShip2[i] = new JLabel();
            myShip2[i].setSize(70,25);
            myShip2[i].setLocation(10 + 80* i ,90);
            myShip2[i].setOpaque(true);
            myShip2[i].setBackground(Color.GREEN);
            add(myShip2[i]);
        }
    }
    private void createMyShip1()
    {
        myShip1 = new JLabel[4];
        for(int i=0;i<4;i++) {
            myShip1[i] = new JLabel();
            myShip1[i].setSize(30,25);
            myShip1[i].setLocation(10 + 40* i ,120);
            myShip1[i].setOpaque(true);
            myShip1[i].setBackground(Color.GREEN);
            add(myShip1[i]);
        }
    }
    private void createEnemyShip4()
    {
        enemyShip4 = new JLabel[1];
        for(int i=0;i<1;i++) {
            enemyShip4[i] = new JLabel();
            enemyShip4[i].setSize(160,25);
            enemyShip4[i].setLocation(350 + 160*i,30);
            enemyShip4[i].setOpaque(true);
            enemyShip4[i].setBackground(Color.GREEN);
            add(enemyShip4[i]);
        }
    }
    private void createEnemyShip3()
    {
        enemyShip3 = new JLabel[2];
        for(int i=0;i<2;i++) {
            enemyShip3[i] = new JLabel();
            enemyShip3[i].setSize(110,25);
            enemyShip3[i].setLocation(350 + 120*i,60);
            enemyShip3[i].setOpaque(true);
            enemyShip3[i].setBackground(Color.GREEN);
            add(enemyShip3[i]);
        }
    }
    private void createEnemyShip2()
    {
        enemyShip2 = new JLabel[3];
        for(int i=0;i<3;i++) {
            enemyShip2[i] = new JLabel();
            enemyShip2[i].setSize(70,25);
            enemyShip2[i].setLocation(350 + 80*i,90);
            enemyShip2[i].setOpaque(true);
            enemyShip2[i].setBackground(Color.GREEN);
            add(enemyShip2[i]);
        }
    }
    private void createEnemyShip1()
    {
        enemyShip1 = new JLabel[4];
        for(int i=0;i<4;i++) {
            enemyShip1[i] = new JLabel();
            enemyShip1[i].setSize(30,25);
            enemyShip1[i].setLocation(350 + 40*i,120);
            enemyShip1[i].setOpaque(true);
            enemyShip1[i].setBackground(Color.GREEN);
            add(enemyShip1[i]);
        }
    }
    public void removeEnemyShip(int number)
    {
        if(number == 4)
            removeEnemyShip4();
        else if(number == 3)
            removeEnemyShip3();
        if(number == 2)
            removeEnemyShip2();
        if(number == 1)
            removeEnemyShip1();
        revalidate();

    }
    public void removeMyShip(int number)
    {
        if(number == 4)
            removeMyShip4();
        else if(number == 3)
            removeMyShip3();
        if(number == 2)
            removeMyShip2();
        if(number == 1)
            removeMyShip1();
        revalidate();
    }
    private void removeMyShip4()
    {
        myShip4[myShipCounter[4]].setBackground(Color.RED);
        myShipCounter[4]++;
          numberOfMyship--;
        if(numberOfMyship == 0)
            showLose();
    }
    private void removeMyShip3()
    {
        myShip3[myShipCounter[3]].setBackground(Color.RED);
        myShipCounter[3]++;
          numberOfMyship--;
        if(numberOfMyship == 0)
            showLose();
    }
    private void removeMyShip2()
    {
        myShip2[myShipCounter[2]].setBackground(Color.RED);
        myShipCounter[2]++;
          numberOfMyship--;
        if(numberOfMyship == 0)
            showLose();
    }private void removeMyShip1()
    {
        myShip1[myShipCounter[1]].setBackground(Color.RED);
        myShipCounter[1]++;
        numberOfMyship--;
        if(numberOfMyship == 0)
            showLose();
    }
    private void removeEnemyShip4()
    {
        enemyShip4[enemyShipCounter[4]].setBackground(Color.RED);
        enemyShipCounter[4]++;
        numberOfEnemyShip--;
        if(numberOfEnemyShip == 0)
            showWin();
    }
    private void removeEnemyShip3()
    {
        enemyShip3[enemyShipCounter[3]].setBackground(Color.RED);
        enemyShipCounter[3]++;
        numberOfEnemyShip--;
        if(numberOfEnemyShip == 0)
            showWin();
    }
    private void removeEnemyShip2()
    {
        enemyShip2[enemyShipCounter[2]].setBackground(Color.RED);
        enemyShipCounter[2]++;
        numberOfEnemyShip--;
        if(numberOfEnemyShip == 0)
            showWin();
    }
    private void removeEnemyShip1()
    {
        enemyShip1[enemyShipCounter[1]].setBackground(Color.RED);
        enemyShipCounter[1]++;
        numberOfEnemyShip--;
        if(numberOfEnemyShip == 0)
            showWin();
    }
    private void showMessage(String text)
    {
        JFrame frame = new JFrame();
        frame.setLocation(600,500);
        frame.setSize(300,200);
        frame.setLayout(null);
        JLabel textLabel = new JLabel(text);
        textLabel.setSize(200,200);
        textLabel.setLocation(75,0);
        frame.add(textLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        //Game.closeMainFrame();
    }
    private void showWin()
    {
        showMessage("You win the game!");
    }
    private void showLose()
    {
        showMessage("You lose the game!");
    }
}
