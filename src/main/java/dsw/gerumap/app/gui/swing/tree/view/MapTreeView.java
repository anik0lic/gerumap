package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MapTreeView extends JTree {

    public MapTreeView (DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);

        MapTreeCellRenderer treeCellRenderer = new MapTreeCellRenderer();

        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, treeCellRenderer));
        setCellRenderer(treeCellRenderer);

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = getRowForLocation(e.getX(), e.getY());
                if(selRow != -1 && e.getClickCount() == 2) {

                    MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();

                    if(selected.getMapNode() instanceof Project){
                        if(((Project) selected.getMapNode()).getAuthor() != null) {
                            MainFrame.getInstance().getProjectView().setProject((Project) selected.getMapNode());
                            MainFrame.getInstance().getProjectView().refreshTabs();
                            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                        }else{
                            try {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.MUST_SET_AUTHOR);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                }
            }
        };
        addMouseListener(ml);

        setEditable(true);

    }

}
