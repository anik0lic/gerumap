package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;

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
        child.removeFromParent();
        ((MapNodeComposite)(parent.getMapNode())).removeChild(child.getMapNode());
    }
}
