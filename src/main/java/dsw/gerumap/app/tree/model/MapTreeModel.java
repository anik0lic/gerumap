package dsw.gerumap.app.tree.model;

import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

@Getter
@Setter
public class MapTreeModel extends DefaultTreeModel {

    public MapTreeModel(TreeNode root) {
        super(root);
    }

    @Override
    public MapTreeItem getRoot() {
        return (MapTreeItem) super.getRoot();
    }

}
