package dsw.gerumap.app.commands.implementation;

import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.workspace.elements.painters.TopicPainter;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.elements.Topic;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand {

    private MapView map;
    private List<ElementPainter> painteri;
    private int pocetnoX;
    private int pocetnoY;
    private int flag = 1;

    public MoveCommand(MapView map, int pocetnoX, int pocetnoY) {
        this.map = map;
        painteri = new ArrayList<>();
        for(ElementPainter p : map.getSelectionModel().getSelected()){
            if(p instanceof TopicPainter)
                painteri.add(p);
        }
        this.pocetnoX = pocetnoX;
        this.pocetnoY = pocetnoY;
    }

    @Override
    public void doCommand() throws IOException {
        if(flag == 1){
            flag = 0;
            return;
        }

        for(ElementPainter p : painteri){
            Topic t = (Topic) p.getElement();
            int newX = t.getX() + pocetnoX;
            int newY = t.getY() + pocetnoY;
            t.setX(newX);
            t.setY(newY);
        }
    }

    @Override
    public void undoCommand() throws IOException {
        for(ElementPainter p : painteri){
            Topic t = (Topic) p.getElement();
            int newX = t.getX() - pocetnoX;
            int newY = t.getY() - pocetnoY;
            t.setX(newX);
            t.setY(newY);
        }
    }
}
