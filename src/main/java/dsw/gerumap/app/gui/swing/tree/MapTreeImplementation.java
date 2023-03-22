package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.AddChildCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.RemoveChildCommand;
import dsw.gerumap.app.gui.swing.maprepository.NodeFactory;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.maprepository.factory.utils.FactoryUtils;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;


import javax.swing.*;
import javax.swing.tree.TreePath;
import java.io.IOException;


public class MapTreeImplementation implements MapTree{

    private MapTreeView mapTreeView;
    private MapTreeModel treeModel;
    private MapTreeItem root;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MapTreeItem(projectExplorer);
        treeModel = new MapTreeModel(root);
        mapTreeView = new MapTreeView(treeModel);

        return mapTreeView;
    }

    @Override
    public void addChild(MapTreeItem parent) throws IOException {
        if (!((parent.getMapNode()) instanceof MapNodeComposite)) {
            return;
        }

        MapNode child = createChild(parent.getMapNode());
        AbstractCommand command = new AddChildCommand(parent, new MapTreeItem(child));

        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);

        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }

    @Override
    public void deleteChild(MapTreeItem child) throws IOException {
        MapTreeItem parent = (MapTreeItem) child.getParent();
//        parent.remove(child);
//        ((MapNodeComposite) parent.getMapNode()).removeChild(child.getMapNode());
        AbstractCommand command = new RemoveChildCommand(parent, child);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);

        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) mapTreeView.getLastSelectedPathComponent();
    }

    @Override
    public MapTreeView getTreeView() {
        return mapTreeView;
    }

    @Override
    public void loadProject(Project node) throws IOException {
        MapTreeItem loadedProject = new MapTreeItem(node);
        treeModel.getRoot().add(loadedProject);

        MapNodeComposite mapNode = (MapNodeComposite) treeModel.getRoot().getMapNode();
        mapNode.addChild(node);

        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }
    private MapNode createChild(MapNode parent) {
        NodeFactory nf = FactoryUtils.getNodeFactory(parent);
        return nf.getNode(parent);
    }

}
