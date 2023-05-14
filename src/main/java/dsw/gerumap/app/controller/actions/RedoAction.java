package dsw.gerumap.app.controller.actions;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RedoAction extends AbstractGeRuMapAction {

    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo.png "));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ApplicationFramework.getInstance().getGui().getCommandManager().doCommand();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
