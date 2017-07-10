package veiw;

import tools.ChatHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohsen on 7/5/17.
 */
public class MenuBar extends JMenuBar {
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem fileMenuItem;
    JMenuItem historyMenuItem;
    JMenuItem exitItem;
    public MenuBar()
    {
        helpMenu = new JMenu("help");
        fileMenu = new JMenu("File");
        fileMenuItem = new JMenuItem("Save Chat");
        historyMenuItem = new JMenuItem("History Chat");
        exitItem = new JMenuItem("exit");
        fileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatHandler.saveMessage();
            }
        });
        historyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoryChat();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(fileMenuItem);
        fileMenu.add(historyMenuItem);
        fileMenu.add(exitItem);
        add(fileMenu);
//        add(helpMenu);
    }
}
