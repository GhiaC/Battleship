package veiw;

/**
 * Created by mohsen on 7/7/17.
 */
public class PlayerField {
    private boolean[][] ships;
    private boolean[][] fired;
    private boolean[][] mark;
    private boolean[][] tmpShip;
    private boolean[][] conflictTmpShip;
    private int[] dx;
    private int[] dy;
    private int shipFired;
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
        tmpShip = new boolean[10][10];
        conflictTmpShip = new boolean[10][10];
        dx = new int[]{+1, +1, -1, -1 ,+1 , 0 , -1 , 0};
        dy = new int[]{-1, +1, +1, -1 , 0 , +1 , 0 , -1};
    }
    public boolean[][] getField()
    {
        return ships;
    }
    public boolean getTempShip(int i,int j)
    {
        return tmpShip[i][j];
    }
    public boolean getConflictShipAt(int i,int j)
    {
        return conflictTmpShip[i][j];
    }
    public boolean inBoundShip(Ship ship,int i,int j)
    {
        for(int k=0;k<ship.getSize();k++)
        {
            if(!inBound(i,j))
                return false;
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }
        return true;
    }
    public boolean notConflict(Ship ship,int i,int j)
    {
        for(int k=0;k<ship.getSize();k++)
        {
            for(int d = 0;d < 8;d++) {
                int jj = j + dx[d];
                int ii = i + dy[d];
                if(inBound(ii,jj) && ships[ii][jj])
                    return false;
            }
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }
        return true;
    }
    public void putConflictShip(Ship ship)
    {
        int j = ship.getX();
        int i = ship.getY();
        for(int k=0;k<ship.getSize();k++)
        {
           // if(inBound(i,j))
            conflictTmpShip[i][j] = true;
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }
    }
    public void removeAllConflictShip()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                conflictTmpShip[i][j] = false;
    }
    public void removeShip(Ship ship)
    {
        int j = ship.getX();
        int i = ship.getY();
        for(int k=0;k<ship.getSize();k++)
        {
           // if(inBound(i,j))
            ships[i][j] = false;
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }

    }
    public void putShip(Ship ship)
    {
        int j = ship.getX();
        int i = ship.getY();
        for(int k=0;k<ship.getSize();k++)
        {
           // if(inBound(i,j))
            ships[i][j] = true;
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }

    }
    public void putTempShip(Ship ship)
    {
        int j = ship.getX();
        int i = ship.getY();
        for(int k=0;k<ship.getSize();k++)
        {
           // if(inBound(i,j))
            tmpShip[i][j] = true;
            j+=dx[ship.getDir()+4];
            i+=dy[ship.getDir()+4];
        }
    }
    public void removeAllTempShip()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                tmpShip[i][j] = false;
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
        shipFired++;
        mark[i][j] = true;
        for(int k = 4;k < 8;k++)
        {
            int jj = j + dx[k];
            int ii = i + dy[k];
            if(inBound(ii,jj) && !mark[ii][jj]) {
                fired[ii][jj] =true;
                if(ships[ii][jj]) {
                    shipFired++;
                    shipCrushed(ii, jj);
                }
            }
        }
    }
    public int getShipFired()
    {
        return shipFired;
    }
    public void setShipFired()
    {
        shipFired = 0;
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
    public void resetField()
    {
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                ships[i][j] = false;
                tmpShip[i][j] = false;
                conflictTmpShip[i][j] = false;
            }
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
