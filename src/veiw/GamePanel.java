package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

/**
 * Created by mohsen on 7/5/17.
 */
public class GamePanel extends JPanel {
    JLabel[][] gameField;
    public GamePanel()
    {
        /*DropTarget dropTarget =new DropTarget(this, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dropTargetDropEvent) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_REFERENCE);
                //Object data = dropTargetDropEvent.getTransferable().getTransferData(DataFlavor.)
            }
        })*/
        gameField = new JLabel[10][10];
        setLayout(new GridLayout(10,10));
        setSize(300,300);
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
            {
                gameField[i][j] = new JLabel();
                //if(i == 5 && j == 5)
                 //   gameField[i][j].setBackground(Color.BLACK);
                gameField[i][j].setOpaque(true);
                gameField[i][j].setBackground(Color.gray);
                gameField[i][j].setBorder(new LineBorder(Color.BLACK,1));
                add(gameField[i][j]);
            }
    }
}
