package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import java.io.IOException;
import java.util.List;

public class RemoveElementCommand extends AbstractCommand {
    private List<ElementPainter> painters ;
    private MapView map;

    public RemoveElementCommand(List<ElementPainter> painters, MapView map) {
        this.painters = painters;
        this.map = map;
    }

    @Override
    public void doCommand() throws IOException {
        for(ElementPainter p : painters){
            map.getMindMap().removeChild(p.getElement());
            map.getSelectionModel().getSelected().remove(p.getElement());
            map.getMindMap().getPainterList().remove(p);
        }
    }

    @Override
    public void undoCommand() throws IOException {
        for(ElementPainter p : painters){
            map.getMindMap().addChild(p.getElement());
            map.getSelectionModel().getSelected().add(p.getElement());
            map.getMindMap().getPainterList().add(p);
        }
    }
}
