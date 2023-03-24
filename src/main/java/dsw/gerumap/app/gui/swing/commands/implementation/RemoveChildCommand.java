package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

import java.io.IOException;

public class RemoveChildCommand extends AbstractCommand {

    private MapTreeItem parent;
    private MapTreeItem child;

    public RemoveChildCommand(MapTreeItem parent, MapTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() throws IOException {
        if (child == null || parent == null) {
            return;
        }
        parent.remove(child);
        ((MapNodeComposite) parent.getMapNode()).removeChild(child.getMapNode());
    }

    @Override
    public void undoCommand() throws IOException {
        if (child == null || parent == null) {
            return;
        }
        parent.add(child);
        ((MapNodeComposite)(parent.getMapNode())).addChild(child.getMapNode());
    }
}
