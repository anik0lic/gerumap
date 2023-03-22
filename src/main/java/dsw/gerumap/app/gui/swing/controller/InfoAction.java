package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.InfoDialog;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class InfoAction extends AbstractGeRuMapAction{

    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InfoDialog help = null;
        try {
            help = new InfoDialog(MainFrame.getInstance(), "Info", false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        help.setVisible(true);
    }
}
