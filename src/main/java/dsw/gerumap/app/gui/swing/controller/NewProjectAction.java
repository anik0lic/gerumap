package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.maprepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.message.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class NewProjectAction extends AbstractGeRuMapAction {

    public NewProjectAction () {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
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

        if(selected.getMapNode() instanceof Project){
            try {
                MainFrame.getInstance().getMapTree().addChild(selected);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }


        if(selected.getMapNode() instanceof MindMap){
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.CANNOT_ADD_CHILD_TO_LEAF);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        try {
            MainFrame.getInstance().getMapTree().addChild(selected);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
