package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.message.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class AuthorAction extends AbstractGeRuMapAction {

    public AuthorAction () {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Create author");
        putValue(SHORT_DESCRIPTION, "Create author");
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
        }else if(selected.getMapNode() instanceof Project){
            String author = JOptionPane.showInputDialog(MainFrame.getInstance(), "Input your name");

            if(author == null)
                return;

            if(!(author.isEmpty())) {
                try {
                    ((Project) selected.getMapNode()).setAuthor(author);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                try {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.MUST_INSERT_NAME);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else{
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.PROJECT_NOT_SELECTED);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
