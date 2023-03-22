package dsw.gerumap.app.gui.swing.maprepository.factory;

import dsw.gerumap.app.gui.swing.maprepository.NodeFactory;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.observer.Subscriber;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MindMapFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode node) {
        return new MindMap("MindMap " + ((Project) node).getChildren().size(), node);
    }
}
