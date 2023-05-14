package dsw.gerumap.app.controller.actions;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.tree.model.MapTreeItem;
import dsw.gerumap.app.view.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DeleteAction extends AbstractGeRuMapAction {

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();

        if(selected == null) {
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.NODE_NOT_SELECTED);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }

        if(selected.getMapNode() instanceof ProjectExplorer){
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.CANNOT_DELETE_PROJECT_EXPLORER);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }

        if(selected.getMapNode() instanceof Project){
            MainFrame.getInstance().getProjectView().clearTab();
        }

        try {
            MainFrame.getInstance().getMapTree().deleteChild(selected);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

