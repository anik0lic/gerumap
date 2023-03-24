package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.workspace.elements.Topic;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.TopicPainter;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ElementPainter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveElementCommand extends AbstractCommand {
    private List<ElementPainter> paintersForDelete ;
    private List<ElementPainter> deleted;
    private MapView map;

    public RemoveElementCommand(List<ElementPainter> painters, MapView map) {
        this.paintersForDelete = painters;
        this.map = map;
        deleted = new ArrayList<>();
    }

    @Override
    public void doCommand() throws IOException {
        List<ElementPainter> deletedTopics = new ArrayList<>();
        for(ElementPainter p : paintersForDelete){
            if(p instanceof TopicPainter){
                deleted.add(p);
                deletedTopics.add(p);
                map.getMindMap().removeChild(p.getElement());
                map.getMindMap().getPainterList().remove(p);
            }else{
                deleted.add(p);
                map.getMindMap().removeChild(p.getElement());
                map.getMindMap().getPainterList().remove(p);
            }
        }

        for(ElementPainter p : deletedTopics){
            Topic topic = (Topic) p.getElement();
            for(ElementPainter con : topic.getConnectionList()){
                deleted.add(con);

                map.getMindMap().removeChild(con.getElement());
                map.getMindMap().getPainterList().remove(con);
            }
        }
    }

    @Override
    public void undoCommand() throws IOException {
        for(ElementPainter p : deleted){
            map.getMindMap().addChild(p.getElement());
            map.getMindMap().getPainterList().add(p);
        }
    }
}
