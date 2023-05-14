package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;

import java.io.IOException;

public class ProjectExplorer extends MapNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) throws IOException {
        if (child instanceof Project) {
            Project project = (Project) child;
            if(!this.getChildren().contains(project)) {
                child.setParent(this);
                this.getChildren().add(project);
                notify(this);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) throws IOException {
        if (child instanceof Project) {
            Project project = (Project) child;
            this.getChildren().remove(project);
            child.setParent(null);
            notify(this);
        }
    }
}

