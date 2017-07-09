package veiw;

/**
 * Created by mohsen on 7/9/17.
 */
public class Ship {


    private int x,y,size,dir;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDir() {
        dir = (dir+1)%4;
    }

    public Ship(int size, int dir) {
        this.size = size;
        this.dir = dir;
    }

    public Ship(int x, int y, int size, int dir) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dir = dir;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getDir() {
        return dir;
    }


}
