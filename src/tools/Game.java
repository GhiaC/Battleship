package tools;

import veiw.MainFrame;
import veiw.PlayerField;

/**
 * Created by mohsen on 7/8/17.
 */
public class Game {
    private static PlayerField[] field = new PlayerField[2];
    private static MainFrame mainFrame = new MainFrame();
    private static int turn;
    public Game()
    {
    }
    public static void setOneField(PlayerField field,int ind)
    {
        Game.field[ind] = field;
    }
    public static void attackAt(int i,int j)
    {
        boolean flag = false;
        if(field[turn].isChangeTurn(i,j))
        {
            flag = true;
        }
        field[turn].setFiredAt(i,j);
        mainFrame.printField(field[turn]);
        if(flag)
            turn = 1-turn;
    }
    public static void sendAttackPoint(int i,int j)
    {
        //TODO by masoud
    }
}
