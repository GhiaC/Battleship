package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    public StatusPanel()
    {
      //  setBackground(Color.gray);
        setLayout(null);
        setBorder(new LineBorder(Color.BLACK,1));
        setLocation(0,550);
        setSize(700,150);
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

    }
    private void makeShipNumberLabel4() {
        shipNumberLabel4 = new JLabel("*4");
        shipNumberLabel4.setLocation(185,16);
        shipNumberLabel4.setSize(30,20);
        add(shipNumberLabel4);
    }
    private void makeShipNumberLabel3()
    {
        shipNumberLabel3 = new JLabel("*3");
        shipNumberLabel3.setLocation(185,50);
        shipNumberLabel3.setSize(30,20);
        add(shipNumberLabel3);
    }
    private void makeShipNumberLabel2()
    {
        shipNumberLabel2 = new JLabel("*2");
        shipNumberLabel2.setLocation(185,85);
        shipNumberLabel2.setSize(30,20);
        add(shipNumberLabel2);
    }
    private void makeShipNumberLabel1()
    {
        shipNumberLabel1 = new JLabel("*1");
        shipNumberLabel1.setLocation(185,120);
        shipNumberLabel1.setSize(30,20);
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
    private void actoinListenterShipLabel4()
    {
        shipLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }
        });
    }
}
