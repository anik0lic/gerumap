package dsw.gerumap.app.gui.swing.maprepository.implementation;

import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MindMap extends MapNodeComposite {

    private boolean template;
    private transient CommandManager commandManager = new CommandManager();
    private transient List<ElementPainter> painterList = new ArrayList<>();

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }
//    public MindMap(String name, MapNode parent, boolean template) {
//        super(name, parent);
//        this.template = template;
//    }

    @Override
    public void addChild(MapNode child) throws IOException {
        if (child != null && child instanceof Element) {
            Element element = (Element) child;
//            if (!this.getChildren().contains(element)) {
//                this.getChildren().add(element);
//            }
            this.getChildren().add(element);
            notify(this);
        }
    }

    @Override
    public void removeChild(MapNode child) throws IOException {
        if (child != null && child instanceof Element) {
            Element element = (Element) child;
            this.getChildren().remove(element);
            notify(this);
        }
    }

    @Override
    public void setName(String name) throws IOException {
        super.setName(name);
        notify(this);
    }

    public int numberOfTopics(){
        int num = 0;
        for(MapNode el : getChildren()){
            if(el instanceof Topic){
                num++;
            }
        }
        return num;
    }

    @Override
    public String toString() {
        return getName();
    }
}
