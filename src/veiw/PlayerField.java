package veiw;

/**
 * Created by mohsen on 7/7/17.
 */
public class PlayerField {
    private boolean[][] ships;
    private boolean[][] fired;
    private boolean[][] mark;
    private int[] dx;
    private int[] dy;
    public boolean isChangeTurn(int i,int j)
    {
        if(fired[i][j])
            return false;
        else if(ships[i][j])
            return false;
        else
            return true;

    }
    public PlayerField()
    {
        ships = new boolean[10][10];
        fired = new boolean[10][10];
        mark = new boolean[10][10];
        dx = new int[]{+1, +1, -1, -1 ,+1 , -1 , 0 , 0};
        dy = new int[]{-1, +1, +1, -1 , 0 , 0 , +1 , -1};
    }
    public void setShipAt(int i,int j)
    {
        ships[i][j] = true;
    }
    private boolean inBound(int i, int j)
    {
        return i>=0 && i<=9 && j>=0 && j<=9;
    }
    private boolean checkShip(int i,int j)
    {
        mark[i][j] = true;
        if(ships[i][j] && !fired[i][j]) return true;
        else if(!ships[i][j]) return false;
        for(int k=4;k<8;k++) {
            int jj = j + dx[k];
            int ii = i + dy[k];
            if (inBound(ii,jj) && !mark[ii][jj])
                if(checkShip(ii, jj))
                    return true;
        }
        return false;
    }
    private void shipCrushed(int i,int j)
    {
        mark[i][j] = true;
        for(int k = 4;k < 8;k++)
        {
            int jj = j + dx[k];
            int ii = i + dy[k];
            if(inBound(ii,jj) && !mark[ii][jj]) {
                fired[ii][jj] =true;
                if(ships[ii][jj])
                    shipCrushed(ii,jj);
            }
        }
    }
    private void checkNeighbors(int i,int j)
    {
        if(ships[i][j]) {
            for(int k=0;k<4;k++) {
                int jj = j + dx[k];
               int ii = i + dy[k];
               if(inBound(ii,jj))
                   setFiredAt(ii,jj);
           }
           if(!checkShip(i,j))
           {
               fillMark();
               shipCrushed(i,j);
           }
        }
    }
    public void setFiredAt(int i,int j)
    {
       fired[i][j] = true;
       checkNeighbors(i,j);
       fillMark();
    }
    private void fillMark()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                mark[i][j] = false;
    }
    public boolean getShipAt(int i,int j)
    {
        return ships[i][j];
    }
    public boolean getFiredAt(int i,int j)
    {
        return fired[i][j];
    }
}
