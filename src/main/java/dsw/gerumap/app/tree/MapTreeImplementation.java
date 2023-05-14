package dsw.gerumap.app.tree;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.commands.implementation.AddChildCommand;
import dsw.gerumap.app.commands.implementation.RemoveChildCommand;
import dsw.gerumap.app.maprepository.factory.NodeFactory;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.factory.utils.FactoryUtils;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.tree.model.MapTreeItem;
import dsw.gerumap.app.tree.model.MapTreeModel;
import dsw.gerumap.app.tree.view.MapTreeView;


import javax.swing.*;
import java.io.IOException;


public class MapTreeImplementation implements MapTree{

    private MapTreeView mapTreeView;
    private MapTreeItem root;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MapTreeItem(projectExplorer);
        MapTreeModel treeModel = new MapTreeModel(root);
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
    public void loadProject(Project node) throws IOException {
        MapTreeItem loadProject = new MapTreeItem(node);
        root.add(loadProject);

        ((MapNodeComposite) root.getMapNode()).addChild(node);
        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }
    private MapNode createChild(MapNode parent) {
        NodeFactory nf = FactoryUtils.getNodeFactory(parent);
        return nf != null ? nf.getNode(parent) : null;
    }

}
