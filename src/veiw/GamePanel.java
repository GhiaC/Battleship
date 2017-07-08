package veiw;

import tools.MessageManagerHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private JLabel[][] gameFieldDisplay;
    private PlayerField myField;
    public GamePanel()
    {
        /*DropTarget dropTarget =new DropTarget(this, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dropTargetDropEvent) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_REFERENCE);
                //Object data = dropTargetDropEvent.getTransferable().getTransferData(DataFlavor.)
            }
        })*/
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
            setDefaultField();
            setActionListenerGameField();
    }
    private void setDefaultField()
    {
        myField.setShipAt(0,0);
        myField.setShipAt(0,1);
        myField.setShipAt(0,2);
        myField.setShipAt(5,5);
        myField.setShipAt(5,6);
        myField.setShipAt(9,9);
        myField.setShipAt(8,9);
        myField.setShipAt(7,9);
        myField.setShipAt(6,9);
        myField.setShipAt(3,3);
        paintAgain();
    }
    private void paintAgain()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                if(myField.getShipAt(i,j) && !myField.getFiredAt(i,j))
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
    private void setActionListener(int i,int j)
    {
        gameFieldDisplay[i][j].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                MessageManagerHandler.sendPointAttack(i,j);
                myField.setFiredAt(i,j);
                paintAgain();
            }
        });
    }
}
