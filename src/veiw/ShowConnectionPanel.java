package veiw;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by mohsen on 7/8/17.
 */
public class ShowConnectionPanel extends JPanel {
    private int numberOfConnection = 0;

    public ShowConnectionPanel()
    {
       setLocation(0,50);
       setBackground(Color.BLACK);
       setPreferredSize(new Dimension(300,2000));
       setLayout(null);
    }
    public void addNewConnection(String IP,String name)
    {
        NewConnectionPanel newConnectionPanel = new NewConnectionPanel(IP,name,numberOfConnection);
        numberOfConnection++;
        add(newConnectionPanel);
    }
}
