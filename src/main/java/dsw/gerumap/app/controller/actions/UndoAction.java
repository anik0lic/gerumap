package dsw.gerumap.app.controller.actions;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class UndoAction extends AbstractGeRuMapAction {

    public UndoAction () {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ApplicationFramework.getInstance().getGui().getCommandManager().undoCommand();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
