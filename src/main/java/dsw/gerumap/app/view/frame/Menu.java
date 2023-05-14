package dsw.gerumap.app.view.frame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {

    public Menu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
        editMenu.addSeparator();
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAuthorAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
    }
}
