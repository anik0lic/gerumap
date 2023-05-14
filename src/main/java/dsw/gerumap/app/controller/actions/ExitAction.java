package dsw.gerumap.app.controller.actions;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGeRuMapAction {

    public ExitAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/log-out.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }

    public void actionPerformed(ActionEvent arg0){
        System.exit(0);
    }

}
