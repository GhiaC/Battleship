package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class GamePanel extends JPanel {
    JLabel[][] gameField;
    public GamePanel()
    {

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
