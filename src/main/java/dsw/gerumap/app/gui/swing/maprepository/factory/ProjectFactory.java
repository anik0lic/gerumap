package dsw.gerumap.app.gui.swing.maprepository.factory;

import dsw.gerumap.app.gui.swing.maprepository.NodeFactory;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.Subscriber;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode node) {
        return new Project("Project " + ((ProjectExplorer) node).getChildren().size(), node);
    }
}
