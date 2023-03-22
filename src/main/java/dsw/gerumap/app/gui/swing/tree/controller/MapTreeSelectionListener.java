package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath treePath = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem) treePath.getLastPathComponent();

        System.out.println("Selected node: " + treeItemSelected.getMapNode().getName());
        System.out.println("Path: " +  e.getPath());

    }
}
