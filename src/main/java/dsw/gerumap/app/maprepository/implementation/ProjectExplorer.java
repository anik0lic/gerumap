package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.observer.Subscriber;

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
                this.getChildren().add(project);
                child.setParent(this);

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

