package veiw;

import javax.swing.*;

/**
 * Created by mohsen on 7/5/17.
 */
public class MenuBar extends JMenuBar {
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem fileMenuItem;
    public MenuBar()
    {
        helpMenu = new JMenu("File");
        fileMenu = new JMenu("Help");
        fileMenuItem = new JMenuItem("File");
        add(fileMenu);
        add(helpMenu);
    }
}
