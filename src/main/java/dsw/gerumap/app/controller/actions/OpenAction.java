package dsw.gerumap.app.controller.actions;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.view.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractGeRuMapAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open .png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getMapTree().loadProject(p);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
