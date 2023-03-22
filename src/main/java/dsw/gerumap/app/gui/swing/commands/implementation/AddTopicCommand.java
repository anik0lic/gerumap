package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import java.io.IOException;

public class AddTopicCommand extends AbstractCommand {

    private MapView map;
    private ElementPainter painter;

    public AddTopicCommand(MapView map, ElementPainter painter) {
        this.map = map;
        this.painter = painter;
    }

    @Override
    public void doCommand() throws IOException {
        if (map == null || painter == null) {
            return;
        }
        Topic topic = (Topic) painter.getElement();
        map.getMindMap().getPainterList().add(painter);
        map.getMindMap().addChild(topic);
    }

    @Override
    public void undoCommand() throws IOException {
        if (map == null || painter == null) {
            return;
        }
        Topic topic = (Topic) painter.getElement();
        map.getMindMap().getPainterList().remove(painter);
        map.getMindMap().removeChild(topic);
    }
}
