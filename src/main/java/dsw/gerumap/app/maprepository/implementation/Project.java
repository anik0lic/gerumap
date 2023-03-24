package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Project extends MapNodeComposite {

    private String filePath;
    private boolean changed = true;
    private String author;

    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    public Project(String name, MapNode parent, String author) {
        super(name, parent);
        this.author = author;
    }

    @Override
    public void addChild(MapNode child) throws IOException {
        if (child instanceof MindMap) {
            MindMap mindMap = (MindMap) child;
            this.getChildren().add(mindMap);
            notify(this);
            changed = true;
        }
    }

    @Override
    public void removeChild(MapNode child) throws IOException {
        if (child instanceof MindMap) {
            MindMap mindMap = (MindMap) child;
            this.getChildren().remove(mindMap);
            notify(this);
            changed = true;
        }
    }

    @Override
    public void setName(String name) throws IOException {
        super.setName(name);
        notify(this);
        changed = true;
    }

    public void setAuthor(String author) throws IOException {
        this.author = author;
        notify(this);
        changed = true;
    }
}
