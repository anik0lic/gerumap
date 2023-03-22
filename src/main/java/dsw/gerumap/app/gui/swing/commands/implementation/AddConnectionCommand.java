package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.Connection;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import java.io.IOException;

public class AddConnectionCommand extends AbstractCommand {

    private Topic t1;
    private Topic t2;
    private MapView map;
    private ElementPainter painter;
    private Connection connection;

    public AddConnectionCommand(Topic t1, Topic t2, MapView map, ElementPainter painter, Connection connection) {
        this.t1 = t1;
        this.t2 = t2;
        this.map = map;
        this.painter = painter;
        this.connection = connection;
    }

    @Override
    public void doCommand() throws IOException {
        t1.getConnectionList().add((ConnectionPainter) painter);
        t2.getConnectionList().add((ConnectionPainter) painter);
        map.getMindMap().addChild(connection);
        if(!map.getMindMap().getPainterList().contains(painter))
            map.getMindMap().getPainterList().add(painter);
    }

    @Override
    public void undoCommand() throws IOException {
        t1.getConnectionList().remove((ConnectionPainter) painter);
        t2.getConnectionList().remove((ConnectionPainter) painter);
        map.getMindMap().removeChild(connection);
        map.getMindMap().getPainterList().remove(painter);
    }
}
