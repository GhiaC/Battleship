package tools;

import veiw.MainFrame;
import veiw.PlayerField;
import veiw.StatusPanel;

/**
 * Created by mohsen on 7/8/17.
 */
public class Game {
    private static PlayerField[] field = new PlayerField[2];
    private static MainFrame mainFrame ;
    private static int turn = 0;
    private static int readyCounter = 0;
    public Game() {

    }
    public static void openMainFrame(){
        mainFrame = new MainFrame();
    }
    public static void setOneField(PlayerField field,int ind)
    {
        Game.field[ind] = field;
        readyCounter++;
    }
    public static void pointClicked(int i,int j)
    {
        if(turn == 0 && readyCounter == 2) {
            System.out.println("DS");
            attackAt(i, j);
            sendAttackPoint(i,j);
        }

    }
    public static void setTurn(int num){
        turn = num;
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
    public static void printField(int ind)
    {
        mainFrame.printField(field[ind]);
    }
    public static void sendAttackPoint(int i,int j)
    {
        MessageManagerHandler.sendPointAttack(i,j);
    }
}
