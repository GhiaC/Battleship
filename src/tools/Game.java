package tools;

import veiw.*;

/**
 * Created by mohsen on 7/8/17.
 */
public class Game {
    private static PlayerField[] field = new PlayerField[2];
    private static MainFrame mainFrame;
    private static int turn = 0;
    private static int readyCounter = 0;
    private static boolean flag =false;
    public Game() {

    }

    public static void openMainFrame() {
        System.out.println("222222");
        if(!flag){
            mainFrame = new MainFrame();
            flag = true;
        }
    }

    public static void setOneField(PlayerField field, int ind) {
        Game.field[ind] = field;
        readyCounter++;
        if (ind == 0)
            Game.field[ind].setPlayerType(false);
        else
            Game.field[ind].setPlayerType(true);
        if (readyCounter == 2)
            Game.printField(false);
    }

    public static void pointClicked(int i, int j) {
        if (turn == 0 && readyCounter == 2) {
            sendAttackPoint(i, j);
            attackAt(i, j);
        }

    }

    public static void setTurn(int num) {
        turn = num;
    }

    public static void attackAt(int i, int j) {
        boolean flag = false;
        if (field[1 - turn].isChangeTurn(i, j)) {
            flag = true;
        }
        field[1 - turn].setFiredAt(i, j);
        if (field[1 - turn].getShipFired() > 0) {
            //TODO
            mainFrame.setShipFired(field[1 - turn].getShipFired() / 2 + 1, 1 - turn);
            field[1 - turn].setShipFired();

        }
        printField(flag);
        if (flag) {
            turn = 1 - turn;
        }
    }

    public static void printField(boolean flag) {
        class printFieldThread extends Thread {
            public void run() {
                try {
                    if (flag) {
                        mainFrame.printField(field[turn]);
                        Thread.sleep(2000);
                        mainFrame.printField(field[1 - turn]);
                    } else {
                        mainFrame.printField(field[1 - turn]);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
        printFieldThread printFieldThread = new printFieldThread();
        printFieldThread.start();
    }

    public static void sendAttackPoint(int i, int j) {
        MessageManagerHandler.sendPointAttack(i, j);
    }


}
