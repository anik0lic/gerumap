package dsw.gerumap.app.commands.implementation;

import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.tree.model.MapTreeItem;

import java.io.IOException;

public class AddChildCommand extends AbstractCommand {

    private MapTreeItem parent;
    private MapTreeItem child;

    public AddChildCommand (MapTreeItem parent, MapTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() throws IOException {
        if (child == null || parent == null) {
            return;
        }
        parent.add(child);
        ((MapNodeComposite) parent.getMapNode()).addChild(child.getMapNode());
    }

    @Override
    public void undoCommand() throws IOException {
        if (child == null || parent == null) {
            return;
        }
        parent.remove(child);
        ((MapNodeComposite)(parent.getMapNode())).removeChild(child.getMapNode());
    }
}
