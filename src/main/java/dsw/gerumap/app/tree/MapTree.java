package dsw.gerumap.app.tree;

import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.tree.model.MapTreeItem;
import dsw.gerumap.app.tree.view.MapTreeView;

import java.io.IOException;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    MapTreeItem getSelectedNode();
    void addChild(MapTreeItem parent) throws IOException;
    void deleteChild (MapTreeItem child) throws IOException;
    void loadProject(Project node) throws IOException;

}
