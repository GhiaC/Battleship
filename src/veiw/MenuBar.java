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
        helpMenu = new JMenu("help");
        fileMenu = new JMenu("File");
        fileMenuItem = new JMenuItem("Save Chat");
        fileMenu.add(fileMenuItem);
        add(fileMenu);
        add(helpMenu);
    }
}
